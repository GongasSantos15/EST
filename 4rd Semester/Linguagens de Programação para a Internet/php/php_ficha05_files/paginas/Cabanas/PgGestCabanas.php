<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>3vago</title>
</head>

<style>  
  
	body{
		background-color:#376141; 
	}
	#cabecalho{
		margin: -8px;
		height:200px;
		background-image:url(../../media/imgs_sistema/cabecalho.png);
		border: 2px solid #0B610B;
	}
	
	.input-div{    
		margin:25px;
		float:right;
		height:150px;
	}
  
	input[type=submit]{
   
		background-color:#088A29;
		padding:10px 30px;
		height:50px;
		font: bold 15px sans-serif;
		color:white;
		box-shadow:2px 2px 5px #000000;
		cursor:pointer;
		border:0px;
	}
	
	input[type=submit]:hover{
		box-shadow:1px 1px 5px #000000;
	}
	
	#botoes{
		margin:70px;
	}
  
	#botao{
		float:right;
		margin:10px;
	}
  
	#logo{
		float:left;
		background-image:url(../../media/imgs_sistema/logo.png);
		margin-left:20px;
		margin-top:-10px;
		width:300px;
		height:200px;
	}
  
	#corpo{
		margin: 25px;
	}
	
	td {
		font: normal 16px sans-serif;
		text-align: center;
	}
	
	th {
		font: bold 16px sans-serif;
		text-align: center;
	}
	table, th, td {
		
		border-collapse: collapse;
	}
	th, td {
		padding: 15px 20px;
	}
	table#t01 tr:nth-child(even) {
		background-color: #4C8E5C;
		color:white;
	}
	table#t01 tr:nth-child(odd) {
		background-color: #BCF5A9;
		
	}
	
	#btnNv{
		font: bold 19px sans-serif;
		margin-bottom: 20px;
		padding: 10px 70px;
	}
</style>  
<body>  
	<!-- GRAFISMO CABECALHO -->
<div id="cabecalho">
	<a href='../index.php'>
		<div id="logo">
		</div>
	</a>
    <div class= "input-div">
		<div id="botoes"> 
		<?php
			ob_start();
			session_start();
			
			if(isset($_SESSION["user"])){
				echo "
				<div id='botao'>
					<form action='../Utilizador/logout.php'>
						<input type='submit' value='Logout'>
					</form>
				</div>
				<div id='botao'>
					<form action='../Utilizador/PgUtilizador.php'>
						<input type='submit' value='Página Inicial'>
					</form>
				</div>
				";	
			}else
				echo "<script> setTimeout(function () { window.location.href = '../index.php'; }, 2000)</script>";
				
			
		?>
			<div id='botao'>
			  <form action="../contatos.php">
				<input type='submit' value='Contactos'>
			  </form>
			</div>
		</div>
    </div>
</div>
  <!-- GRAFISMO CORPO -->
<div id="corpo">
	<form action="PgCabana.php">
		<input type='submit' value='Nova Cabana' id="btnNv">
	</form>
	<div id="tabela">
		<?php
			if(isset($_SESSION["user"])){
				
				$user=$_SESSION["user"];
				unset($_SESSION);
				$_SESSION["user"]=$user;
				
				include "../../base_dados/basedados.h";
				include "./ConstUtilizadores.php";
				// ==========================Quem é o utilizador==========================
				$sql = "SELECT tipoUtilizador FROM utilizador WHERE nomeUtilizador='".$_SESSION["user"]."'";
							
				// ====================================================		
				$retval = mysqli_query( $conn, $sql );
				if(! $retval ){
					die('Could not get data: ' . mysqli_error($conn));// se não funcionar dá erro
				}
				$row = mysqli_fetch_array($retval);
				$tipoUtilizador = $row["tipoUtilizador"];
				
				$pode=true;
				
				if(isset($_GET["IdUser"])){
					if($tipoUtilizador!=ADMINISTRADOR){
						$pode=false;
						echo "<script> setTimeout(function () { window.location.href = '../index.php'; }, 000)</script>";
					}else{
						$_SESSION["IdUser"]=$_GET["IdUser"];
					}
				}
				
				if($pode){
					
					$retval = mysqli_query( $conn, $sql );
					if(! $retval ){
						die('Could not get data: ' . mysqli_error($conn));// se não funcionar dá erro
					}
					$row = mysqli_fetch_array($retval);
										
					$sql = "SELECT * FROM cabana";
					$retval = mysqli_query( $conn, $sql );
					if(! $retval ){
						die('Could not get data: ' . mysqli_error($conn));// se não funcionar dá erro
					}
					
					echo "
					<table width='100%' id = 't01'>
					<tr>
						<th>Imagem:</th>
						<th>Id_cabana:</th>
						<th>Lotacao:</th>
						<th>Tipo:</th>
						<th>Título:</th>
						<th>Preço:</th>
						<th>Editar:</th>
						<th>Remover/Desbloquear:</th>
					</tr>";
					while($row = mysqli_fetch_array($retval)){
						
						$imagem = $row["img_cabana"];
						echo"
						<tr>
							<td><img src='../../media/bungalows/".$imagem."' width=100 height=75></td>
							<td>".$row["IdCabana"]."</td>
							<td>".$row["Lotacao"]."</td>
							<td>".$row["tipo"]."</td>
							<td>".$row["nome"]."</td>
							<td>".$row["preco_p_noite"]."€</td>
							<td><a href='preparaEditar.php?IdCabana=".$row["IdCabana"]."' ><img src='../../media/imgs_sistema/editar.png' width=50 height=50></a></td>
							";
						if($row["estado"]==0)
							echo"<td><a href='ativa.php?IdCabana=".$row["IdCabana"]."' ><img src='../../media/imgs_sistema/validar.png' width=50 height=50></a></td>";
						else if($row["estado"]==1)
							echo"<td><a href='desativa.php?IdCabana=".$row["IdCabana"]."' ><img src='../../media/imgs_sistema/remove.png' width=50 height=50></a></td>";
						
						echo"</tr>"
						;	
					}
					echo"</table>";
				}else
					echo "<script> setTimeout(function () { window.location.href = '../index.php'; }, 2000)</script>";
			}
		?>
	</div>
</div>
</body>
</html>