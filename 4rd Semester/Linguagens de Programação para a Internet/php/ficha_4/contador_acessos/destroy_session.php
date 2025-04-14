<?php
    session_start();
    
    unset($_SESSION['contador']);
    
    session_destroy();

    header("refresh:2; url: contador_acessos.php");
?>