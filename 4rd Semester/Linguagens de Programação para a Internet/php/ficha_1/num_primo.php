<?php
    // Escreva uma função PHP que receba um número como parâmetro devolvendo FALSE se esse número não for primo e TRUE caso seja primo.

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

    echo(ePrimo(6));
?>