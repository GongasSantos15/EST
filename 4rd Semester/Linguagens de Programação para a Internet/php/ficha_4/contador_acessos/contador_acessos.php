<?php
session_start();

// Verifica se a variável de sessão "contador" já existe
if (!isset($_SESSION['contador'])) {
    $_SESSION['contador'] = 1;
} else {
    $_SESSION['contador']++;
}

echo "Número de acessos: " . $_SESSION['contador'];
?>