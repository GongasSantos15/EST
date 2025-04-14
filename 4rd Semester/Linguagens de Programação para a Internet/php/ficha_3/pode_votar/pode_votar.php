<?php
    if (isset($_GET["nome"]) and isset($_GET["idade"])) {
        $nome = $_GET["nome"];
        $idade = $_GET["idade"];
    }
    
    echo "O nome é $nome e a idade é igual a $idade <br>";
    echo(podeVotar($idade));

    function podeVotar($idade) {
        return ($idade >= 18) ? "Pode votar!" : "Não pode votar! Ainda não atingiu a idade mínima";
    }

?>