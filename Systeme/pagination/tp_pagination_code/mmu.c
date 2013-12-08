/*
 * mmu.c
 *
 *  Created on: 1 nov. 2008
 *      Author: lescouarnecn
 */
#include "mmu.h"
#include <sys/time.h>
#include <sys/mman.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>

void* start_addr;
unsigned int size=0;

struct timeval delay_load={0,20000};
struct timeval delay_flush={0,100000};

struct sigaction saction_segv;
struct info_mmu* mmu_array;

void segv_sigaction(int signum, siginfo_t* sinfo, void * p);

int stats_faults;

#ifdef __GNUC__
#define UNUSED __attribute__ (( unused ))
#else
#define UNUSED
#endif


void print_stats(){
	printf("Nombre de fautes de pages: %d\n",stats_faults);
}

void mmu_init(int vmsize){
  // Address a block of virtual memory without any permission
  start_addr=mmap(NULL,vmsize*PAGESIZE,PROT_NONE,MAP_ANONYMOUS|MAP_PRIVATE,0,0);
  if(start_addr== ((void*)-1)){
	  perror("mmap");
  }

  // Set counter to 0
  stats_faults=0;

  // Register a handler for segfault
  saction_segv.sa_sigaction=segv_sigaction;
  sigemptyset(&saction_segv.sa_mask);
  saction_segv.sa_flags=SA_SIGINFO;
  //saction_segv.sa_restorer=NULL;
  sigaction(SIGSEGV,&saction_segv,NULL);

  size=vmsize;
  int i;
  mmu_array=malloc(sizeof(struct info_mmu)*vmsize);
  for(i=0;i<vmsize;i++){
	mmu_array[i].accessed=0;
	mmu_array[i].dirty=0;
	mmu_array[i].loaded=0;
	mmu_array[i].pp=INVALID;
	mmu_array[i].cpt=0; // initialisation du compteur de chaques pages
	mmu_array[i].tv_askedFlush.tv_sec=0;
	mmu_array[i].tv_askedFlush.tv_usec=0;
	mmu_array[i].tv_askedLoad.tv_sec=0;
	mmu_array[i].tv_askedLoad.tv_usec=0;
	mmu_array[i].swapped=0;
  }

}

// Invalidate page... but wait until write has been done
void mmu_invalidatePage(page_virt pv){
	if(mmu_array[pv].swapped == FALSE){
		printf("Page has not been swapped yet...\n");
		printf(" Hint: run the debugger and set a breakpoint for this line\n");
		exit(1);
	}
  mprotect(ADDROF(pv),PAGESIZE,PROT_NONE);
  mmu_array[pv].dirty=0;
  mmu_array[pv].pp=INVALID;
  mmu_array[pv].loaded=0;
  struct timeval delay;
  struct timeval now;
  gettimeofday(&now,NULL);
  timeradd(&delay,&delay_flush,&delay);
  timersub(&delay,&now,&delay);
  if(delay.tv_sec >= 0){
	  int UNUSED r;
	  struct timespec s;
	  TV2TS(delay,s);
	  //do{
	//	  r=nanosleep(&s,&s);
	//  }while(r < 0 && errno == EINTR);
  }
}

void mmu_addTranslation(page_virt pv, page_phys pp){
  if(pp == INVALID){
	  printf("Proposed address is invalid...\n");
	  printf(" Hint: run the debugger and set a breakpoint for this line\n");
	  abort();
  }else if(! ((!mmu_array[pv].swapped) || (mmu_array[pv].loaded))){  // Si ! (swapped => loaded)
	  printf("Page was swapped and invalidated but not reloaded...\n");
	  printf(" Hint: run the debugger and set a breakpoint for this line\n");
	  abort();
  }
  mmu_clearAccessedBit(pv); // Just to be sure everything is ok
  mmu_clearDirtyBit(pv); // Idem
  mprotect(ADDROF(pv),PAGESIZE,PROT_READ);
  mmu_array[pv].pp=pp;

}

void mmu_setDirtyBit(page_virt pv){
  mprotect(ADDROF(pv),PAGESIZE,PROT_WRITE|PROT_READ);
  mmu_array[pv].dirty=1;
  mmu_array[pv].swapped=0;
  cpt++;
}

void mmu_clearDirtyBit(page_virt pv){
  mprotect(ADDROF(pv),PAGESIZE,mmu_array[pv].accessed ? PROT_READ : PROT_NONE);
  mmu_array[pv].dirty=0;
  ctp++;
}

void mmu_setAccessedBit(page_virt pv){
  mprotect(ADDROF(pv),PAGESIZE,PROT_READ);
  mmu_array[pv].accessed=1;
  mmu_array[pv].cpt=cpt; // charge le compteur que quand on accède à la page
  cpt++;
}

void mmu_clearAccessedBit(page_virt pv){
  mprotect(ADDROF(pv),PAGESIZE,PROT_NONE);
  mmu_array[pv].accessed=0;
  cpt++;
}


int mmu_isDirty(page_virt pv){
  return mmu_array[pv].dirty;
}

int mmu_isAccessed(page_virt pv){
  return mmu_array[pv].accessed;
}


void segv_sigaction(int signum, siginfo_t* sinfo, void * p){
  // Check signal
  if (SIGSEGV != signum) {
    printf ("Bad handler for bad signal...\n");
    exit (1);
  }


  // Check if fault is from managed segment
  if(sinfo->si_addr < start_addr || sinfo->si_addr >= size*PAGESIZE+start_addr){
    printf("Segfault somewhere else... good luck...\n");
    printf(" Hint: run the debugger and set a breakpoint for this line\n");
    exit(1);
  }

  // Check cause of fault
  if(sinfo->si_code != SEGV_ACCERR){
    printf("Object not mapped...\n");
    printf(" Hint: run the debugger and set a breakpoint for this line\n");
    exit(1);
  }

  // Find out virtual page in fault
  page_virt pv=PAGEOF(sinfo->si_addr);

  // If not invalid then setDirtyBit
  // Allow read, but wait until page has been loaded...
  if(mmu_array[pv].pp != INVALID){
    if(!mmu_array[pv].accessed){
      struct timeval delay;
      struct timeval now;
      gettimeofday(&now,NULL);
      timeradd(&delay,&delay_load,&delay);
      timersub(&delay,&now,&delay);
      if(delay.tv_sec >= 0){
    	  int UNUSED r;
    	  struct timespec s;
    	  TV2TS(delay,s);
    	  //do{
    	  //	  r=nanosleep(&s,&s);
    	  //  }while(r < 0 && errno == EINTR);
      }
      mmu_setAccessedBit(pv);
    }else{
      mmu_setDirtyBit(pv);
    }
  }else{
  // Else call page fault handler
	  stats_faults++;
    page_fault_handler(pv);
  }

  // Check that translation has been added
  if(mmu_array[pv].pp == INVALID){
    printf("Page fault handler did not update MMU...\n");
    exit(1);
  }
}



