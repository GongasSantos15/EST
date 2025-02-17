# EX 18: Escreva um programa que, dado um array de 5 elementos do tipo .word altere os 8 bits menos significativos da
# seguinte forma:
	# b7, b6, b5, b4, b3 b2, b1, b0 -> 0, 0, b5, nb4, 1, 1, b1, nb0
	
# Nota: Os outros bits devem ser colocados a 0

.data 
	array: .word 257, 258, 3, 4, 5
.text
	addi $t1, $zero, 5			# Comprimento do Array
	move $t2, $zero				# Contador
	li $t3, 0				# Ponteiro
	
	# MÁSCARAS
	addi $t5, $t5, 0x3F # 001111111		# Máscara para usar com o AND nos 2 primeiros bits (Colocar os bits a 0)
	addi $t6, $t6, 0x0C # 00001100		# Máscara para usar com o OR nos bits 3 e 4 (Colocar os bits a 1)
	addi $t7, $t7, 0x11 # 00010001		# Máscara para usar com o XOR nos bits 0 e 4 (Negação dos bits)
	
	LOOP:
		beq, $t2, $t1, FIM
		lw $t4, array($t3)
		
		# Operação com Máscaras
		and $t4, $t4, $t5
		or $t4, $t4, $t6
		xor $t4, $t4, $t7
		
		sw $t4, array($t3)
		addi $t3, $t3, 4
		addi $t2, $t2, 1
		j LOOP
		
	FIM:
		li $v0, 10
		syscall