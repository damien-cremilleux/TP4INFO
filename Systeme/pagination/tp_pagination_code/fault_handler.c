/*
 * fault_handler.c
 *
 *  Created on: 1 nov. 2008
 *      Author: lescouarnecn
 */
#include "mmu.h"
#include "physical_memory.h"
#include "swap.h"
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h> 

// Page fault handler
//     Free one page if needed
//     Get free page
//     Load
//     Update MMU

enum state_page {SWAPPED, ALLOCATED, NOTMAPPED};

#define PRECLEAN

struct page_table_entry{
	enum state_page status;
	page_phys pp;
};

// Page table
struct page_table_entry* page_table;
int page_table_size;

// List of allocated pages
page_virt* allocated_pages;
int allocated_pages_capacity;
int allocated_pages_size;
int allocated_pages_first;
int allocated_pages_free;
int allocated_pages_last_used;



void init_faultHandler(int nb_pages_virt, int nb_pages_phys){
	int i;
	page_table=(struct page_table_entry*)malloc(sizeof(struct page_table_entry)*nb_pages_virt);
	page_table_size=nb_pages_virt;
	for(i=0;i<nb_pages_virt;i++){
		page_table[i].pp=INVALID;
		page_table[i].status=NOTMAPPED;
	}

	allocated_pages=(page_virt*)malloc(sizeof(page_virt)*nb_pages_phys);
	allocated_pages_capacity=nb_pages_phys;
	for(i=0;i<nb_pages_phys;i++){
		allocated_pages[i]=INVALID;
	}

	//Initialise l'indice des pages libres
	allocated_pages_free = 0;
	
	//Initialise le randomitron
	srand(time(NULL));
}

void unloadAPage();
void loadAPage(page_virt pv);

void page_fault_handler(page_virt pv){



	// Get a Free block
	if(pm_isFull()){
		printf("PF: Unload a page\n");
		unloadAPage();
	}
	page_phys pp = pm_getFreePage();

	// Mark page as loaded
	printf("PF: Load a page (%d)\n",pv);
	loadAPage(pv);

	// Read from Swap
	if(page_table[pv].status == SWAPPED){
		swap_read(pv,pp);
	}

	// Update page table
	page_table[pv].pp=pp;
	page_table[pv].status=ALLOCATED;

	// Write to MMU
	mmu_addTranslation(pv,pp);

	// That's all
}

#ifdef POLICY_FIFO
void unloadAPage(){
 printf("Welcome in unload-FIFO\n");

  printf("indice de la premiere page physique : %d\n", allocated_pages_first);
  
  //Trouve la page virtuelle associée 
  int id_page_virt;
  id_page_virt = allocated_pages[allocated_pages_first];

  // Vérifie si modification de la page
  if(mmu_isDirty(id_page_virt))
    {
      swap_write(id_page_virt,page_table[id_page_virt].pp);
    }
  page_table[id_page_virt].status = SWAPPED;
 
 // Désalloue la page physique
  allocated_pages[allocated_pages_first]=INVALID;
  pm_freePage(page_table[id_page_virt].pp);

  // Invalide la traduction
  mmu_invalidatePage(id_page_virt);
}

void loadAPage(page_virt pv){
  //NB fautes de pages : 474

  // Incremente l'indice de la première page, remet à 0 quand atteind capacity
  allocated_pages[allocated_pages_first] = pv;
  allocated_pages_first = (allocated_pages_first + 1)%allocated_pages_capacity;

}
#endif

#ifdef POLICY_RANDOM
void unloadAPage(){
  printf("Welcome in unload - RANDOM\n");

  // Génère un nombre aléatoire entre 0 et le nb de pages physiques
  int id_page_phys;

  id_page_phys = rand()%allocated_pages_capacity; 
  
  printf("indice de la page physique : %d\n", id_page_phys);
  
  //Trouve la page virtuelle associée 
  int id_page_virt;
  id_page_virt = allocated_pages[id_page_phys];

  // Vérifie si modification de la page
  if(mmu_isDirty(id_page_virt))
    {
      swap_write(id_page_virt,page_table[id_page_virt].pp);
      
    }
  page_table[id_page_virt].status = SWAPPED;
 
 // Désalloue la page physique
  allocated_pages[id_page_phys]=INVALID;
  pm_freePage(page_table[id_page_virt].pp);

  // Invalide la traduction
  mmu_invalidatePage(id_page_virt);

  // Mémorise la page libére
  allocated_pages_free = id_page_phys;
}

