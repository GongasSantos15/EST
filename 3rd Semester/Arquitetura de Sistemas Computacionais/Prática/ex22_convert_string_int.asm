# Ex: 22 Escreva um programa que converta uma string num inteiro. O valor final convertido deve  ser guardado na vari�vel
# VAR. Nota: A String pode conter v�rios caracteres

.data
	str: .asciiz "123"  	# asciiz porque este tipo de dados tem um caractere nulo, de paragem no final da string
	var: .word 0

.text
	li $t0, 10
	li $t1 , 0		# Recebe o caractere da string
	li $t2, 0		# Ponteiro
	lb $t1, str($t2)
	li $t3, 0
	
	# Converter a string num inteiro
	LOOP:
		subi $t1, $t1, 48		# Diferen�a entre o caractere e o n�mero ("1" e 1) 
		add $t3, $t3, $t1		
		addi $t2, $t2, 1		# Adicionar ao ponteiro
		lb $t1, str($t2)		# Buscar o elemento seguinte para $t1
		beq $t1, 0, FIM
		mult $t3, $t0			# Multiplica-se por 10 para deslocar os n�meros para a esquerda
						# tendo o n�mero 12 e percebendo que existe outro n�mero, ou seja, � de 
						# 3 algarismos, logo vai ser 120 + x
						# Para obter o 120, multiplicar o 12 * 10 = 120 e somar a seguir o x		
		mflo $t3
		j LOOP
		
	FIM:
		sw $t3, var
		li $v0, 10
		syscall