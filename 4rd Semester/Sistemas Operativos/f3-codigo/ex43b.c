#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {

    // Identificar o processo Pai
    printf("Pai (PID=%d)\n", getpid());
    
    // Imprimir os números de 1 a 10
    for (int i = 1; i <= 10; i++) {
        printf("%d ", i);
    }
    printf("\n");

    pid_t pid = fork();

    if (pid == 0) {
        // Processo filho - imprime os números de 10 a 20
        printf("Filho (PID=%d, PPID=%d)\n", getpid(), getppid());
        for (int i = 10; i <= 20; i++) {
            printf("%d ", i);
        }
        printf("\n");
    } else if (pid > 0) {
        // Processo pai - imprime os números de 20 a 30
        wait(NULL);
        for (int i = 20; i <= 30; i++) {
            printf("%d ", i);
        }
        printf("\n");
    } else {
        perror("Erro no fork");
    }

    return 0;
}
