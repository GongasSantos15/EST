#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define KEY 1234

// Função para realizar a operação "down" no semáforo, ou seja, decrementá-lo
void down(int semid, int semnum) {
    struct sembuf op = {semnum, -1, 0};
    semop(semid, &op, 1);
}

// Função para realizar a operação "up" no semáforo, ou seja, incrementá-lo
void up(int semid, int semnum) {
    struct sembuf op = {semnum, 1, 0};
    semop(semid, &op, 1);
}

int main() {
    // Obtém o conjunto de semáforos usando a chave definida
    // Só acede, pressupõe que já foi criado pelo programa1
    int semid = semget(KEY, 2, 0666);
    if (semid == -1) {
        perror("Erro ao obter semáforo");
        exit(1);
    }

    // Mensagem indicando que o programa2 aguarda indicação do programa1 para avançar
    printf("Programa2: A aguardar indicação do Programa1...\n");
    down(semid, 0); // Semáforo 0 -> espera sinal do Programa1

    // Após ser liberado, o Programa2 espera 30 segundos
    printf("Programa2: Recebeu sinal. Esperar 30s...\n");
    sleep(30);

    // Após 30 segundos, diz ao programa1 através do semáforo 1
    printf("Programa2: Sinaliza para o programa1.\n");
    up(semid, 1); // Semáforo 1 -> sinaliza para o Programa1

    // Depois de receber a indicação, espera 5 segundos antes de terminar
    printf("Programa2: A aguardar 5s antes de terminar...\n");
    sleep(5);

    // Finaliza o programa2
    printf("Programa2: Programa finalizado!\n");
    return 0;
}
