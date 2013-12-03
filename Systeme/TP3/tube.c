/* TP3 - Question 1 */

#include <unistd.h>
#include <stdlib.h>

int main(){
  int pidFils;

  /* création du pipe */
  int fp[2];
  pipe(fp);

  /* buffer pour les données */
  char buffer[1000];

  /* fork du programme */
  pidFils = fork();
  if (pidFils!=0)
    { /* ------------ code du père ----------------- */
      close(fp[0]); /* on ferme le tube en lecture */
      dup2(fp[1], STDOUT_FILENO); /* redirection de la sortie standard 
				     sur l'entrée du pipe */
      execl("/usr/bin/who","who",NULL);
    }
  else
    { /* ------------ code du fils ----------------- */
      close(fp[1]);
      dup2(fp[0], STDIN_FILENO);
      execl("/bin/grep","grep","dcremill",NULL);
    }
}
