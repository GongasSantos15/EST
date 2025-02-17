.data
 var1: .byte '+'
.text
 espera:
li $v0, 12		# 12 -> À espera de um input do utilzador, de seguida escreve no ecrã o valor desse mesmo input
syscall
move $t0, $v0
move $a0, $t0
li $v0, 11		# li -> Load Immediate
syscall
lb $a0, var1		# lb -> Load Byte
li $v0, 11   
syscall
beq $t0, 'f', fim	# beq -> Branch equal
beq $t0, 'F', fim
j espera		# j -> Jump
 fim:
li $v0, 10
syscall
