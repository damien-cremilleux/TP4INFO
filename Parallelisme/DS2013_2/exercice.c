/* Exercice - DS2013 */
/* Damien Cremilleux */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <mpi.h>

#define N 3

int main(int argc, char * argv[])
{
  int i;
  int j;
  int k;
  float A[N][N];
  float B[N][N];
  float C[N][N];

  int rank, size;

  MPI_Status stat;
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  if(size != 3) {
    printf("Ce programme ne fonctionne qu\'avec 3 processus\n");
    exit(-1);
  }

  if(rank==0){
    A[0][0] = 1;
    A[0][1] = 2;
    A[0][2] = 3;
    A[1][0] = 4;
    A[1][1] = 5;
    A[1][2] = 6;
    A[2][0] = 7;
    A[2][1] = 8;
    A[2][2] = 9;

    B[0][0] = 10;
    B[0][1] = 11;
    B[0][2] = 12;
    B[1][0] = 13;
    B[1][1] = 14;
    B[1][2] = 15;
    B[2][0] = 16;
    B[2][1] = 17;
    B[2][2] = 18;

    for(i=1 ; i<size ; i++){
      MPI_Send(B, N*N, MPI_FLOAT, i ,0 , MPI_COMM_WORLD);
      MPI_Send(A+i*(N/size), N*(N/size), MPI_FLOAT, i, 0, MPI_COMM_WORLD);
    }

    /* affichage des matrices */
    printf("Matrice A :\n");
    for(i = 0 ; i < N ; i++) {
      for(j = 0 ; j < N ; j++) {
        printf("%f ", A[i][j]);
      }
      printf("\n");
    }
    printf("Matrice B :\n");
    for(i = 0 ; i < N ; i++) {
      for(j = 0 ; j < N ; j++) {
        printf("%f ", B[i][j]);
      }
      printf("\n");
    }
  }

  if(rank != 0){
    MPI_Recv(B, N*N, MPI_FLOAT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
    MPI_Recv(A+i*(N/size), N*(N/size), MPI_FLOAT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
  }


  for(i = 0 ; i < N/size ; i++) {
    for(j = 0 ; j < N ; j++) {
      float res_ij;
      res_ij = 0;
      for(k = 0 ; k < N ; k++) {
        res_ij += A[i*(N/size)][k] * B[k][j];
      }
      C[rank*(N/size)+i][j] = res_ij;
    }
  }

  if(rank != 0) {
    MPI_Send(C + rank*(N/size), N*(N/size), MPI_FLOAT, 0, 0, MPI_COMM_WORLD);
  }

  if(rank == 0) {
    for(i = 1; i < size ; i++) {
      MPI_Recv(C + i*(N/size), N*(N/size), MPI_FLOAT, i, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
    }

    /* Affichage du resultat */
    printf("Matrice C :\n");
    for(i = 0 ; i < N ; i++) {
      for(j = 0 ; j < N ; j++) {
        printf("%f ", C[i][j]);
      }
      printf("\n");
    }
  }

  MPI_Finalize();

  return 0;
}
