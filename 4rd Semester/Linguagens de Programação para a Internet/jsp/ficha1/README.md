# Ficha prática nº1de JSP

Os scripts JSP tem que ser colocados na directoria do Tomcat, normalmente é C:\Tomcat\webapps\ROOT\, ou numa directoria dentro desta. 
Para executar/correr os scripts JSP, tem que (no browser) escrever localhost:8000 ou localhost:8080 (dependendo da porta que foi atribuída ao Tomcat).

Implemente as seguintes tarefas (cada exercício deve ser um ficheiro separado):
  1. Mostre no browser a frase “Olá mundo!!”.
  2. Mostre todos os números pares até 20.
  3. Crie uma página JSP_f1_ex03.htm (um formulário HTML) que receba através de caixas de texto um nome e uma idade. Esses dados serão passados (experimente os métodos GET e POST) para outra página, JSP_f1_ex03.jsp, que deverá verificar se a idade é maior ou igual a 18. O script exibirá nesta segunda página (JSP_f1_ex03.jsp) a seguinte mensagem: <nome> “pode votar” ou <nome> “não pode votar”, conforme o caso.
  4. Um script que receba um número (fornecido pelo utilizador, através de um formulário HTML) e mostre todos os número primos menores que esse número e o total de números encontrado. Por exemplo, se o número for 7, deverá mostrar:<br>
  
    2 é primo<br>
    3 é primo<br>
    5 é primo<br>
    Total = 3 números primos menores que 7.<br>
    
5. Implemente a função factorial, de forma que o script apresente o factorial do número pedido ao utilizador (fornecido pelo utilizador, através de um formulário HTML).
