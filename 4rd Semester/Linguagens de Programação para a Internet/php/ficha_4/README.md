# Ficha Prática de PHP nº 04 (PHP+Sessions)

- Informações sobre sessões:
  - http://www.php.net/manual/pt_BR/ref.session.php
  - http://www.tutorialspoint.com/php/php_sessions.htm
  - http://stackoverflow.com/questions/21302733/how-can-i-get-session-id-in-php-and-show-it

**NOTA: ** Se usar $HTTP_SESSION_VARS ou $_SESSION e desabilitar register_globals, não deve usar session_register(), session_is_registered() e session_unregister(), foram descontinuadas após a versão 5.4 do PHP.

1. Desenvolva um contador de acessos à página, para tal utilize uma variável de sessão “contador” usando o $_SESSION[].

**OBS:** a função isset($var) serve para verificar se a variável $var existe, devolvendo TRUE ou FALSE.

2. Altere o exercício anterior para fazer o mesmo mas usando session_register(). Funcionou? Apareceu algum warning, porquê?
3. Desenvolva um script para apenas “eliminar”, apenas, a variável de sessão “contador”.
4. Crie um sistema simples de login com 4 páginas php baseado na descrição seguinte, utilizando sessões.
  - Descrição:
    - “index.php” – Formulário com 2 campos “utilizador” e ”password”.
    - “login.php” – Verifica se campos de autenticação são correctos (comparando com dados existentes numa tabela “user “ em MySQL), se sim cria as variáveis de sessão para identificar o utilizador e o estado da autenticação e encaminha após 2 segundos para a página “menu_admin.php”.
    - “menu_admin.php” – Verifica através das variáveis de sessão se o utilizador está autenticado e tem privilégio para aceder a esta página, caso contrário apresenta mensagem e volta à página inicial. Se o utilizador estiver devidamente autenticado mostra mensagem de boas-vindas (com o nome do utilizador) e apresenta link para fazer logout.
    - “logout.php” – destrói as variáveis de sessão e a própria sessão e volta para a página inicial.
5. Desenvolva um script PHP que crie uma variável de sessão chamada “login”, numa sessão com o nome “aulaLPI” e com o id de sessão “lpi123”.
