CREATE USER C##_20231487 IDENTIFIED BY ola;

GRANT CREATE SESSION TO C##_20231487;

GRANT RESOURCE TO C##_20231487;

ALTER USER C##_20231487 QUOTA UNLIMITED ON users;
