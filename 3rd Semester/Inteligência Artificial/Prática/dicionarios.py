dicionario = {
    "brand": "Ford",
    "model": "Mustang",
    "year": 1964
}

# Acesso às chaves do dicionário
for c in dicionario:
    print("Chave: " + c)
    
#Acesso aos valores do dicionário
for c in dicionario:
    print(dicionario[c])
    
          # OU
          
for v in dicionario.values():
    print(v)
    
# Acesso às chaves e aos valores
for c in dicionario:
    print(c, dicionario[c])
    
          # OU

for c,v in dicionario.items():
    print(c, v)
    
# Atualização do valor da chave
dicionario['brand'] = 'fiat'
print(dicionario)

# Ao fazer uma atribuição a uma chave não existente, a chave vai ser criada e via constar mais uma entrada no dicionário
dicionario['matricula'] = 'rz-gb-23'
print(dicionario)
    
# Retirar uma chave sabendo a mesma
dicionario.pop('matricula')
print(dicionario)

# Retirar o último elemento do dicionario
dicionario.popitem()
print(dicionario)

# Apagar o elemento sem o retornar
dicionario['matricula'] = 'rz-gb-23'


del dicionario['matricula']
print(dicionario)

# Apagar os pares chave:valor do dicionário, mas sem o apagar (LIMPA O DICIONARIO)
dicionario.clear()
print(dicionario)

# Apagar o dicionario
del dicionario

def metabolismoBasal(peso, altura, idade, género):
    # Caso seja homem
    if genero == 'm':
        return 66 + (6.3 * peso) + (12.9 * altura) - (6.8 * idade)
    else:
        return 65 + (4.3 * peso) + (4.7 * altura) - (4.7 * idade)
    
# EXERCÍCIO 1
def dicionarioMet_Basal(d):
    basal = dict()
    for bi in d:
        pessoa = d[bi]
        
        # for bi, pessoa in d.items:
        basal[bi] = metabolismoBasal(pessoa["peso"], pessoa["altura"], pessoa["idade"], pessoa["sexo"])
    return basal

# EXERCÍCIO 2