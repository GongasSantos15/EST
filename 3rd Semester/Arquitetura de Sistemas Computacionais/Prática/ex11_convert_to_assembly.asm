# Ex: 11 -> Escreva um programa que crie a seguinte estrutura condicional
	# IF(($t0 < $t1) AND ($t2 > $t3))
		# THEN FIM
	# ELSE
		# $t0 = $t0 - 1
		# $t2 = $t2 + 1
		
.data 
.text
	li $t0, 5
	li $t1, 4
	li $t2, 3
	li $t3, 6
	
	bge $t0, $t1, ELSE	# Se $t0 >= $t1 ent�o salta para o ELSE
	ble $t2, $t3, ELSE	# Se $t2 <= $t3 ent�o salta para o ELSE
	j FIM 			# Se saltar para o fim, � porque as 2 condi��es s�o verdadeiras!
	1
	ELSE:
		subi $t0, $t0, 1
		addi $t2, $t2, 1

	FIM:
		li $v0, 10
		syscall
		