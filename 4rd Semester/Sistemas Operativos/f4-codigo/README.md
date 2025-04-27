# Ficha 4 de SO

1. Criar um conjunto de semáforos (criar_sem.c):

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/sem.h>
	#include <errno.h>

	#define KET 123
	#define NUM_SEMS 4

	int main(int argc, char *argv[]) {
		/* identificador do conjundo de semaforos */
		int semid;
	
		/* criar o grupo de semaforos (4 semaforos) */
		if((semid = semget(KEY, NUM_SEMS, IPC_CREAT|IPC_EXCL|0600)) == -1) {
			perror("Erro ao criar o semaforo");
			return 1;
		}
		printf("ID grupo semaforos: %d\n", semid) ;
		printf("Identificado pela chave unica : %d\n", KEY);
		return 0;
	}

2. Exemplo de decremento de um semáforo (sem_op_sem.c)

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/sem.h>
	#include <errno.h>

	#define KEY 123
	#define NUM_SEMS 4

	int main(int argc, char *argv[]) {
		struct sembuf sempar;
		int semid, semval;
	
		// Obter o identificador de um conjunto de semáforos anteriormente criado
		if ((semid = semget(KEY, 0, 0)) == -1) {
			perror ("Error semget()");
			return 1;
		}
	
		printf("ID grupo semáforos: %d\n", semid);
		printf("Identificado pela chave única: %d\n", KEY);
	
		// Operação de decremento do segundo semáforo
		sempar.sem_num = 1;         // Vamos operar o segundo semáforo
		sempar.sem_op = -1;         // Decrementa este valor ao valor do semáforo
		sempar.sem_flg = SEM_UNDO;  // Desfaz após o processo terminar
		if (semop(semid, &sempar, 1) == -1) {
			perror("Error semop()");
			return -1;
		}
	
		printf("O valor do segundo semáforo foi decrementado!\n");
		return 0;
	}

3. Exemplo de incremento de um semáforo (inc_op_sem.c)

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/ipc.h>
	#include <sys/sem.h>
	#include <unistd.h>
	#include <errno.h>

	#define KEY 123
	#define NUM_SEMS 4

	int main(int argc, char *argv[]) {
		struct sembuf sempar;
		int semid, semval;
	
		// Obter o identificador de um conjunto de semáforos anteriormente criado
		if ((semid = semget(KEY, 0, 0)) == -1) {
			perror("Error semget()");
			return 1;
		}
	
		printf("ID grupo semáforos: %d\n", semid);
		printf("Identificado pela chave única: %d\n", KEY);
	
		// Operação de incremento do segundo semáforo
		sempar.sem_num = 1;       // Vamos operar o segundo semáforo
		sempar.sem_op = 1;        // Incrementa este valor ao valor do semáforo
		if (semop(semid, &sempar, 1) == -1) {
			perror("Error semop()");
			return -1;
		}
	
		printf("O valor do segundo semáforo foi incrementado!\n");
		sleep(1);
		return 0;
	}

## Exercícios

1. Construa dois programas, para execução simultânea, com o seguinte comportamento:
- O `programa1` deve esperar a passagem de 20 segundos. Em seguida deve permitir que o `programa2` avance na sua execução e esperar a indicação deste para que possa voltar a avançar. Depois de receber essa indicação espera 5 segundos antes de terminar.
- O `programa2` deverá esperar a indicação do `programa1` para avançar e passado 30 segundos deve dar a este a indicação de avançar. Após essa indicação deverá deixar passar 5 segundos e terminar.
- A evolução de execução dos dois programas deve ser percebida através da apresentação de mensagens indicativas das etapas de execução dos programas.
