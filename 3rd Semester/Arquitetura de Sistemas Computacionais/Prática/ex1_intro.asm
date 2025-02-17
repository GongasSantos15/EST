.data
.text
addi $a0, $zero, 'A'
li $v0, 11
syscall
addi $a0, $zero, 'u'
li $v0, 11
syscall
addi $a0, $zero, 'l'
li $v0, 11
syscall
addi $a0, $zero, 'a'
li $v0, 11
syscall
addi $a0, $zero, 's'
li $v0, 11
syscall
			# 11 -> Print de um caractere
li $v0, 10              # 10 -> Significa sair do programa
syscall
