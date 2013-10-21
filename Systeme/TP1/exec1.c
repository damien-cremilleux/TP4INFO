#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>

  

main(){

  pid_t pidFils= fork();
  if (pidFils!=0){
    /* ------------ code du père ----------------- */
    printf("je lance un terminal\n"); 
  }
  else{
    /* ------------ code du fils ----------------- */
    execl("/usr/bin/xterm", "mon terminal",NULL);
    }
}
  
