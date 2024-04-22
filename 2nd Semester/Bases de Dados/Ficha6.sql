USE BD_20231487_2324_INF;

-- Exercício 1: Adicione uma coluna com o numero de dependentes dos empregados da empresa
ALTER TABLE emp
ADD NumDependentesEmp INT;

SELECT *
FROM emp;

-- Exercício 2: Insira um novo departamento à tabela DEPARTAMENTO, com o numdept 50, nomedept: Marketing e Localidade: Lisboa.
INSERT INTO departamento (numdept, nomedept, Localidade)
VALUES(50, 'Marketing', 'Lisboa');

SELECT *
FROM departamento;

-- Exercício 3: Actualize o registo do Silva, de modo a que seja alterada a sua função para Analista e o seu salário sofra um aumento de 10%.
UPDATE emp
SET funcao='Analista', salario *= 1.10
WHERE nome='Silva';

-- Exercício 4: Apague os dados do departamento 50 criado na alínea 2
SELECT * FROM departamento;

DELETE FROM departamento
WHERE numdept = 50;

-- Exercício 5: Crie uma vista EMP_DEPT10 com o numdept, o nome do empregado e o salário. Liste todos os campos desta vista
CREATE VIEW EMP_DEPT10 
AS
	SELECT e.depnum, e.nome, e.salario
	FROM emp e
	WHERE e.depnum = 10;

	-- Consultar os campos da vista criada
	SELECT * FROM EMP_DEPT10;

-- Exercício 6: Crie uma vista chamada SUMARIODEPT contendo o nome do departamento, o salário mínimo do departamento, o salário máximo do departamento e o
				-- salário médio do departamento.

-- 1ª Forma:
	CREATE VIEW SUMARIODEPT(nomeDep, MinSalario, MaxSalario, MedioSalario)
	AS 
		SELECT d.nomedept, MIN(e.salario), MAX(e.salario), AVG(e.salario)
		FROM emp e INNER JOIN departamento d
			ON d.numdept = e.depnum
		GROUP BY d.nomedept;

	SELECT * FROM SUMARIODEPT;

-- 2ª Forma: 
	CREATE VIEW SUMARIODEPT
	AS 
		SELECT d.nomedept, 
				MIN(e.salario) MinSalario, 
				MAX(e.salario) MaxSalario, 
				AVG(e.salario) AvgSalario
		FROM emp e INNER JOIN departamento d
			ON d.numdept = e.depnum
		GROUP BY d.nomedept;

	SELECT * FROM SUMARIODEPT;

/* EXTRA */

-- Exercício 1:
	-- a) Crie uma nova coluna na tabela EMP designada por ‘altura’, com o tipo de dado ‘int’.
	ALTER TABLE emp
	ADD altura INT;

	/* Para visualizar a alteração:
	SELECT * FROM emp;
			ou
	EXEC SP_COLUMNS emp */

	-- b) Altere o tipo de dados do atributo criado (altura), para decimal com 4 casas, duas das quais decimais.
	ALTER TABLE emp
	ALTER COLUMN altura DECIMAL(4, 2); 

	-- c) Remova da tabela a coluna criada (altura).
	ALTER TABLE emp
	DROP COLUMN altura;

-- Exercício 2:
	/* a) Crie uma nova tabela designada 'Armazem', tendo em conta as
		seguintes restrições:
			- deverá ser composta pelos seguintes atributos: (id_armazem,
			nome_armazem, capacidade_armazenamento, id_departamento)
				- (id_armazem) deverá ser a chave primária;
				- (nome_armazem) deverá ser único, e de tamanho 30 e não nulo;
				- (capacidade_armazenamento) deverá ser um valor inteiro, variando entre zero e mil, e um valor por defeito 0;
				- (id_departamento) deverá ser uma chave forasteira para a tabela existente departamento; */
	CREATE TABLE armazem
	(id_armazem INT,
	nome_armazem VARCHAR(30) UNIQUE NOT NULL,
	capacidade_armazenamento INT DEFAULT 0,
	id_departamento INT,
	CONSTRAINT tb_Armazem_PK PRIMARY KEY(id_armazem),
	CONSTRAINT tb_Armazem_CHK_CapacidadeArmazenamento CHECK(capacidade_armazenamento BETWEEN 0 AND 1000)
	);

	ALTER TABLE armazem
	ADD CONSTRAINT tb_Armazem_FK_Departamento
	FOREIGN KEY(id_departamento) REFERENCES Departamento(numdept);

	SELECT * FROM armazem;

	-- b) Crie inserções que validem as restrições criadas
		-- Não funciona porque: "Viola a restrição de PK"
		INSERT INTO armazem VALUES (1, 'Armazem 2', 200, 20);

		-- Não funciona porque: "Viola a restrição de UNIQUE"
		INSERT INTO armazem VALUES (2, 'Armazem 1', 200, 20);

	INSERT INTO armazem VALUES (1, 'Armazem 1', 100, 10);
	INSERT INTO armazem VALUES (2, 'Armazem 2', 200, 20);
	INSERT INTO armazem VALUES (3, 'Armazem 3', 300, 30);
	INSERT INTO armazem VALUES (4, 'Armazem 4', 400, 40);
	INSERT INTO armazem VALUES (5, 'Armazem 5', 500, 50);

