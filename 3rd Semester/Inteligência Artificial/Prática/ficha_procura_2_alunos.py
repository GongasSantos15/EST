import copy
import math

# Configuração do puzzle que se pretende obter
goal=[[0, 0], [0, 1], [0, 2], # - 1 2
      [1, 0], [1, 1], [1, 2], # 3 4 5
      [2, 0], [2, 1], [2, 2]] # 6 7 8

puzzle_inicial_1 = [[0, 1], [0, 0], [0, 2], # 1 - 2
                    [2, 0], [1, 2], [2, 1], # 6 7 4
                    [1, 1], [1, 0], [2, 2]] # 3 5 8

puzzle_inicial_2 = [[0, 1], [0, 0], [0, 2], # 1 _ 2
                    [2, 1], [1, 1], [2, 2], # 8 4 6
                    [1, 2], [2, 0], [1, 0]] # 7 3 5

puzzle_inicial_3 = [[0, 0], [2, 1], [1, 1], # - 3 7
                    [0, 1], [1, 0], [1, 2], # 4 2 5
                    [2, 0], [0, 2], [2, 2]] # 6 1 8

node_inicial = {'estado': puzzle_inicial_3,
                'prog': 'none',
                'desc': '',
                'prof': 0,
                'g': 0,
                'h': 22,
                'f':22}

# Heurística definida com base no nº de peças fora do lugar, que tem como parâmetro um nódo da árvore
def heuristica1(n):
  
  # Puzzle
  puzzle = n['estado']  
  
  # Variável contador para contar quantas peças estão fora do lugar
  contador = 0
  
  # Para cada puzzle[i] != goal[i] (peça no puzzle inicial a comparar com a peça no puzzle goal), incrementar a variável contador
  for i in range(len(puzzle)):
    if puzzle[i] != goal[i]:
      contador += 1

  # Retornar o número de peças fora do lugar
  return contador
  
print(heuristica1(node_inicial))

# Heurística definida com base nas distâncias de cada uma das peças à posição objectivo, que tem como parâmetro um nódo da árvore
# Utiliza como medida de distância a distânca de Manhattan

# Recebe as coordenadas dos 2 pontos
def dist_manhattan(x1, y1, x2, y2):
  return abs(x1 - x2) + abs(y1 - y2)

def heuristica2(n): 
  
  puzzle = n['estado']
  soma_dist = 0
  
  for i in range(len(puzzle)):
    #                     linha -> x1   coluna -> y1  linha -> x2 coluna -> y2  
    dist = dist_manhattan(puzzle[i][0], puzzle[i][1], goal[i][0], goal[i][1])
    soma_dist += dist
  
  # Retornar o valor da distância de manhattan
  return soma_dist

print(heuristica2(node_inicial))

"""
1. Define uma função mover_baixo que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de 
trocar o espaço em branco com a peça na célula a baixo, ou False caso o movimento não seja possível. 
"""

def mover_baixo(n):
  
  puzzle = n['estado']
  profundidade_no = 0
  
  # Ver se o movimento é possível
  # Se for possível fazer de forma semelhante ao q foi feito antes
  if puzzle[0][0] == int(math.sqrt(len(puzzle)))-1:
    return False
  else:
    # Fazer deepcopy de um nódo
    novo = copy.deepcopy(n)
    pai = 
    
    novo, pai = pai, novo
    return novo  
  
  return

print(mover_baixo(node_inicial))
    
"""
2. Define uma função mover_cima que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de trocar 
o espaço em branco com a peça na célula a cima, ou False caso o movimento não seja possível. 
"""
def mover_cima(n):
  return
    
"""
3. Define uma função mover_esquerda que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de 
trocar o espaço em branco com a peça na célula à esquerda, ou False caso o movimento não seja possível.
"""
def mover_esquerda(n):
  return
        
"""
4. Define uma função mover_direita que deve receber como argumento um puzzle definido como descrito em cima e devolver um novo puzzle que resulta de 
trocar o espaço em branco com a peça na célula à direita, ou False caso o movimento não seja possível. 
"""
def mover_direita(n):
  return
        
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
  

