#include "simple_semaphore.h"

int main(int argc, char *argv[]){

  int i;
  int semid;
  semid = atoi(argv[1]);
  
  for(i = 0; i<5; i++)
    {
    /* exclusion mutuelle */;
    p(semid);
    printf("%s\n",argv[2]);
    sleep(1);
    printf("%s\n",argv[2]);
    v(semid);
  }
  
  exit(0);
}

