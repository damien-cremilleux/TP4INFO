#include <stdio.h>
#include <mpi.h>
int main(int argc, char *argv[])
  {
    int rank, size;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    MPI_Comm_size(MPI_COMM_WORLD,&size);

    printf("Hello wold from node %d of %d nodes\n", rank, size);
    MPI_Finalize();
    printf("Fini\n");
    return(0);
  }
