/* TP1 - Producteur/consommateur */
/* Damien Crémilleux - Lauriane Holy */

#include <pthread.h>
#include <stdio.h>
#define NB_CASE 10
#define NB_THREAD 100

int tampon[NB_CASE];		/* tableau des ressources */
int thread_bloque[NB_THREAD];	/* tableau des threads bloqués */
int nb_val;			/* nombre de ressources présentes dans le tableau */
int indice_prod;
int indice_consom; 
int cpt;

pthread_mutex_t mutex_tampon = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_nb_val= PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t producteur = PTHREAD_COND_INITIALIZER;
pthread_cond_t consommateur = PTHREAD_COND_INITIALIZER;


int plein()
{
  int res;
  pthread_mutex_lock(&mutex_nb_val);
  res = (nb_val == NB_CASE);
  pthread_mutex_unlock(&mutex_nb_val);
  return res;
}

int vide()
{
  int res;
  pthread_mutex_lock(&mutex_nb_val);
  res = (nb_val == 0);
  pthread_mutex_unlock(&mutex_nb_val);
  return res;
}

void ajouter_thread_bloquer()
{
  
}

void put(int v)
{
  pthread_mutex_lock(&mutex_tampon);
  while(plein())
    {
      ajouter_thread_bloque();
      pthread_cond_wait(&producteur, &mutex_tampon);
      enlever_thread_bloque();
    }
  tampon[indice_prod] = v;
  indice_prod = (indice_prod++)%NB_CASE;

  pthread_mutex_lock(&mutex_nb_val);
  nb_val++;
  pthread_mutex_unlock(&mutex_nb_val);

  pthread_mutex_unlock(&mutex_tampon);
  pthread_cond_signal(&consommateur);
}

int get()
{
  int v;
  pthread_mutex_lock(&mutex_tampon);
  while(vide())
    {
      ajouter_thread_bloque();
      pthread_cond_wait(&consommateur, &mutex_tampon);
      enlever_thread_bloque();
    }
  v = tampon[indice_consom];
  tampon[indice_consom] = 0;
  indice_consom = (indice_consom++)%NB_CASE;

  pthread_mutex_lock(&mutex_nb_val);
  nb_val--;
  pthread_mutex_unlock(&mutex_nb_val);

  pthread_mutex_unlock(&mutex_tampon);
  pthread_cond_signal(&producteur);
  
  return v; 
}

void* Nb_get()
{
  int i;
  for (i =0; i < NB_CASE/2; i++)
    {
      get();
      sleep(1);
    }
  pthread_exit(NULL);
}

void* Nb_put()
{
  int i;
  for (i =0; i < NB_CASE/2; i++)
    {
      put(2);
      sleep(1);
    }
  pthread_exit(NULL);
}

void affiche()
{
  int i;
  printf("Tampon : ");
  i =0;
  for (i=0; i< NB_CASE; i++)
    {
      printf(" %d ", tampon[i]);
      
    }
  printf("\n");
}

int main(int argc, char* argv[])
{
  int encore;

  encore = 1;
  cpt = 1;
  indice_prod =0;
  indice_consom = 0;
  nb_val = 0;

  while(encore)
    {
      char action;
      printf("Bonjour, que voulez vous faire ? Taper \"c\" pour consommer, \"p\" pour produire, \"a\" pour afficher le tampon, ou \"q\" pour quitter :\n");
      scanf(" %c", &action);
      if (action  == 'c')
	{
	  int thr_id;
	  pthread_t p_thread;
	  pthread_attr_t attr;
	  int a = cpt;
	  
	  pthread_attr_init(&attr);
	  thr_id = pthread_create(&p_thread,&attr, Nb_get, (void*) &a);
	}
      
      if (action  == 'p')
	{
	  int thr_id;
	  pthread_t p_thread;
	  pthread_attr_t attr;
	  int a = cpt;
	  
	  pthread_attr_init(&attr);
	  thr_id = pthread_create(&p_thread,&attr, Nb_put, (void*) &a);
	}     
      
      if (action  == 'a')
	{
	  affiche();
	}

      if (action  == 'q')
	{
	  encore=0;
	}
    }
 
  return 0;
}
