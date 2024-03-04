USE BD_20231487_2324_INF;

/* Usar uma sigla de 1 letra para selecionar qual tabela estamos a retirar a coluna 
	EX: SELECT e.nome
		FROM nomes AS e;  A palavra AS PODE SER OMITIDA QUE O SIGNIFICADO N�O MUDA
*/

SELECT * 
FROM EMP;


/* COME�O FICHA 2 */
-- 1. Liste os n�meros de todos os empregados, os seus nomes e n�meros dos seus chefes. 

-- 2. Considerando que o sal�rio � o sal�rio anual, liste o sal�rio mensal de cada empregado 
	 SELECT e.nome, e.salario SalarioAnual, e.salario/12 SalarioMensal
	 FROM EMP e;

-- 3. Liste, para todos os empregados, o seu sal�rio mais comiss�es.
	-- Usando o isnull (para converter os valores nulos em 0, de forma a podermos somar)
		SELECT e.nome, e.salario, e.comissao, e.salario + isnull(e.comissao, 0) 'Salario c/ Comissao'	
		FROM EMP e;
	-- Outra forma, usando o COALESCE
		SELECT e.nome, e.salario, e.comissao, e.salario + COALESCE(e.comissao, 0) 'Salario c/ Comissao'	
		FROM EMP e;

-- 4. Liste os departamentos existentes na empresa (aten��o! Sem repeti��es p.f.).
	SELECT DISTINCT e.depnum
	FROM EMP e;

-- 5. Liste os nomes, fun��o e sal�rio anual dos empregados da empresa, ordenados alfabeticamente por nome.
	-- 1� forma:
		SELECT e.nome, e.funcao, e.salario
		FROM EMP e
		ORDER BY e.nome;
	-- Outra forma: posicional
		SELECT e.nome, e.funcao, e.salario
		FROM EMP e
		ORDER BY 1;   -- ULTIMA LINHA A SER TRATADA

-- 6. Liste todos empregados (n�mero, nome, fun��o e n�mero de departamento) de todos os Administrativos
	SELECT e.empnum, e.nome, e.funcao, e.depnum
	FROM EMP e
	WHERE e.funcao = 'Admin';

-- 7. Liste os empregados cujo sal�rio anual � maior do que 20000�
	SELECT e.nome, e.salario
	FROM EMP e
	WHERE e.salario > 20000;

-- 8. Liste os empregados cujo sal�rio est� entre 15000� e 20000�
	SELECT e.nome, e.salario
	FROM EMP e
	WHERE e.salario BETWEEN 15000 AND 20000;

-- 9. Liste os empregados que sejam chefiados pelo n�33 e pelo n�39
	SELECT e.nome, e.chefe
	FROM EMP e
	WHERE e.chefe IN (33, 39);

-- 10. Liste os empregados cujo nome comece pela letra S
	SELECT e.nome
	FROM EMP e
	WHERE e.nome LIKE 'S%';

-- 11. Liste todos os vendedores com sal�rio maior que 16000�
	SELECT e.nome, e.funcao, e.salario
	FROM EMP e
	WHERE e.funcao = 'Vendedor' AND e.salario > 16000;

-- 12. Liste os empregados que n�o tenham chefe.
	SELECT e.nome, e.chefe
	FROM EMP e
	WHERE e.chefe IS NULL;

/* 
	13. Liste os empregados que ganhem mais do que 17000� (sal�rio + comiss�es), que sejam 
	vendedores, que trabalhem para o departamento 10 e que tenham sido admitidos
	antes de julho de 2000.
*/
	SELECT e.nome, e.salario, e.comissao, 
		   e.salario + isnull(e.comissao, 0) 'Sal�rio c/ Comiss�o', 
		   e.funcao, e.depnum, e.data_adm
	FROM EMP e
	WHERE e.salario + isnull(e.comissao, 0) > 17000 AND e.funcao = 'Vendedor' AND e.depnum = 10 AND data_adm < '2000.07.01'