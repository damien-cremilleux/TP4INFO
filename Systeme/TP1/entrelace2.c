#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


pid_t pidFils;  

main(){

  pidFils = fork();
  if (pidFils!=0){
    /* ------------ code du père ----------------- */
    int i;
    for(i=0;i<10;i++){
      printf("je suis le pere\n");
      sleep(1);
    }

  }
  else{
    /* ------------ code du fils ----------------- */
    int i;
    for (i=0;i<10;i++){
    printf("je suis le fils\n");
    sleep(1);
    }
  }

}
