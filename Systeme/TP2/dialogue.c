/* TP2 Question 6-7 */

#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>

struct sigaction action_fils;
struct sigaction action_pere;
sigset_t mask_nv;
sigset_t mask_anc;
int cpt_rec;
int cpt_env;

void handler_fils(int sig){
  cpt_rec++;
  printf("Signal recu : %d\n", cpt_rec);
}

void handler_pere(int sig){
  printf("Signal recu SIGUSR2\n");
}

int main (int argc, char * argv[])
{  
  int i;

  sigemptyset(&mask_nv);
  sigaddset(&mask_nv, SIGUSR1);// on ajoute le signal SIGUSR1 // A decommenter 
  sigaddset(&mask_nv, SIGUSR2);// on ajoute le signal SIGUSR2 // A decommenter
  sigprocmask(SIG_BLOCK, &mask_nv, &mask_anc); // met en place le nouveau masque

  //Définition du handler
  action_fils.sa_handler=handler_fils;
  sigaction(SIGUSR1,&action_fils,NULL); 
  
  //Définition du handler 2
  action_pere.sa_handler=handler_pere;
  sigaction(SIGUSR2,&action_pere,NULL);

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
      cpt_rec = 0;
      printf("Je suis le fils\n");
      while(1) {
	sigsuspend(&mask_anc); // le masque est temporairement remplacé en attendant le fils
	int pid_papa = getppid();
	kill(pid_papa,SIGUSR2); // envoie de SIGUSR2
      }
    }
  else //pere
    {
      cpt_env = 0;
      while(1) {
	printf("Envoi du : %d\n", ++cpt_env);
	kill(pid,SIGUSR1); // envoie de SIGUSR1
	// attente de la réception
	sigsuspend(&mask_anc); 
      }
    }  
  exit(0);
}
