/* Introducion à OpenMP - Exercice 3 : boucles parallèles */
/* Damien Crémilleux - Lauriane Holy */

#include <omp.h>
#include <stdio.h>

/* Affiche le rang du thread OpenMP */
void affiche()
{
  int i;
#pragma omp parallel for
    for(i = 0; i < 20; i++) {
      int id_threadOMP = omp_get_thread_num();
      int nb_threadOMP = omp_get_num_threads();
      printf("Mon rang est : %d sur %d - i : %d\n", id_threadOMP, nb_threadOMP, i);
    }
}

int main()
{
  printf("Début du programme\n");
  affiche();
  printf("Fin du programme\n");

  return 0;
}
