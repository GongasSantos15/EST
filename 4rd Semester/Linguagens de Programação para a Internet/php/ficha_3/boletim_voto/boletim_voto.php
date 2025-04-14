<?php
    $nome = isset($_GET["nome"]) ? $_GET["nome"] : null;

    $candidatos_selecionados = isset($_GET['candidatos']) ? $_GET['candidatos'] : [];

    $total_candidatos = count($candidatos_selecionados);  // Contar quantos checkbox foram ativados
    
    $candidatos = implode(", ", $candidatos_selecionados); // A função implode serve para converter o conteúdo do array em strings

    // Condições para apresentar o devido texto
    if($total_candidatos == 0)
        echo "$nome votou em branco";
    else if($total_candidatos > 3)
        echo "$nome votou em mais de 3. Anulado";
    else {
        echo "$nome votou em $candidatos";
    }

?>