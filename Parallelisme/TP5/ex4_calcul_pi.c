/* Introducion à OpenMP - Exercice 4 : calcul de Pi */
/* Damien Crémilleux - Lauriane Holy */

#include <omp.h>
#include <stdio.h>

int main()
{
  static long nb_pas = 10000000;
  double pas;
  int i;
  double x, pi, som = 0.0;
  pas = 1.0/(double) nb_pas;

  #pragma omp parallel for reduction(+:som) private(x)
  for (i = 1; i <= nb_pas; i++) {
    x = (i - 0.5) * pas;
    som = som + 4.0 / (1.0 + x * x);
  }

  pi = pas * som;
  /*  printf("PI=%f\n",pi);*/

  return 0;
}
