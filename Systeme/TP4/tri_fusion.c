/* TP4 - Question 3 */
/* Damien Crémilleux - Lauriane Holy */

#include "simple_semaphore.h"
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

#define CLEF 12345

struct mybuf{
  int nbVal; /* le nombre de valeur */
  int val[10]; /* le tableau de valeur */
};


void fusion(int tableau[],int deb1,int fin1,int fin2)
{
  int *table1;
  int deb2=fin1+1;
  int compt1=deb1;
  int compt2=deb2;
  int i;
  
  printf("Processus %d (père : %d) : fusion\n", getpid(), getppid());
  
  table1=malloc((fin1-deb1+1)*sizeof(int));
 
  //on recopie les éléments du début du tableau
  for(i=deb1;i<=fin1;i++)
    {
      table1[i-deb1]=tableau[i];
    }
                        
  for(i=deb1;i<=fin2;i++)
    {        
      if (compt1==deb2) //c'est que tous les éléments du premier tableau ont été utilisés
	{
	  break; //tous les éléments ont donc été classés
	}
      else if (compt2==(fin2+1)) //c'est que tous les éléments du second tableau ont été utilisés
	{
	  tableau[i]=table1[compt1-deb1]; //on ajoute les éléments restants du premier tableau
	  compt1++;
	}
      else if (table1[compt1-deb1]<tableau[compt2])
	{
	  tableau[i]=table1[compt1-deb1]; //on ajoute un élément du premier tableau
	  compt1++;
	}
      else
	{
	  tableau[i]=tableau[compt2]; //on ajoute un élément du second tableau
	  compt2++;
	}
    }
  free(table1);
}
        

void tri_fusion_bis(int* tableau, int deb, int fin)
{
  if (deb!=fin)
    {
      int mutex = init_semaphore(0);
      int milieu=(fin+deb)/2;
      if(fork()){
	
	tri_fusion_bis(tableau,deb,milieu);
	v(mutex);
	exit(0);
      }
      else if(fork()) {
	tri_fusion_bis(tableau,milieu+1,fin);
	v(mutex);
	exit(0);
      } 
      p(mutex);
      p(mutex);
      fusion(tableau,deb,milieu,fin);
      
    }
}

void tri_fusion(int * tableau, int longueur)
{
  if (longueur>0)
    {
      tri_fusion_bis(tableau,0,longueur-1);
    }
}

int main(int argc, char *argv[]){
  
  int i;
  int id_mem;
  struct mybuf * m;
  
  /* Création d'une région de mémoire partagée */
  id_mem = shmget(CLEF, sizeof(*m),IPC_CREAT | 0777);
  if(id_mem == -1) 
    {
      perror("Erreur de creation de la mémoire partagée !\n");
      exit(-1);
    }
  
  /* Attachement de la région de mémoire partagée */
  m = (struct mybuf *) shmat(id_mem, NULL, 0);
  m->nbVal = 10;
  m->val[0] = 8;
  m->val[1] = 5;
  m->val[2] = 6;
  m->val[3] = 1;
  m->val[4] = 2;
  m->val[5] = 3;
  m->val[6] = 9;
  m->val[7] = 0;
  m->val[8] = 7;
  m->val[9] = 4;

  /* Affichage des valeurs initiales */
  printf("Tableau initial : ");
  for(i = 0; i<m->nbVal; i++)
    {
      printf("%d ",m->val[i]);
    }
  printf("\n");

  tri_fusion(m->val, m->nbVal);
  
  /* Affichage des valeurs finales */
  printf("Tableau final : ");
  for(i = 0; i<m->nbVal; i++)
    {
      printf("%d ",m->val[i]);
    }
  printf("\n");

  /* Destruction de la région de mémoire partagée */
  shmctl(CLEF, IPC_RMID, NULL);
  
  exit(0);
}

