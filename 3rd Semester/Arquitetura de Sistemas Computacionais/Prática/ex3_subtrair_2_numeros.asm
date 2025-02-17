# Escreva um programa que efetue a subtra��o de 2 n�meros inteiros de 32 bits armazenados nas vari�veis 
# num1 e num2. O resultado da opera��o deve ser armazenado na vari�vel result

.data 
	num1: .word 10		# Carrega o n� 10 para a vari�vel num1
	num2: .word 3		# Carrega o n� 3 para a vari�vel num2
	result: .word 0		# Carrega o n�0 para o resultado. Inicializado a 0

.text
	lw $t1, num1		# Carregar o n� 10 para o registo $t1
	lw $t0, num2		# Carregar o n� 3 para o registo $t0
	
	sub $t2, $t1, $t0	# Subtrair os 2 valores
	
	sw $t2, result		# Guardar o resultado da subtra��o na vari�vel result

li $v0, 10
syscall