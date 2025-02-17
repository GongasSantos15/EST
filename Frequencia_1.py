""" Frequência 1: """

# Defina uma função raiz_quadrada que dada a matriz de entrada (lista de listas de igual tamanho) calcule a raiz quadrada para cada 
# valor e devolve a matriz com as respetivas raízes quadradas, ordenada por ordem decrescente dos valores presentes na sublista.
import math

def raiz_quadrada(matriz):

    for lista_interior in matriz:
        for numero in range(len(lista_interior)):
            lista_interior[numero] = int (math.sqrt(lista_interior[numero]))
    # matriz.sort(reverse = True)
    
    matriz.sort()
    matriz.reverse()
    
    return matriz

print(raiz_quadrada([[9, 9, 9], [25, 25, 25], [16, 16, 16]]))

# Defina uma função e_par que dada uma lista de valores numéricos devolve uma lista de tuplos em que o primeiro elemento do tuplo 
# deve apresentar um dos elementos da lista de entrada e o segundo uma string que deve indicar se esse elemento é par ou ímpar.
# A lista que a função retorna não deve ter elementos repetidos e deve estar ordenada por ordem crescente
def e_par(lista):
    
    novaLista = []
    
    for numero in set(lista):
        if (numero % 2 == 0):
            novaLista.append(tuple((numero, "é par")))
        else:
            novaLista.append(tuple((numero, "não é par")))
        
        novaLista.sort()
          
    return list(novaLista)
        
print(e_par([1,2,1,2,5]))

# Escreva uma função de nome primeiro_nao_repetido(str) que recebe uma string e encontra o 1º caractere não repetido dentro da string 
# Caso todos se encontrem repetidos, a função deverá devolver -1
def primeiro_nao_repetido(str):
    for char in str:
        if str.count(char) == 1:
            return char
    return -1

                
print(primeiro_nao_repetido("aasdefsd"))