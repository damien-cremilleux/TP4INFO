#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <setjmp.h>


int i = 0;
int j = 0;
jmp_buf env;
struct sigaction action;

void handler(int sig){

  printf("div 0\n");
  i=1;
  j=1;
  //permet de revenir à l'instruction précédente
  longjmp(env,0);  // A decommenter
}

int main (int argc, char * argv[])
{
  
  

  //Définition du handler
  action.sa_handler=handler;
  signal(SIGFPE,(*handler));
  //sauvegarde de l'environnement
  setjmp(env);    // A decommenter
  printf("deb i=%d j=%d\n", i ,j);
  j= 12/i ; // la division par 0 va générer l'erreur, et l'affectation va boucler desssus
  printf("fin i=%d j=%d\n", i, j);
  exit(0);
}
