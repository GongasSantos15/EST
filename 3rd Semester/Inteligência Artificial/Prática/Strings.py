
'''EXERCÍCIOS PYHTON STRINGS
PROBLEMA 2.1 - Associe a uma variável um objecto do tipo cadeia de caracter que contenha
o seu nome inicial, do meio e final separados por vírgulas'''

nome = 'Gonçalo, Santos'

# a) Utilizando o operador de fatiamento (slice) imprima o seu primeiro nome

primeiro_nome = nome[:7]
print('O primeiro nome é: ' + primeiro_nome + '\n')

#b) Utilizando o operador de fatiamento (slice) imprima o seu último nome

ultimo_nome = nome[9:]
print ('O último nome é: ' + ultimo_nome + '\n' )

#c) Utilizando o operador de fatiamento (slice) e de concatenação (+) transforme o seu nome de modo a ter a forma "último nome, primeiro nome"
print(ultimo_nome + ', ' + primeiro_nome + '\n')

'''PROBLEMA 2.2 - Escreva um programa que dada uma cadeia de caracteres mostre no écran todos os seus prefixos. Por exemplo, dada a cadeia de caracteres
   ’UJI’, no écran deve aparecer:'''
cadeia_caracteres = input('Escreva uma palavra: ')

for i in range (1, len(cadeia_caracteres)+1):
    print('Letra(s) :' + cadeia_caracteres[:i])
print('\n')

''' PROBLEMA 2.3 - Escreva um programa que peça uma cadeia de caracteres e apresente no écrã todas as suas sub cadeias de caracteres de comprimento n. 
    Por exemplo, para sub cadeias de comprimento 3, e dada ainda a cadeia de caracteres ’Programming’, no écrã deve surgir'''
cadeia_caracteres_2 = input('Escreva uma palavra: ')
comprimento = int(input('Agora escolha o comprimento a fatiar: '))

print(type(comprimento))
cadeia_caracteres_2[::3]


for i in range(len(cadeia_caracteres_2)):
    subcadeia = cadeia_caracteres_2[i:i + comprimento]
    if len(subcadeia) == comprimento: 
        print(subcadeia)
print('\n')

""" 
SOLUCAO

def sub_cadeias(n):
   print("Introduza uma palavra: ")
   palavra = input()

   for i in range(len(palavra)-n+1):
      print(palavra[i:i+n])
      
"""

''' PROBLEMA 2.4 - Escreva um programa que, dadas duas cadeia de caracteres, determine se a primeira ocorre na segunda em qualquer posição '''
cadeia_caracteres_3 = input('Escreva a 1ª cadeia de caracteres: ')
cadeia_caracteres_4 = input('Escreva a 2ª cadeia de caracteres: ') 

if cadeia_caracteres_3 in cadeia_caracteres_4:
    print('A primeira palavra ocorre na segunda!')
    
"""
SOLUCAO

def ocorrencia(palavra1, palavra2):
   return palavra1 in palavra2
"""    
        
''' PROBLEMA 2.5 - Escreva um programa que, dadas duas cadeia de caracteres, determine se a primeira é um prefixo da segunda. 
Exemplo: ’hidro’ é um prefixo de ’hidroplanador’.'''
cadeia_caracteres_5 = input('Escreva a 1ª cadeia de caracteres: ')
cadeia_caracteres_6 = input('Escreva a 2ª cadeia de caracteres: ') 

if cadeia_caracteres_5 == cadeia_caracteres_6[0:len(cadeia_caracteres_5)]:
    print('A palavra %s é um prefixo de %s' % (cadeia_caracteres_5, cadeia_caracteres_6))
    
''' PROBLEMA 2.6 - Uma sequência de caracteres diz-se uma capicua (ou palíndrome), se for igual lida da esquerda para a direita ou das direita para a 
esquerda. Exemplo: ’madam’. Desenvolva um programa que permita determinar se uma cadeia de caracteres é, ou não, uma capicua. Existem vários modos de 
resolver esta questão. Escolha o que lhe parecer mais simples e natural.'''

capicua = input('Escreva a palavra: ')

if capicua == capicua[::-1]:
    print('A palavra %s é uma capicua' % (capicua))
else:
    print('A palavra %s não é uma capicua' % (capicua))
        
'''PROBLEMA 2.7 - Define-se por distância de Hamming entre duas cadeias de caracteres como sendo o número de posições em que divergem. 
Implemente um programa que lhe permita calcular a distância de Hamming entre duas sequências. A sua solução prevê o caso das sequência terem comprimentos 
diferentes?'''

def dist_hamming(palavra1, palavra2):
    size1 = len(palavra1)
    size2 = len(palavra2)
   
   # Verificar qual o comprimento da palavra maior
    if size1 > size2:
        dist = size1 - size2
        lim = size2
    else:
        dist = size2 - size1
        lim = size1
        
    for i in range(lim):
        if palavra1[i] != palavra2[i]:
            dist += 1
        return dist

""" PROBLEMA 2.8 - Desenvolva um programa que substitua as ocorrências de vogais numa cadeia de caracteres por espaços em branco """
def substitui_vogais(palavra):
    vogais="aeiouAEIOU"
    
    # String para armazenar os caracteres
    nova = str()
    
    for x in palavra:
        if x in vogais:
            nova += " "
        else:
            nova += x
        
    return nova

""" PROBLEMA 4.2 - Os numerologistas afirmam que são capazes de determinar os traços de personalidade de uma pessoa através do valor numérico do seu 
nome. Este valor obtém-se somando o índice alfabético dos caracteres do nome. Por exemplo, o ’a’ vale 1 , o ’b’, vale 2, . . . , o ’z’ vale 26. Escreva 
um programa que determine o valor numérico de um nome formado por uma só palavra contendo apenas caracteres minúsculos. """
