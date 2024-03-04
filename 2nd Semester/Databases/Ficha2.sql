USE BD_20231487_2324_INF;

/* Usar uma sigla de 1 letra para selecionar qual tabela estamos a retirar a coluna 
	EX: SELECT e.nome
		FROM nomes AS e;  A palavra AS PODE SER OMITIDA QUE O SIGNIFICADO NÃO MUDA
*/

SELECT * 
FROM EMP;


/* COMEÇO FICHA 2 */
-- 1. Liste os números de todos os empregados, os seus nomes e números dos seus chefes. 

-- 2. Considerando que o salário é o salário anual, liste o salário mensal de cada empregado 
	 SELECT e.nome, e.salario SalarioAnual, e.salario/12 SalarioMensal
	 FROM EMP e;

-- 3. Liste, para todos os empregados, o seu salário mais comissões.
	-- Usando o isnull (para converter os valores nulos em 0, de forma a podermos somar)
		SELECT e.nome, e.salario, e.comissao, e.salario + isnull(e.comissao, 0) 'Salario c/ Comissao'	
		FROM EMP e;
	-- Outra forma, usando o COALESCE
		SELECT e.nome, e.salario, e.comissao, e.salario + COALESCE(e.comissao, 0) 'Salario c/ Comissao'	
		FROM EMP e;

-- 4. Liste os departamentos existentes na empresa (atenção! Sem repetições p.f.).
	SELECT DISTINCT e.depnum
	FROM EMP e;

-- 5. Liste os nomes, função e salário anual dos empregados da empresa, ordenados alfabeticamente por nome.
	-- 1ª forma:
		SELECT e.nome, e.funcao, e.salario
		FROM EMP e
		ORDER BY e.nome;
	-- Outra forma: posicional
		SELECT e.nome, e.funcao, e.salario
		FROM EMP e
		ORDER BY 1;   -- ULTIMA LINHA A SER TRATADA

-- 6. Liste todos empregados (número, nome, função e número de departamento) de todos os Administrativos
	SELECT e.empnum, e.nome, e.funcao, e.depnum
	FROM EMP e
	WHERE e.funcao = 'Admin';

-- 7. Liste os empregados cujo salário anual é maior do que 20000€
	SELECT e.nome, e.salario
	FROM EMP e
	WHERE e.salario > 20000;

-- 8. Liste os empregados cujo salário está entre 15000€ e 20000€
	SELECT e.nome, e.salario
	FROM EMP e
	WHERE e.salario BETWEEN 15000 AND 20000;

-- 9. Liste os empregados que sejam chefiados pelo nº33 e pelo nº39
	SELECT e.nome, e.chefe
	FROM EMP e
	WHERE e.chefe IN (33, 39);

-- 10. Liste os empregados cujo nome comece pela letra S
	SELECT e.nome
	FROM EMP e
	WHERE e.nome LIKE 'S%';

-- 11. Liste todos os vendedores com salário maior que 16000€
	SELECT e.nome, e.funcao, e.salario
	FROM EMP e
	WHERE e.funcao = 'Vendedor' AND e.salario > 16000;

-- 12. Liste os empregados que não tenham chefe.
	SELECT e.nome, e.chefe
	FROM EMP e
	WHERE e.chefe IS NULL;

/* 
	13. Liste os empregados que ganhem mais do que 17000€ (salário + comissões), que sejam 
	vendedores, que trabalhem para o departamento 10 e que tenham sido admitidos
	antes de julho de 2000.
*/
	SELECT e.nome, e.salario, e.comissao, 
		   e.salario + isnull(e.comissao, 0) 'Salário c/ Comissão', 
		   e.funcao, e.depnum, e.data_adm
	FROM EMP e
	WHERE e.salario + isnull(e.comissao, 0) > 17000 AND e.funcao = 'Vendedor' AND e.depnum = 10 AND data_adm < '2000.07.01'