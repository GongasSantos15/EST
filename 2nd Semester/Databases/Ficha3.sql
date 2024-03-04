USE BD_20231487_2324_INF;

SELECT *
FROM emp;

/* Ficha 3 */

-- Exerc�cio 1: Calcule a m�dia de sal�rios de todos os empregados
SELECT AVG(e.salario) 'Media de Salarios'
FROM emp e;

-- Exerc�cio 2: Encontre o m�nimo sal�rio recebido por um empregado administrativo.
SELECT MIN(e.salario) 'Salario Minimo Admin'
FROM emp e
WHERE funcao = 'Admin';

-- Exerc�cio 3: Quantos empregados tem o departamento 20?
SELECT COUNT(e.depnum) 'Count Empregados c/ Dep = 20'
FROM emp e
WHERE e.depnum = 20;

-- Exerc�cio 4: Calcule o sal�rio m�dio para cada fun��o
SELECT e.funcao, AVG(e.salario) 'Salario Medio'
FROM emp e
GROUP BY e.funcao;

-- Exerc�cio 5: Calcule o sal�rio m�dio para cada fun��o, excluindo o presidente.
SELECT e.funcao, AVG(e.salario) 'Salario Medio exceto Presidente'
FROM emp e
WHERE e.funcao != 'Presidente'
GROUP BY e.funcao;

-- Exerc�cio 6: Encontre o sal�rio m�dio para cada fun��o dentro de cada departamento
SELECT e.depnum, e.funcao, AVG(e.salario) 'Salario Medio por Departamento'
FROM emp e
GROUP BY e.depnum, e.funcao;

-- Exerc�cio 7: Encontre o sal�rio mais baixo para cada departamento
SELECT e.depnum, MIN(e.salario) 'Salario + baixo por Departamento'
FROM emp e
GROUP BY e.depnum;

-- Exerc�cio 8: Mostre o sal�rio m�dio de cada departamento com mais de 4 pessoas
SELECT e.depnum, AVG(e.salario) 'Salario Medio por Departamento c/ + 4 pessoas'
FROM emp e
GROUP BY e.depnum
HAVING COUNT(e.depnum) > 4;

-- Exerc�cio 9: Mostra apenas as fun��es cujo sal�rio m�ximo � maior ou igual a 20000
SELECT e.funcao, MAX(e.salario) 'Salario Maximo >= 20000 por Funcao'
FROM emp e
GROUP BY e.funcao
HAVING MAX(e.salario) >= 20000;
