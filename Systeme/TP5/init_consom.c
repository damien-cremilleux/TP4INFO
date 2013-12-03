/* Initialisation pour les producteurs consommateurs */
#include "simple_semaphore.h"
#include "init_consom.h"

int main(int argc, char *argv[]){

  /* creation de la zone mémoire contenant les informations mémoire et sémaphiore */
  int id_mem_mem;
  struct mybuf_mem * m;
  struct mybuf_tab * mtab1;
  struct mybuf_tab * mtab2;
  id_mem_mem = shmget(CLEF,sizeof(struct mybuf_mem),IPC_CREAT | 0666);

  if(id_mem_mem == -1) 
    {
      perror("Erreur de creation de la mémoire partagée !\n");
      exit(-1);
    }

  /* creation de la zone mémoire contenant le tableau de données */
  int id_mem_data;
  id_mem_data = shmget(IPC_PRIVATE,sizeof(struct mybuf_tab),IPC_CREAT | 0666);

  if(id_mem_data == -1) 
    {
      perror("Erreur de creation de la mémoire partagée !\n");
      exit(-1);
    }

  /* creation de la zone mémoire contenant le tableau d'indice de remplissage */
  int id_mem_indice;
  id_mem_indice = shmget(IPC_PRIVATE,sizeof(struct mybuf_tab),IPC_CREAT | 0666);

  if(id_mem_indice == -1) 
    {
      perror("Erreur de creation de la mémoire partagée !\n");
      exit(-1);
    }

  
  m =(struct mybuf_mem *) shmat(id_mem_mem,NULL,0);
  mtab1 =(struct mybuf_tab *) shmat(id_mem_data,NULL,0);
  mtab2 =(struct mybuf_tab *) shmat(id_mem_indice,NULL,0);
  m->tab1 = mtab1;
  m->tab2 = mtab2;
  m->nbSem = 4;
  
  m->sem_data = init_semaphore(0);
  m->sem_prod = init_semaphore(1);
  m->sem_consom = init_semaphore(1);
  
  exit(0);
}
