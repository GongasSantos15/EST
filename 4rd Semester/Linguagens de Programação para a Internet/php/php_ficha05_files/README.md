# Ficha Prática de PHP nº 05 (PHP+Sessions+MySQL)

1. O código disponibilizado no ficheiro “php_ficha05_files.zip” tem apenas alguns scripts/páginas de um projecto para “aluguer de cabanas de um parque de campismo”.
2. Deve descompactar o ficheiro “php_ficha05_files.zip” para uma sub-directoria dentro do c:/xampp/htdocs, por exemplo, “ficha05”.
3. Antes de executar o código php, tem que importar a base de dados para o MySQL.
4. Verifique que a base de dados “3vago” e respectivas tabelas foram criadas:
5. De seguida vamos testar o projecto para “aluguer de cabanas de um parque de campismo. No browser deverá colocar o endereço: “http://localhost/exemplo_PWBD_login/paginas/pagina_inicial.php” 
6. Experimente as várias funcionalidades. Já existem os seguintes utilizadores pré-definidos: admin/admin; funcionario/funcionario; socio/socio. Verificará que apenas algumas funcionalidades/páginas foram disponibilizadas.
7. Pretende-se que complete este projecto, acrescentando as seguintes funcionalidades/páginas:
  - Registo de um novo utilizador. O novo utilizador, que por omissão deverá ser do tipo “cliente”, só poderá efectuar login após validado pelo “admin”;
  - “Dados Pessoais” para o utilizador “socio”;
  - “Gestão de Reservas” para o utilizador “socio”. Entenda-se que por “gestão” são as quatro operações: visualizar; inserir; editar; apagar. Como é óbvio, o “socio” só poderá fazer gestão das suas reservas, sendo que a inserção de uma reserva fica pendente de validação por parte do “funcionario”;
  - “Gestão de Reservas” para o utilizador “funcionario”. Entenda-se que por “gestão” são as quatro operações: visualizar; inserir; editar; apagar. O “funcionario” faz a gestão de todas as reservas (nomeadamente, inserir uma nova reserva para um determinado sócio).
