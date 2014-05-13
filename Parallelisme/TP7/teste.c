#include <stdlib.h>
#include <stdio.h>
#include <mpi.h>

int main (int argc, char *argv[])
{
  


  int i;      /* numero du processus courant */
  int p;      /* nombre total de processus */

  int recu; /* valeur recue par le processus courant */
  MPI_Status stat; /* pour l'initialisation */

  MPI_Init(&argc, &argv); 
  MPI_Comm_rank(MPI_COMM_WORLD, &i); // Rang du processus
  MPI_Comm_size(MPI_COMM_WORLD, &p); // Nombre de processus

  if (p<=1) {
    printf("ce programme ne fonctionne qu\"avec au moins 2 processus\n");
    exit(-1);
  }
 
  printf("je suis le pss %d qui demarre parmi %d processus \n",i,p);
   if(i==0) {
     int n = 2;/* la valeur envoyée par le 1er processus */
     printf("je suis le pss 0 j'envoie au pss  %d la valeur  %d\n",i+1,n);
    MPI_Send(&n, 1, MPI_INT, i+1, 0, MPI_COMM_WORLD);
    printf("je suis le pss 0 j'attend du processus %d\n",p-1);
    MPI_Recv(&recu, 1,MPI_INT, p-1, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
    printf("je suis le pss 0 je recoit de %d la valeur %d\n",i+1,recu);
    }
     
   else if (i<p-1) {
     printf("je suis le pss %d j'attend du processus %d\n",i,i-1);
   
    MPI_Recv(&recu, 1,MPI_INT, i-1, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
    recu = recu *2;
    MPI_Send(&recu, 1, MPI_INT, i+1, 0, MPI_COMM_WORLD);
    }
    else
     {
       printf("je suis le pss %d j'attend du processus %d\n",i,i-1);
    MPI_Recv(&recu, 1,MPI_INT, i-1, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
    recu = recu *2;
    MPI_Send(&recu, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

  
  MPI_Finalize();
  return (0);
}

