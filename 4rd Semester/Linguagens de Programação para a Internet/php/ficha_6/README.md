# Ficha Prática de PHP nº 06 (PHP+Files)

Aqui estão alguns links que te podem ajudar!
- http://php.net/manual/en/function.fopen.php
- http://www.w3schools.com/php/func_filesystem_fopen.asp
- http://php.net/manual/en/function.fwrite.php
- http://php.net/manual/en/function.fgets.php
- http://php.net/manual/en/function.feof.php
- http://php.net/manual/en/function.fclose.php

1. Crie um ficheiro de nome lusiadas.txt com a primeira estrofe do canto I dos Lusíadas (usando programação PHP):
  1. As armas e os barões assinalados.
     Que da ocidental praia Lusitana.
     Por mares nunca de antes navegados.
     Passaram ainda além da Taprobana.
     Em perigos e guerras esforçados.
     Mais do que prometia a força humana.
     E entre gente remota edificaram.
     Novo Reino, que tanto sublimaram.

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()` (usando o modo de abertura “w”)

2. Acrescente ao ficheiro lusiadas.txt a terceira estrofe do canto I dos Lusíadas (usando programação PHP):
  1. Cessem do sábio Grego e do Troiano.
     As navegações grandes que fizeram.
     Cale-se de Alexandro e de Trajano.
     A fama das vitórias que tiveram.
     Que eu canto o peito ilustre Lusitano.
     A quem Neptuno e Marte obedeceram.
     Cesse tudo o que a Musa antígua canta.
     Que outro valor mais alto se alevanta.

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()` (usando o mode de abertura “a”)
- `fwrite()`, fputs() ou fprintf(), entre outras;

3. Acrescente ao ficheiro lusiadas.txt a segunda estrofe do canto I dos Lusíadas, na posição correcta (usando programação PHP):
   1. E também as memórias gloriosas.
      Daqueles Reis, que foram dilatando.
      A Fé, o Império, e as terras viciosas.
      De África e de Ásia andaram devastando.
      E aqueles, que por obras valerosas.
      Se vão da lei da morte libertando.
      Cantando espalharei por toda parte.
      Se a tanto me ajudar o engenho e arte.

**NOTA:** Atendendo que não é possível acrescentar “conteúdo a meio de um ficheiro”, deve ser usado um ficheiro temporário para copiar a primeira parte, de seguida acrescentar o referido “conteúdo a meio de um ficheiro” e finalmente copiar a parte final.

4. Escreva um script PHP para ler e mostrar o conteúdo do ficheiro lusiadas.txt anteriormente criado. O conteúdo deve ser o seguinte:
   1. As armas e os barões assinalados.
      Que da ocidental praia Lusitana.
      Por mares nunca de antes navegados.
      Passaram ainda além da Taprobana.
      Em perigos e guerras esforçados.
      Mais do que prometia a força humana.
      E entre gente remota edificaram.
      Novo Reino, que tanto sublimaram.
      E também as memórias gloriosas.
      Daqueles Reis, que foram dilatando.
      A Fé, o Império, e as terras viciosas.
      De África e de Ásia andaram devastando.
      E aqueles, que por obras valerosas.
      Se vão da lei da morte libertando.
      Cantando espalharei por toda parte.
      Se a tanto me ajudar o engenho e arte.
      Cessem do sábio Grego e do Troiano.
      As navegações grandes que fizeram.
      Cale-se de Alexandro e de Trajano.
      A fama das vitórias que tiveram.
      Que eu canto o peito ilustre Lusitano.
      A quem Neptuno e Marte obedeceram.
      Cesse tudo o que a Musa antígua canta.
      Que outro valor mais alto se alevanta.

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()`
- `fgets()`
