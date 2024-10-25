"""TEORICA""" 

#fruits = ['apple', 'banana', 'cherry', 'orange']
#cars = ['Ford', 'BMW', 'Volvo']

## COPY
#print(fruits.copy())          # OUTPUT: ['apple', 'banana', 'cherry', 'orange']

## COUNT
#print(fruits.count('cherry')) # OUTPUT: 1

## EXTEND
#fruits.extend(cars)
#print(fruits)                 # OUTPUT: ['apple', 'banana', 'cherry', 'orange', 'Ford', 'BMW', 'Volvo']

## INDEX
#print(fruits.index("cherry")) # OUTPUT: 2

## INSERT
#fruits.insert(1, "kiwi")      
#print(fruits)                 # OUTPUT: ['apple', 'kiwi', 'banana', 'cherry', 'orange', 'Ford', 'BMW', 'Volvo']

## POP
#fruits.pop(1)
#print(fruits)                 # OUTPUT: ['apple', 'banana', 'cherry', 'orange', 'Ford', 'BMW', 'Volvo']

## REMOVE
#fruits.remove("banana")
#print(fruits)                 # OUTPUT: ['apple', 'cherry', 'orange', 'Ford', 'BMW', 'Volvo']

## REVERSE
#fruits.reverse()
#print(fruits)                 # OUTPUT: ['Volvo', 'BMW', 'Ford', 'orange', 'cherry', 'apple']

"""PRATICA"""
# EXERCÍCIO 1: my_count(lst, el) - conta o número de vezes em que el surge na lista list

def my_count(lst, el):
    count = 0

    for e in lst:
        if e == el:
            count += 1

    return count

l1 = [1, 2, 3, 4, 5, 6, 2, 2, 3, 1]
el = 2

print("O nº de vezes que " + str(el) + " aparece na lista " + str(l1) + " é igual a " + str(my_count(l1, el)))



# EXERCÍCIO 2:  my_reverse(lst) – devolva uma lista que é o inverso da lista lst que recebe como argumento
def my_reverse(lst):
    return lst[::-1]

l1 = [1, 2, 3, 4, 5, 6, 2, 2, 3, 1]
print("A lista invertida é: " + str(my_reverse(l1)))



# EXERCÍCIO 3: função calcula-menor – deve devolver a idade menor
def calcula_menor(idades_lst):
    menor = idades_lst[0]
    for idade in idades_lst:
        if idade < menor:
            menor = idade
    return menor

idades_lst = [18, 21, 23, 40, 50, 21, 16, 10]
print("A idade menor é igual a " + str(calcula_menor(idades_lst)))



# EXERCÍCIO 4: my_insert(indx, el, lst) – retorna o resultado de inserir el na lista lst na posição indx. Não deve alterar lst.
def my_insert(indx, el, lst):
    new = []
    
    for i in range(len(lst)):
        if i == indx:
            new.append(el)
            
        new.append(lst[i])
        
    return new

l1 = [1, 2, 3, 4, 5, 6, 2, 2, 3, 1]
print("A nova lista é: " + str(my_insert(0, 10, l1)))

# Alternativa
def my_insert1(indx, el, lst):
    new = lst
    
    new.insert(indx, el)
    
    return new

l1 = [1, 2, 3, 4, 5, 6, 2, 2, 3, 1]
print("A nova lista é: " + str(my_insert1(0, 10, l1)))

# Nova Alternativa
def my_insert2(indx, el, lst):
    return lst[:indx] + [el] + lst[indx:]

l1 = [1, 2, 3, 4, 5, 6, 2, 2, 3, 1]
print("A nova lista é: " + str(my_insert1(0, 10, l1)))



# EXERCÍCIO 5: função conta_inferiores – calcula e devolve o número de idades inferiores a uma dada como referência
def conta_inferiores(lst, ref_idade):
    count = 0
    
    for idade in lst:
        if idade < ref_idade:
            count += 1
    return count

l1 = [18, 21, 23, 40, 50, 21, 16, 10]
print("Existem " + str(conta_inferiores(l1, 23)) + " idades inferiores a 23 anos")



# EXERCÍCIO 6: Defina uma função com o nome negativo que receba como parâmetro de entrada uma lista de listas que representa uma imagem e devolva o seu 
# negativo, isto é uma nova imagem em que o branco passa a preto e o preto a branco.
def negativo(imagem):
    nova = []
    # iterar sobre as linhas
    for linha in imagem:
        nova_linha = []
        for el in linha:
            if el == 0:
                nova_linha.append(1)
            else:
                nova_linha.append(0)
        nova.append(nova_linha)
    return nova
    
imagem = [[0, 1, 0], [1, 1, 1], [0, 1, 0]]
print("A nova imagem é: " + str(negativo(imagem)))
    



# EXERCÍCIO 7: Escreva uma função, de nome filtra_lista, que recebe uma lista de números e um limite e devolve uma nova lista constituída apenas pelos
# números de valor inferior ao limite recebido.
def filtra_lista(lista, limite):
    return [x for x in lista if x < limite]

# lista de números inteiros
l1 = [1, 2, 3, 4, 5]

# lista de números inteiros
l2 = [5, 6, 7, 8, 9, 10, 4]
print("A lista é: " + str(filtra_lista(l1, 4)))
        
    
    
    
    
    
