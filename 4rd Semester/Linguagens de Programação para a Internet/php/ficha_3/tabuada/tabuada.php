<?php
    $num = $_GET["num"];
    
    for($i = 1; $i <= 10; $i++) {
        $mul = $num * $i;
        echo "$num x $i = $mul <br>";
    }
?>