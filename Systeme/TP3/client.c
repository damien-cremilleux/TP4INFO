/* TP3 - Question 2 */

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <signal.h>

int main(int argc, char *argv[]){

  int fd; /* tube nommé */
  int nb;
  char buf[80];
  int i;
  int pid_serveur;
  
  /* Récupération du pid de serveur */
  pid_serveur = atoi(argv[1]);
   
  /* ouverture du pipe en lecture seule */
  if((fd = open ("/tmp/montube",O_RDONLY)) == -1)
    {
      perror("open");
      exit(1);
    }

  //   printf("a\n"); /* commenter cette instruction empeche le bon déroulement du programme */ 

  /* envoi successif de SIGUSR1 et SIGUSR2 */
  for (i =0; i<5 ; i++)
    {
      
      kill(pid_serveur,SIGUSR1); 
      
      /* lecture du pipe */
      nb=read(fd, buf, 80);

      if (nb > 0)
	{
	  printf("Il y a un nouveau message !\n");
	  printf("Message lu : %s\n", buf);
	}

      kill(pid_serveur,SIGUSR2);
      
      /* lecture du pipe */
      nb=read(fd, buf, 80);
      
      if (nb > 0)
	{
	  printf("Il y a un nouveau message !\n");
	  printf("Message lu : %s\n", buf);
	}

      sleep(1);
    }

  close(fd);

  return 0;
}

