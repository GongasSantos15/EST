<html>
<head>
<style>
	body{
		background-image:url(../../media/imgs_sistema/fundoLogin.jpg);
		background-position: top center;
	}
	
	#loading{
		background-color:#A9F5A9;
		width:380px;
		margin: 200px auto 0px;
		overflow:hidden;
		box-shadow:0px 0px 5px #6F6666;
		text-align:center;
		font: bold 20px/50px sans-serif;
		color: white;
	}
	
</style>
</head>
<body>
</body>

</html>  

<?php

	session_start();

	include '../../base_dados/basedados.h';
	include './ConstUtilizadores.php';

	if (isset($_POST['user']) && isset($_POST['pass']) && isset($_POST['email']) && isset($_POST['confirm-pass']) && 
	isset($_POST['address']) && isset($_POST['phone']) && $_POST['pass'] === $_POST['confirm-pass']) {

		// Dados recebidos do formulário
		$nome_utilizador = $_POST['user'];
		$email = $_POST['email'];
		$imagem = '../../media/imgs_utilizadores/default.png';
		$pass = $_POST['pass'];
		$pass_confirmation = $_POST['confirm-pass'];
		$morada = $_POST['address'];
		$numero_telemovel = $_POST['phone-number'];
		$tipo_utilizador = CLIENTE_NAO_VALIDO;

		$sql = "INSERT INTO utilizador
		VALUES ('$nome_utilizador', '$email', '$imagem', '$morada', '".md5($pass)."', '$numero_telemovel', '$tipo_utilizador')";

		$res = mysqli_query($conn, $sql);
				
		echo "<div id='loading'>Registado com Sucesso<br>Loading...</div><script>setTimeout(function(){ 
			window.location.href = '../pagina_inicial.php'; }, 2000)</script>";
	}
?>