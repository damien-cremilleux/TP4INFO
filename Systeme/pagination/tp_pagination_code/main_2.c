/*
 * main.c
 *
 *  Created on: 3 déc. 2008
 *      Author: lescouarnecn
 */

#include "malloc.h"
#include "mmu.h"
#include "swap.h"
#include "physical_memory.h"
#include <stdlib.h>
#include <stdio.h>

int main(void){
	// Set up the whole thing
	mmu_init(1000); // 1000 pages of virtual memory
	pm_init(20); // 20 pages of physical memory
	swap_init(1000); // Enough swap for every virtual memory page
	init_faultHandler(1000,20); // Init page fault handler structure
	malloc_init(start_addr,1000*PAGESIZE); // Allocate malloc structure

	// Get a block from VM (my_malloc)
	int* bloc=my_malloc(1000000*sizeof(int));

	// Do some stuff
	int i,j;
	for(i=0;i<1000;i++){
		if(i%100 == 0) printf("B1i: (%d)\n",i);
		for(j=0;j<1000;j++){
			//printf("B1: (%d,%d)\n",i,j);
			bloc[i*100+j]=j*100+i;
		}
	}

	for(i=0;i<1000;i++){
		if(i%100 == 0) printf("B2i: (%d)\n",i);
		for(j=0;j<1000;j++){
			//printf("B2: (%d,%d)\n",i,j);
			int t=bloc[i*100+j];
			int z=bloc[t];
			bloc[t]=bloc[i*100+j];
			bloc[i*100+j]=z;
		}
	}

	// Print stats
	print_stats();

	// Exit
	return EXIT_SUCCESS;
}
