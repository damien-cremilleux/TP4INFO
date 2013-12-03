/* TP4  */

#include "simple_semaphore.h"

int init_semaphore(int valeur)
{
  int id  = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);
  union semun {
               int              val;    /* Value for SETVAL */
               struct semid_ds *buf;    /* Buffer for IPC_STAT, IPC_SET */
               unsigned short  *array;  /* Array for GETALL, SETALL */
               struct seminfo  *__buf;  /* Buffer for IPC_INFO
                                           (Linux-specific) */
  } arg;
  arg.val = valeur;

  semctl(id,0,SETVAL,arg);
  return id;
}


void delete_semaphore(int semid)
{
  semctl(semid,0,IPC_RMID);
}


void p(int semid)
{
  struct sembuf operation;
  operation.sem_num = 0;
  operation.sem_op = -1;
  operation.sem_flg = 0;
  semop(semid,&operation,1);
}

void v(int semid)
{
  struct sembuf operation;
  operation.sem_num = 0;
  operation.sem_op = 1;
  operation.sem_flg = 0;
  semop(semid,&operation,1);
}


/*void main(){
  
  int idsem1;
  int idsem2;
  idsem1 = init_semaphore(2);
  idsem2 = init_semaphore(0);

  v(idsem2);


  sleep(10);


  delete_semaphore(idsem1);
  delete_semaphore(idsem2);

 
  exit(0);
  }*/
