#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


pid_t pidFils;  

main(){
  int j;
  int k;

  /* On boucle pour générer les niveaux */
  for (j=0; j<3;j++)
    {
      /* on boucle pour générer les deux fils */
      for(k=0; k<2;k++)
	{
	  pidFils = fork(); 
	  if (pidFils==0)
	    break;
	}
      if (pidFils!=0)
	break;
    }
 
  /* ------------ code du fils ----------------- */
  int i;
  for (i=0;i<1;i++){
    printf("je suis le processus %d, mon pere est %d\n", getpid(), getppid());
    sleep(1);
	
	
  }
}
