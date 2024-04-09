USE BD_20231487_2324_INF

-- Criação da tabela "empregado2"
	CREATE TABLE empregado2
	(
		empnum INT NOT NULL, 
		nome VARCHAR(20), 
		funcao VARCHAR(20), 
		chefe INT, 
		data_adm DATETIME, 
		salario REAL, 
		comissao REAL, 
		depnum INT
		CONSTRAINT tb_emp_PK_2 PRIMARY KEY (empnum)
	);

-- Insert's múltiplos
	INSERT INTO empregado2
	SELECT *
	FROM emp;

-- Visualizar o conteúdo da tabela "empregado2"
	SELECT *
	FROM empregado2;

-- Apagar a tabela EMP
	DROP TABLE EMP;

-- Criar a tabela departamento 
	CREATE TABLE departamento
	(
		numdept INT,
		nomedept VARCHAR(20),
		Localidade VARCHAR(20),
		CONSTRAINT tb_departamento_PK PRIMARY KEY (numdept)
	);

-- Inserir os dados da tabela "departamento"
	INSERT INTO departamento
	VALUES (10, 'Consultoria', 'Lisboa');

	INSERT INTO departamento
	VALUES (20, 'CRMs', 'Porto');

	INSERT INTO departamento
	VALUES (30, 'ERPs', 'Coimbra');

	INSERT INTO departamento
	VALUES (40, 'Integracão', 'Aveiro');

-- Visualizar a tabela "departamento"
	SELECT *
	FROM departamento;

-- Criar a tabela empregado com a FK dentro da tabela
	CREATE TABLE EMP
	(
		empnum INT NOT NULL, 
		nome VARCHAR(20), 
		funcao VARCHAR(20), 
		chefe INT, 
		data_adm DATETIME, 
		salario REAL, 
		comissao REAL, 
		depnum INT,
		CONSTRAINT tb_emp_FK_departamento FOREIGN KEY (depnum) REFERENCES departamento(numdept),
		CONSTRAINT tb_emp_PK PRIMARY KEY (empnum)
	);

-- Criar a FK fora da tabela (ACONSELHÁVEL!!!)
	ALTER TABLE EMP
	ADD CONSTRAINT tb_emp_FK_departamento FOREIGN KEY (depnum) REFERENCES departamento(numdept);

-- Inserir os dados (usando INSERTs múltiplos)
	INSERT INTO EMP
	SELECT *
	FROM empregado2;

-- Visualizar a tabela "EMP"
	SELECT *
	FROM EMP;

/* Ficha 4 */
-- Exercício 1 - Liste o nome e função de cada empregado e o nome do departamento onde trabalha
	SELECT e.nome 'Nome', e.funcao 'Funcao', d.nomedept 'Nome Departamento'
	FROM emp e INNER JOIN departamento d
		ON e.depnum = d.numdept;

-- Exercício 2 - Liste todos os empregados que ganhem menos que o seu chefe, apresentando o nome do empregado, o seu salário, o nome do chefe e o seu salário.
	SELECT e2.empnum 'Numero Empregado',
		   e2.nome 'Nome Empregado',
		   e2.chefe 'Numero Chefe',
		   e2.salario 'Salario Empregado',
		   e1.empnum 'Numero Empregado Chefe',
		   e1.nome 'Nome Chefe', 
		   e1.salario 'Salario Chefe'
	FROM emp e1 CROSS JOIN emp e2
	WHERE e2.chefe = e1.empnum AND e2.salario < e1.salario

/* Extra */
-- a) Crie uma consulta que liste o nome do departamento e o respetivo número de trabalhadores.
	SELECT d.nomedept, COUNT(e.empnum) 'Numero de Trabalhadores'
	FROM departamento d INNER JOIN EMP e
		ON e.depnum = d.numdept
	GROUP BY d.nomedept;

-- b) Crie uma consulta que liste o nome do departamento e o respetivo número de trabalhadores. Apresentado apenas no resultado o departamentos
-- com pelo menos cinco trabalhadores.
	SELECT d.nomedept, COUNT(e.depnum) 'Numero de Trabalhadores'
	FROM departamento d INNER JOIN EMP e
		ON e.depnum = d.numdept
	GROUP BY d.nomedept
	HAVING COUNT(e.depnum) >= 5;

-- c) Crie uma consulta que liste o nome do departamento e o respetivo salario medio, mas apenas para os departamento que não comecem pela 
-- letra ‘C’. Deve apresentar o resultado por ordem crescente do valor médio do salario
	SELECT d.nomedept, AVG(e.salario) 'Media Salario no Departamento'
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept NOT LIKE 'C%'
	GROUP BY d.nomedept
	ORDER BY 2;

-- d) Crie uma consulta que liste o nome dos empregados que trabalham no departamento ‘Integração’, e que tenham sido admitidos no primeiro 
-- semestre. Deve apresentar o resultado começando pelos que tenham sido admitidos mais recentemente.
	SELECT e.nome, d.nomedept, e.data_adm
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = 'Integração' AND MONTH(e.data_adm) <= 6
	ORDER BY 2 DESC;

	-- Modificar uma linha da tabela emp
	UPDATE emp
	SET data_adm = '2000-12-04'
	WHERE empnum = 69;

