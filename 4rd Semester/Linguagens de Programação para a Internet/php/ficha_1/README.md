# Ficha Prática de PHP nº 01 2024/25

**Observação**: Para a resolução desta ficha é necessário ter instalado e bem configurado um servidor web de PHP (servidor Apache). É também necessário um editor de texto simples (notepad, notepad++ ou EditPlus), ou um 
editor mais avançado que também interprete código (DzSoft PHP Editor, Eclipse, …).

O servidor (a usar na sala de aula prática) é o XAMPP pelo que será necessária particular atenção em fazer o start do servidor e a pasta para colocar os scripts PHP em c:\xampp\htdocs (ou numa pasta dentro desta) e 
posteriormente correr os scripts com o URL http://localhost/test.php.
Para instalar o XAMPP recomenda-se uma pesquisa por “xampp download” e de seguida descarregar do site https://www.apachefriends.org/download.html: PHP Basic
De seguida é necessário executar/start o Apache (que é o servidor do PHP):
Para verificar se o XAMPP ficou bem instalado e se o start foi realizado, pode abrir um browser e escrever o endereço “localhost”. Se aparecer a seguinte página então está “tudo bem instalado e a correr”.

1. Escreva um script PHP que mostre no browser a frase “Olá mundo!!”.
2. Escreva um script PHP que mostre todos os números pares até 20, usando:
  a instrução for;
  b. a instrução if.
3. Escreva uma função PHP que receba um número como parâmetro devolvendo FALSE se esse número não for primo e TRUE caso seja primo.
4. Escreva uma função PHP que receba um número como parâmetro e que mostre todos os número primos menores que esse argumento e o total de números encontrados (use a função do exercício anterior). Por exemplo, se for passado
   um 7, deverá mostrar:
     2 é primo
     3 é primo
     5 é primo
    Total = 3 números primos menores que 7.
