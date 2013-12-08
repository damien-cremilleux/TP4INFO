/*
 * malloc.h
 *
 *  Created on: 3 déc. 2008
 *      Author: lescouarnecn
 */

void* my_malloc(int s);
void malloc_init(void* pointer, int size);
// This method free (and merge)
void my_free(void * a);
