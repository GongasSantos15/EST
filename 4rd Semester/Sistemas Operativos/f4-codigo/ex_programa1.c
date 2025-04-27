#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define KEY 1234

// Função para realizar a operação "down" no semáforo, ou seja, decrementá-lo
void decrement(int semid, int semnum) {
    struct sembuf op = {semnum, -1, 0};
    semop(semid, &op, 1);
}

// Função para realizar a operação "up" no semáforo, ou seja, incrementá-lo
void increment(int semid, int semnum) {
    struct sembuf op = {semnum, 1, 0};
    semop(semid, &op, 1);
}

int main() {

    // Obter o conjunto de semáforos usando a chave definida
    // Cria se não existir, senão apenas acessa o semáforo
    int semid = semget(KEY, 2, IPC_CREAT | 0666);
    if (semid == -1) {
        perror("Erro ao obter semáforo");
        exit(1);
    }

    printf("Programa1: Esperar 20s...\n");
    sleep(20);
    
    // Faz a operação "up" no semáforo 0 após 20 segundos, permitindo que o programa2 avance
    printf("Programa1: Executa Programa2.\n");
    increment(semid, 0); // Semáforo 0 -> Sinaliza o programa2

    // Faz a operação "down" no semáforo 1, o programa1 vai bloquear até receber um sinal do programa2
    printf("Programa1: A aguardar o sinal do Programa2...\n");
    decrement(semid, 1); // Semáforo 1 -> Espera receber o sinal do programa2

    // Após receber o sinal do programa2, espera mais 5 segundos antes de terminar
    printf("Programa1: Recebeu sinal do Programa2. Aguarda 5s antes de terminar...\n");
    sleep(5);

    // Termina o programa
    printf("Programa1: Programa Finalizado!\n");
    return 0;
}
