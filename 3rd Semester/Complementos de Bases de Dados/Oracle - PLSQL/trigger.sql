/* 
1. Admitindo que o "Presidente" é o empregado com o maior salário, vamos criar um TRIGGER que verifique antes de toda a inserção, que o 
salário do novo empregado a iniserir não vai ser maior do que o do "Presidente".
    No caso de o ser, a inserção não pode ocorrer, e é gerada uma exceção que informa:
    "O salário" não pode ser maior que o do Presidente
    Caso o salário seja inferior ao do Presidente, a inserção realiza-se com sucesso.
*/

CREATE OR REPLACE TRIGGER verifica_sal_presidente

-- 1. Decidir se o TRIGGER atua ANTES ou DEPOIS (BEFORE / AFTER)
BEFORE

-- 2. Qual a ação que o TRIGGER vai 'controlar' (INSERT / UPDATE / DELETE / ou combinação de várias)
INSERT

-- 3. Qual a tabela em que o TRIGGER vai ficar associado (ON)
ON emp

-- SEMPRE que seja necessário usar ':NEW' ou ':OLD' temos de usar o 'FOR EACH ROW'
FOR EACH ROW

DECLARE
    v_max_sal emp.sal%TYPE; 

    BEGIN
        SELECT MAX(sal)
        INTO v_max_sal
        FROM emp;
        
        IF :NEW.sal > v_max_sal THEN
            RAISE_APPLICATION_ERROR(-20001, 'O salário não pode ser maior do que o do Presidente');
        END IF;    
        
END;       

SELECT * FROM emp ORDER BY sal DESC;

-- O TRIGGER deve impedir que esta inserção ocorra pq o valor do 'sal:5555', é maior do que o valor máximo atual (5000)
INSERT INTO emp VALUES('8888', 'Rui', 'CLERK', 9999, '82.12.17', 5555, 100, 10);

-- O TRIGGER deve impedir que esta inserção ocorra pq o valor do 'sal:4444', é menor do que o valor máximo atual (5000)
INSERT INTO emp VALUES('8888', 'Filipe', 'CLERK', 9999, '82.12.17', 4444, 100, 10);


/* 
1.1 Admitindo que o "Presidente" é o empregado com o maior salário, vamos criar um TRIGGER que verifique antes de toda a inserção, que o 
salário do novo empregado a iniserir não vai ser maior do que o do "Presidente".
    No caso de o ser, a inserção não pode ocorrer, e é gerada uma exceção que informa:
    "O salário" não pode ser maior que o do Presidente
    Caso o salário seja inferior ao do Presidente, a inserção realiza-se com sucesso.
    
    -- Queremos ainda que o TRIGGER não permita a atualização do atributo salário, para um valor superior ao máximo existente
*/

CREATE OR REPLACE TRIGGER verifica_sal_presidente

-- 1. Decidir se o TRIGGER atua ANTES ou DEPOIS (BEFORE / AFTER)
BEFORE

-- 2. Qual a ação que o TRIGGER vai 'controlar' (INSERT / UPDATE / DELETE / ou combinação de várias)
INSERT OR UPDATE

-- 3. Qual a tabela em que o TRIGGER vai ficar associado (ON)
ON emp

-- SEMPRE que seja necessário usar ':NEW' ou ':OLD' temos de usar o 'FOR EACH ROW'
FOR EACH ROW

DECLARE
    v_max_sal emp.sal%TYPE; 

    BEGIN
    
        SELECT MAX(sal)
        INTO v_max_sal
        FROM emp;
        
        -- Ao inserir:
        IF INSERTING THEN
            
            IF :NEW.sal > v_max_sal THEN
                RAISE_APPLICATION_ERROR(-20001, 'O salário não pode ser maior do que o do Presidente');
            END IF;
            
        END IF;
        
        -- Ao atualizar:
        IF UPDATING THEN
        
            IF :NEW.sal > v_max_sal THEN
                RAISE_APPLICATION_ERROR(-20002, 'O salário não pode ser atualizado para um salário maior do que o do Presidente');
            END IF;
            
        END IF;
        
END;    

-- INSERTING
    -- O TRIGGER deve impedir que esta inserção ocorra pq o valor do 'sal:5555', é maior do que o valor máximo atual (5000)
    INSERT INTO emp VALUES('8888', 'Rui', 'CLERK', 9999, '82.12.17', 5555, 100, 10);
    
    -- O TRIGGER deve permitir que esta inserção ocorra pq o valor do 'sal:4444', é menor do que o valor máximo atual (5000)
    INSERT INTO emp VALUES('8888', 'Filipe', 'CLERK', 9999, '82.12.17', 4444, 100, 10);

-- UPDATING
    -- O TRIGGER deve impedir que esta atualização ocorra, pq o valor do '':NEW.sal:6666' a inserir é maior do que o valor máximo atual 'MAX(sal): 5000'
    
    UPDATE emp
    SET sal = 6666
    WHERE empno = 7777;
    
    -- O TRIGGER deve permitir que esta atualização ocorra, pq o valor do '':NEW.sal:2222' a inserir é menor do que o valor máximo atual 'MAX(sal): 5000'
    UPDATE emp
    SET sal = 6666
    WHERE empno = 2222;
    
DROP TRIGGER verifica_sal_presidente;    
    
    
/*
2. Suponha agora que pretende guardar registo de cada alteração que é feita ao salário de um dado empregado, isto é, por cada alteração
guarda registo do empregado em causa, do anterior salário, do novo salário, e da data em que essa alteração ocorreu, numa tabela aux

REGISTA_ALTERACOES_SALARIO(empno, salario_antigo, salario_novo, data_alteracao)
*/    

-- 1. Vamos criar a tabela:
CREATE TABLE REGISTA_ALTERACOES_SALARIO(
    empno  NUMBER(4),
    salario_antigo NUMBER(7,2),
    salario_novo NUMBER(7,2),
    data_alteracao DATE
);

SELECT * FROM REGISTA_ALTERACOES_SALARIO;

-- 2. Vamos criar o TRIGGER
CREATE OR REPLACE TRIGGER regista_alteracoes
AFTER UPDATE ON emp

FOR EACH ROW

    BEGIN
        INSERT INTO REGISTA_ALTERACOES_SALARIO VALUES
        -- Obter o novo empno, o antigo salario (o que estava antes da alteração), o novo salario (o valor que vamos colocar) e a data (sistema)
        (:NEW.empno, :OLD.sal, :NEW.sal, sysdate); 
    END;    
    
-- 3. Teste
UPDATE emp
SET sal = 1111
WHERE empno = 8888;