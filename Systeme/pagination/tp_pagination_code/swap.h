/*
 * swap.h
 *
 *  Created on: 14 nov. 2008
 *      Author: LESCOUARNECN
 */

#ifndef SWAP_H_
#define SWAP_H_

#include "mmu.h"

void swap_init(int s);
void allocate_swap(page_virt pv);
void swap_write(page_virt pv, page_phys pp);
void swap_read(page_virt pv, page_phys pp);
#endif /* SWAP_H_ */
