#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>


struct sigaction action;
sigset_t mask_nv;
sigset_t mask_anc;

void handler(int sig){
  printf("Signal recu\n");
}

int main (int argc, char * argv[])
{
  
  // bloque SIGUSR1
  sigemptyset(&mask_nv);
  sigaddset(&mask_nv, SIGUSR1);// on ajoute le signal SIGUSR1 // A decommenter
  sigprocmask(SIG_BLOCK, &mask_nv, &mask_anc); // met en place le nouveau masque

  //Définition du handler
  action.sa_handler=handler;
  sigaction(SIGUSR1,&action,NULL);

  //creation d'un processus
  int pid=fork();
	
  if(pid==-1)
    {
      perror("Creation du processus fils echoue");
      exit(0);
    }

  //fils
  if(pid==0)
    {
      sleep(1);
      printf("Debut attente du fils\n");
      sigsuspend (&mask_anc); // le masque est temporairement remplacé en attendant le fils
      printf("Fin attente du fils\n");
      exit(0);
    }
  else //pere
    {
      kill(pid,SIGUSR1); //On envoie le signal SIGUSR1 au processus fils dont le pid est pid
      //On a redéfini le comportement du handler de SIGUSR1
      printf("Debut attente du pere\n");
      wait(NULL);//Attente de la fin de tous les fils
      printf("Fin attente du pere\n");
	
    }	
  exit(0);
}
