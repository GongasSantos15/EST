#include <stdio.h>
#include <unistd.h>

int main() {

    // Identificar o processo com o seu PID
    printf("Sou o processo PID=%d\n", getpid());
    
    // Imprimir os valores de 1 a 10
    for (int i = 1; i <= 10; i++) {
        printf("%d ", i);
    }
    printf("\n");
    return 0;
}
