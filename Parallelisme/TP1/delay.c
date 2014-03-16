/* TP1 - Question 1 */
/* Damien Crémilleux - Lauriane Holy */

#include <pthread.h>
#include <stdio.h>

/* function to be executed by the new data */
void* do_loop(void * data)
{
    int i;
    int me = *((int*) data); 	/* thread number */
    for(i=0; i<10;i++)
    {
	printf("%d - step %d\n", me ,i);
	sleep(1);
    }
  
    /* terminate the thread */
    pthread_exit(NULL);
}

int main(int argc, char* argv[])
{
    int thr_id;			/* thread id */
    pthread_t p_thread;		/* thread's structure */
    int a = 1;			/* thread 1 identifying */
    int b = 2;			/* thread 2 identifying */
    pthread_attr_t attr;       	/* thread attributes */

    /* create a new thread that will execute 'do_loop()' */
    pthread_attr_init(&attr); 
    thr_id = pthread_create(&p_thread, &attr, do_loop, (void*)&a);
  
    /* run 'do_loop()' in the main thread as well */
    do_loop((void*) &b);
  
    return 0;
}
