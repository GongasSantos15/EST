# EX 17: Escreva um programa que ordene por ordem crescente os valores de um array de 5 elementos

# Implementação com o Algoritmo Bubble-Sort
.data
	array: .word 14, 1, 6, 3, 2
	
.text 
	addi $t1, $zero, 5	# Comprimento do Array
	
	
	LOOP1:
		subi $t1, $t1, 1
		beqz $t1, FIM
		li $t3, 0	# Ponteiro1
		li $t4, 4	# Ponteiro2
		move $t2, $zero
		
	LOOP2:
		beq $t2, $t1, LOOP1
		lw $t5, array($t3)
		lw $t6, array($t4)
		blt $t5, $t6, NAOMAIOR
		# Guarda os valores trocados
		sw $t5, array($t4)
		sw $t6, array($t3)
		
	NAOMAIOR:
		addi $t3, $t3, 4
		addi $t4, $t4, 4
		addi $t2, $t2, 1
		j LOOP2
	 
	FIM: 
		li $v0, 10
		syscall