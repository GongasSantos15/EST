// ex26b.c
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
	pid_t p = fork();
	if(p == 0) {
	  while(1) {
	    printf("[%d] Eu sou o processo filho. O meu pai e o %d.\n", getpid(), getppid());
		sleep(1);
	  }
	} else if(p > 0) {
		printf("[%d] Eu sou o processo pai. \n", getpid());
		sleep(5);
	} else {
		printf("Fork error!\n");
	}
	return 0;
}
