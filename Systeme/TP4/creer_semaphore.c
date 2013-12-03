/* TP4  */

#include "simple_semaphore.h"

int main(int argc, char *argv[]){
  
  int idsem1;
  int val;
  val=  atoi(argv[1]);

  idsem1 = init_semaphore(val);

  printf("idsem : %d\n",idsem1);
  
  exit(0);
 }
