USE BD_20231487_2324_INF

-- Cria��o da tabela "empregado2"
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

-- Insert's m�ltiplos
	INSERT INTO empregado2
	SELECT *
	FROM emp;

-- Visualizar o conte�do da tabela "empregado2"
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
	VALUES (40, 'Integrac�o', 'Aveiro');

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

-- Criar a FK fora da tabela (ACONSELH�VEL!!!)
	ALTER TABLE EMP
	ADD CONSTRAINT tb_emp_FK_departamento FOREIGN KEY (depnum) REFERENCES departamento(numdept);

-- Inserir os dados (usando INSERTs m�ltiplos)
	INSERT INTO EMP
	SELECT *
	FROM empregado2;

-- Visualizar a tabela "EMP"
	SELECT *
	FROM EMP;

/* Ficha 4 */
-- Exerc�cio 1 - Liste o nome e fun��o de cada empregado e o nome do departamento onde trabalha
	SELECT e.nome 'Nome', e.funcao 'Funcao', d.nomedept 'Nome Departamento'
	FROM emp e INNER JOIN departamento d
		ON e.depnum = d.numdept;

-- Exerc�cio 2 - Liste todos os empregados que ganhem menos que o seu chefe, apresentando o nome do empregado, o seu sal�rio, o nome do chefe e o seu sal�rio.
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
-- a) Crie uma consulta que liste o nome do departamento e o respetivo n�mero de trabalhadores.
	SELECT d.nomedept, COUNT(e.depnum) 'Numero de Trabalhadores'
	FROM departamento d INNER JOIN EMP e
		ON e.depnum = d.numdept
	GROUP BY d.nomedept;

-- b) Crie uma consulta que liste o nome do departamento e o respetivo n�mero de trabalhadores. Apresentado apenas no resultado o departamentos
-- com pelo menos cinco trabalhadores.
	SELECT d.nomedept, COUNT(e.depnum) 'Numero de Trabalhadores'
	FROM departamento d INNER JOIN EMP e
		ON e.depnum = d.numdept
	GROUP BY d.nomedept
	HAVING COUNT(e.depnum) >= 5;

-- c) Crie uma consulta que liste o nome do departamento e o respetivo salario medio, mas apenas para os departamento que n�o comecem pela 
-- letra �C�. Deve apresentar o resultado por ordem crescente do valor m�dio do salario
	SELECT d.nomedept, AVG(e.salario) 'Media Salario no Departamento'
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	GROUP BY d.nomedept, e.depnum
	HAVING d.nomedept NOT LIKE 'C%'
	ORDER BY 2;

-- d) Crie uma consulta que liste o nome dos empregados que trabalham no departamento �Integra��o�, e que tenham sido admitidos no primeiro 
-- semestre. Deve apresentar o resultado come�ando pelos que tenham sido admitidos mais recentemente.
	SELECT e.nome, d.nomedept, e.data_adm
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = 'Integra��o' /* Falta a segunda condi��o*/;

-- e) Crie uma consulta que liste os nomes dos empregados, as suas fun��es, e os seus sal�rios. Estes empregados t�m de trabalhar no 
-- departamento �Consultoria�, e os seus sal�rios devem variar entre os 15000 e os 16000. Devem apresentar o resultado ordenado por fun��o, de
-- Z para A.
	SELECT e.nome, e.funcao, e.salario
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE d.nomedept = 'Consultoria' AND e.salario BETWEEN 15000 AND 16000
	ORDER BY 2 DESC;

-- f) Crie uma consulta que liste os nomes dos empregados, a data de admiss�o, bem como os respetivos nomes dos departamentos que tiveram 
-- empregados admitidos no �ltimo trimestre do ano �2001�. Devem ordenar o resultado pelo nome de departamento.
	SELECT e.nome, e.data_adm, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE MONTH(e.data_adm) >= 10 AND YEAR(e.data_adm) = 2001
	ORDER BY 3

-- g) Crie uma consulta que liste os nomes dos empregados, a sua fun��o, bem como os respetivos nomes dos departamentos. Esta consulta deve
-- apenas conter os nomes dos departamentos constitu�dos exatamente por 4 letras. Devem ordenar o resultado pelo nome de departamento, e dentro
-- deste por fun��o.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE LEN(d.nomedept) = 4
	ORDER BY 3, 2

-- h) Liste o nome e a fun��o de todos os empregados cuja fun��o n�o � �admin� nem �vendedor� e cujo nome termina com 'o', e que trabalham no
-- departamento �Integra��o�.
	SELECT e.nome, e.funcao, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.funcao != 'Vendedor' AND e.funcao != 'Admin' AND e.nome LIKE '%o' AND d.nomedept = 'Integra��o';

-- i) Liste toda informa��o dos empregados e o nome do seu respetivo departamento, que entraram para a empresa entre 9-11-2002 e 12-12-2002 e
-- cuja comiss�o n�o � nula. Estes trabalhadores n�o devem trabalhar nem no departamento �ERPs� nem no �CRMs
	SELECT e.empnum, e.nome, e.chefe, e.data_adm, e.salario, e.comissao, e.depnum, d.nomedept
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE (e.data_adm BETWEEN '2002.9.11' AND '2002.12.12') AND e.comissao IS NOT NULL AND d.nomedept != 'ERPs' AND d.nomedept != 'CRMs'

-- j) Liste toda a informa��o dos empregados cujo nome come�a com uma vogal e n�o cont�m a letra 'N'. Deve ainda garantir que estes trabalhem
-- num departamento em 'Lisboa'
	SELECT *
	FROM EMP e INNER JOIN departamento d
		ON e.depnum = d.numdept
	WHERE e.nome LIKE 'a%' AND e.nome LIKE 'e%' AND e.nome LIKE 'i%' AND e.nome LIKE 'o%' AND e.nome LIKE 'u%' AND d.nomedept = 'Lisboa'