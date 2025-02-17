import copy
import math

"""
1. Define uma função mover_baixo que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de 
trocar o espaço em branco com a peça na célula a baixo, ou False caso o movimento não seja possível. 
"""

def mover_baixo(puzzle):
    if puzzle[0][0] == int(math.sqrt(len(puzzle)))-1:
        return False
    else:
        # Fazer deepcopy de um puzzle novo com base no puzzle dado como argumento
        novo = copy.deepcopy(puzzle)
        ind = puzzle.index([puzzle[0][0] + 1, puzzle[0][1]])
        novo[0], novo[ind] = novo[ind], novo[0]
        return novo
    
"""
2.Define uma função mover_cima que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de trocar
o espaço em branco com a peça na célula a cima, ou False caso o movimento não seja possível. 
"""

def mover_cima(puzzle):
    if puzzle[0][0] == 0:
        return False
    else:
        # Fazer deepcopy de um puzzle novo com base no puzzle dado como argumento        
        novo = copy.deepcopy(puzzle)
        ind = puzzle.index([puzzle[0][0] - 1, puzzle[0][1]])
        novo[0], novo[ind] = novo[ind], novo[0]
        return novo
    
"""
3.Define uma função mover_esquerda que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de 
trocar o espaço em branco com a peça na célula à esquerda, ou False caso o movimento não seja possível.
"""

def mover_esquerda(puzzle):
    if puzzle[0][1] == 0:
        return False
    else:
        # Fazer deepcopy de um puzzle novo com base no puzzle dado como argumento        
        novo = copy.deepcopy(puzzle)
        ind = puzzle.index([puzzle[0][0],puzzle[0][1] - 1])
        novo[0], novo[ind] = novo[ind], novo[0]
        return novo
        
"""
4.	Define uma função mover_direita que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta 
de trocar o espaço em branco com a peça na célula à direita, ou False caso o movimento não seja possível. 
"""

def mover_direita(puzzle):
    if puzzle[0][1] == int(math.sqrt(len(puzzle))) - 1:
        return False
    else:
        # Fazer deepcopy de um puzzle novo com base no puzzle dado como argumento        
        novo = copy.deepcopy(puzzle)
        ind = puzzle.index([puzzle[0][0],puzzle[0][1] + 1])
        novo[0], novo[ind] = novo[ind], novo[0]
        return novo
        
"""
5.	Defina uma função imprime_puzzle que deve receber como argumento um puzzle definido como uma lista de listas e deve retornar o respétivo puzzle 
sob a forma de uma string, tal como mostra a figura em baixo. Antes de retornar a string a função deve fazer print da mesma.
"""

def imprime_puzzle(puzzle):
    p_str = str()
    for i in range(int(math.sqrt(len(puzzle)))):
        for j in range(int(math.sqrt(len(puzzle)))):
            ind = puzzle.index([i,j])
            if (ind == 0):
                p_str = p_str + " -"
            else:
                p_str = p_str + " " + str(ind)
        p_str = p_str + '\n'
    print(p_str)
    return p_str

if __name__ == '__main__':
    p = [[1,1], [2, 0], [2, 2], [0, 0], [1, 2], [1, 0], [0, 2], [0, 1], [2,1]]
    print("Puzzle Inicial:")
    imprime_puzzle(p)
    n = mover_baixo(p)
    if n : 
        print("Movendo para baixo:")
        imprime_puzzle(n)
    
    n = mover_cima(p)
    if n : 
        print("Movendo para cima:")
        imprime_puzzle(n)
        
    n = mover_esquerda(p)
    
    if n : 
        print("Movendo para a esquerda:")
        imprime_puzzle(n)    
        
    n = mover_direita(p)
    if n : 
        print("Movendo para a direita:")
        imprime_puzzle(n)    
   
    