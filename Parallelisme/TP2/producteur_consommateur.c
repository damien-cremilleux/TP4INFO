/* TP2 - Producteur/consommateur */
/* Damien Crémilleux - Lauriane Holy */

#include <pthread.h>
#include <stdio.h>
#define NB_CASE 20

/* État des trames */
#define EN_COURS_DE_LECTURE 1
#define LUE_OU_VIDE 2
#define NON_LUE 3
#define EN_COURS_ECRITURE 4

int trame[NB_CASE];		/* tableau des trames */
int etat[N];			/* etat de la trame */
int temps[N];			/* temps de la trame */

int indice_prod;
int indice_consom; 
int nb_val;
int cpt;			/* Compteur pour le numéro de trame */

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t producteur = PTHREAD_COND_INITIALIZER;
pthread_cond_t consommateur = PTHREAD_COND_INITIALIZER;


int vide()
{
  int res;
  pthread_mutex_lock(&mutex_nb_val);
  res = (nb_val == 0);
  pthread_mutex_unlock(&mutex_nb_val);
  return res;
}

void ecrire(int t)
{
  int val_ecrite = 0;

  /* On cherche la valeur la plus vieille */
  int indice_vieux = cpt;
  int i;
  for (i =0; i< NB_CASE; i++)
    {
      switch (etat[i])
	{
	case EN_COURS_ECRITURE :
	case EN_COURS_DE_LECTURE :
	  break;
	case LUE_OU_VIDE :
	  etat[i] = EN_COURS_ECRITURE;
	  trame[i] = t;
	  val_ecrite = 1;
	  etat[i] = NON_LUE;
	  break;
	case NON_LUE :
	  if(indice_vieux > temps[i]) 
	    {
	      indice_vieux = temps[i];
	    }	  
	  break;
  	}
      
      if(val_ecrite)
	break;
    }

  /* On est dans le cas ou il faut écrire à la place de la plus vieille donnée */
  if(!val_ecrite)
    {
      int action;

      pthread_mutex_lock(&mutex); 

      switch (etat[indice_vieux])
	{
	case NON_LUE :
	  etat[indice_vieux] = EN_COURS_ECRITURE;
	  action =1;
	  break;
	case EN_COURS_DE_LECTURE :
	   action =2;
	case EN_COURS_ECRITURE :
	  action =2;
	  break;
	case LUE_OU_VIDE: 
	  etat[indice_vieux] = EN_COURS_ECRITURE;
	  action = 1;
	  break;	   
	}
      
      pthread_mutex_unlock(&mutex); 
    
      switch (action)
	{
	case 1 :
	  trame[i] = t;
	  val_ecrite = 1;
	  etat[i] = NON_LUE;
	  break;
	case 2 :
	  ecrire(t);
	  break;
	}
    }
}

/* int get() */
/* { */
/*   int v; */
/*   pthread_mutex_lock(&mutex_tampon); */
/*   while(vide()) */
/*     { */
/*       ajouter_thread_bloque(); */
/*       pthread_cond_wait(&consommateur, &mutex_tampon); */
/*       enlever_thread_bloque(); */
/*     } */
/*   v = tampon[indice_consom]; */
/*   tampon[indice_consom] = 0; */
/*   indice_consom = (indice_consom+1)%NB_CASE; */

/*   pthread_mutex_lock(&mutex_nb_val); */
/*   nb_val--; */
/*   pthread_mutex_unlock(&mutex_nb_val); */

/*   pthread_mutex_unlock(&mutex_tampon); */
/*   pthread_cond_signal(&producteur); */
  
/*   return v;  */
/* } */

/* void* Nb_get() */
/* { */
/*   int i; */
/*   for (i =0; i < NB_CASE/2; i++) */
/*     { */
/*       get(); */
/*       sleep(1); */
/*     } */
/*   pthread_exit(NULL); */
/* } */

/* void* Nb_put() */
/* { */
/*   int i; */
/*   for (i =0; i < NB_CASE/2; i++) */
/*     { */
/*       put(2); */
/*       sleep(1); */
/*     } */
/*   pthread_exit(NULL); */
/* } */

/* void affiche() */
/* { */
/*   int i; */
/*   printf("Tampon : "); */
/*   i =0; */
/*   for (i=0; i< NB_CASE; i++) */
/*     { */
/*       printf(" %d ", tampon[i]); */
/*     } */
/*   printf("\n"); */

/*   printf("Threads bloqués : "); */
/*   i =0; */
/*   for (i=0; i< nb_thread_bloque; i++) */
/*     { */
/*       printf(" %d ", thread_bloque[i]);  */
/*     } */
/*   printf("\n"); */
/* } */

int main(int argc, char* argv[])
{
  int encore;

  encore = 1;
  cpt = 1;
  indice_prod =0;
  indice_consom = 0;
  nb_val = 0;
  nb_thread_bloque = 0;

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
