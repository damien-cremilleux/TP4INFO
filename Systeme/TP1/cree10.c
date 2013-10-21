#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>


pid_t pidFils;  

main(){
  /* creation de 10 processus */
  int j;

  for (j=0; j<10;j++)
    {
      pidFils = fork(); 
      if (pidFils==0)
	break;
    }
  
  
  if (pidFils!=0){
    /* ------------ code du père ----------------- */
    int i;
    for(i=0;i<1;i++){
      printf("je suis le pere %d\n", getpid());
      sleep(1);
    }
  }  
  else{
    /* ------------ code du fils ----------------- */
    int i;
    for (i=0;i<1;i++){
      printf("je suis le fil %d\n", getpid());
	sleep(1);
	
	
    }
  }
}
