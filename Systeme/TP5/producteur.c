/* Producteur */
#include "simple_semaphore.h"
#include "init_consom.h"
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>

int main(int argc, char *argv[]){

  struct mybuf_mem * m;

  int clef_mem;
  clef_mem = atoi(argv[1]);
  
  int valeur;
  clef_mem = atoi(argv[2]);


  m  = (struct mybuf_mem *)  shmat(clef_mem,NULL, 0);
  printf("test : %d\n",m->nbSem);
 

  /* on récupère le tableau d'indice */
  int* tab_indice;
  int* tab_val;
  tab_indice = m->tab1->val;
  tab_val = m->tab2->val;

  int i;
  int indice;
  int k;
  for(k = 0; k < 10;k++)
    {
      /* on s'assure qucase est vide */
      p(m->sem_vide);

      /* on réserve le tableau d'indice */
      p(m->sem_prod);

      /* on parcours à la recherche d'une case libre */
      indice = -1;
      for(i = 0; i<5 && indice == -1; i++)
	{
	  if(tab_indice[i] == VIDE)
	    {
	      indice = i;
	      tab_indice[indice] = ENCOURS;
	    }
	}

      sleep(1);

      /* on relache le tableau d'indice */
      v(m->sem_prod);


      /*on écrit la valeur dans le tableau de donnée */
      if(indice != -1)
	{
	  tab_val[indice] = valeur+indice;
      
	  sleep(1);

	  /* on réserve le tableau d'indice */
	  p(m->sem_prod);

	  /* on met la, case à 1 */
	  tab_indice[i] = PLEIN;
      
	  /* on relache le tableau d'indice */
	  v(m->sem_prod);
  
	  v(m->sem_data);
	}
    }
}
