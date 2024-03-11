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

/* Exercícios EXTRA: */

-- Exercício a): Crie uma consulta que devolve o número de empregados com data de admissão posterior a 1/1/2001
SELECT 'Após 1/1/2001' 'Data inicial de pesquisa', COUNT(e.empnum) 'Número de Empregados'
FROM emp e
WHERE e.data_adm > '2001.1.1';

-- Exercício b): Cria uma consulta que devolve o nome do primeiro e do último empregado por ordem alfabética, bem como o número de 
-- empregados registados na base de dados.
SELECT MIN(e.nome) 'Primeiro Empregado', MAX(e.nome) 'Último Empregado', COUNT(e.empnum) 'Número de Empregados'
FROM emp e;

-- Exercício c): Cria uma consulta que devolve para cada departamento o montante médio dos salários. Na solução apenas devem constar
-- departamentos cujo montante médio seja superior a 17000. O resultado deve ser ordenado pelo montante médio de forma decrescente.
SELECT e.depnum 'Depnum', ROUND(AVG(e.salario), 0) 'Montante médio dos salários por Dep'
FROM emp e
GROUP BY e.depnum
HAVING AVG(e.salario) > 17000
ORDER BY AVG(e.salario) DESC;

-- Exercício d): Crie uma consulta que devolva o número total de empregados e o montante dos salários mais e menos dispendiosos, 
-- cuja data de admissão é posterior a 1/1/2001.
SELECT 
	'Após 1/1/2001' 'Data inicial de pesquisa', 
	COUNT(e.empnum) 'Número de Empregados', 
	MAX(e.salario) 'Salário de maior montante',
	MIN(e.salario) 'Salário de menor montante'
FROM emp e
WHERE e.data_adm > '2001.1.1';

-- Exercício e): Listar o número de funcionários por função, ordenado o resultado pelo número de funcionários em ordem decrescente.
SELECT e.funcao 'funcao', COUNT(e.empnum) 'num_funcionarios'
FROM emp e
GROUP BY e.funcao
ORDER BY 2 DESC;

-- Exercício f): Consultar a média salarial por departamento, apenas para departamentos com mais de 3 funcionários e ordenando o 
-- resultado pela média salarial em ordem decrescente
SELECT e.depnum 'Depnum', ROUND(AVG(e.salario), 0) 'media_salarial'
FROM emp e
GROUP BY e.depnum
HAVING COUNT(*) > 3
ORDER BY 2 DESC;

-- Exercício g): Consultar o salário máximo por função, apenas para funções onde o salário máximo seja superior a 5000, ordenado pelo
-- salário máximo em ordem decrescente.
SELECT e.funcao 'funcao', MAX(e.salario) 'salario_maximo'
FROM emp e
GROUP BY e.funcao
HAVING MAX(e.salario) > 5000
ORDER BY 2 DESC;

-- Exercício h): Consultar o número de funcionários por ano de admissão, apenas para anos com mais de 5 funcionários, ordenado pelo
-- ano de admissão em ordem crescente.
SELECT YEAR(e.data_adm) 'ano_admissao', COUNT(e.empnum) 'num_funcionarios'
FROM emp e
GROUP BY YEAR(e.data_adm)
HAVING COUNT(e.empnum) > 5
ORDER BY 1;

-- Exercício i): Listar o número de chefes por departamento, apenas para departamentos onde o número de chefes seja superior a 1, 
-- ordenado pelo número de chefes em ordem decrescente.
SELECT e.depnum 'Depnum', COUNT(e.chefe) 'num_chefes'
FROM emp e
GROUP BY e.depnum
HAVING COUNT(e.chefe) > 1
ORDER BY 2 DESC;

-- Exercício j): Consultar a soma das comissões por departamento, apenas para departamentos com mais de 1000 em comissões, ordenados
-- pela soma das comissões em ordem decrescente.
SELECT e.depnum 'Depnum', SUM(e.comissao) 'total_comissoes'
FROM emp e
GROUP BY e.depnum
HAVING SUM(e.comissao) > 1000
ORDER BY 2 DESC;

-- Exercício k): Consultar para cada a função o menor salário, ordenado pelo salário mínimo em ordem crescente.
SELECT e.funcao 'funcao', MIN(e.salario) 'salario_minimo'
FROM emp e
GROUP BY e.funcao
ORDER BY 2;

-- Exercício l): Consultar o número de funcionários por chefe, apenas para chefes que tenham mais de 3 funcionários sob sua 
-- supervisão, ordenado pelo número de funcionários em ordem decrescente.
SELECT e.chefe 'chefe', COUNT(e.empnum) 'num_funcionarios'
FROM emp e
GROUP BY e.chefe
HAVING COUNT(e.chefe) > 3
ORDER BY 2 DESC;

-- Exercício m): Consultar o número de funcionários por departamento e função, apenas para departamentos com mais de 1 funcionários
-- em cada função, ordenado pelo número de funcionários em ordem decrescente
SELECT e.depnum 'Depnum', e.funcao 'funcao', COUNT(e.empnum) 'num_funcionarios'
FROM emp e
GROUP BY e.depnum, e.funcao
HAVING COUNT(e.empnum) > 1
ORDER BY 3 DESC;

-- Exercício n): Consultar a média salarial por função, apenas para funções onde a média salarial seja superior a 4000, ordenado pela
-- média salarial em ordem decrescente.
SELECT e.funcao 'funcao', ROUND(AVG(e.salario), 0) 'media_salarial'
FROM emp e
GROUP BY e.funcao
HAVING AVG(e.salario) > 4000
ORDER BY 2 DESC;

-- Exercício o): Consultar o número de funcionários por departamento, apenas para departamentos onde o número de funcionários seja 
-- inferior a 10, ordenado pelo número de funcionários em ordem crescente.
SELECT e.depnum 'Depnum', COUNT(e.empnum) 'num_funcionarios'
FROM emp e
GROUP BY e.depnum
HAVING COUNT(e.empnum) < 10
ORDER BY 2;

-- Exercício p): Consultar a função com o maior salário médio, ordenado pelo salário médio em ordem decrescente.
SELECT e.funcao 'funcao', ROUND(AVG(e.salario), 0) 'salario_medio'
FROM emp e
GROUP BY e.funcao
ORDER BY 2 DESC;
