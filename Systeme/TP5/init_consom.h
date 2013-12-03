/* Initialisation pour les producteurs consommateurs */
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>

#define CLEF 1234
#define VIDE 0
#define PLEIN 1
#define ENCOURS -1
#define NBVAL 5

struct mybuf_tab{
  int val[NBVAL]; /* le tableau de val */
};

struct mybuf_mem{
  struct mybuf_tab * tab1; /* le tableau d'indice */
  struct mybuf_tab * tab2; /* le tableau de valeur */
  int sem_data; /* le semaphore du nombre de donnée */
  int sem_vide; /* le semaphore du nombre de case vide */
  int sem_prod; /* le semaphore pour les producteurs */
  int sem_consom; /* le semaphore pour les consommateurs */
};

