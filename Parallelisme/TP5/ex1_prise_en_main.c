/* Introducion à OpenMP - Exercice 1 : prise en main */
/* Damien Crémilleux - Lauriane Holy */

#include <omp.h>
#include <stdio.h>

/* Affiche le rang du thread OpenMP */
void affiche()
{
#pragma omp parallel
  {
    int id_threadOMP = omp_get_thread_num();
    int nb_threadOMP = omp_get_num_threads();
    printf("Mon rang est : %d sur %d\n", id_threadOMP, nb_threadOMP);
  }
}

int main()
{
  printf("Début du programme\n");
  affiche();
  printf("Fin du programme\n");

  return 0;
}