-- Exercício 3: Liste, para cada empregado o numero, o nome, o salario mensal, o salario diário, e o salário anual; ordenados de forma ascendente pelo salario 
				-- anual. (Considere que o salário presente na tabela é o salario mensal, e que os meses têm 30 dias)
	SELECT e.empnum, e.nome, e.salario 'Salario Mensal', e.salario / 30 'Salario Diario', e.salario * 12 'Salario Anual' 
	FROM emp e
	ORDER BY 3;

-- Exercício 4: Liste toda a informação dos empregados que foram contratados no segundo semestre do ano 2000, ordenada de forma descendente pela respetiva função.
				/* (Nota: responda de 3 formas distintas usando:
					- condição de desigualdades ('>', '<', ...)
					- 'Between';
					- 'IN' (sabendo que a função MONTH(data), e YEAR(data) recebem uma 'data' e devolve um valor inteiro)).*/
	
	-- Usando condições de desigualdade:
	SELECT e.*
	FROM emp e
	WHERE e.data_adm >= '2000.07.01' AND e.data_adm <= '2000.12.31'
	ORDER BY e.funcao DESC;

	-- Usando BETWEEN
	SELECT e.*
	FROM emp e
	WHERE e.data_adm BETWEEN '2000.07.01' AND '2000.12.31'
	ORDER BY e.funcao DESC;

	-- Usando IN
	SELECT e.*
	FROM emp e
	WHERE MONTH(e.data_adm) IN (7,8,9,10,11,12) AND YEAR(e.data_adm) = 2000
	ORDER BY e.funcao DESC;

-- Exercício 5: Liste toda a informação sobre os empregados que não tenha a função de administrativos nem de analistas, ordenados por função de forma descendente.
				/* (NOTA: Responda à questão de três formas distintas usando:
					- o operador diferente '!=';
					- o operador diferente '<>';
					- NOT IN;)*/
	
	-- Usando o operador '!='
	SELECT e.*
	FROM emp e
	WHERE e.funcao != 'Admin' AND e.funcao != 'Analista'
	ORDER BY e.funcao DESC;

	-- Usando o operador '<>'
	SELECT e.*
	FROM emp e
	WHERE e.funcao <> 'Admin' AND e.funcao <> 'Analista'
	ORDER BY e.funcao DESC;

	-- Usando o operador NOT IN
	SELECT e.*
	FROM emp e
	WHERE e.funcao NOT IN ('Admin', 'Analista')
	ORDER BY e.funcao DESC;

-- Exercício 6: Liste toda a informação dos empregados que tenham 4 letras no seu nome, em que a 3ª letra seja um 'T' e termine com a letra 'O'.
				-- (Nota: a função LEN(nome) recebe uma cadeia de carateres e devolve um valor inteiro representando o tamanho da cadeia de carateres recebida)
	SELECT e.*
	FROM emp e
	WHERE e.nome LIKE '__t%o' AND LEN(e.nome) = 4;

-- Exercício 7: Liste toda a informação dos empregados que não trabalhem no departamento 20, ordenada de forma ascendente por número de departamento.
				/* (Nota: Responda à questão de 4 forma diferentes:
					- NOT IN;
					- '!='
					- '<>';
					- NOT LIKE;)*/

	-- Usando o NOT IN:
	SELECT e.*
	FROM emp e
	WHERE e.depnum NOT IN (20)
	ORDER BY 8;

	-- Usando o '!=':
	SELECT e.*
	FROM emp e
	WHERE e.depnum != 20
	ORDER BY 8;

	-- Usando o '<>':
	SELECT e.*
	FROM emp e
	WHERE e.depnum <> 20
	ORDER BY 8;

	-- Usando o NOT LIKE:
	SELECT e.*
	FROM emp e
	WHERE e.depnum NOT LIKE 20
	ORDER BY 8;


-- Exercício 8: Liste toda a informação dos empregados, juntamento com o nome e localização do respetivo departamento, que trabalhem no departamento
				--'Consultoria' e 'ERPs' e que sejam 'Vendedores', ordenada de forma ascendente, por número de departamento.
	SELECT e.*, d.nomedept, d.Localidade
	FROM emp e INNER JOIN departamento d
		ON d.numdept = e.depnum
	WHERE d.nomedept IN ('Consultoria', 'ERPs') AND e.funcao = 'Vendedor'
	ORDER BY d.numdept;
