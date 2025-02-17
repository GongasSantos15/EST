# Escreva um programa que armazene no registo $t3 o maior dos 3 valores contidos nos registos $t0, $t1 e $t2
.data

.text
	li $t0, 1
	li $t1, 2
	li $t2, 3
	
	bgt $t0, $t1, t0_maior_t1    	# Comparação de t0 com t1, se saltar é pq t0 é maior que t1, senão t1 é maior que t0
	bgt $t1, $t2, t1_maior       	# Comparação de t1 (porque é maior que t0) com t2, salta se t1 for o maior
	j t2_maior		     	# Senão t2 é o maior dos 3 números
	
	t0_maior_t1:
		bgt $t0, $t2, t0_maior  # Como t0 é maior que t1, vamos comparar com t2 -> se o t0 for maior salta
		j t2_maior              # Senão t2 é o maior dos 3 números
		
	t0_maior:
		move $t3, $t0		# Como t0 é o maior, copia-se o número para o registo t3 (resposta)
		j fim			# Acaba-se o programa
	
	t1_maior:
		move $t3, $t1		# Como t1 é o maior, copia-se o número para o registo t3 (resposta)
		j fim			# Acaba-se o programa
	
	t2_maior:
		move $t3, $t2		# Como t2 é o maior, copia-se o número para o registo t3 (resposta)
		j fim			# Acaba-se o programa

fim: 
	li $v0, 10
	syscall