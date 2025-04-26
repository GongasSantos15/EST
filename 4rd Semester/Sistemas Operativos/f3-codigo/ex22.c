// ex22.c
#include <stdio.h>
#include <unistd.h>

int main() {
    // Apresentar o PID do processo
    printf("Eu sou o processo %d\n", getpid());

    printf("SLEEP...\n");
    sleep(30);
    printf("THE END\n");
    return 0;
}
