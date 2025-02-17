# Ex. 14: Escreva um programa que inverta todos os bits dos valores contidos num array de 5 elementos
# do tipo word

.data 
	array: .word 1,2,3,4,5

.text
	addi $t0, $zero, 5
	move $t1, $zero				# Contador
	li $t2, 0				# Ponteiro
	
	# Percorrer o array de 5 elementos
	CICLO: 
		beq $t1, $t0 , FIM
		lw $t3, array($t2)
		not $t3, $t3			# Inverte os bits
		sw $t3, array($t2)		# Guarda os valores no array	
		addi $t2, $t2, 4
		addi $t1, $t1, 1			
		j CICLO					
	
FIM:
	li $v0, 10
	syscall
	