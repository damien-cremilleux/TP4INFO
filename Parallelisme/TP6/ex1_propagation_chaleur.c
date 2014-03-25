/* Parallélisation avec OpenMP - Exercice 1 : propagation de la chaleur sur une plaque rectangulaire */
/* Damien Crémilleux - Lauriane Holy */

#include <omp.h>
#include <stdio.h>
#include <math.h>
#include <stdio.h>


#define N 100
#define M 100
#define MAX 1000

int main()
{ 
  int i, j;
  omp_set_num_threads(1);
  
  /* initialisation */
  double T[N+2][M+2], T1[N+2][M+2];
  for(i = 0 ; i<N+2; i++)
    {
      for(j=0; j<M+2; j++)
	{
	  if(i==0 || i== N+1 || j ==0 || j == M+1)
	    T[i][j]= MAX;
	  else
	    T[i][j]= 0;
	}
    }
  
  /* on affiche le tableau initial */
  printf("Tableau initial :\n");
  for(i = 0 ; i<N+2; i++)
    {
      for(j=0; j<M+2; j++)
	{
	  printf("%5.1f ",T[i][j]); 
	}
      printf("\n");
    }
  
  double debut =  omp_get_wtime();

  /* propagation */
  double delta;
  double seuil;
  seuil = 0.000001;
  do {
    delta = 0;
    
#pragma omp parallel for reduction(+:delta) private(j) /* on doit déclarer j private pour qu'il ne soit pas partagé */
    for(i = 1 ; i<N+1; i++)
      {
	for(j=1; j<M+1; j++)
	  {
	    T1[i][j] = (double) (T[i][j+1] + T[i][j-1] + T[i+1][j] + T[i-1][j] + T[i][j]) / 5;
	    delta = delta +  fabs(T1[i][j]-T[i][j]);
	  }
      }
    
    /* recopie du tableau */
#pragma omp parallel for private(j)
    for(i = 0 ; i<N+2; i++)
      {
	for(j=0; j<M+2; j++)
	  {
	    if(i==0 || i== N+1 || j ==0 || j == M+1)
	      T[i][j]= MAX;
	    else
	      T[i][j] = T1[i][j]; 
	  }
      }
  }  
  while(delta > seuil);

  double fin = omp_get_wtime();  

  /* on affiche le tableau final */
  printf("Tableau final\n");
  for(i = 0 ; i<N+2; i++)
    {
      for(j=0; j<M+2; j++)
	{
	  printf("%6.2f ",T[i][j]); 
	}
      printf("\n");
    }

   double temps = (double)(fin - debut);
  printf("Finished in about %f seconds. \n", temps);

  return 0;
}
