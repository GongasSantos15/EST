.data
.text

	li $t1, 1

		bne $t1, $zero, EL
		addi $t1, $t1, 1
		j FIM
	EL:
		addi $t1, $t1, 2
	FIM:
		li $v0, 10
		syscall
