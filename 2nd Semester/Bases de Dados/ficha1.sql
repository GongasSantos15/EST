USE BD_20231487_2324_INF

/*
	Crie a tabela, definindo como chave prim�ria o atributo �empnum�.
	i. Insira a primeira linha (Considere que o formato da data pode
	variar, ex: �2001.10.10�; e que a comiss�o n�o tem valor, ex:
	NULL);
	ii. Teste o mecanismo de chave prim�ria, repetindo a inser��o da
	mesma linha;
	iii. Remova a tabela (DROP TABLE emp).
*/

--Criar a PK (PRIMARY KEY) depois de todos os atributos, com CONSTRAINT
CREATE TABLE EMP
(
	empnum INT NOT NULL, 
	nome VARCHAR(20), 
	funcao VARCHAR(20), 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL, 
	comissao REAL, 
	depnum INT
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum)
);

SELECT *
FROM EMP;

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (89, 'Antunes', 'Admin', 35, '10.10.2001', 14000, null, 10);

DROP TABLE EMP;

/* 
	Crie de novo a tabela com as seguintes restri��es:
	i. Definindo como chave prim�ria o atributo �empnum�;
	ii. O nome o empregado n�o se pode repetir (UNIQUE);
	Escola Superior de Tecnologia de Castelo Branco
	Base de Dados I
	iii. Teste o mecanismo, repetindo a inser��o de uma mesma linha
	(alterando o empnum, mas repetindo o nome do empregado);
	iv. Remova a tabela (DROP TABLE emp).
*/

--Criar a tabela usando o atributo UNIQUE para n�o se repetir o nome
CREATE TABLE EMP
(
	empnum INT, 
	nome VARCHAR(20) UNIQUE, 
	funcao VARCHAR(20), 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL, 
	comissao REAL, 
	depnum INT
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum)
);

SELECT *
FROM EMP;

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (89, 'Antunes', 'Admin', 35, '10.10.2001', 14000, null, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (99, 'Antunes', 'Admin', 35, '10.10.2001', 14000, null, 10);

DROP TABLE EMP;

/*
	Crie de novo a tabela com as seguintes restri��es:
	i. Definindo como chave prim�ria o atributo �empnum�;
	ii. Crie uma restri��o global que impe�a a inser��o de sal�rios com
	valor igual ou inferior a zero (CHECK);
	iii. Teste o mecanismo de restri��o geral, inserindo uma linha com
	sal�rio igual a zero, e outra com sal�rio inferior a zero.
	iv. Remova a tabela (DROP TABLE emp).
*/

--Criar a tabela com constraint para verificar se o salario > 0
CREATE TABLE EMP
(
	empnum INT, 
	nome VARCHAR(20) UNIQUE, 
	funcao VARCHAR(20), 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL, 
	comissao REAL, 
	depnum INT
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum)
	CONSTRAINT tb_emp_CHK_salario CHECK (salario > 0)
);

SELECT *
FROM EMP;

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (89, 'Antunes', 'Admin', 35, '10.10.2001', 14000, null, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (90, 'Joao', 'Admin', 35, '10.10.2001', 0, null, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (91, 'Maria', 'Admin', 35, '10.10.2001', -200, null, 10);

DROP TABLE EMP;

/* 
	Crie de novo a tabela com as seguintes restri��es:
	i. Definindo como chave prim�ria o atributo �empnum�;
	ii. O valor do sal�rio por defeito deve ser 100 (DEFAULT);
	iii. Teste o mecanismo default inserindo uma linha sem preencher o
	valor do sal�rio;
	iv. Remova a tabela (DROP TABLE emp)
*/

--Criar a tabela usando o atibuto DEFAULT para colocar o valor do salario a 100 se n�o for preenchido
CREATE TABLE EMP
(
	empnum INT, 
	nome VARCHAR(20) UNIQUE, 
	funcao VARCHAR(20), 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL DEFAULT 100, 
	comissao REAL, 
	depnum INT,
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum),
	CONSTRAINT tb_emp_CHK_salario CHECK (salario > 0),
);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, comissao, depnum)
VALUES (89, 'Antunes', 'Admin', 35, '10.10.2001', null, 10);

