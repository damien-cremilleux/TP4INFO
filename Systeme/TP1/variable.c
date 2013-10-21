#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


pid_t pidFils;  

main(){
  int i = 1;
  pidFils = fork();
  if (pidFils!=0){
    /* ------------ code du père ----------------- */
    while (i<10){
      printf("je suis le pere, la valeur de i est %d\n",i);
      i=i+1;
      sleep(1);
    }
    printf("je suis le pere, la valeur de i est %d\n",i);
  }
  else{
    /* ------------ code du fils ----------------- */
    while (i>-10){
      printf("             je suis le fils, la valeur de i est %d\n",i);
      i=i-1;
      sleep(1);
    }
    printf("               je suis le fils, la valeur de i est %d\n",i);
  }

}
