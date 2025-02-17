#Ex:23 Escreva um programa que converta um inteiro numa string. O valor a converter pode ter vários dígitos

.data
	str: .space 10
	val: .word 1234

.text
	li $t0, 10
	lw $t1, val
	li $t2, 1
	move $t5, $t0
	
	LOOP1:
		div $t1, $t5
		mflo $t4
		beq $t4, 0, LOOP2
		add $t2, $t2, 1
		mult $t5, $t0
		mflo $t5
		j LOOP1
		
	LOOP2:
		sub $t2, $t2, 1
		div $t1, $t0
		mfhi $t4
		mflo $t1
		addi $t4, $t4, 48
		sb $t4, str($t2)
		beq $t2, 0, FIM
		j LOOP2
	
	FIM:
		la $a0, str
		li $v0, 4
		syscall
		
		li $v0, 10
		syscall
		syscall