# Ex. 15: Escreva um programa que determine o valor máximo contido numa tabela de 10 elementos do tipo 
# word. O valor máximo deverá ser guardado em $t0

.data 
	TABELA: .word 1,2,3,4,5,8,13,52,34,9

.text
	addi $t1, $zero, 10
	move $t2, $zero	
	li $t3, 0				#lw $t0, TABELA($t3) -> Ponteiro
	lw $t0, TABELA
	addi $t3, $t3, 4
	
	# Percorrer o array de 10 elementos
	LOOP: 
		beq $t2, $t1 , FIM
		lw $t4, TABELA($t3)
		blt $t4, $t0, NAO_MAIOR 
		move $t0, $t4			
	
	# Se não for maior, passar para o elemento seguinte
	NAO_MAIOR:
		addi $t3, $t3, 4
		addi $t2, $t2, 1
		j LOOP				
	
FIM:
	li $v0, 10
	syscall
	