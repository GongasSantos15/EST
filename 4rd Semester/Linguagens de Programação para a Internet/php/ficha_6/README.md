# Ficha Prática de PHP nº 06 (PHP+Files)

Aqui estão alguns links que te podem ajudar!
- http://php.net/manual/en/function.fopen.php
- http://www.w3schools.com/php/func_filesystem_fopen.asp
- http://php.net/manual/en/function.fwrite.php
- http://php.net/manual/en/function.fgets.php
- http://php.net/manual/en/function.feof.php
- http://php.net/manual/en/function.fclose.php

1. Crie um ficheiro de nome lusiadas.txt com a primeira estrofe do canto I dos Lusíadas (usando programação PHP):
     As armas e os barões assinalados,<br>
     Que da ocidental praia Lusitana,<br>
     Por mares nunca de antes navegados,<br>
     Passaram ainda além da Taprobana,<br>
     Em perigos e guerras esforçados,<br>
     Mais do que prometia a força humana,<br>
     E entre gente remota edificaram<br>
     Novo Reino, que tanto sublimaram;<br>

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()` (usando o modo de abertura “w”)

2. Acrescente ao ficheiro lusiadas.txt a terceira estrofe do canto I dos Lusíadas (usando programação PHP):
     Cessem do sábio Grego e do Troiano<br>
     As navegações grandes que fizeram;<br>
     Cale-se de Alexandro e de Trajano<br>
     A fama das vitórias que tiveram;<br>
     Que eu canto o peito ilustre Lusitano,<br>
     A quem Neptuno e Marte obedeceram:<br>
     Cesse tudo o que a Musa antígua canta,<br>
     Que outro valor mais alto se alevanta.<br>

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()` (usando o mode de abertura “a”)
- `fwrite()`, fputs() ou fprintf(), entre outras;

3. Acrescente ao ficheiro lusiadas.txt a segunda estrofe do canto I dos Lusíadas, na posição correcta (usando programação PHP):
      E também as memórias gloriosas<br>
      Daqueles Reis, que foram dilatando<br>
      A Fé, o Império, e as terras viciosas<br>
      De África e de Ásia andaram devastando;<br>
      E aqueles, que por obras valerosas<br>
      Se vão da lei da morte libertando;<br>
      Cantando espalharei por toda parte,<br>
      Se a tanto me ajudar o engenho e arte.<br>

**NOTA:** Atendendo que não é possível acrescentar “conteúdo a meio de um ficheiro”, deve ser usado um ficheiro temporário para copiar a primeira parte, de seguida acrescentar o referido “conteúdo a meio de um ficheiro” e finalmente copiar a parte final.

4. Escreva um script PHP para ler e mostrar o conteúdo do ficheiro lusiadas.txt anteriormente criado. O conteúdo deve ser o seguinte:
      As armas e os barões assinalados,<br>
      Que da ocidental praia Lusitana,<br>
      Por mares nunca de antes navegados,<br>
      Passaram ainda além da Taprobana,<br>
      Em perigos e guerras esforçados,<br>
      Mais do que prometia a força humana,<br>
      E entre gente remota edificaram<br>
      Novo Reino, que tanto sublimaram;<br>
      <br>
      E também as memórias gloriosas<br>
      Daqueles Reis, que foram dilatando<br>
      A Fé, o Império, e as terras viciosas<br>
      De África e de Ásia andaram devastando;<br>
      E aqueles, que por obras valerosas<br>
      Se vão da lei da morte libertando;<br>
      Cantando espalharei por toda parte,<br>
      Se a tanto me ajudar o engenho e arte.<br>
      <br>
      Cessem do sábio Grego e do Troiano<br>
      As navegações grandes que fizeram;<br>
      Cale-se de Alexandro e de Trajano<br>
      A fama das vitórias que tiveram;<br>
      Que eu canto o peito ilustre Lusitano,<br>
      A quem Neptuno e Marte obedeceram:<br>
      Cesse tudo o que a Musa antígua canta,<br>
      Que outro valor mais alto se alevanta.<br>

Para a resolução deste exercício recomenda-se o uso das seguintes funções:
- `fopen()`
- `fgets()`
