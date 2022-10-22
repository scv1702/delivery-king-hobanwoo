-- ***********************************************************************
-- * Contents:
-- 1. Dname_change trigger TEST
-- 2. BusinessHour_VIOLATION trigger TEST
-- ***********************************************************************

-- 1. Dname_change trigger TEST
-- 학과명 변경시 해당 학과를 참조하고 있는 모든 릴레이션 또한 갱신
ALTER TRIGGER Dname_change ENABLE;

-- 학과 이름을 바꾸기 TEST
-- 바꾸기 전
SELECT *
FROM USERS U
WHERE U.dname='IT대학';
-- USER_ID      USER_NAME     DNAME      PASSWORD     PHONE_NUMBER
-- -----------    ---------------    ----------     --------------    ---------------------
--         3          jwillavoys2        IT대학      G6DjPD               870-733-2996
--         4          dkybird3           IT대학      29HTIojH            142-994-2550
--         7          rbratchell6         IT대학      vcEY3cMpwNi5     328-471-6380
--         32          jlamontv          IT대학      0YpZLm1fG         793-351-5936
--         35        mgodrichy         IT대학      Zige4eN6BQ        404-304-7162
--         60          tkidwell1d         IT대학      YXUyGy             694-748-5018

SELECT *
FROM Cooperates C
WHERE c.dname='IT대학';
--  STORE_ID       DNAME
--  ------------     -----------
--            8        IT대학
--            10       IT대학
--            18       IT대학

UPDATE DEPARTMENT 
SET Dname = '컴퓨터학부'
WHERE Dname = 'IT대학';

-- 바꾸고 난 후
SELECT *
FROM USERS U
WHERE U.dname='컴퓨터학부';
-- USER_ID      USER_NAME     DNAME         PASSWORD     PHONE_NUMBER
-- -----------    ---------------    ------------     ---------------    ---------------------
--         3          jwillavoys2     컴퓨터학부     G6DjPD                870-733-2996
--         4          dkybird3        컴퓨터학부     29HTIojH             142-994-2550
--         7          rbratchell6      컴퓨터학부     vcEY3cMpwNi5     328-471-6380
--         32          jlamontv       컴퓨터학부     0YpZLm1fG          793-351-5936
--         35        mgodrichy       컴퓨터학부     Zige4eN6BQ        404-304-7162
--         60          tkidwell1d      컴퓨터학부     YXUyGy              694-748-5018

SELECT *
FROM Cooperates C
WHERE c.dname='컴퓨터학부';
--  STORE_ID       DNAME
--  ------------     -----------
--            8        컴퓨터학부
--            10       컴퓨터학부
--            18       컴퓨터학부

-- 2. BusinessHour_VIOLATION trigger TEST
-- 영업 시간이 지난 Store에 Order 넣을 시 발생 트리거
ALTER TRIGGER BusinessHour_VIOLATION ENABLE;

-- 영업 시간이 지난 Store에 Order 넣을 시 오류 보고 TEST
INSERT INTO Orders VALUES(51, 1, 7, '카드', '접수 중', to_date(sysdate, 'yyyy-mm-dd hh24:mi:ss'))

-- 명령의 50 행에서 시작하는 중 오류 발생 -
-- INSERT INTO Orders VALUES(51, 1, 7, '카드', '접수 중', to_date(sysdate, 'yyyy-mm-dd hh24:mi:ss'))
-- 오류 보고 -
-- ORA-20007: 영업시간 종료 -> 주문불가
-- ORA-06512: "DELIVERYKINGHOBANWOO.BUSINESSHOUR_VIOLATION", 9행
-- ORA-04088: 트리거 'DELIVERYKINGHOBANWOO.BUSINESSHOUR_VIOLATION'의 수행시 오류

SELECT * 
FROM orders
WHERE Order_ID = 51;
-- 선택된 행 없음

-- 영업 시간 내에 Store에 Order에 넣을 시 TEST
INSERT INTO Orders VALUES(51, 1, 7, '카드', '접수 중', to_date(sysdate, 'yyyy-mm-dd hh24:mi:ss'))
-- 1 행 이(가) 삽입되었습니다.

SELECT * 
FROM orders
WHERE Order_ID = 51;
-- ORDER_ID   USER_ID   STORE_ID   PAYMENT    STATE       ORDER_DATE
-- ------------   ---------   -----------   -----------    ---------   -----------------
--           51           1              7           카드    접수 중          22/10/22