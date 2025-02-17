# Escreva um programa que utilizando somas, multiplique 2 números inteiros armazenados nas variáveis num1 e num2,
# guardando o resultado na variável “result”

.data
	num1: .word 5
	num2: .word 10
	result: .word 0

.text
	lw $t0, num1
	lw $t1, num2
	li $t2, 0
	
	ciclo:
		beq $t0, $zero, fim     # Testa se t0 = 0, se fim salta para o fim
		add $t2, $t1, $t2	# Adiciona o t1 com 0 e guarda o resultado em t2	
		subi $t0, $t0, 1	# Subtrai o valor do t0 uma vez
		j ciclo			# Volta ao ciclo e faz tudo novamente
	
	fim:
		sw $t2, result		# Mete o valor do resultado na variável result
		li $v0, 10		# Acaba o programa
		syscall

li $v0, 10
syscall