.data 
	array: .word 1,2,3,4,5
	
.text
	li $t0, 0
	
	lw $t1, array($t0)
	addi $t0, $t0, 4
	lw $t2, array($t0)