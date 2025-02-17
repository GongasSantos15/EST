""" Frequência 2"""
# Defina uma função mais_frequente que recebe como argumento uma lista e devolve um tuplo que tem como 1º argumento o elemento mais frequente e como segundo
# elemento o nº de vezes que esse elemento aparece

def mais_frequente(lista):
    
    contador = 0
    valor_mais_frequente = 0
    
    for elemento in lista:
        frequencia_atual = lista.count(elemento)
        if(frequencia_atual > contador):
            contador = frequencia_atual
            valor_mais_frequente = elemento
    
    return tuple([valor_mais_frequente, contador])
        
    
print(mais_frequente([1, 2, 3, 3, 3, 3, 4]))

# Defina uma funcao nao_vogal que dada uma determinada frase, devolve o nº de palavras que acabam numa consoante
def nao_vogal(frase):
    vogais = "AEIOUaeiou"
    palavras = frase.split()
    contador = 0
    
    for palavra in palavras:
        if palavra[-1] not in vogais:
            contador += 1
            
    return contador

print(nao_vogal("A frase eu gosto do filme senhor dos aneis"))

# Escreva uma função que dada uma lista de strings retorne a de maior tamanho. Use compreensão de listas
def maior_string(lista):
    return [string for string in lista if len(string) > len(max(lista))]

print(maior_string(["maça", "banana", "cereja", "kiwi"]))
