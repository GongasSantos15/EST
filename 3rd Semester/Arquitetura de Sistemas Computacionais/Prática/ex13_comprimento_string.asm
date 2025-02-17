# Ex 13 : Escreva um programa que determine o comprimento de uma string terminada com o caractere nulo. O endereço inicial 
# da string é string e o resultado deverá ser armazenado em $t2

.data
	STRING: .asciiz "Arquitetura"

.text
	move $t1, $zero			# Ponteiro
	
	CICLO: 
		lb $t0, STRING($t1)			# Carregar no $t0 o 1º elemento do array STRING
		beq $t0, 0, FIM				# Comparar o caractere com 0, se for um caractere nulo, FIM
		addi $t1, $t1, 1			# Adicionar um a $t1, para continuar a percorrer o array, assim ao 
							# iniciar novamente o ciclo, carrega o elemento String($t1)
		j CICLO					# Voltar ao inicio do CICLO
	
	FIM:
		move $t2, $t1
		li $v0, 10
		syscall
	