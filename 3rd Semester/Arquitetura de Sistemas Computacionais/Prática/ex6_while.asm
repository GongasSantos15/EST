.data
.text

	li $t1, 1

	CICLO:
		beq $t1, $zero, FIM
		subi $t1, $t1, 1
		j CICLO
	FIM:
		li $v0, 10
		syscall