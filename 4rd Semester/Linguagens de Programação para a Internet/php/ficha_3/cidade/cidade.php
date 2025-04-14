<?php
    // Variáveis retiradas do HTML
    $nomes_cidades = isset($_GET["nomes"]) ? $_GET["nomes"] : [];
    $populacoes_cidades = isset($_GET["populacoes"]) ? $_GET["populacoes"] : [];

    // Array combine -> Juntar os arrays
    $nomes_populacoes_cidades = array_combine($nomes_cidades, $populacoes_cidades);

    // Ordenar o array de acordo com a key por ordem alfabética crescente
    ksort($nomes_populacoes_cidades);
    print_r($nomes_populacoes_cidades);

    echo "<br>";

    // Ordenar o array de acordo com a key por ordem numérica descendente
    arsort($nomes_populacoes_cidades);
    print_r($nomes_populacoes_cidades);

?>