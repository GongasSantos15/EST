<html>
<head>
<style>
  body{
    background-image:url(../../media/imgs_sistema/fundoLogin.jpg);
    background-position: top center;
  }
  
  #login-box{
    background-color:#A9F5A9;
    width:380px;
    height:600px;
    margin: 140px auto 0px;
    overflow:hidden;
    box-shadow:0px 0px 5px #6F6666;
  }
  
  #login-cabecalho{
    background-color:#088A29;
    height:50x;
    border-bottom:2px solid #BDBDBD;
    text-align:center;
    font: bold 20px/50px sans-serif;
    color: white;
  }
  
  .input-div{
    margin:20px;
    padding:5px;
    font: bold 14px sans-serif;
    color:#0B6121;
   }
 
  .input-div input{
    width:325px;
    height:35px;
    padding-left:7px;
    font: normal 13px sans-serif;
    color:#0B6121;
  }
  #input-pass{
    margin-top:-15px;
  }
  #input-user{
    margin-top:10px;
  }
  
  #acoes{
    width:330px;
    margin:25px;
  }
  
  #registo{
    float:left;
    margin-top:-10px;
  }
  
  #volta{
	float:left;
    margin-top:-10px;
  }
  
  input[type=submit]{
    float:right;
    background-color:#088A29;
    padding: 10px 50px;
    margin-top:-15px;
    font: bold 13px sans-serif;
    color:white;
    box-shadow:2px 2px 5px #6F6666;
    cursor:pointer;
    border:0px;
  }
  
  input[type=submit]:hover{
    box-shadow:1px 1px 5px #6F6666;
  }
  
</style>
<body>

	<?php
		session_start();


		
        if(isset($_SESSION["user"]))
			echo "<script> setTimeout(function () { window.location.href = '../Utilizador/PgUtilizador.php'; }, 0)</script>";

	?>
	
	<div id="login-box">
	  <div id="login-cabecalho">Registo 3vago</div>
	  
	  <form action="registo.php" method="POST">

        <div class="input-div" id="input-user">
            Nome de utilizador:
            <input type="text" name="user" required/>
        </div> 
        
        <div class="input-div" id="input-user">
            Email:
            <input type="text" name="email" required/>
        </div> 
        
        <div class="input-div" id="input-pass">
            Password:
            <input type="password" name="pass" required/>
        </div>
        
        <div class="input-div" id="input-pass">
            Confirmar Password:
            <input type="password" name="confirm-pass" required/>
        </div>

        <div class="input-div" id="input-user">
            Morada:
            <input type="text" name="address" required/>
        </div> 

        <div class="input-div" id="input-user">
            Telemóvel:
            <input type="text" name="phone-number" required/>
        </div> 
		  
        <!--=====================Registo=====================-->
		  
		  <div id="acoes">
			<input type="submit" value ="Registo">
			<br><div id = "volta"><a href="../pagina_inicial.php">Página Principal</a></div>
		  </div>
	  </form>
	</div>
 
</body>	
</html>