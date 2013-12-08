/*
 * malloc.c
 *
 *  Created on: 15 nov. 2008
 *      Author: LESCOUARNECN
 */

#include <assert.h>
#include <stdlib.h>
#define MIN(a,b) (a < b ? a : b)
#define MAXSIZEBLOCK 32
struct malloc_header* freeBlocks[MAXSIZEBLOCK]; // Element i of array is the pointer to the first block of size 2^(i+1) >= z >= 2^i

enum block_status  {FREE, BUSY} ;

struct malloc_header {
	int blocksize;
	enum block_status status;
	struct malloc_header* next_block;
	struct malloc_header* previous_block;
	struct malloc_header* next_free_block;
	struct malloc_header* previous_free_block;
	void* block;
};

void removeFromFreeList(struct malloc_header* a);
void addToFreeList(struct malloc_header *a);
void merge_if_needed(struct malloc_header* a);
void merge(struct malloc_header* a,struct malloc_header* b);
void split(struct malloc_header* a, int size);

// Int index of size
int index_of_size(int s){
	int k=0;
	unsigned int size=s;
	while(size != 1){
		size>>=1;
		k++;
	}
	return MIN(k,MAXSIZEBLOCK);
}

// This method alloc (and split (if remaining size is enough))
void* my_malloc(int s){
	struct malloc_header *mha;
	int k=index_of_size(s);
	// Search for the smallest big enough block
	while(k<MAXSIZEBLOCK && (mha=freeBlocks[k]) == NULL){
		k++;
	}
	if(k<MAXSIZEBLOCK){
		mha->status=BUSY;
		removeFromFreeList(mha);
		split(mha,s);
		return mha->block;
	}else{
		return NULL;
	}
}

// This method free (and merge)
void my_free(void * a){
	struct malloc_header * mha=a-sizeof(struct malloc_header);
	merge_if_needed(mha); // This method add to Free list.
	mha->status = FREE;
}


void removeFromFreeList(struct malloc_header* a){
	int k=index_of_size(a->blocksize);
	if(a == freeBlocks[k]){
		freeBlocks[k]=a->next_free_block;
	}
	if(a->next_free_block != NULL){
		a->next_free_block->previous_free_block=a->previous_free_block;
	}
	if(a->previous_free_block != NULL){
		a->previous_free_block->next_free_block=a->next_free_block;
	}
}

void addToFreeList(struct malloc_header *a){
	int k=index_of_size(a->blocksize);
	a->next_free_block=freeBlocks[k];
	a->previous_free_block = NULL;
	freeBlocks[k]=a;
}

void malloc_init(void* pointer, int size){
	struct malloc_header* a =(struct malloc_header*)pointer;
	a->block=((void*)a)+sizeof(struct malloc_header);
	a->next_block=NULL;
	a->previous_block=NULL;
	a->next_free_block=NULL;
	a->previous_free_block=NULL;
	a->blocksize=size-sizeof(struct malloc_header);
	a->status=FREE;

	int i;
	for(i=0;i<MAXSIZEBLOCK;i++){
		freeBlocks[i]=NULL;
	}

	addToFreeList(a);

}

void merge_if_needed(struct malloc_header* a){
	while((a->previous_block != NULL && a->previous_block->status == FREE)
		  ||( a->next_block != NULL && a->next_block->status == FREE)){
		if(a->previous_block != NULL && a->previous_block->status==FREE){
			removeFromFreeList(a->previous_block);
			merge(a->previous_block,a);
			a=a->previous_block;
		}else if(a->next_block != NULL && a->next_block->status == FREE){
			removeFromFreeList(a->next_block);
			merge(a,a->next_block);
		}
	}
	addToFreeList(a);
}
void merge(struct malloc_header* a,struct malloc_header* b){
	assert(a->status == FREE && b->status==FREE);
	a->blocksize=a->blocksize+sizeof(struct malloc_header)+b->blocksize;
	a->next_block=b->next_block;
	if(b->next_block != NULL) b->next_block->previous_block=a;
}

void split(struct malloc_header* a, int size){
	if(a->blocksize > size+sizeof(struct malloc_header)){
		struct malloc_header* b = a->block+size;
		b->previous_block=a;
		if(a->next_block != NULL){
			b->next_block=a->next_block;
			a->next_block->previous_block=b;
		}
		a->next_block=b;
		b->status=FREE;
		b->block=((void*)b)+sizeof(struct malloc_header);
		b->blocksize=a->blocksize-size-sizeof(struct malloc_header);
		a->blocksize=size;
		b->previous_free_block=NULL;
		b->next_free_block=NULL;
		addToFreeList(b);
	}
}
