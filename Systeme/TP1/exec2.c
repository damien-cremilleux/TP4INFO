#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


main(){

  pid_t pidFils= fork();
  if (pidFils!=0){
    /* ------------ code du père ----------------- */
    printf("je tourne en boucle\n"); 
    while(1);{}
  }
  else{
    /* ------------ code du fils ----------------- */
    printf("fils\n");
    }
}
  
