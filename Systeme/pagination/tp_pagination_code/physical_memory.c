#include "physical_memory.h"

#include <stdlib.h>
#include <stdio.h>

page_phys * listOfFreePages;
int listOfFreePages_size;
int physical_mem_size;

page_phys pm_getFreePage(){
  if(pm_isFull()){
    printf("pm_getFreePage was called while there is no free memory. Test with pm_isFull before calling this method.\n");
    exit(-1);
  }
  return listOfFreePages[--listOfFreePages_size];
}

void pm_freePage(page_phys pp){
  listOfFreePages[listOfFreePages_size++]=pp;
}

int pm_isFull(){
  return listOfFreePages_size==0;
}

void pm_init(int size){
  listOfFreePages=(page_phys*)malloc(sizeof(page_phys)*size);
  listOfFreePages_size=size;
  physical_mem_size=size;
  int i;
  for(i=0;i<size;i++){
    listOfFreePages[i]=i;
  }
}

