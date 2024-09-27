import random
import math

""" 
LISTAGEM 1.1
5 * 6 * 10

# EXEMPLO 1.1 - Calcular o peso ideal conhecida altura e o sexo
# Usar diretamente as fórmulas e efetuar os cálculos
(72.7 * 1.81) - 58
(62.1 * 1.81) - 44.7

# Usar nomes para desginar objetos
altura_1 = 1.81
altura_2 = 1.55
(72.7 * altura_1) - 58
(62.1 * altura_2) - 44.7
"""

# Usar definições como mecanismo de abstração
def peso_homem(altura):
    return (72.7 * altura) - 58

# peso_homem(1.81)


def peso_mulher(altura):
    return (62.1 * altura) - 44.7

# peso_mulher(1.55)

# Solução de controlo if
def peso_h_m(altura, sexo):
    if sexo == 1:
        return (72.7 * altura) - 58
    else:
        return (62.1 * altura) - 44.7
"""
peso_h_m(1.81, 1)
peso_h_m(1.61, 0)
"""

# EXEMPLO 1.2 - Cálculo do Volume de uma esfera
# Assumir que o valor do pi é 3.14
def volume_esfera(raio):
    return 4.0/3.0 * 3.14 * raio ** 3

# volume_esfera(2)

# Usar o módulo math
# import math

def volume_esfera(raio):
    volume = (4.0/3.0) * math.pi * raio ** 3
    return volume

# volume_esfera(2)

# EXEMPLO 1.3 - Um jogo em que o user tenta descobrir um número definido pelo computador
# Um número fixo e uma tentativa apenas
def adivinha(numero):
    if numero == 25:
        return True
    else:
        return False
    
"""
adivinha(33) #False
adivinha(25) #True
"""

# Usar o módulo random
# import random

"""
dir(random)
help(random.randint) #Help no método radiant no módulo help
"""

def adivinha(numero):
    secreto = random.randint(0, 100)
    if numero == secreto:
        return True
    else:
        return secreto
    
# adivinha(33)

# 3 Tentativas

def adivinha(n_1, n_2, n_3):
    secreto = random.randint(0, 100)
    if n_1 == secreto:
        return True
    if n_2 == secreto:
        return True
    if n_3 == secreto:
        return True
    return secreto

# Solução mais limpa que a anterior (usar else if)
def adivinha(n_1, n_2, n_3):
    secreto = random.randint(0, 100)
    if n_1 == secreto:
        return True
    elif n_2 == secreto:
        return True
    elif n_3 == secreto:
        return True
    else:
        return secreto

# Usar ciclos while
import random
def adivinha(tenta):
    secreto = random.randint(0, 100)
    conta = 0
    while conta < tenta:
        meu_numero = input()
        if meu_numero == secreto:
            return True
        conta = conta + 1
    return secreto

# adivinha(3)

# Usar o print para dar feedback ao user
import random
def adivinha(tenta):
    secreto = random.randint(0, 100)
    conta = 0
    while conta < tenta:
        numero = int(input("O seu palpite sff: "))
        if numero == secreto:
            print("Uau, acertou!")
            return True
        else:
            if numero > secreto:
                print("Muito grande...")
            else:
                print("Muito pequeno...")
        conta = conta + 1
    print("Lamento, mas esgotou as suas tentativas.")
    return secreto

# EXEMPLO 1.4 - Cálculo da raiz quadrada de um inteiro positivo pelo método de Newton
def raiz_quadrada(a, repete):
    conta = 0
    x = float(input("Valor inicial: "))
    while conta < repete:
        x = 0.5 * (x + (a / x))
        conta = conta + 1
        print(x)
    return "Fim"

# EXERCICIO 1 - Função de valor absoluto
def absoluto(num):
    if num < 0:
        return -num
    else:
        return num
    return num

# EXERCICIO 2 - Fatorial
def fatorial(num):
    n = 1
    while num >= 1:
        n = n * num
        num = num - 1
    return n
    
# EXERCICIO 3 - É par?
def e_par(num):
    if num % 2 == 0:
        print("O número é par!")
    else:
        print("O número é ímpar!")
        
# EXERCICIO 4 - Média
def media(n):
    
    conta = 0
    num = 1
    sum = 0
    
    while conta < n:
        num = int(input("Introduza um número: "))
        sum += num
        conta = conta + 1
    average = sum / n
    return average
