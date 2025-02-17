# EX 16: Escreva um programa que, utilizando instruções SHIFT, multiplique por 4, todos os 
# todos os valores contidos num array de 6 elementos do tipo word.
# De seguida, altere o programa de modo a que os valores iniciais sejam divididos por 2,
# utilizando instruções SHIFT

.data
	array: .word 2,4,6,8,10,12
	
.text
	addi $t1, $zero, 6	# Comprimento do Array
	move $t2, $zero		# Contador
	li $t3, 0            	# Ponteiro
	
	

	CICLO:
		beq $t2, $t1, FIM    	# Comparar o contador com o comprimento do array
		lw $t4, array($t3)	# Colocar o valor do índice do array no registo $t4
		sw $t4, array($t3)	# Guardar o valor ""   ""   ""   ""  ""    ""   ""
		sll $t4, $t4, 2		# Executar o shift 2 vezes para a esquerda, para multiplicar por 4
		#srl $t4, $t4, 1	# Executar o shift 1 vez para a direita para dividir por 2
		addi $t3, $t3, 4	# Adicionar 4 para incrementar o índice pq o tipo de dados do array é word (4 bytes)
		addi $t2, $t2, 1	# Incrementar o contador em 1 unidade
		j CICLO			# Voltar a fazer o ciclo até o $t2 = $t1 (6 = 6)

	FIM:
		li $v0, 10
		syscall