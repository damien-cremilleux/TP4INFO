/*
 * swap.c
 *
 *  Created on: 1 nov. 2008
 *      Author: lescouarnecn
 */

#include "mmu.h"
#include <stdlib.h>

void swap_init(int s){

}

void allocate_swap(page_virt pv){
}

void swap_write(page_virt pv, page_phys pp){
  gettimeofday(&mmu_array[pv].tv_askedFlush,NULL);
  mmu_array[pv].swapped=TRUE;
}

void swap_read(page_virt pv, page_phys pp){
  gettimeofday(&mmu_array[pv].tv_askedLoad,NULL);
  mmu_array[pv].loaded=TRUE;
}
