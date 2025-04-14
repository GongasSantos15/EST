<?php
    session_start();

    if (isset($_SESSION["username"]) AND $_SESSION["tipo_user"] == 1) {
        echo("Bem vindo, " . $_SESSION["username"] . " <a href='index.php'>Logout</a>");
    } else {
        echo("O utilizador não existe!");
        header("refresh:2; url = login.php");
    }

    /*
        Resolução da aula:
        <?php 
            session_start();

            if (!(isset($_SESSION["login"]) AND isset($_SESSION["tipo"]) AND $_SESSION["tipo"] == 1)) {
                session_destroy();
                header("location:index.php");
            } else {
                echo("<a href=login.php ? id = 123>Gestão de Utilizadores</a>")
            }
        ?>
    */
?>