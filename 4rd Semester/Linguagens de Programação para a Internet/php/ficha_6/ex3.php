<?php

	//Segunda estrofe dos lusiadas
	$estrofe_2a = "\nE também as memórias gloriosas
Daqueles Reis, que foram dilatando
A Fé, o Império, e as terras viciosas
De África e de Ásia andaram devastando;
E aqueles, que por obras valerosas
Se vão da lei da morte libertando;
Cantando espalharei por toda parte,
Se a tanto me ajudar o engenho e arte.\n";
	
	//variavel que abre o ficheiro
	$fp = fopen("lusiadas.txt", "r");
	
	//Enquanto há linhas lê o ficheiro em $fp
	while($linha = fgets($fp)){
		
		$fp_temp = fopen("temp.txt","a");
		
		echo $linha."<br>";
		
		if (strlen($linha) == 2 ){
			fprintf($fp_temp, $estrofe_2a);
		}
		
		fprintf($fp_temp, $linha);
	}
	
	fclose($fp);
	fclose($fp_temp);
	
	rename("lusiadas.txt","temp.txt");
?>