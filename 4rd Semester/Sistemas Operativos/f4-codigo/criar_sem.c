#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <errno.h>

#define KEY 123
#define NUM_SEMS 4

int main()
{
  /* identificador do conjundo de semaforos */
  int semid;

  /* criar o grupo de semaforos (4 semaforos) */
  if((semid = semget(KEY,NUM_SEMS,IPC_CREAT|IPC_EXCL|0600)) == -1) {
    perror("Erro ao criar o semaforo") ;
    return 1;
  }
  printf("ID grupo semaforos: %d\n",semid) ;
  printf("Identificado pela chave unica : %d\n",KEY);
  return 0;
}
