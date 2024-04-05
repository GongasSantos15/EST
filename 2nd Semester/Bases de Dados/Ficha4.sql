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

-- Extra
-- a) Crie uma consulta que liste o nome do departamento e o respetivo número de trabalhadores.
	SELECT d.nomedept, COUNT(e.depnum) 'Numero de Trabalhadores'
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
	GROUP BY d.nomedept, e.depnum
	HAVING d.nomedept NOT LIKE 'C%'
	ORDER BY 2;

-- d) Crie uma consulta que liste o nome dos empregados que trabalham no departamento ‘Integração’, e que tenham sido admitidos no primeiro 
-- semestre. Deve apresentar o resultado começando pelos que tenham sido admitidos mais recentemente.
	SELECT e.nome, d.nomedept, e.data_adm
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = 'Integração' /* Falta a segunda condição*/;

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
	ORDER BY 3

-- g) Crie uma consulta que liste os nomes dos empregados, a sua função, bem como os respetivos nomes dos departamentos. Esta consulta deve
-- apenas conter os nomes dos departamentos constituídos exatamente por 4 letras. Devem ordenar o resultado pelo nome de departamento, e dentro
-- deste por função.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE LEN(d.nomedept) = 4
	ORDER BY 3, 2

-- h) Liste o nome e a função de todos os empregados cuja função não é ‘admin’ nem ‘vendedor’ e cujo nome termina com 'o', e que trabalham no
-- departamento ‘Integração’.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.funcao != 'Vendedor' AND e.funcao != 'Admin' AND e.nome LIKE '%o' AND d.nomedept = 'Integração';

-- i) Liste toda informação dos empregados e o nome do seu respetivo departamento, que entraram para a empresa entre 9-11-2002 e 12-12-2002 e
-- cuja comissão não é nula. Estes trabalhadores não devem trabalhar nem no departamento ‘ERPs’ nem no ‘CRMs
	SELECT e.empnum, e.nome, e.chefe, e.data_adm, e.salario, e.comissao, e.depnum, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE (e.data_adm BETWEEN '2002.9.11' AND '2002.12.12') AND e.comissao IS NOT NULL AND d.nomedept != 'ERPs' AND d.nomedept != 'CRMs'

-- j) Liste toda a informação dos empregados cujo nome começa com uma vogal e não contém a letra 'N'. Deve ainda garantir que estes trabalhem
-- num departamento em 'Lisboa'
	SELECT *
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.nome LIKE 'a%' AND e.nome LIKE 'e%' AND e.nome LIKE 'i%' AND e.nome LIKE 'o%' AND e.nome LIKE 'u%' AND d.nomedept = 'Lisboa'