SELECT *
FROM EMP;

DROP TABLE EMP;

/*
	Crie de novo a tabela com as seguintes restri��es:
	i. Definindo como chave prim�ria o atributo �empnum�;
	ii. Defina que a fun��o tem obrigatoriamente de ser preenchida
	(NOT NULL);
	iii. Teste o mecanismo not null inserindo uma linha sem preencher
	o valor da fun��o;
	iv. Remova a tabela (DROP TABLE emp)
*/

--Criar a tabela usando o atributo NOT NULL para n�o colocar null no valor da fun�ao
CREATE TABLE EMP
(
	empnum INT, 
	nome VARCHAR(20) UNIQUE, 
	funcao VARCHAR(20) NOT NULL, 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL DEFAULT 100, 
	comissao REAL, 
	depnum INT,
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum),
	CONSTRAINT tb_emp_CHK_salario CHECK (salario > 0),
);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (89, 'Antunes', NULL, 35, '10.10.2001', 14000, null, 10);

SELECT *
FROM EMP;

DROP TABLE EMP;

/*
	Criar a tabela com os comandos SQL e introduza todas as linhas com a
	informa��o descrita no exemplo
*/

CREATE TABLE EMP
(
	empnum INT, 
	nome VARCHAR(20) UNIQUE, 
	funcao VARCHAR(20) NOT NULL, 
	chefe INT, 
	data_adm DATETIME, 
	salario REAL DEFAULT 100, 
	comissao REAL, 
	depnum INT,
	CONSTRAINT tb_emp_PK PRIMARY KEY (empnum),
	CONSTRAINT tb_emp_CHK_salario CHECK (salario > 0),
);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (89, 'Antunes', 'Admin', 35, '10.10.2001', 14000, NULL, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (45, 'Pascoal', 'Vendedor', 35, '14.5.2000', 15000, 4000, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (69, 'Silva', 'Admin', 39, '4.12.2000', 14000, NULL, 40);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (78, 'Rato', 'Vendedor', 35, '9.11.2002', 16000, 3000, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (36, 'Santos', 'Vendedor', 39, '25.8.2001', 13000, 500, 40);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (35, 'Alves', 'Chefe', 79, '3.6.2000', 23000, NULL, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (39, 'Monteiro', 'Chefe', 79, '14.9.2000', 23000, NULL, 40);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (85, 'Farias', 'Analista', 35, '12.12.2002', 20000, NULL, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (79, 'Xavier', 'Presidente', NULL, '1.6.2000', 40000, NULL, 10);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (30, 'Brito', 'Vendedor', 39, '2.3.2002', 14000, 6000, 40);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (12, 'Ribeiro', 'Vendedor', 98, '16.7.2000', 15000, 3000, 30);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (18, 'Barros', 'Analista', 98, '12.3.2000', 20000, NULL, 30);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (46, 'Souto', 'Vendedor', 33, '4.5.2001', 15000, 2000, 20);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (32, 'Rafael', 'Admin', 98, '26.11.2000', 13000, NULL, 30);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (98, 'Neto', 'Chefe', 79, '28.11.2001', 23000, NULL, 30);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (77, 'Moura', 'Analista', 33, '3.8.2000', 20000, NULL, 20);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (21, 'Pires', 'Admin', 33, '18.9.2000', 14000, NULL, 20);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (14, 'Lima', 'Admin', 33, '23.5.2002', 14000, NULL, 20);

INSERT INTO EMP (empnum, nome, funcao, chefe, data_adm, salario, comissao, depnum)
VALUES (33, 'Barroso', 'Chefe', 79, '24.2.2000', 20000, NULL, 20);

SELECT *
FROM EMP;

DROP TABLE EMP;
