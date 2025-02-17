
/*
Avalie cada uma das declarações a seguir. Determine quais delas não são validas e explique
porquê.
a. DECLARE v_id NUMBER(4);                      -- VÁLIDA
b. DECLARE v_x, v_y, v_z VARCHAR2(10);          -- INVÁLIDA
c. DECLARE v_birthdate DATE NOT NULL;           -- INVÁLIDA
d. DECLARE v_in_stock BOOLEAN := 1;             -- INVÁLIDA
*/

-- a. DECLARE v_id NUMBER(4); 
SET SERVEROUTPUT ON;

CLEAR SCREEN;
DECLARE
    v_id NUMBER(4);
    
BEGIN
    v_id:=10;
    DBMS_OUTPUT.PUT_LINE('O valor de v_id é: ' || v_id);
END;    

-- b. DECLARE v_x, v_y, v_z VARCHAR2(10);
SET SERVEROUTPUT ON;

CLEAR SCREEN;
DECLARE

    -- v_x, v_y, v_z VARCHAR2(10);    -- INVÁLIDO
    v_x VARCHAR2(10);
    v_y VARCHAR2(10);                 -- VÁLIDO
    v_z VARCHAR2(10);
    
BEGIN
    v_x:='OLA_X';
    v_y:='OLA_Y';
    v_z:='OLA_Z';
   
    DBMS_OUTPUT.PUT_LINE('O valor de v_x é: ' || v_x);
    DBMS_OUTPUT.PUT_LINE('O valor de v_y é: ' || v_y);
    DBMS_OUTPUT.PUT_LINE('O valor de v_z é: ' || v_z);
END;

-- c. DECLARE v_birthdate DATE NOT NULL
SET SERVEROUTPUT ON;

CLEAR SCREEN;
DECLARE 
   -- v_birthdate DATE NOT NULL;  -- INVÁLIDO
   v_birthdate DATE NOT NULL := '2000.01.02';

BEGIN
    DBMS_OUTPUT.PUT_LINE('O valor de v_birthdate é: ' || v_birthdate);
END;

/*
3. Crie e execute um bloco PL/SQL que aceite o seu nome através das variáveis de
substituição do SQL*Plus, e que mostre no ecrã, “Bom dia, Filipe”.
*/

SET SERVEROUTPUT ON;
CLEAR SCREEN;

ACCEPT vs_v_nome PROMPT 'Introduza o seu nome: '
DECLARE
    v_nome VARCHAR(30) := '&vs_v_nome';

BEGIN
    DBMS_OUTPUT.PUT_LINE('Bom dia, ' || v_nome);
END;    

/*
4. Determine cada um dos valores a seguir de acordo com a figura:
O valor de V_MESSAGE no sub-bloco.
O valor de V_TOTAL_COMP no bloco principal.
O valor de V_COMM no sub-bloco.
O valor de V_COMM no bloco principal.
O valor de V_MESSAGE no bloco principal.
*/

SET SERVEROUTPUT ON;
CLEAR SCREEN;

DECLARE
    V_SAL NUMBER(7,2) := 60000;
    V_COMM NUMBER(7,2) := V_SAL * .20;
    V_MESSAGE VARCHAR2(255) := 'eligible for comission';
    
BEGIN
    DECLARE
        V_SAL NUMBER(7,2) := 50000;
        V_COMM NUMBER(7,2) := 0;
        V_TOTAL_COMP NUMBER(7,2) := V_SAL + V_COMM;
    BEGIN
        V_MESSAGE := 'CLERK not' || V_MESSAGE;
        
        DBMS_OUTPUT.PUT_LINE('O valor de V_MESSAGE no sub-bloco é: ' || V_MESSAGE);
        DBMS_OUTPUT.PUT_LINE('O valor de V_COMM no sub-bloco é: ' || V_COMM);
    END;
    V_MESSAGE := 'SALESMAN' || V_MESSAGE;
    
     DBMS_OUTPUT.PUT_LINE('O valor de V_TOTAL_COMP no bloco principal é: ' || V_TOTAL_COMP);
     DBMS_OUTPUT.PUT_LINE('O valor de V_COMM no bloco principal é: ' || V_COMM);
     DBMS_OUTPUT.PUT_LINE('O valor de V_MESSAGE no bloco principal é: ' || V_MESSAGE);
    
END;    