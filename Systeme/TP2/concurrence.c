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
  int i;

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
      perror("Creation du processus fils echoue\n");
      exit(0);
    }

 //fils
  if(pid==0)
    {
      printf("Je suis le fils, je vais attendre\n");
      sigsuspend(&mask_anc); // le masque est temporairement remplacé en attendant le fils
      printf("Je suis le fils, j'ai fini d'attendre.\n");
      for(i = 0; i<10; i++)
	  printf("b\n");
      printf("Je suis le fils, adieu !.\n");
    }
  else //pere
    {
      sleep(3);
      printf("Je suis le pere.\n");
      for(i = 0; i<10; i++)
	  printf("a\n");
      
      printf("Je suis le pere, j'envoie la sauce.\n");
      kill(pid,SIGUSR1); // envoie de SIGUSR1
      printf("Je suis le pere, j'attend le fils.\n");
      wait(NULL); // attente du fils
      printf("Je suis le père, adieu !\n");
    }  
  exit(0);
}
