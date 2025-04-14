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
		background-image:url(../media/imgs_sistema/cabecalho.png);
		border: 2px solid #0B610B;
	}
	
	.input-div{    
		margin:25px;
		float:right;
		height:150px;
	}
  
	input[type=submit]{
   
		background-color:#088A29;
		padding:20px;
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
		background-image:url(../media/imgs_sistema/logo.png);
		margin-left:20px;
		margin-top:-10px;
		width:300px;
		height:200px;
		
	}
  
	#corpo{
		
		width:1000px;
		margin: 8px auto 0px;
		background-color: #BCF5A9;
		
	}
	
	#preco{
		
		margin:-40px;
		margin-top:50px;
		color:white;
		padding:10px 20px;
		background-color:#0B610B;
		font: normal 15px sans-serif;
		text-decoration:no;
		text-align: right;
		
	}
	
	#precoSocio{
		
		font: normal 13px sans-serif;
		
	}
	
	#titulo{
		
		background-color:#0B610B;
		text-align: bottom;
		font: bold 20px sans-serif;
		color:white;
		padding:10px 20px;
		
	}
	
	#texto{
		
		text-align: left;
		font: normal 17px sans-serif;
		padding:40px 40px;
		border: 2px solid #0B610B;
	}
	
	#cab{
		
		float:left;

	}
	
	#imagem{
		
		width:200px;
		height:150px;
		border: 2px solid #0B610B;
		
	}
	
	#usable{
		height:500px;
		margin:50px;
		padding:50px;
		margin-top:-8px;
		
	}
	
	#reserva{
		margin:20px auto 20px;
		padding:15px 100px;
	}
</style>  
<body>  
	<!-- GRAFISMO CABECALHO -->
<div id="cabecalho">
    <a href='index.php'>
		<div id="logo">
		</div>
	</a>
    
    <div class= "input-div">
      
	  <?php
		ob_start();
		session_start();
	  
		if(isset($_SESSION["user"])){
			echo " 
			<div id='botao'>
				<form action='./Utilizador/logout.php'>
					<input type='submit' value='Logout'>
				 </form>
			</div>
			<div id='botao'>
				<form action='./Utilizador/PgUtilizador.php'>
					<input type='submit' value='Area Pessoal'>
				 </form>
			</div>";	
		}else {
			
			echo "
			<div id='botao'>
				<form action='./Login/PgLogin.php'>
					<input type='submit' value='Login'>
				</form>
			</div>
			<div id='botao'> 
				<form action='./Registo/PgRegisto.php'>
					<input type='submit' value='Regista-te'>
				</form>
			</div>
			";
		}
	  ?>
	  
		<div id='botao'>
			<form action='contatos.php'>
				<input type='submit' value='Contactos'>
			</form>
		</div>"
    </div>
</div>
  <!-- GRAFISMO CORPO -->
<div id="corpo">
	<?php
		if(isset($_GET['IdCabana'])){
			
			$_SESSION['IdCabana']=$_GET['IdCabana'];
			include "../base_dados/basedados.h";
			$sql = "SELECT * FROM cabana WHERE idCabana = ".$_GET['IdCabana']."";
			$retval = mysqli_query( $conn, $sql );
			
			if(! $retval ){
				die('Could not get data: ' . mysqli_error($conn));// se não funcionar dá erro
			}
			$row = mysqli_fetch_array($retval);
			$preco_socio = $row["preco_p_noite"]*0.8;
			
			echo "
			<div id='usable'>
				<div id= 'imagem'>
					<img src='../media/bungalows/".$row["img_cabana"]."' whith=200 height=150>
				</div>
				<div id='titulo'>	
					".$row["nome"]."
				</div>
				<div id= 'cab'>
					<div id='texto'>	
						".$row["Descricao"]."
						
						<div id='preco'>	
							<b>".$row["preco_p_noite"]."€ p/ noite</b>
							<div id='precoSocio'>
								Sócio: ".$preco_socio."€ p/ noite
							</div>	
						</div>
						
					</div>
					<form action='./Reserva/PgReserva.php'>
						<input type='submit' value='Reservar' id='reserva'>
					</form>
				</div>
			</div>
			";
			
		}else
			echo "<script>setTimeout(function(){ window.location.href = 'index.php'; }, 0)</script>";
	?>

<?php
	$_SESSION['IdCabana']=$_GET['IdCabana'];
	$_SESSION["origem"]="../PgDescricao.php?IdCabana=".$_GET['IdCabana'];
	$_SESSION["inc"]="./Calendario/inc.php";
	$_SESSION["dec"]="./Calendario/dec.php";
	include "./Calendario/grelha.php";	
	
?>
		
</div>
</body>

</html>
