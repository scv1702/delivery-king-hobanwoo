CREATE OR REPLACE TRIGGER Dname_change
AFTER UPDATE OF Dname ON Department
FOR EACH ROW
BEGIN
    UPDATE Users U
    SET U.Dname = :NEW.Dname 
    WHERE U.Dname = :OLD.Dname;
    
    UPDATE Cooperates C
    SET  C.Dname = :NEW.Dname
    WHERE C.Dname = :OLD.Dname;
END;
/

ALTER TRIGGER Dname_change ENABLE;

SELECT * FROM USERS;

UPDATE DEPARTMENT 
SET Dname = '컴퓨터학부'
WHERE Dname = 'IT대학';

rollback;

commit;