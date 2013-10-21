/* TP2 Question 8 */
/* Lauriane Holy, Damien Crémilleux */

#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <unistd.h>

struct sigaction action_fils;
struct sigaction action_pere;
sigset_t mask_nv;
sigset_t mask_anc;
int tab_fils[10]; // tableau contenant les pid des fils

void handler_fils(int sig){
  printf("Fils : signal recu ! Suicide !\n");
  exit(0);
}

void handler_pere(int sig){
  int j;
  int pid_f;
  int pid;
 
  printf("Pere : signal recu, destruction des fils\n");

  /* on envoie le signal à tous les fils */
  for(j =0 ; j<10; j++)
    {
      pid_f = tab_fils[j];
      kill(pid_f, SIGUSR1);
    }

 /* on attend la mort de tous les fils */
  // Cette partie la devrait etre hors du handler, ici ca marche parceque SIGCHILD est plus prioritaire que SIGUSR1
  /*  for(j =0 ; j<10; j++)
    {
      wait(NULL);
    }
  

  printf("Pere : mission terminée, adieu.\n");
  exit(0);   
  */
}

int main (int argc, char * argv[])
{  
  int i;
  int pidFils;
  int pid;

  sigemptyset(&mask_nv);
  //  sigaddset(&mask_nv, SIGUSR1);// on ajoute le signal SIGUSR1 // A decommenter 
  //  sigprocmask(SIG_UNBLOCK, &mask_nv, &mask_anc); // met en place le nouveau masque

      //Définition du handler 

  action_fils.sa_handler=handler_fils;
  sigaction(SIGUSR1,&action_fils,NULL); 

 
  //creation de 10 processus
  for (i=0; i<10;i++)
    {
      pidFils = fork(); 
      if (pidFils==0)
	break;
      
      tab_fils[i] = pidFils;
    }

  if(pidFils==-1)
    {
      perror("Creation du processus fils echoue\n");
      exit(0);
    }

  if(pidFils==0) // fils
    {
      //Définition du handler 
      //A mettre avant les fork car si le signal est reçu avant la redifinition du handler, le SE utilisera le handler par defaut
      //
      //      sigaction(SIGUSR1,&action_fils,NULL); 
      
      while(1) {
	//	printf("Je suis un fils\n");
	sleep(1);
      }

    }
  else //pere
    {   
      int k,j;

      //Définition du handler
      // Dans le cas present pas de probleme de le laisser ici car c'est lui qui lance le signal
      action_pere.sa_handler=handler_pere;
      sigaction(SIGUSR1,&action_pere,NULL);

      //      for (k =0 ; k< 2; k++) {
	//	printf("C'est qui le papa ?\n");
	//		sleep(3);
      //      }

      // Je remplacerais le kill en tapant dans le terminal kill -s SIGUSR1 pid
      pid = getpid();
      kill(pid, SIGUSR1); // envoie de SIGUSR1 à soi même
      

 /* on attend la mort de tous les fils */
  // Cette partie la devrait etre hors du handler, ici ca marche car SIGCHILD est plus prioritaire que SIGUSR1
      for(j =0 ; j<10; j++)
	{
	  wait(NULL);
	}
      printf("Pere : mission terminée, adieu.\n");
      exit(0);   
  

    }  
  exit(0);
}
