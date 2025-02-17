# Escreva um programa que efetue a adição de 2 números inteiros presentes nos registos $t0 e $t1. 
# O resultado da operação deverá ser guardado no registo $t2

.data
.text
	addi $t0, $zero, 5	# Carregar o número 5 para o registo $t0
	addi $t1, $zero, 2	# Carregar o número 2 para o registo $t1
	add $t2, $t0, $t1	# Somar os 2 números e guardar em $t2

	li $v0, 10		# Termina o programa
	syscall
	
	