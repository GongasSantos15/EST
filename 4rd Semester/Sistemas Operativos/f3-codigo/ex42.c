#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

// Número máximo de processos
#define NUM_PROCS 5

int main() {

    // Cria um novo processo, com o seu PID
    for (int i = 1; i <= NUM_PROCS; i++) {
        printf("Processo %d (PID=%d)\n", i, getpid());

	// Se o num_proces == 5, para de criar processos
        if (i == NUM_PROCS) break;

	pid_t p = fork();

        if (p > 0) {
	    // Processo pai espera o filho
            int status;
            wait(&status);
            
            // Imprime uma mensagem com o seu PID
            printf("Processo %d (PID=%d) viu o filho terminar.\n", i, getpid());
            break;
        }
    }

    return 0;
}
