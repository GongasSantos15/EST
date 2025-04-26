#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_CMD 256

int main() {
		// Definir a variável comando
    char comando[MAX_CMD];

    while (1) {
        printf("Comando> ");

        // Lê o comando do utilizador
        if (!fgets(comando, sizeof(comando), stdin)) continue;

        // Remove o '\n' no final (causado pelo ENTER)
        comando[strcspn(comando, "\n")] = '\0';

        // Se o utilizador escrever "quit", termina o programa
        if (strcmp(comando, "quit") == 0) break;

				// Cria um processo filho
        pid_t p = fork();

        if (p == 0) {
            execlp(comando, comando, NULL);
            perror("Erro ao executar o comando");
            exit(1);
        } else if (p > 0) {
            // Processo pai
            wait(NULL); // Espera o filho terminar
        } else {
            perror("Erro no fork");
        }
    }

		// Imprime "A terminar..." no final
		printf("A terminar...\n");
    return 0;
}
