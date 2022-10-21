-- Department Name Change
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

--test
UPDATE DEPARTMENT 
SET Dname = '컴퓨터학부'
WHERE Dname = 'IT대학';

rollback;
commit;


-- 영업시간이 지난 Store에 Order 넣을 시 발생
CREATE OR REPLACE TRIGGER BusinessHour_VIOLATION
BEFORE INSERT ON Orders
FOR EACH ROW
DECLARE
   Business_Hour NUMBER;
BEGIN
    SELECT S.business_hour INTO Business_Hour
    FROM Store S
    WHERE S.Store_ID = :NEW.Store_ID;
    
    IF (TO_NUMBER(TO_CHAR(SYSDATE, 'HH24')) >= Business_Hour) THEN
        RAISE_APPLICATION_ERROR(-20007, '영업시간 종료 -> 주문불가');
    END IF;
END;
/
ALTER TRIGGER BusinessHour_VIOLATION ENABLE;

-- test
-- insert into store values(7, '대구 북구 경대로 19 17', '분식', 
-- '조아라떡볶이닭강정', '053-292-5959', NULL, 1000, '7.jpeg', 17);
-- 영업시간: 17시까지
insert into Orders VALUES(51, 1, 7, '카드', '접수 중', to_date(sysdate, 'yyyy-mm-dd hh24:mi:ss'));

select * 
from orders;

rollback;
commit;