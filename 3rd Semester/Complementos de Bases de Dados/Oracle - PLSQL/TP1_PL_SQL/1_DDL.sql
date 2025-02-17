CREATE TABLE freg_conc_dist(
id_codigo INT,
nome_distrito VARCHAR(50),
nome_concelho VARCHAR(50),
nome_freguesia VARCHAR(50),
CONSTRAINT TB_freg_conc_dist_pk PRIMARY KEY(id_codigo)
);

CREATE TABLE distrito(
id_distrito INT,
nome_distrito VARCHAR(50),
CONSTRAINT TB_distrito_pk PRIMARY KEY(id_distrito)
);

CREATE TABLE concelho(
id_concelho INT,
nome_concelho VARCHAR(50),
id_distrito INT,
CONSTRAINT TB_concelho_pk PRIMARY KEY(id_concelho)
);

CREATE TABLE freguesia(
id_freguesia INT,
nome_freguesia VARCHAR(50),
id_concelho INT,
CONSTRAINT TB_freguesia_pk PRIMARY KEY(id_freguesia)
);

ALTER TABLE concelho
ADD CONSTRAINT tb_concelho_FK_distrito
FOREIGN KEY (id_distrito) REFERENCES distrito(id_distrito);

ALTER TABLE freguesia
ADD CONSTRAINT tb__freguesia_FK_concelho
FOREIGN KEY (id_concelho) REFERENCES concelho(id_concelho);


--- DROPs ----
/*
ALTER TABLE concelho
DROP CONSTRAINT tb_concelho_FK_distrito;

ALTER TABLE freguesia
DROP CONSTRAINT tb__freguesia_FK_concelho;


DROP TABLE distrito;
DROP TABLE concelho;
DROP TABLE freguesia;
-- DROP TABLE freg_conc_dist;
*/
-------------------------------------------------------------


CREATE TABLE tipo_pluviosidade(
id_tipo_pluviosidade INT,
nome_tipo_pluviosidade VARCHAR(50),
descricao VARCHAR(100),
CONSTRAINT tb_tipoPluviosidade_PK PRIMARY KEY(id_tipo_pluviosidade) 
);

CREATE TABLE registo_chuva(
id_freguesia INT,
id_tipo_pluviosidade INT,
data_registo DATE,
valor DECIMAL(6,2),
CONSTRAINT tb_registo_chuva_PK 
PRIMARY KEY(id_freguesia, id_tipo_pluviosidade, data_registo)
);

ALTER TABLE registo_chuva
ADD CONSTRAINT tb_registo_chuva_FK_freguesia
FOREIGN KEY (id_freguesia) REFERENCES freguesia(id_freguesia);

ALTER TABLE registo_chuva
ADD CONSTRAINT tb_registo_chuva_FK_tipo_pluviosidade
FOREIGN KEY (id_tipo_pluviosidade) REFERENCES tipo_pluviosidade(id_tipo_pluviosidade);