-- e) Crie uma consulta que liste os nomes dos empregados, as suas funções, e os seus salários. Estes empregados têm de trabalhar no 
-- departamento ‘Consultoria’, e os seus salários devem variar entre os 15000 e os 16000. Devem apresentar o resultado ordenado por função, de
-- Z para A.
	SELECT e.nome, e.funcao, e.salario
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = 'Consultoria' AND e.salario BETWEEN 15000 AND 16000
	ORDER BY 2 DESC;

-- f) Crie uma consulta que liste os nomes dos empregados, a data de admissão, bem como os respetivos nomes dos departamentos que tiveram 
-- empregados admitidos no último trimestre do ano ‘2001’. Devem ordenar o resultado pelo nome de departamento.
	SELECT e.nome, e.data_adm, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE MONTH(e.data_adm) >= 10 AND YEAR(e.data_adm) = 2001
	ORDER BY 3;

-- g) Crie uma consulta que liste os nomes dos empregados, a sua função, bem como os respetivos nomes dos departamentos. Esta consulta deve
-- apenas conter os nomes dos departamentos constituídos exatamente por 4 letras. Devem ordenar o resultado pelo nome de departamento, e dentro
-- deste por função.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = '____' -- LEN(d.nomedept)
	ORDER BY 3, 2;

-- h) Liste o nome e a função de todos os empregados cuja função não é ‘admin’ nem ‘vendedor’ e cujo nome termina com 'o', e que trabalham no
-- departamento ‘Integração’.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.funcao NOT IN ('Admin', 'Vendedor') 
	AND e.nome LIKE '%o' 
	AND d.nomedept = 'Integração';

-- i) Liste toda informação dos empregados e o nome do seu respetivo departamento, que entraram para a empresa entre 9-11-2002 e 12-12-2002 e
-- cuja comissão não é nula. Estes trabalhadores não devem trabalhar nem no departamento ‘ERPs’ nem no ‘CRMs
	SELECT e.*, d.nomedept -- e.* -> vai selecionar todos os atributos da tabela emp
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.data_adm BETWEEN '2002.9.11' AND '2002.12.12' 
	AND e.comissao IS NOT NULL 
	AND d.nomedept NOT IN ('CRMs', 'ERPs')

-- j) Liste toda a informação dos empregados cujo nome começa com uma vogal e não contém a letra 'N'. Deve ainda garantir que estes trabalhem
-- num departamento em 'Lisboa'
	SELECT e.*
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.nome LIKE '[a,e,i,o,u]%' --> inclui todos os nomes começados por vogal, se for letras consecutivas fazer [a-x], x -> última letra 
	AND e.nome NOT LIKE '%n%'
	AND d.localidade = 'Lisboa';

-- k) Considere agora que cada departamento é supervisionado por um empregado.
	-- a) Escreva a instrução SQL para que alterando a tabela departamento, tal possa ser possível.
	 ALTER TABLE departamento
	 ADD supervisor INT;

	 SELECT * FROM departamento;

	/* 
	b) Atribua a chefia do:
		i. Departamento 10 ao ‘Antunes’;
		ii. Departamento 20 ao ‘Moura’;
		iii. Departamento 30 ao ‘Neto’;
		iv. Departamento 40 ao ‘Brito’;
	*/
	UPDATE departamento
	SET supervisor = 89
	WHERE numdept = 10;

	UPDATE departamento
	SET supervisor = 77
	WHERE numdept = 20;

	UPDATE departamento
	SET supervisor = 98
	WHERE numdept = 30;

	UPDATE departamento
	SET supervisor = 30
	WHERE numdept = 40;

	
	 SELECT * FROM departamento;

-- l) Liste o nome dos departamentos com supervisores que tenham sido admitidos nos meses ímpares do ano 2001. No resultado deverá 
-- ainda mostrar o nome do supervisor e a respetiva data de admissão. Ordene o resultado por antiguidade de admissão.
	SELECT d.nomedept, e.nome, e.data_adm
	FROM emp e INNER JOIN departamento d
		ON e.empnum = d.supervisor
	WHERE MONTH(e.data_adm) IN (1,3,5,7,9,11) --> Para representar os números ímpares também se pode usar: MONTH(e.data_adm) % 2 != 0
	AND YEAR(e.data_adm) = 2001
	ORDER BY 3 ASC;

-- m) Liste o nome dos supervisores, o salário, a comissão, a data de admissão e a função, que não recebam comissão, e que o seu 
-- salário seja inferior a 25000. Certifique-se que na solução não constem supervisores que tenham a função ‘Chefe’. Ordene o resultado
-- por data de admissão do mais recente para o mais antigo.
	SELECT e.nome, e.salario, e.comissao, e.data_adm, e.funcao
	FROM emp e INNER JOIN departamento d
		ON e.empnum = d.supervisor
	WHERE e.comissao IS NULL 
	AND e.salario < 25000
	AND e.funcao != 'Chefe'
	ORDER BY 4 DESC;

-- n) Liste os nomes de empregados que tenham o mesmo número de meses de antiguidade na empresa (isto é, foram contratados no mesmo mês
-- e ano).
	SELECT e1.nome
	FROM emp e1 CROSS JOIN emp e2 
	WHERE MONTH(e1.data_adm) = MONTH(e2.data_adm) 
	AND YEAR(e1.data_adm) = YEAR(e2.data_adm)
	AND e1.empnum != e2.empnum
	ORDER BY 1;
