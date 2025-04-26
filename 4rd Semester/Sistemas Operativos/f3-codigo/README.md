# Código da Ficha 3 de SO
1. Copie o seguinte código (crie uma directoria ficha3, e aí dentro, crie o ficheiro ex21.c):

        #include <stdio.h>
        #include <unistd.h>

        int main() {
          printf(“SLEEP...\n”);
          sleep(30);
          printf(“THE END\n”);
          return 0;
        }
  
  a) Compile e execute o executável resultante em background. `prompt$ <ficheiro_exe>&`
  b) Execute o mesmo executável 3 vezes, do mesmo modo. De seguida, execute o comando ps.

2. Altere o código ex21.c de modo que, antes da chamada ao sleep seja apresentado o PID do processo em execução (ficheiro ex22.c):

        ...
        printf(“Eu sou o processo %d\n”, ????);
        ...

3. Qual é o output do seguinte programa?
  a) Copie, compile e execute (ficheiro ex23.c).

        #include <stdio.h>
        #include <unistd.h>
        #include <sys/types.h>

        int main() {
          pid_t p = fork();

           if(p == 0) {
             printf("Eu sou o processo filho.\n");
             sleep(20);
          } else if(p > 0) {
            printf("Eu sou o processo pai.\n");
            sleep(20);
          } else {
            printf("Fork error!\n");
          }
          return 0;
      }

4. Altere o programa anterior para que cada processo apresente o seu PID e o PID do processo que o criou, ou seja, do seu processo pai (ficheiro ex24a.c). Consulte o manual (man 2 getppid).

       ...
       printf("[%d] Eu sou o processo xpto. O meu pai é o processo %d\n", getpid(), ????);
       ...

  a) Compile e execute. Execute o comando ps. Quem é o pai do “processo pai”?

5. Altere, novamente, o programa para que o processo pai mostre o PID do processo filho (ficheiro ex24b.c).
  a) Compile e execute.

6. Compile e execute o seguinte código (ficheiro ex25a.c):

        #include <stdio.h>
        #include <stdlib.h>
        #include <unistd.h>
        #include <sys/types.h>
        #include <sys/wait.h>

        int main() {
          int status = 0;
          pid_t p = fork();

          if(p == 0) {
            exit(10);
          } else if(p > 0) {
            waitpid(p, &status, 0);
            printf("O processo filho terminou com estado %d.\n", WEXITSTATUS(status));
          } else {
            printf("Fork error!\n");
          }
          return 0;
        }

7. Implemente um programa (ficheiro ex25b.c) em que o processo pai cria um processo, imprime a mensagem de apresentação, espera que o processo filho termine a sua execução e imprime uma mensagem em que indica que o processo filho terminou (com o PID do filho). O processo filho deverá imprimir uma linha de apresentação (“[PID] Eu sou o filho. O meu pai é o processo PPID”) e dormir 15 segundos.
  a) Compile e execute.

8. 
  a) Repare nos valores dos parâmetros das chamadas sleep. Qual é a ordem temporal dos acontecimentos (criação e terminação de processos)?

        #include <stdio.h>
        #include <unistd.h>
        #include <sys/types.h>
        #include <sys/wait.h>

        int main() {
          int status;
          pid_t p = fork();

          if(p == 0) {
            printf("[%d] Eu sou o processo filho. O meu pai é o %d.\n", getpid(), getppid());
            sleep(15);
          } else if(p > 0) {
            printf("[%d] Eu sou o processo pai.\n", getpid());
            sleep(30);
            waitpid(p, &status, 0);
            printf("O meu filho terminou.\n");
            sleep(15);
          } else {
            printf("Fork error!\n");
          }
          return 0;
        }

  b) Copie o código e compile-o (ficheiro ex26a.c). Abra duas consolas antes de executar o binário. Execute-o numa consola e execute o comando ps –a na outra. Vá repetindo o comando ps –a.

