USE BD_20231487_2324_INF;

SELECT *
FROM emp;

/* Ficha 3 */

-- Exercício 1: Calcule a média de salários de todos os empregados
SELECT AVG(e.salario) 'Media de Salarios'
FROM emp e;

-- Exercício 2: Encontre o mínimo salário recebido por um empregado administrativo.
SELECT MIN(e.salario) 'Salario Minimo Admin'
FROM emp e
WHERE funcao = 'Admin';

-- Exercício 3: Quantos empregados tem o departamento 20?
SELECT COUNT(e.depnum) 'Count Empregados c/ Dep = 20'
FROM emp e
WHERE e.depnum = 20;

-- Exercício 4: Calcule o salário médio para cada função
SELECT e.funcao, AVG(e.salario) 'Salario Medio'
FROM emp e
GROUP BY e.funcao;

-- Exercício 5: Calcule o salário médio para cada função, excluindo o presidente.
SELECT e.funcao, AVG(e.salario) 'Salario Medio exceto Presidente'
FROM emp e
WHERE e.funcao != 'Presidente'
GROUP BY e.funcao;

-- Exercício 6: Encontre o salário médio para cada função dentro de cada departamento
SELECT e.depnum, e.funcao, AVG(e.salario) 'Salario Medio por Departamento'
FROM emp e
GROUP BY e.depnum, e.funcao;

-- Exercício 7: Encontre o salário mais baixo para cada departamento
SELECT e.depnum, MIN(e.salario) 'Salario + baixo por Departamento'
FROM emp e
GROUP BY e.depnum;

-- Exercício 8: Mostre o salário médio de cada departamento com mais de 4 pessoas
SELECT e.depnum, AVG(e.salario) 'Salario Medio por Departamento c/ + 4 pessoas'
FROM emp e
GROUP BY e.depnum
HAVING COUNT(e.depnum) > 4;

-- Exercício 9: Mostra apenas as funções cujo salário máximo é maior ou igual a 20000
SELECT e.funcao, MAX(e.salario) 'Salario Maximo >= 20000 por Funcao'
FROM emp e
GROUP BY e.funcao
HAVING MAX(e.salario) >= 20000;
