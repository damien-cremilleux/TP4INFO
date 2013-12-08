/*
 * mmu.h
 *
 *  Created on: 14 nov. 2008
 *      Author: LESCOUARNECN
 */

#ifndef MMU_H_
#define MMU_H_

#include <sys/time.h>

extern void * start_addr;

#define TV2TS(a,b) do{ b.tv_sec=a.tv_sec; b.tv_nsec=1000*a.tv_usec;} while(0)

typedef unsigned int addr_phys;
typedef void* addr_virt;
typedef unsigned int page_virt;
typedef unsigned int page_phys;
typedef enum {FALSE=0, TRUE=-1} boolean;

#define PAGESIZE (1<<12)
#define PAGEOF(x) ((page_virt)(x-start_addr)>>12)
#define ADDROF(x) ((addr_virt)((x<<12)+start_addr))
#define RECORDOF(a) mmu_array[a]
#define INVALID 0xffffffff

struct info_mmu{
  int dirty;
  int accessed;
  page_phys pp;
  int loaded; // For internal check
  int swapped; // Allow to check that page is swapped correctly
  unsigned int cpt; // Compteur pour stocker la date de la dernière utilisation de chaque page
  struct timeval tv_askedFlush;
  struct timeval tv_askedLoad;
};

int cpt=0;

extern struct info_mmu* mmu_array;


void mmu_init(int vmsize);
void mmu_invalidatePage(page_virt pv);
void mmu_addTranslation(page_virt pv, page_phys pp);
void mmu_clearDirtyBit(page_virt pv);
void mmu_clearAccessedBit(page_virt pv);
int mmu_isDirty(page_virt pv);
int mmu_isAccessed(page_virt pv);
void page_fault_handler(page_virt pv);
void print_stats();
void init_faultHandler(int nb_pages_virt, int nb_pages_phys);

#endif /* MMU_H_ */
