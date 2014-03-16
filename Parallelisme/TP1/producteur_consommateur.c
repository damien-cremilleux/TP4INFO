/* TP1 - Producteur/consommateur */
/* Damien Crémilleux - Lauriane Holy */

#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

#define NB_CASE 10
#define NB_THREAD 100

int tampon[NB_CASE];		/* tableau des ressources */
int thread_bloque[NB_THREAD];	/* tableau des threads bloqués */
int nb_val;			/* nombre de ressources présentes dans le tableau */
int nb_thread_bloque;		/* nombre de thread bloqué */
int indice_prod;		/* indice courant pour produire */
int indice_consom; 		/* indice courant pour consommer */
int cpt;

/* Les sémaphores d'exclusion mutuelle */
pthread_mutex_t mutex_tampon = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_nb_val= PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_thread = PTHREAD_MUTEX_INITIALIZER;

/* Les conditions */
pthread_cond_t producteur = PTHREAD_COND_INITIALIZER;
pthread_cond_t consommateur = PTHREAD_COND_INITIALIZER;

/* Vérifie si le tableau de ressource est plein */
int plein()
{
    int res;
    pthread_mutex_lock(&mutex_nb_val);
    res = (nb_val == NB_CASE);
    pthread_mutex_unlock(&mutex_nb_val);
    return res;
}

/* Vérifie si le tableau de ressource est vide */
int vide()
{
    int res;
    pthread_mutex_lock(&mutex_nb_val);
    res = (nb_val == 0);
    pthread_mutex_unlock(&mutex_nb_val);
    return res;
}

/* Ajoute le thread en exécution au tableau de thread bloqué */
void ajouter_thread_bloque()
{
    pthread_t pid;
    pid = pthread_self();	/* pthread_self : obtenir l'id du thread appelant */
    pthread_mutex_lock(&mutex_thread);
    thread_bloque[nb_thread_bloque] = pid;
    nb_thread_bloque++;
    pthread_mutex_unlock(&mutex_thread);
}

/* Enlève le thread en exécution du tableau de thread bloqué */
void enlever_thread_bloque()
{
    int i;
    pthread_t pid;
    int trouve;
    pid = pthread_self();

    pthread_mutex_lock(&mutex_thread);
    trouve = 0;
    for(i = 0; i < nb_thread_bloque; i++) {
	if(thread_bloque[i] == pid) {
	    trouve =1;
	}      
	if(trouve && i<nb_thread_bloque-1) {
	    thread_bloque[i]= thread_bloque[i+1];
	}
    }
    nb_thread_bloque--;  
    pthread_mutex_unlock(&mutex_thread);
}

/* Ajoute une valeur au tampon */
void put(int v)
{
    pthread_mutex_lock(&mutex_tampon);
    while(plein()) {
	ajouter_thread_bloque();
	pthread_cond_wait(&producteur, &mutex_tampon);
	enlever_thread_bloque();
    }
    tampon[indice_prod] = v;
    indice_prod = (indice_prod+1)%NB_CASE;

    pthread_mutex_lock(&mutex_nb_val);
    nb_val++;
    pthread_mutex_unlock(&mutex_nb_val);

    pthread_mutex_unlock(&mutex_tampon);
    pthread_cond_signal(&consommateur);
}

/* Retire une valeur du tampon */
int get()
{
    int v;
    pthread_mutex_lock(&mutex_tampon);
    while(vide()) {
	ajouter_thread_bloque();
	pthread_cond_wait(&consommateur, &mutex_tampon);
	enlever_thread_bloque();
    }
    v = tampon[indice_consom];
    tampon[indice_consom] = 0;
    indice_consom = (indice_consom+1)%NB_CASE;

    pthread_mutex_lock(&mutex_nb_val);
    nb_val--;
    pthread_mutex_unlock(&mutex_nb_val);

    pthread_mutex_unlock(&mutex_tampon);
    pthread_cond_signal(&producteur);
  
    return v; 
}

/* Consommer N/2 éléments */
void* consommer()
{
    int i;
    for (i =0; i < NB_CASE/2; i++) {
	get();
	sleep(1);
    }
    pthread_exit(NULL);
}

/* Produire N/2 éléments */
void* produire()
{
    int i;
    for (i =0; i < NB_CASE/2; i++) {
	put(cpt);
	sleep(1);
    }
    pthread_exit(NULL);
}

/* Afficher l'état du système : tampon et threads bloqués */
void affiche()
{
    int i;
    printf("Tampon : ");
    i =0;
    for (i=0; i< NB_CASE; i++) {
	printf(" %d ", tampon[i]);
    }
    printf("\n");

    printf("Threads bloqués : ");
    i =0;
    for (i=0; i< nb_thread_bloque; i++) {
	printf(" %d ", thread_bloque[i]); 
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
    nb_thread_bloque = 0;

    while(encore)
    {
	char action;
	printf("Bonjour, que voulez vous faire ? Taper \"c\" pour consommer, \"p\" pour produire, \"a\" pour afficher l'état courant, ou \"q\" pour quitter :\n");
	scanf(" %c", &action);
	if (action  == 'c') {
	    pthread_t p_thread;
	    pthread_attr_t attr;
	    int a = cpt;
	  
	    pthread_attr_init(&attr);
	    pthread_create(&p_thread,&attr, consommer, (void*) &a);
	}
      
	if (action  == 'p') {
	    pthread_t p_thread;
	    pthread_attr_t attr;
	    int a = cpt;
	    
	    pthread_attr_init(&attr);
	    pthread_create(&p_thread,&attr, produire, (void*) &a);
	}     
      
	if (action  == 'a') {
	    affiche();
	}

	if (action  == 'q') {
	    encore=0;
	}
    }
 
    return 0;
}
