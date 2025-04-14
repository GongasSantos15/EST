<?php
	//variavel que abre o ficheiro
	$fp = fopen("lusiadas.txt", "a");

	//Escrever no ficheiro aberto, com uma dada linha
	fprintf($fp, "\nCessem do sábio Grego e do Troiano\n
As navegações grandes que fizeram;
Cale-se de Alexandro e de Trajano
A fama das vitórias que tiveram;
Que eu canto o peito ilustre Lusitano,
A quem Neptuno e Marte obedeceram:
Cesse tudo o que a Musa antígua canta,
Que outro valor mais alto se alevanta.");
	fclose($fp);
?>