void loadAPage(page_virt pv){
  // Incrémente l'indice pour gérer les cas de plusieurs load consécutifs
  allocated_pages[allocated_pages_free++] = pv;
}

#endif

#ifdef POLICY_CLOCK
void unloadAPage(){
  // NB fautes de pages : 369


  printf("Welcome in unload - CLOCK\n");

  //Indice de la page virtuelle
  int id_page_virt;

  // booleen si page trouvée
  int trouve = 0;

  while(!trouve){
    id_page_virt = allocated_pages[allocated_pages_last_used];
   
    // Si Bit à 1 => page utilisée
    if(!mmu_isAccessed(id_page_virt))
      trouve = 1;
    // Sinon on avance à la prochaine
    else{
      mmu_clearAccessedBit(id_page_virt);
      allocated_pages_last_used = (allocated_pages_last_used + 1)%allocated_pages_capacity;
    }
  } //while
  

  // Vérifie si modification de la page
  if(mmu_isDirty(id_page_virt))
    {
      swap_write(id_page_virt,page_table[id_page_virt].pp);
    }
  page_table[id_page_virt].status = SWAPPED;
 
  // Désalloue la page physique
  allocated_pages[allocated_pages_last_used]=INVALID;
  pm_freePage(page_table[id_page_virt].pp);

  // Invalide la traduction
  mmu_invalidatePage(id_page_virt);
  
}

void loadAPage(page_virt pv){
  // Incremente l'indice de la première page, remet à 0 quand atteind capacity
  allocated_pages[allocated_pages_last_used] = pv;
  allocated_pages_last_used = (allocated_pages_last_used + 1)%allocated_pages_capacity;
}
#endif


#ifdef POLICY_CLOCK2
void unloadAPage(){
  // NB fautes de pages : 369


  printf("Welcome in unload - CLOCK2\n");

  //Indice de la page virtuelle
  int id_page_virt;

  // booleen si page trouvée
  int trouve = 0;

  // Initialisation de min avec la premier compteur de mmu
  int min = mmu_array[0];

  while(!trouve){
    id_page_virt = allocated_pages[allocated_pages_last_used];
   
    // On cherche le compteur le plus petit, cad celui accèdé il y a le plus de temps
    for(int i=0; i<allocated_pages_capacity; i++){
      if(min > mmu_array[i].cpt)
	min=mmu_array[i].cpt;

      if(){
	mmu_clearAccessedBit(id_page_virt);
	allocated_pages_last_used = (allocated_pages_last_used + 1)%allocated_pages_capacity;
      }
	
    /*
    // Si Bit à 1 => page utilisée
    if(!mmu_isAccessed(id_page_virt))
      trouve = 1;
    // Sinon on avance à la prochaine
  
  } //while
  

  // Vérifie si modification de la page
  if(mmu_isDirty(id_page_virt))
    {
      swap_write(id_page_virt,page_table[id_page_virt].pp);
    }
  page_table[id_page_virt].status = SWAPPED;
 
  // Désalloue la page physique
  allocated_pages[allocated_pages_last_used]=INVALID;
  pm_freePage(page_table[id_page_virt].pp);

  // Invalide la traduction
  mmu_invalidatePage(id_page_virt);
  
}

void loadAPage(page_virt pv){
  // Incremente l'indice de la première page, remet à 0 quand atteind capacity
  allocated_pages[allocated_pages_last_used] = pv;
  allocated_pages_last_used = (allocated_pages_last_used + 1)%allocated_pages_capacity;
}
#endif
