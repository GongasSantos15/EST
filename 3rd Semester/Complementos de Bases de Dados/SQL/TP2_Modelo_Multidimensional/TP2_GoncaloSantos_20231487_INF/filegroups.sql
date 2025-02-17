/* 1. Criação dos FILEGROUPS */

	-- Filegroup para as Tabelas de Dimensão
	ALTER DATABASE INF_20231487_2425
	ADD FILEGROUP FG_TD;
	
	-- Filegroup para os Indices
	ALTER DATABASE INF_20231487_2425
	ADD FILEGROUP FG_INDICES;

	-- Filegroup para o 1º Semestre da Tabela de Factos
	ALTER DATABASE INF_20231487_2425
	ADD FILEGROUP FG_1SEMESTRE_TF;

	-- Filegroup para o 2º Semestre da Tabela de Factos
	ALTER DATABASE INF_20231487_2425
	ADD FILEGROUP FG_2SEMESTRE_TF;

/* 2. Criação dos Files em cada Filegroup */

	-- File para as Tabelas de Dimensão
	ALTER DATABASE INF_20231487_2425 ADD FILE
		( 
			NAME = f_td,
			FILENAME = 'f:\fgf\f_td.ndf',
			SIZE = 1MB,
			MAXSIZE = 50MB,
			FILEGROWTH = 1MB)
	TO FILEGROUP FG_TD;

	-- File para os Indices
	ALTER DATABASE INF_20231487_2425 ADD FILE
		( 
			NAME = dw_i,
			FILENAME = 'g:\fgg\f_indices.ndf',
			SIZE = 1MB,
			MAXSIZE = 50MB,
			FILEGROWTH = 1MB)
	TO FILEGROUP FG_INDICES;

	-- File para as a 1ª Partição da Tabela de Factos
	ALTER DATABASE INF_20231487_2425 ADD FILE
		( 
			NAME = dw_1semestre_tf,
			FILENAME = 'h:\fgh\dw_1semestre_tf.ndf',
			SIZE = 1MB,
			MAXSIZE = 50MB,
			FILEGROWTH = 1MB)
	TO FILEGROUP FG_1SEMESTRE_TF;

	-- File para as a 2ª Partição da Tabela de Factos
	ALTER DATABASE INF_20231487_2425 ADD FILE
		( 
			NAME = dw_2semestre_tf,
			FILENAME = 'i:\fgi\f_2semestre_tf.ndf',
			SIZE = 1MB,
			MAXSIZE = 50MB,
			FILEGROWTH = 1MB)
	TO FILEGROUP FG_2SEMESTRE_TF;

/* 3. Criação das Tabelas num determinado FILEGROUP */
	
	-- Tabelas de Dimensão
	CREATE TABLE dim_tempo(
		id_tempo INT NOT NULL,
		dia INT,
		diaSemana VARCHAR(20),
		mes INT,
		ano INT
	)
	ON FG_TD;

	ALTER TABLE dim_tempo ADD CONSTRAINT tb_dim_tempo_pk PRIMARY KEY(id_tempo);

	CREATE TABLE dim_localizacao(
		id_localizacao INT NOT NULL,
		freguesia VARCHAR(100),
		distrito VARCHAR(100)
	)
	ON FG_TD;

	ALTER TABLE dim_localizacao ADD CONSTRAINT tb_dim_localizacao_pk PRIMARY KEY(id_localizacao);

	CREATE TABLE dim_precipitacao(
		id_precipitacao INT NOT NULL,
		tipoChuva VARCHAR(100),
	)
	ON FG_TD;

	ALTER TABLE dim_precipitacao ADD CONSTRAINT tb_dim_precipitacao_pk PRIMARY KEY(id_precipitacao);

	-- Indices
	CREATE TABLE indices(
		id_indice INT NOT NULL,
		id_tempo INT,
		id_localizacao INT,
		id_precipitacao INT,
	)
	ON FG_INDICES;

	ALTER TABLE indices ADD CONSTRAINT tb_indices_pk PRIMARY KEY(id_indice);
	
	ALTER TABLE indices
	ADD CONSTRAINT FK_indices_tempo
	FOREIGN KEY (id_tempo) REFERENCES dim_tempo(id_tempo);

	ALTER TABLE indices
	ADD CONSTRAINT FK_indices_localizacao
	FOREIGN KEY (id_localizacao) REFERENCES dim_localizacao(id_localizacao);

	ALTER TABLE indices
	ADD CONSTRAINT FK_indices_precipitacao
	FOREIGN KEY (id_precipitacao) REFERENCES dim_precipitacao(id_precipitacao);


	-- Criação da Tabela de Factos Particionada
	CREATE PARTITION FUNCTION
	partFuncPorIdTempo (int)
	AS RANGE LEFT
	FOR VALUES (181)

	CREATE PARTITION SCHEME partSchemePorIdTempo
	AS PARTITION partFuncPorIdTempo
	TO (FG_1SEMESTRE_TF, FG_2SEMESTRE_TF)

	-- Tabela de Factos
		CREATE TABLE tabela_factos(
			id_facto INT NOT NULL,
			id_tempo INT NOT NULL,
			id_localizacao INT,
			id_precipitacao INT,
			duracao INT,
			quantidade INT,
		) ON partSchemePorIdTempo(id_tempo)

		ALTER TABLE tabela_factos
		ADD CONSTRAINT tb_tabela_factos_pk
		PRIMARY KEY (id_tempo, id_facto);

		ALTER TABLE tabela_factos
		ADD CONSTRAINT FK_tabela_factos_tempo
		FOREIGN KEY (id_tempo) REFERENCES dim_tempo(id_tempo);

		ALTER TABLE tabela_factos
		ADD CONSTRAINT FK_tabela_factos_localizacao
		FOREIGN KEY (id_localizacao) REFERENCES dim_localizacao(id_localizacao);

		ALTER TABLE tabela_factos
		ADD CONSTRAINT FK_tabela_factos_precipitacao
		FOREIGN KEY (id_precipitacao) REFERENCES dim_precipitacao(id_precipitacao);
	
	-- Drop FK
		ALTER TABLE tabela_factos
		DROP CONSTRAINT FK_tabela_factos_1_semestre_tempo; 

		ALTER TABLE tabela_factos
		DROP CONSTRAINT FK_tabela_factos_localizacao; 

		ALTER TABLE tabela_factos
		DROP CONSTRAINT FK_tabela_factos_precipitacao; 

	-- Drop table
		DROP TABLE tabela_factos;