9. Repare nos valores dos parâmetros das chamadas sleep. Qual é a ordem temporal dos acontecimentos (criação e terminação de processos)?

        #include <stdio.h>
        #include <unistd.h>
        #include <sys/types.h>

        int main() {
          pid_t p = fork();

          if(p == 0) {
            while(1) {
              printf("[%d] Eu sou o processo filho. O meu pai é o %d.\n", getpid(), getppid());
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

  b) Copie o código, compile-o (ficheiro ex26b.c) e execute-o.

10. Teste o seguinte programa com as várias alternativas em comentário (ficheiro ex31.c).
  a) A mensagem “THE END” aparece no ecrã em situações que não se verificam erros nas chamadas às funções exec...? Justifique.

        #include <stdio.h>
        #include <unistd.h>

        int main() {
          int res_exec;
          printf("STARTED...\n");
          res_exec = execlp("ls", "ls", "-la", NULL);
          // Alternativa:
          // res_exec = execl("/bin/ls", "ls", "-la", NULL);
          if(res_exec == -1) {
            printf("exec() error");
          }
        }
        printf("THEreturn 0;
        END\n");

## Exercícios

1. Construa um programa que aceite e execute comandos, do sistema Linux, indicados pelo utilizador. O programa deve estar sempre disponível para aceitar e executar novos comandos indicados pelo utilizador, e apenas deve terminar quando o utilizador digitar “quit”. 

2. Construa um programa que crie uma cadeia de NUM_PROCS processos. NUM_PROCS deve ser uma constante global > 2. O processo original (processo 1) cria um processo (processo 2), o processo 2 cria o 3, o 3 cria o 4, até existirem NUM_PROCS processos. Cada processo deve inicialmente apresentar uma mensagem com a sua identificação na hierarquia e o seu identificador. Em seguida, cada processo deve esperar que o seu filho termine, após o que deve apresentar uma mensagem indicando a terminação do seu filho.

3.
    a) Construa um programa que apresente os valores inteiros de 1 a 10. Cada processo antes de apresentar os valores inteiros deve identificar-se com o seu PID.
    b) Altere o programa anterior de modo que, o processo original (pai) após apresentar os valores inteiros entre 1 e 10 crie um processo filho, ficando o processo pai à espera da terminação deste filho. O processo filho, após ser criado, deve apresentar os valores inteiros entre 10 e 20 e em seguida terminar. O processo pai, após terminar a espera pelo processo filho, deve apresentar os valores inteiros entre 20 e 30 e em seguida terminar também.
    c) Altere o programa anterior para que inclua a criação de um terceiro processo. Este novo processo deve ser criado como filho do filho.
      i. O primeiro processo (pai) apresenta os valores de 1 a 10.
      ii. O segundo processo (filho) apresenta os valores de 10 a 20.
      iii. O terceiro processo (filho do filho) apresenta os valores de 20 a 30.
      iv. O segundo processo, depois de terminar a espera pelo terceiro, apresenta de 30 a 40.
      v. O primeiro processo, depois de terminar a espera pelo segundo, apresenta de 40 a 50.

4. Considere o exemplo de código seguinte:

        #include <stdio.h>
        #include <stdlib.h>
        #include <unistd.h>
        #include <sys/types.h>
        #include <sys/wait.h>

        int main() {
          int c = 2;
          pid_t p = 0;
          int status;
          p = fork();

          if(p == 0) {
            c = c + 5;
          } else if(p > 0) {
            c = c + 2;
            waitpid(p, &status, 0);
          } else {
            printf("Fork error!\n");
          }
          printf("c=%d\n", c);
          exit(0);
        }

     Antes de codificar, determine qual será o valor apresentado por “c”? Confirme a sua resposta executando o código. (ficheiro ex44.c)

5. Os seguintes exercícios visam a realização de uma auto-avaliação da aprendizagem sobre a criação e gestão de processos em Linux.
  a) Antes de codificar e executar, pense qual será o output dos seguintes programas:

(ficheiro ex45a.c)

        #include <stdio.h>
        #include <stdlib.h>
        #include <unistd.h>

        int main() {
          printf(“INICIO\n”);
          fork();
          printf(“FIM\n”);
          exit(0);
        }

(ficheiro ex45b.c)

        #include <stdio.h>
        #include <stdlib.h>
        #include<unistd.h>

        int main() {
          printf(“INICIO\n”);
          fork();
          fork();
          printf(“FIM\n”);
          exit(0);
        }

(ficheiro ex45c.c)

        #include <stdio.h>
        #include <stdlib.h>
        #include <unistd.h>

        int main() {
          printf(“INICIO\n”);
          if(fork() == 0) {
            printf(“FIM FILHO\n”);
          }
          printf(“FIM\n”);
          exit(0);
        }

(ficheiro ex45d.c)

      #include <stdio.h>
      #include <stdlib.h>
      #include <unistd.h>
      
      int main() {
        printf(“INICIO\n”);
        if(fork() == 0) {
          printf(“FIM FILHO\n”);
          exit(0);
        }
        printf(“FIM\n”);
        exit(0);
      }

  b) Execute agora cada um dos programas em cima e confirme se os resultados gerados estão de acordo com os que obteve na alínea a). Caso contrário, volte a analisar o código o tente entender o resultado obtido.
ESTCB
 Sistemas Operativos
 10/10
