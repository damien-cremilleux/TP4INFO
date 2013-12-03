/* TP3 - Question 2 */

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>
#include <stdio.h>

/* tube nommé */
int fd;
int test;
struct sigaction action1;
struct sigaction action2;
struct sigaction action3;
sigset_t mask_nv;
sigset_t mask_anc;

void handler1(int sig){
  write(fd,"Service 1 !",12);
}

void handler2(int sig){ 
  write(fd,"Service 2 !",12);  
}

void handler3(int sig) {
  test = 0;
}

int main(){
  
  /* Ajout de SIGUSR1 et SIGUSR2 aux signaux */
  sigemptyset(&mask_nv);
  sigaddset(&mask_nv, SIGUSR1);
  sigaddset(&mask_nv, SIGUSR2);

  // MODIFICATION
  // Masquer les signaux pour eviter que l'appel à open soit interrompu
  sigprocmask(SIG_BLOCK, &mask_nv, &mask_anc); /* met en place le nouveau masque */

  /* Définition du handler pour SIGUSR1 */
  action1.sa_handler=handler1;
  sigaction(SIGUSR1,&action1,NULL);

  /* Définition du handler pour SIGUSR2 */
  action2.sa_handler=handler2;
  sigaction(SIGUSR2,&action2,NULL);

  /* Définition du handler pour SIGINT */
  action3.sa_handler=handler3;
  sigaction(SIGINT,&action3,NULL);

  printf("pid: %d\n", getpid());
  /* Création du tube nommé */
  if(mkfifo("/tmp/montube",0666) == -1)
    {
      perror("mkfifo");
      exit(1);
    }
  // MODIFICATIONS
  //Debloquer les signaux pour ne les prendre en compte que lorsque le open réalisé pour eviter que le premier service soit afficher sur le terminal et pas ecrit dans le tube 
  if((fd = open("/tmp/montube",O_WRONLY)) == -1) /* ouverture en lecture seule */
    {
      perror("open");
      exit(1);
    }
  sigprocmask(SIG_UNBLOCK, &mask_nv, &mask_anc); /* met en place le nouveau masque */

  printf("a %d\n", fd);
 test= 1;
  while(test)
    {
      sleep(1);/* boucle infinie pour attendre */    
    }
  
  close(fd);
  unlink("/tmp/montube");
  return 0;
}
