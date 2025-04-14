<?php
    // 2. Escreva um script PHP que mostre todos os números pares até 20, usando:

    echo "Com for e if: <br>";

    for($i = 0; $i <= 20; $i++) {
        if($i % 2 == 0)
            echo "$i <br>";
    }

    echo "<br> Apenas com for: <br>";

    for($i = 0; $i <= 20; $i += 2)
        echo "$i <br>";
?>
