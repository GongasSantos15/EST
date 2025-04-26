#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t p = fork(); // Cria o processo filho

    if (p == 0) {
        // Processo filho
        printf("[%d] Eu sou o filho. O meu pai é o processo %d\n", getpid(), getppid());
        sleep(15); // Filho dorme 15 segundos
    } else if (p > 0) {
        // Processo pai
        printf("[%d] Eu sou o pai. Criei o processo filho [%d]\n", getpid(), p);
        wait(NULL); // Espera que o processo filho termine
        printf("[%d] O processo filho [%d] terminou.\n", getpid(), p);
    } else {
        // Erro no fork
        printf("Fork error!\n");
    }

    return 0;
}
