 /* FICHA 2 */

-- Criação da Tabela Messages
CREATE TABLE messages (results VARCHAR2 (60))

-- Visualização da tabela messages
SELECT * FROM messages;

/* 
10. Crie um bloco PL/SQL para seleccionar o nome do funcionário com um determinado
salário.
a. Se o salário informado retornar mais do que uma linha, trate a excepção com um handler de
excepção apropriado e insira, na tabela MESSAGES, a mensagem "More than one employee with a
salary of <salário>".
b. Se o salário escolhido não retornar qualquer linha, trate a excepção com um handler de excepção
apropriado e insira, na tabela MESSAGES, a mensagem "No employee with a salary of <salário>".
c. Se o salário escolhido retornar apenas uma linha, insira, na tabela MESSAGES, o nome do
funcionário e o valor do salário.
d. Teste o bloco para vários casos.
*/ 

SET SERVEROUTPUT ON;

CLEAR SCREEN;

-- Prompt para introduzir o salário
ACCEPT vs_v_salario PROMPT 'Introduza o salário: '

-- Declaração das variáveis (substituição ou não)
DECLARE
    v_salario emp.sal%TYPE := &vs_v_salario;
    v_ename emp.ename%TYPE;
    
BEGIN

    -- Query para visualizar o nome do empregado com x salário
    DELETE FROM messages;
    SELECT ename
    INTO v_ename
    FROM emp
    WHERE sal = v_salario;
    
    -- Print dos dados
    DBMS_OUTPUT.PUT_LINE('NomeEmp: ' || v_ename);
    
    -- Inserir os dados na tabela messages para visualizar os resultados
    INSERT INTO messages VALUES (v_ename || '--' || v_salario);
    
    -- Tratamento das exceções pedidas no enunciado
    EXCEPTION 
        WHEN TOO_MANY_ROWS THEN
            INSERT INTO messages VALUES ('More than one employee with a salary of: ' || v_salario);
            
        WHEN NO_DATA_FOUND THEN
            INSERT INTO messages VALUES ('No employee with a salary of: ' || v_salario);
    
END;