/* Introducion à OpenMP - Exercice 2 : variables privées */
/* Damien Crémilleux - Lauriane Holy */

#include <omp.h>
#include <stdio.h>

/* Affiche le rang du thread OpenMP, et incrémente valeur2 */
void affiche(int valeur1, int valeur2)
{
#pragma omp parallel firstprivate(valeur2) shared(valeur1) /* firstprivate permet de garder la valeur avant appel */
  {
    int id_threadOMP = omp_get_thread_num();
    int nb_threadOMP = omp_get_num_threads();
    valeur2++;
    valeur1++;
    printf("Mon rang est : %d sur %d - valeur1 : %d - valeur2 : %d\n", id_threadOMP, nb_threadOMP,valeur1,valeur2);
    
  }
}

int main()
{
  int valeur1;
  int valeur2;
  valeur1 = 1000;
  valeur2 = 2000;
  
  printf("Début du programme\n");
  affiche(valeur1,valeur2);
  printf("Fin du programme\n");

  return 0;
}
