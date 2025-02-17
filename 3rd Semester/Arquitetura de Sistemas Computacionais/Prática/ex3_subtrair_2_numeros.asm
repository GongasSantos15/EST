# Escreva um programa que efetue a subtração de 2 números inteiros de 32 bits armazenados nas variáveis 
# num1 e num2. O resultado da operação deve ser armazenado na variável result

.data 
	num1: .word 10		# Carrega o nº 10 para a variável num1
	num2: .word 3		# Carrega o nº 3 para a variável num2
	result: .word 0		# Carrega o nº0 para o resultado. Inicializado a 0

.text
	lw $t1, num1		# Carregar o nº 10 para o registo $t1
	lw $t0, num2		# Carregar o nº 3 para o registo $t0
	
	sub $t2, $t1, $t0	# Subtrair os 2 valores
	
	sw $t2, result		# Guardar o resultado da subtração na variável result

li $v0, 10
syscall