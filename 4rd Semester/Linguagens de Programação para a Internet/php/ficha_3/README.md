# Ficha Prática de PHP nº 03 (PHP+HTML básico)

1. Crie uma página f03_ex01.htm que receba através de caixas de texto um nome e uma idade. Esses dados serão passados (experimente os métodos GET e POST) para outra página, f03_ex01.php, que deverá verificar se a idade é maior ou igual a 18. O programa exibirá nesta segunda página a seguinte mensagem: <nome> “pode votar”
ou <nome> “não pode votar”, conforme o caso.
2. Construa uma página HTML que receba um número e execute um script PHP que mostre a tabuada desse número (só existe tabuada de números entre 1 e 10!).
3. Crie o seguinte formulário: Cada eleitor pode seleccionar no máximo 3 candidatos. Ao submeter o formulário, os resultados possíveis serão:
  • <nome do eleitor> votou em branco
  • <nome do eleitor> votou em mais de 3. Anulado
  • <nome do eleitor> votou em: <lista de eleitos>

**OBS** – a linha que envolve os candidatos é criada através da tag fieldset, exemplos em:
  • https://www.w3schools.com/tags/tag_fieldset.asp
  • https://developer.mozilla.org/pt-PT/docs/Web/HTML/Element/fieldset

4. Construa uma página HTML que receba o nome e população de 4 cidades e execute um script PHP que mostre as cidades (nome e população), primeiro por ordem  alfabética das cidades e depois por ordem decrescente de população. Recomenda-se que o nome das cidades seja um “array” e a população seja outro “array”, sendo a junção destes dois “array” realizada no PHP num array cujo índice seja o nome da cidade e o valor seja a população correspondente (usando a função array_combine(), https://www.w3schools.com/php/func_array_combine.asp). A visualização das cidades e respectiva população deve ser no formato de tabela HTML (http://www.web-source.net/html_codes_chart.htm).
