/* 1. Crie um procedimento (aumenta_salario) que receba como parâmetro de entrada um número de empregado e aumente o seu salário em 10%. */

-- Inserir valores na tabela emp
INSERT INTO emp 
VALUES(9999, 'FFidalgo', 'PRESIDENT', null, '81-NOV-17', 1000, null, 10);

-- CRIAÇÃO DE UM PROCEDIMENTO
    
    -- aumenta_salario como parâmetro de entrada o número do empregado
    CREATE OR REPLACE PROCEDURE aumenta_salario( v_num_emp IN emp.empno%TYPE )
    
    -- Secção declarativa
    IS 
    
    BEGIN
        UPDATE emp
        SET sal = sal * 1.10
        WHERE empno = v_num_emp;
    END; 
    
    -- Executar um procedimento
    EXECUTE aumenta_salario(9999);
    
    -- Executar um procedimento(outra forma -> num bloco de PL/SQL):
    CLEAR SCREEN;
    DECLARE
        v_num_emp emp.empno%TYPE := &vs_v_num_emp;
    BEGIN
        aumenta_salario(v_num_emp);
    END;    
    
    -- Apagar um procedimento
    DROP PROCEDURE aumenta_salario;
    
    -- Visualizar a tabela EMP
    SELECT * from emp;
    
/*
2. Crie um procedimento (consulta_emp) que receba como parâmetro de entrado um número de empregado e que tenha como parâmetros de saída o “nome”, 
“salário”, “comissão” desse mesmo empregado. No final da execução aceda a cada uma dessas variáveis como confirmação 
*/

CREATE OR REPLACE PROCEDURE consulta_emp(
    v_num_emp IN emp.empno%TYPE,
    v_ename OUT emp.ename%TYPE,
    v_sal OUT emp.sal%TYPE,
    v_comm OUT emp.comm%TYPE
)

IS

BEGIN 
    SELECT ename, sal, comm
    INTO v_ename, v_sal, v_comm
    FROM emp
    WHERE empno = v_num_emp;
END;  

-- Executar o procedimento
CLEAR SCREEN
DECLARE 
    v_num_emp emp.empno%TYPE := &vs_v_num_emp;
    o_ename emp.ename%TYPE;
    o_sal emp.sal%TYPE;
    o_comm emp.comm%TYPE;
BEGIN
    consulta_emp(v_num_emp, o_ename, o_sal, o_comm);
    
    DBMS_OUTPUT.PUT_LINE('Nome: ' || o_ename);
    DBMS_OUTPUT.PUT_LINE('Salário: ' || o_sal);
    DBMS_OUTPUT.PUT_LINE('Comissão: ' || o_comm);
END;

/* 5. Crie uma função (mostra_salario) que receba como parâmetro de entrada o identificador do empregado e retorne o seu salário. */

    -- SELECT * FROM emp

CREATE OR REPLACE FUNCTION mostra_salario(id_empregado IN emp.empno%TYPE)
RETURN NUMBER
    
    IS 
    
        v_sal emp.sal%TYPE;
        
        BEGIN
            SELECT sal
            INTO v_sal
            FROM emp
            WHERE id_empregado = empno;
            
            RETURN(v_sal);
        END;    

-- Chamada à função (mostra_salario)    
SELECT mostra_salario(9999)
FROM DUAL;

-- DUAL - Usar para obter por exemplo a data do sistema
SELECT sysdate FROM DUAL;

-- Chamada à função (EX: 3 - 'na linha comando')
VAR v_res NUMBER;
EXEC :v_res := mostra_salario(9999);
PRINT v_res;

-- Nome dos empregados que ganham o mesmo que o empregado num: '9999'
SELECT ename
FROM emp
WHERE sal = mostra_salario(9999);  
        
        /* 
        7. Crie um pacote (pacote_comissao) que valide valores para a comissão.
        Para tal use:
        
        - uma função privada (valida_comissao), que receba como parâmetro de entrada o valor de uma comissão e verifique se é superior à máxima comissão 
        existente, dando como resposta “False” nesse caso e “True” no caso inverso;
        
        - um procedimento publico (actualiza_comissão), que receba o identificador de um empregado e um valor para a comissão, como parâmetros de entrada. 
        Deve fazer a respectiva actualização do valor da comissão, para esse empregado apenas nos casos em que essa nova comissão não é superior à máxima 
        existente
        */
        
        -- CABEÇALHO DO PACOTE (Apenas os elementos públicos)
        CREATE OR REPLACE PACKAGE pacote_comissao
        
        IS
            -- Todos os elementos públicos
            PROCEDURE atualiza_comissao (
                vp_num_emp IN emp.empno%TYPE,
                vp_comm IN emp.comm%TYPE);
        
        END pacote_comissao;
            
        -- CORPO (BODY) DO PACOTE:
        CREATE OR REPLACE PACKAGE BODY pacote_comissao
            
            IS
                -- Descrição dos elementos públicos e PRIVADOS
                -- FUNÇÃO privada porque não está declarada no CABEÇALHO, apenas no BODY
                FUNCTION valida_comissao(vf_comm IN emp.comm%TYPE) 
                RETURN BOOLEAN
                
                IS
                    v_max_comm emp.comm%TYPE;
                
                    BEGIN
                        SELECT MAX(comm)
                        INTO v_max_comm
                        FROM emp;
                        
                        IF vf_comm > v_max_comm THEN
                            RETURN(FALSE);
                        ELSE
                            RETURN(TRUE);
                        END IF;    
                                
                END valida_comissao;
                        
                -- Procedimento PÚBLICO porque está definido no CABEÇALHO do pacote
                PROCEDURE atualiza_comissao( vp_num_emp IN emp.empno%TYPE, vp_comm IN emp.comm%TYPE)
                
                IS
                
                    BEGIN
                        IF valida_comissao(vp_comm) THEN
                            UPDATE emp
                            SET comm = vp_comm
                            WHERE empno = vp_num_emp;
                        ELSE 
                            RAISE_APPLICATION_ERROR(-20001, 'Comissão Inválida!');
                        END IF;     
                END atualiza_comissao;
                    
        END pacote_comissao;                    
        
        -- Execução do Pacote:
        EXEC pacote_comissao.atualiza_comissao(9999, 1300);