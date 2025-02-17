"""
1. Desenvolva uma função de nome conta_palavras que dada uma frase devolve um conjunto composto por tuplos em que o 1º elemento é uma string e o 2º 
é o nº de vezes que essa string aparece na frase
"""

""" Minha forma de resolução: """
def my_conta_palavras(frase):
    
     # Divide a frase em palavras
     palavras = frase.split(" ")
     
     # Contador para as palavras
     contador = {}
     
     # Ciclo para percorrer as palavras todas e contar quantas vezes aparecem na frase
     for palavra in palavras:
          
          # Se a palavra estiver na frase mais de uma vez incrementar o valor, contador, senão manter
          if palavra in contador:
               contador[palavra] += 1
          else:
               contador[palavra] = 1
         
     # Percorrer cada palavra no contador
     resultado = {(palavra, contador[palavra]) for palavra in contador}
     
     # Retornar o resultado
     return resultado

""" Forma de resolução do Professor: """
def conta_palavras(frase):
    
     # Divide a frase em palavras
     palavras = frase.split(" ")
     
     # Insere no conjunto palavras_unicas as palavras devolvidas pelo split anterior
     palavras_unicas = set(palavras)
     
     # Conjunto resultado
     contagem = set()
     
     # Para cada palavra no conjunto palavras_unicas, contar quantas vezes tem uma determinada palavra na frase e adicionar ao conjunto contagem a 
     # palavra e a respetiva contagem da mesma
     for palavra in palavras_unicas:
          count = palavras.count(palavra)
          contagem.add((palavra, count))
     
     # Retornar o resultado
     return contagem

# TESTES          
frase = "A Ana João deu à Ana Maria uma caneta"

my_resultado = my_conta_palavras(frase)
resultado = conta_palavras(frase)

print(my_resultado)
print(resultado)         
    
     
"""
2. Desenvolva uma função de nome conta_vogais que dado um texto conta quantas palavras presentes no mesmo começam por uma vogal.
"""
def conta_vogais(frase):
     
     # Definir as vogais e o contador
     vogais = "aeiouAEIOU"
     contador = 0
     palavras = frase.split(" ")
     
     # Se cada palavra estiver no conjunto vogais, incrementar o contador
     for palavra in palavras:
          if palavra[0] in vogais:
               contador += 1
     
     # Retornar o contador  
     return contador

# TESTES
frase = "A UC de Inteligência Artifical tem duas components"
resultado = conta_vogais(frase)
print("\n")
print("Esta frase tem " + str(resultado) + " vogais")


"""
3. Defina uma função de nome ordena_por_idade que dada uma lista de pessoas representadas por um tuplo do tipo (nome, idade) a ordena por ordem crescente
das idades. A função deve retornar a lista ordenada
"""

""" Forma mais básica de resolver o exercício """
def ordena_por_idade(pessoas):
     
     # Crie um for em que percorre a lista de pessoas
     for i in range(len(pessoas)):
          minimo = i
          
          # Percorrer a lista de pessoas e verificar se a idade dessa pessoa é menor do que a pessoa seguinte, se sim o mínimo passa a ser a idade 
          # dessa mesma pessoa, se sim trocar a posição das 2 pessoas comparadas na lista
          for j in range(i+1, len(pessoas)):
               if pessoas[j][1] < pessoas[minimo][1]:
                    minimo = j
          
          pessoas[i], pessoas[minimo] = pessoas[minimo], pessoas[i]
     
     return pessoas

""" Utilizando o sort e a key """
def ordena_por_idade_v2(pessoas):
     return sorted(pessoas, key = lambda p: p[1])

# TESTE
pessoas = [('Maria', 18), ('Ju',23), ('Teresa',20), ('Tomás', 17)]
print("\n")
print(ordena_por_idade(pessoas))
print(ordena_por_idade_v2(pessoas))


"""
4. Defina uma função elimina_repetidos que recebe uma lista de números, onde cada um pode aparecer mais do que uma vez e devolve uma nova lista 
constituída pelos números presentes na lista de entrada, mas sem repetições.
"""
def elimina_repetidos(numeros):
     # Set elimina os repetidos
     return list(set(numeros))

# TESTE
numeros = [1, 1, 21, 2333, 3, 3, 3, 3, 44, 4, 44 ,4]
print(elimina_repetidos(numeros))
print("\n")

"""
5. Defina uma função atualiza_idade que recebe como entrada uma lista de tuplos como os da questão anterior (nome, idade), e apresenta essa lista ao 
utilizador conforme se pode ver no exemplo em baixo. Questiona-o qual o nº da pessoa para a qual pretende alterar a idade e de seguida diz qual é a 
nova idade. A função deve devolver a lista atualizada.
"""
def atualiza_idade(pessoas):
     for i in range(len(pessoas)):
          
          # Semelhante ao printf do Java
          print(f"{i} - {pessoas[i][0]}")
     
     # Dar ao utilizador a opção de escolher a opção 
     opcao = int(input("\nQual o nº da pessoa para a qual pretende alterar a idade? "))
     
     nova_idade = int(input("Qual é a nova idade? "))
     
     pessoas[opcao] = (pessoas[opcao][0], nova_idade)
     
     return pessoas
     
pessoas = [('Maria', 18), ('Ju',23), ('Teresa',20), ('Tomás', 17)]
print(atualiza_idade(pessoas))