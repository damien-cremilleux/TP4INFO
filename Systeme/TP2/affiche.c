/* TP2 Question 5 */

#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>

struct sigaction action;
sigset_t mask_nv;
sigset_t mask_anc;

int cpt;

void handler(int sig){
  printf("cpt = %d\n", cpt);
}

int main (int argc, char * argv[])
{  
  cpt =0;

  sigemptyset(&mask_nv);
  sigaddset(&mask_nv, SIGINT);// on ajoute le signal SIGINT (Ctrl-C)
  sigprocmask(SIG_UNBLOCK, &mask_nv, &mask_anc); // met en place le nouveau masque, on débloque SIGINT
 
  //Définition du handler
  action.sa_handler=handler;
  sigaction(SIGINT,&action,NULL);
  while(cpt< 15) {
    sleep(1);
    cpt++;
    
  }  
  exit(0);
}
