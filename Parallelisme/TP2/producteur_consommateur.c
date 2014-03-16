/* TP2 - Producteur/consommateur */
/* Damien Crémilleux - Lauriane Holy */

#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

#define NB_CASE 5

/* État des trames */
#define EN_COURS_DE_LECTURE 1
#define VIDE 2
#define NON_LUE 3
#define EN_COURS_ECRITURE 4
#define LECTEUR1
#define LECTEUR2

int trame[NB_CASE];		/* tableau des trames */
int etat[NB_CASE];		/* etat de la trame */
int temps[NB_CASE];		/* temps de la trame */

int nb_val;			/* nombre de trame restant*/
int cpt;			/* compteur pour le numéro de trame */

pthread_mutex_t mutex[NB_CASE];

pthread_mutex_t mutex_nb_val = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex_consom = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t consommer = PTHREAD_COND_INITIALIZER;

/* Vérifie s'il y a des trames à lire */
int vide()
{
    int res;
    pthread_mutex_lock(&mutex_nb_val);
    res = (nb_val == 0);
    pthread_mutex_unlock(&mutex_nb_val);
    return res;
}

/* Recopie une trame vidéo dans le tampon */
void ecrire(int t)
{
    int val_ecrite;		/* indique si la trame a été écrite */
    val_ecrite = 0;

    while(!val_ecrite) {
	/* on écrit la trame dans une case vide, sinon on on cherche la valeur la plus vieille */
	int indice_vieux;
	int temps_min;
	indice_vieux = -1;
	temps_min = cpt;
	int i;
	for (i = 0; (i < NB_CASE) && (val_ecrite == 0); i++) {
	    switch (etat[i]) {
	    case EN_COURS_ECRITURE :
	    case EN_COURS_DE_LECTURE :
		break;
	    case VIDE :
		etat[i] = EN_COURS_ECRITURE;
		trame[i] = t;
		temps[i] = cpt;
		val_ecrite = 1;
		etat[i] = NON_LUE;
		printf("Écriture de la trame %d, à la case vide %d\n", cpt, i);
		pthread_mutex_lock(&mutex_nb_val);
		nb_val++;
		pthread_mutex_unlock(&mutex_nb_val);
		pthread_cond_broadcast(&consommer); /* on réveille les consommateurs */
		break;
	    case NON_LUE :
		if(temps_min > temps[i]) { 
		    indice_vieux = i;
		    temps_min = temps[i];
		}
		break;
	    }
	}
       
	/* On est dans le cas où il faut écrire à la place de la plus vieille donnée */
	if((!val_ecrite) && (indice_vieux != -1)) {
  	    pthread_mutex_lock(&mutex[indice_vieux]); 

	    switch (etat[indice_vieux]) {
	    case EN_COURS_DE_LECTURE:
		pthread_mutex_unlock(&mutex[indice_vieux]);
		break;
	    case NON_LUE :
	    case VIDE :
		etat[indice_vieux] = EN_COURS_ECRITURE;
		pthread_mutex_unlock(&mutex[indice_vieux]);
		trame[indice_vieux] = t;
		temps[indice_vieux] = cpt;
		val_ecrite = 1;
		etat[indice_vieux] = NON_LUE;
		printf("Écriture de la trame %d, à la case non-vide %d\n", cpt, indice_vieux);
		pthread_mutex_lock(&mutex_nb_val);
		nb_val++;
		pthread_mutex_unlock(&mutex_nb_val);
		pthread_cond_broadcast(&consommer); /* on réveille les consommateurs */
		break;
	    default :
		pthread_mutex_unlock(&mutex[indice_vieux]);
		break;
	    }
   
	}
    }
}

/* Recherche la plus ancienne trame non lue et met à jour l'état de la trame */
int debut_lire()
{
    while(1) {
	pthread_mutex_lock(&mutex_consom);
	while(vide())
	    pthread_cond_wait(&consommer,&mutex_consom);
	pthread_mutex_unlock(&mutex_consom);
	
	int i;
	int temps_min;
	int indice_trame_plus_ancienne;

	/* Recherche de l'indice */
	temps_min = cpt;
	for (i = 0; i< NB_CASE; i++) {
	    if(etat[i] == NON_LUE) {
		if(temps_min > temps[i]) { 
		    indice_trame_plus_ancienne = i;
		    temps_min = temps[i];
		}
	    }
	}

	/* Mise à jour de l'état, la recherche est relancée si celui-ci a changé entre deux */
	pthread_mutex_lock(&mutex[indice_trame_plus_ancienne]);
	if(etat[indice_trame_plus_ancienne] == NON_LUE) {
	    etat[indice_trame_plus_ancienne] = EN_COURS_DE_LECTURE;
	    pthread_mutex_unlock(&mutex[indice_trame_plus_ancienne]);
	    return indice_trame_plus_ancienne;
	}
	pthread_mutex_unlock(&mutex[indice_trame_plus_ancienne]);
    }
}


/* Met à jour l'état de la trame après lecture */
void fin_lire(indice_trame)
{
    pthread_mutex_lock(&mutex[indice_trame]);
    etat[indice_trame] = VIDE;
    pthread_mutex_unlock(&mutex[indice_trame]);
}

/* Réalise la lecture d'une trame */
int lire()
{
    int indice_trame_a_lire;
    int valeur_trame;
    indice_trame_a_lire = debut_lire();
    valeur_trame = trame[indice_trame_a_lire];
    printf("Lecture de la trame %d, à la case %d\n", temps[indice_trame_a_lire], indice_trame_a_lire);
    fin_lire(indice_trame_a_lire);
    return valeur_trame;
}

/* Produit des trames */
void* producteur()
{
    while(1) {
	ecrire(1);
	sleep(1);
	cpt++;
    }
    pthread_exit(NULL);
}

/* Consomme des trames */
void* consommateur()
{
    while(1) {
	lire();
	sleep(2);
    }
    pthread_exit(NULL);
}


int main(int argc, char* argv[])
{
    /* initialisation */
    int i;
    for (i = 0; i < NB_CASE; i++) {
	etat[i] = VIDE;
	pthread_mutex_init(&mutex[i], NULL);
    }
    cpt = 1;
    nb_val = 0;

    /* thread producteur */
    pthread_t p_thread;
    pthread_attr_t attr;
    pthread_attr_init(&attr);
    pthread_create(&p_thread, &attr, producteur, NULL);

    /* on lance un consommateur */
     consommateur(); 
 
    return 0;
}
