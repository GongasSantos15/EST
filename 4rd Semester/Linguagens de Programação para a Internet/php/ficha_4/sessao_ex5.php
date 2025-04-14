<?php
    // ID da sessão
    session_id("lpi123");

    // Nome da sessão
    session_name("aulaLPI");
    session_start();

    // Cria a variável de sessão com o nome login
    $login = $_SESSION["login"];

    echo ("Sessão aulaLPI");
?>