"""
1. Define uma função mover_baixo que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de trocar o
espaço em branco com a peça na célula a baixo, ou False caso o movimento não seja possível. 
"""

def mover_baixo(puzzle):
    
    x_espaco_branco = puzzle[0][0]
    y_espaco_branco = puzzle[0][1]
    
    x_peca_baixo = x_espaco_branco + 1
    y_peca_baixo = y_espaco_branco
    
    celula_baixo = [x_peca_baixo, y_peca_baixo]
    
    if(x_peca_baixo <= 2):
        idx = puzzle.index(celula_baixo)
        puzzle[0], puzzle[idx] = puzzle[idx], puzzle[0]
    else:
        return False
    return puzzle
    
# Teste
puzzle_inicial = [[1,1], [2, 0], [2, 2], [1, 2], [0, 0], [1, 0], [0, 2], [0, 1], [2,1]]
print("O puzzle inicial é: " + str(puzzle_inicial))
print("\n")

print("Movendo o espaço em branco para BAIXO: " + str(mover_baixo(puzzle_inicial)))
print("Movendo o espaço em branco para BAIXO: " + str(mover_baixo(puzzle_inicial)))

"""
Define uma função mover_cima que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de trocar o espaço em 
branco com a peça na célula acima, ou False caso o movimento não seja possível.
"""
def mover_cima(puzzle):
    x_espaco_branco = puzzle[0][0]
    y_espaco_branco = puzzle[0][1]
    
    x_peca_cima = x_espaco_branco - 1
    y_peca_cima = y_espaco_branco
    
    celula_cima = [x_peca_cima, y_peca_cima]
    
    if(x_peca_cima >= 0):
        idx = puzzle.index(celula_cima)
        puzzle[0], puzzle[idx] = puzzle[idx], puzzle[0]
    else:
        return False
    return puzzle    

# Teste
print("\n")
print("Movendo o espaço em branco para CIMA: " + str(mover_cima(puzzle_inicial)))

def mover_esquerda(puzzle):
    x_espaco_branco = puzzle[0][0]
    y_espaco_branco = puzzle[0][1]
    
    x_peca_esquerda = x_espaco_branco
    y_peca_esquerda = y_espaco_branco - 1
    
    celula_esquerda = [x_peca_esquerda, y_peca_esquerda]
    
    if(y_peca_esquerda >= 0):
        idx = puzzle.index(celula_esquerda)
        puzzle[0], puzzle[idx] = puzzle[idx], puzzle[0]
    else:
        return False
    return puzzle        

# TESTES
print("\n")
print("Movendo o espaço em branco para ESQUERDA: " + str(mover_esquerda(puzzle_inicial)))

"""
Define uma função mover_direita que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de
trocar o espaço em branco com a peça na célula à direita, ou False caso o movimento não seja possível.
"""
def mover_direita(puzzle):
    x_espaco_branco = puzzle[0][0]
    y_espaco_branco = puzzle[0][1]
    
    x_peca_direita = x_espaco_branco
    y_peca_direita = y_espaco_branco + 1
    
    celula_direita = [x_peca_direita, y_peca_direita]
    
    if(y_peca_direita <= 2):
        idx = puzzle.index(celula_direita)
        puzzle[0], puzzle[idx] = puzzle[idx], puzzle[0]
    else:
        return False
    return puzzle        

# TESTES
puzzle_inicial = [[1,1], [2, 0], [2, 2], [1, 2], [0, 0], [1, 0], [0, 2], [0, 1], [2,1]]

print("\n")
print("Movendo o espaço em branco para DIREITA: " + str(mover_direita(puzzle_inicial)))