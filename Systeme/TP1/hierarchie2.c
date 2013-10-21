#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


pid_t pidFils;  

main(){
  int j;
  int k;
  int NB_FILS = 2;
  int NB_NIV=4;
  int ancetres[NB_NIV];
 
   /* On boucle pour générer les niveaux */
  for (j=0; j<NB_NIV-1;j++)
    {
      /* on boucle pour générer les deux fils */
      for(k=0; k<NB_FILS;k++)
	{
	  pidFils = fork(); 
	  
	  if (pidFils==0)
	    {
	      ancetres[j]=getppid();
	      break;
	    }
	}
       if (pidFils!=0)
	break;
    }
 
  /* ------------ code du fils ----------------- */
  int i;
 
  printf("Processus %d, de niveau %d, j'ai %d ancêtres qui sont : ", getpid(), j,j);
  for(i = 0; i < j;i++)
    {
      printf("%d\t ", ancetres[i]);
      
    } 
  printf("\n");
    
  sleep(1); 
}

