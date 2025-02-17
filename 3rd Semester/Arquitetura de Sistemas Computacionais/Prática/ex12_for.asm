# Ex: 12 -> Escreva um programa que incremente a variável var1 uma unidade até esta atingir o valor 10 .var1 possui inicialmente o valor 
# zero

.data
	var1: .word 0
	 
.text
	lw $t0, var1
	
	CICLO:
		beq $t0, 10, FIM	# Verificar se $t0 = 10, se sim salta para o fim do programa
		add $t0, $t0, 1		# Senão incrementa 1 unidade o registo
		sw $t0, var1		# E guarda o valor de $t0 na var1
		j CICLO			# Volta ao ciclo até que a primeira condição seja verdadeira

	FIM: 
		li $v0, 10
		syscall
