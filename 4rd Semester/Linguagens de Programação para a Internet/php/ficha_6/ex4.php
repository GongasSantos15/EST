<?php
	//variavel que abre o ficheiro
	$fp = fopen("lusiadas.txt", "r");

	while($linha = fgets($fp)){
		
		$fp_temp = fopen("lusiadas.txt","r");
		
		echo $linha."<br>";
	
	}
		
?>