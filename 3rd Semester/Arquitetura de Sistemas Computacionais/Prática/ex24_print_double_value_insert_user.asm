#Ex 24: Escreva um programa que apresenta no ecrã o dobro de um número inteiro inserido através do teclado
.data
	str: .asciiz "Introduza um valor: "

.text
	la $a0, str
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	move $t0, $v0
	li $t1, 2
	mult $t0, $t1
	mflo $a0
	li $v0, 1
	syscall
	li $v0, 10
	syscall