USE BD_20231487_2324_INF;

/* ---------------------------------------- FICHA 5*/ --------------------------------------------
-- 1. Encontre o empregado que ganha o menor salário na empresa.
	-- Mínimo salário na empresa (não faz parte da solução, ajuda apenas no raciocinio)
	SELECT MIN(e.salario)
	FROM emp e;

	-- Encontrar os empregados que ganham o salário minímo (RESPOSTA)
	SELECT e.nome, e.salario
	FROM emp e
	WHERE e.salario = (SELECT MIN(e.salario)
					   FROM emp e);

-- 2. Encontre todos os empregados que tenham a mesma função que o Barros.
SELECT e.nome, e.funcao
FROM emp e
WHERE e.funcao = (SELECT e1.funcao
				  FROM emp e1
				  WHERE e1.nome = 'Barros');

-- 3. Encontre os empregados que ganham mais do que o salário mais baixo do departamento 30.
SELECT e.nome, e.salario
FROM emp e
WHERE e.salario >ANY (    -- ANY: Significa maior do que o mais pequeno
						SELECT DISTINCT e1.salario
						FROM emp e1
						WHERE e1.depnum = 30
					);

-- 4. Encontre todos os empregados que ganham mais do que qualquer empregado do departamento 20.
SELECT e.nome, e.salario
FROM emp e
WHERE e.salario >ALL (   -- ALL: Significa maior do que o valor mais elevado
						SELECT e1.salario
						FROM emp e1
						WHERE e1.depnum = 20
					);

-- 5. Encontre os empregados que ganhem um salário superior ao salário médio do seu departamento.
SELECT e.nome, e.salario, e.depnum
FROM emp e
WHERE e.salario > (   -- CORRELAÇÃO: quando a subconsulta precisa de 'dados' da consulta principal
						SELECT AVG(e1.salario)
						FROM emp e1
						WHERE e1.depnum = e.depnum
					)

-- 6. Encontre os empregados que tenham pelo menos um subordinado
SELECT e.empnum, e.nome
FROM emp e
WHERE EXISTS (
				SELECT e1.empnum
				FROM emp e1
				WHERE e1.chefe = e.empnum
			);

/* Extras */
-- a) Crie uma consulta que liste o nome do empregado mais antigo da empresa, e respetivo nome do 
-- departamento onde trabalha (Nota: Como confirmação liste também o numero do departamento da tabela 
-- Empregado e Departamento)
SELECT e.nome, e.data_adm, e.depnum, d.numdept, d.nomedept
FROM emp e INNER JOIN departamento d
	ON e.depnum = d.numdept
WHERE e.data_adm <=ALL (
						SELECT e1.data_adm
						FROM emp e1
					);

	-- Modificar uma linha da tabela emp
	UPDATE emp
	SET data_adm = '2000-06-01'
	WHERE empnum = 79;

-- b) Crie uma consulta que liste empregados cujo nome se inicia pela letra 'B', e que trabalhem em 
-- departamentos cujo nome se inicia pela letra 'C'. (Resolva esta questão de duas formas diferentes, 
-- com e sem recurso a subconsultas)
	
	-- SEM recurso a subconsultas
	SELECT e.nome, d.nomedept
	FROM emp e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.nome LIKE 'B%' AND d.nomedept LIKE 'C%';

	-- COM recurso a subconsultas
	SELECT TB2.nome, TB1.nomedept
	FROM 
			(
				SELECT d.numdept, d.nomedept
				FROM departamento d
				WHERE d.nomedept LIKE 'C%') TB1
		INNER JOIN
			(
				SELECT e.depnum, e.nome
				FROM emp e
				WHERE e.nome LIKE 'B%') TB2
		ON TB1.numdept = TB2.depnum;

-- c) Crie uma consulta que liste para cada função, o nome do empregado que tenha o nome mais pequeno 
-- (NOTA: LEN(string) devolve um inteiro correspondente ao tamanho da string
SELECT e.funcao, e.nome
FROM emp e
WHERE LEN(e.nome) = (     -- CORRELAÇÃO NO ATRIBUTO 'funcao'
							SELECT MIN(LEN(e1.nome))
							FROM emp e1
							WHERE e1.funcao = e.funcao
						);

-- d) Crie uma consulta que para cada departamento, liste o nome do departamento e o nome do empregado 
-- admitido mais recentemente.
SELECT e.nome, d.nomedept, e.data_adm
FROM emp e INNER JOIN departamento d
	ON e.depnum = d.numdept
WHERE e.data_adm = 
						(     -- CORRELAÇÃO NO 'numdept'
							SELECT MAX(e1.data_adm)
							FROM emp e1 
							WHERE e1.depnum = d.numdept
						);

-- e) Liste toda a informação dos empregados com mais antiguidade do que o 'Monteiro', e que não tenham
-- comissão. Ordene o resultado pelo valor do salário de forma crescente.
SELECT *
FROM emp e
WHERE e.comissao IS NULL
AND e.data_adm <
					(
						SELECT e1.data_adm
						FROM emp e1
						WHERE e1.nome = 'Monteiro'
					)
ORDER BY 6;

	-- Modificar uma linha da tabela emp
	UPDATE emp
	SET data_adm = '2000-03-12'
	WHERE empnum = 18;

-- f) Liste os empregados do departamento 20, que tenham a mesma função dos do departamento 'Consultoria'
-- Ordene o resultado pelo nome do empregado de Z para A.
SELECT e.*
FROM emp e
WHERE e.depnum = 20
AND e.funcao IN		(
						SELECT e1.funcao
						FROM emp e1 INNER JOIN departamento d 
							ON e1.depnum = d.numdept
						WHERE d.nomedept = 'Consultoria'
					) 
ORDER BY 2 DESC;

-- g) Liste os empregados, que tenham a mesma função do 'Antunes' ou que ganhem um salário maior do que
-- o 'Barros' Ordene o resultado pelo nome do empregado de Z para A
SELECT e.*
FROM emp e
WHERE e.funcao =
				(
					SELECT e1.funcao
					FROM emp e1
					WHERE e1.nome = 'Antunes' 
				) 
OR e.salario >ALL 
				(
					SELECT e2.salario
					FROM emp e2
					WHERE e2.nome = 'Barros'
				)
ORDER BY 2 DESC;

-- h) Liste os empregados, trabalhem em 'Lisboa' ou no 'Porto', que ganhem comissão e que sejam mais 
-- admitidos antes do 'Brito'. Ordene o resultado por salário começando pelo mais elevado
SELECT e.*, d.Localidade
FROM emp e INNER JOIN departamento d
	ON e.depnum = d.numdept
WHERE e.comissao IS NOT NULL 
AND d.Localidade IN 
	(
		SELECT d1.Localidade
		FROM departamento d1
		WHERE d.Localidade = 'Lisboa' OR d.Localidade = 'Porto' 
	) 
AND e.data_adm <ANY 
	(
		SELECT e2.data_adm
		FROM emp e2
		WHERE e2.nome = 'Brito'
	) 
ORDER BY 6 DESC;

	-- Modificar uma linha da tabela emp
	UPDATE emp
	SET data_adm = '2002-03-02'
	WHERE empnum = 30;
