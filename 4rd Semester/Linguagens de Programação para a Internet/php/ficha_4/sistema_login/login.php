<?php
    // Iniciar a sessão
    session_start();

    // Conectar à BD
    $connect = mysqli_connect("localhost", "root", "", "ficha4") or die ("Não foi possível conectar com a BD!!");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $user = $_POST['user'];
        $pass = $_POST['pass'];

        // Consulta SQL
        $sql = "SELECT * FROM user WHERE user = '$user' AND pass = '$pass'";

        // Resultado da Query SQL
        $result = mysqli_query($connect, $sql);

        // Número de linhas do SQL
        $num_rows = mysqli_num_rows($result);

        // Verificar os dados da tabela e igualar a variáveis de sessão
        if($num_rows == 1) {
            $_SESSION['username'] = $user;
            $_SESSION['password'] = $pass;
            $_SESSION['tipo_user'] = 1;        // Utilizador = Administrador

            // Redirecionar o utilizador para a página menu_admin.php
            header("refresh:2; url = menu_admin.php");
        } else {
            // Apresentar uma mensagem de erro e redirecionar para o página principal
            echo("User não encontrado! Vai ser redirecionado para a página principal...");
            header("refresh:2; url = index.php");
        }
    }

    /*
        Resolução da aula:
        <?php 
            if(!isset($_GET["login"]) or empty($_GET["login"]) or !isset($_GET["passwd"]) or empty($_GET["passwd"])) {
                header("location:index.php");
            } else {
                $conn = mysqli_connect(...)
                $sql = "SELECT * FROM user WHERE login=" " . $_GET["login"] . " " AND pass=" " . $_GET["pass"] . " " AND estado = 1";
             $res = mysqli_query($conn, $sql);
            }

            if (mysqli_num_rows($res) == 1) {
                $row = mysqi_fetch_array($res)
                $tipo = $row["tipo"];
                $_SESSION["login"] = $_GET["login"];
                $_SESSION["tipo"] = $_GET["tipo"];

    
                if ($tipo == 1) {
                    header("location:menu_admin.php")
                } else if ($tipo == 2) {
                    ...
                }
            } else {
                 header("location: index.php")
            }
        ?>
    */

?>