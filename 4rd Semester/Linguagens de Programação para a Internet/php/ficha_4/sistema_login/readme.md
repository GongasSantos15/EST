Crie um sistema simples de login com 4 páginas php baseado na descrição seguinte, utilizando sessões.
Descrição:
• “index.php” – Formulário com 2 campos “utilizador” e ”password”.
• “login.php” – Verifica se campos de autenticação são correctos (comparando com dados existentes numa tabela “user
“ em MySQL), se sim cria as variáveis de sessão para identificar o utilizador e o estado da autenticação e encaminha
após 2 segundos para a página “menu_admin.php”.
• “menu_admin.php” – Verifica através das variáveis de sessão se o utilizador está autenticado e tem privilégio para
aceder a esta página, caso contrário apresenta mensagem e volta à página inicial. Se o utilizador estiver devidamente
autenticado mostra mensagem de boas-vindas (com o nome do utilizador) e apresenta link para fazer logout.
• “logout.php” – destrói as variáveis de sessão e a própria sessão e volta para a página inicial.