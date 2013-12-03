/* TP4  */

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#include <stdio.h>
#include <stdlib.h>


int init_semaphore(int valeur);


void delete_semaphore(int semid);


void p(int semid);
void v(int semid);
