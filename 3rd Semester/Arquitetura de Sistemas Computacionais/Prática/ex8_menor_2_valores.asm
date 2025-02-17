# Ex 8: Escreva um programa que armazene no registo $t2 o menor dos 2 valores inteiros contidos nos registos $t0 e $t1

.data

.text

	# 1º - Carregar valores nos registos
	li $t0, 2   # ou addi #t0, $zero, 2
	li $t1, 4
	 
	blt $t0, $t1, t0_menor
	move $t2, $t1
	j fim
	
	t0_menor:
		move $t2, $t0
	
fim:
	li $v0, 10
	syscall