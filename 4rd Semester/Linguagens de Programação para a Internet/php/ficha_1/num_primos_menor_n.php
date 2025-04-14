<?php
/*
    Escreva uma função PHP que receba um número como parâmetro e que mostre todos os número primos menores que esse argumento e o total de 
    números encontrados (use a função do exercício anterior). Por exemplo, se for passado um
        7, deverá mostrar:
        2 é primo
        3 é primo
        5 é primo
        Total = 3 números primos menores que 7
*/

function ePrimo ($num) {
    if ($num <= 1)
        return FALSE;
    else if ($num == 2)
        return TRUE;
    else {
        for ($i = 2; $i < $num; $i++ ) {
            if ($num % $i == 0)
                return FALSE;
            return TRUE;
        }
    }
}

function numPrimosMenorN($n) {
    $contador = 0;

    for ($x = 1; $x < $n; $x++) {
        if (ePrimo($x)) {
            echo "$x é primo! <br>";
            $contador++;
        }
    }

    echo "Total = $contador números primos menores que $n";
}

numPrimosMenorN(7)

?>