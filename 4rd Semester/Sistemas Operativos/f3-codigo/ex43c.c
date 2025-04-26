#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {

		// Processo pai - imprime os valores de 1 a 10
    printf("Pai (PID=%d)\n", getpid());
    for (int i = 1; i <= 10; i++) {
        printf("%d ", i);
    }
    printf("\n");

    pid_t pid_filho = fork();

    // Processo Filho - imprime os valores entre 10 a 20
    if (pid_filho == 0) {
        printf("Filho (PID=%d, PPID=%d)\n", getpid(), getppid());
        for (int i = 10; i <= 20; i++) {
            printf("%d ", i);
        }
        printf("\n");

        pid_t pid_neto = fork();

	// Processo Neto - imprime os valores entre 20 a 30
        if (pid_neto == 0) {
            // Processo neto (filho do filho)
            printf("Neto (PID=%d, PPID=%d)\n", getpid(), getppid());
            for (int i = 20; i <= 30; i++) {
                printf("%d ", i);
            }
            printf("\n");
        } else if (pid_neto > 0) {
            // Enquanto o Filho espera pelo Neto - imprime os valores 30 a 40
            wait(NULL);
            for (int i = 30; i <= 40; i++) {
                printf("%d ", i);
            }
            printf("\n");
        } else {
            perror("Erro no fork (neto)");
        }
    } else if (pid_filho > 0) {
        // Enquanto Pai espera pelo Filho - imprime os valores 40 a 50
        wait(NULL);
        for (int i = 40; i <= 50; i++) {
            printf("%d ", i);
        }
        printf("\n");
    } else {
        perror("Erro no fork (filho)");
    }

    return 0;
}
