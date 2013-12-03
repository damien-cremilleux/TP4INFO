#include "simple_semaphore.h"
#include <sys/ipc.h>
#include <sys/shm.h>

int valeur_non_triee[] = { 8, 5,6 ,1 ,2, 3,9, 0, 7, 4};

struct mybuf{
  int nbVal; /* le nombre de valeur */
  char val[10]; /* le tableau de val */
};


void fusion(int tableau[],int deb1,int fin1,int fin2)
{
  int *table1;
  int deb2=fin1+1;
  int compt1=deb1;
  int compt2=deb2;
  int i;
        
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
        

void tri_fusion_bis(int* tableau,int deb,int fin)
{
  if (deb!=fin)
    {
      int mutex = init_semaphore (0);
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

void tri_fusion(int tableau[],int longueur)
{
  if (longueur>0)
    {
      tri_fusion_bis(tableau,0,longueur-1);
    }
}

int main(int argc, char *argv[]){
  
  int id_mem;
  struct mybuf * m;
  id_mem = shmget(CLE,sizeof(*m),IPC_CREAT | 0777);

  if(id == -1) 
    {
      perror("Erreur de creation de la mémoire partagée !\n");
      exit(-1);
    }
  
  exit(0);
}

