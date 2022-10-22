-- Type 1
-- 1. IT대학에 속하는 USERS 정보 조회
SELECT U.User_ID, U.Password, U.Dname, U.Phone_Number
FROM USERS U
WHERE U.Dname = 'IT대학';                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
    
-- 2. User_ID가 1인 USERS가 가지고 있는 Coupon 정보 조회
SELECT C.Coupon_ID, C.User_ID, C.Discount_Amount, C.Expiration_date, C.Minimum_Order_Amount, C.State
FROM Coupon C
WHERE C.User_ID = 1;

-- Type 2
-- 1. User_ID가 1인 USERS가 속한 DEPARTMENT와 제휴된 전체 STORE 정보
SELECT S.Store_Name, S.Phone_Number, S.Description, S.Food_Category, S.Address
FROM USERS U, DEPARTMENT D, Cooperates C, Store S
WHERE User_ID = 1
AND U.Dname = C.Dname
AND C.Store_ID = S.Store_ID
AND U.Dname = D.Dname;

-- 2. User_ID가 1인 USER가 리뷰를 작성한 모든 Store 정보
SELECT S.Store_Name, S.Food_Category, S.Description, S.Image
FROM Store S, USERS U, Review R
WHERE U.User_ID = 2
AND U.User_ID = R.User_ID
AND R.Store_ID = S.Store_ID;

-- Type 3
-- 1. User_ID가 1인 USER가 현재까지 주문을 진행한 총 횟수
SELECT COUNT(*)
FROM USERS U, ORDERS O
WHERE U.User_ID = 2
AND U.User_ID = O.User_ID
GROUP BY U.User_ID;

-- 2. 각 USER가 가지고 있는 Coupon 중 가장 높은 할인 가격 조회
SELECT U.User_Name, MAX(C.Discount_Amount)
FROM USERS U, Coupon C
WHERE U.User_ID = C.User_ID
GROUP BY U.User_Name;

-- Type 4
-- 1. 모든 Order의 평균 메뉴 금액 이하의 메뉴에 대해서 Order_ID와 메뉴 이름, 메뉴 가격을 보이시오.
SELECT O.Order_ID, O.Menu_Name "메뉴 이름", O.Menu_Price "메뉴 가격"
FROM Order_Menu O
WHERE Menu_Price <= (SELECT AVG(Menu_Price)
		                   FROM Order_Menu);

-- 2. "간호대학"에 다니는 유저와 동일한 학과에 속하는 유저의 이름과 학과 정보, 주문내역을 조회
SELECT U.User_Name, U.Dname, O.Order_ID "주문 번호", S.Store_Name "주문한 가게"
FROM Users U, Orders O, Store S
WHERE U.User_ID = O.User_ID  AND S.Store_ID = O.Store_ID
AND U.Dname IN (SELECT U1.Dname
                FROM Users U1
                WHERE U1.Dname='간호대학');

-- Type 5
-- 1. 주문이 있는 유저의 이름과 전화번호 조회.
SELECT U.User_Name, U.Phone_Number
FROM USERS U
WHERE EXISTS(SELECT *
             FROM ORDERS O
             WHERE U.User_ID=O.User_ID);

-- 2. 리뷰를 쓴 적 있는 유저 ID와 이름 조회
SELECT U.User_ID, U.User_Name
FROM USERS U
WHERE EXISTS(SELECT *
             FROM REVIEW R
             WHERE U.User_ID=R.User_ID);
             
-- Type 6:
-- 1. ‘산격로’에 속하는 Store의 이름, 판매하는 메뉴 이름과 가격 정보 조회
SELECT S.Store_Name, M.Mname, M.Price
FROM Menu M, Store S
WHERE S.Store_Id = M.Store_ID AND S.Store_ID IN (SELECT S1.Store_ID
                                                 FROM Store S1
                                                 WHERE S1.address LIKE '%산격로%');

-- 2. '배달왕 호반우'를 통해 주문을 한 적이 있는 유저의 이름을 검색하시오.
SELECT U.User_Name
FROM USERS U
WHERE U.User_ID IN (SELECT User_ID FROM ORDERS);
                  
-- Type 7
-- 1. User_ID가 3 이하인 User의 이름과 학과, 저장된 주소 정보들을 조회
SELECT U.User_Name "사용자 이름", U.Dname "사용자 학과", UA.UAddress "사용자 주소"
FROM User_Address UA,
	   (SELECT User_ID, User_Name, Dname
	    FROM USERS
        WHERE User_ID <= 3) U
WHERE U.User_ID = UA.User_ID;

-- 2. IT대학에서 주문을 3회 이상 한 User의 이름과 학과를 조회
SELECT U.User_Name "사용자 이름", U.Dname "사용자 학과"
FROM (SELECT U.User_Name, U.Dname, COUNT(*)
      FROM USERS U, ORDERS O
      WHERE U.User_ID = O.User_ID
      GROUP BY U.User_Name, U.Dname
      HAVING COUNT(*) >= 3) U
WHERE U.Dname = 'IT대학';

-- Type 8
-- 1. IT대학과 제휴를 맺은 '산격로'에 위치한 Store 정보 (Store_ID 순으로 정렬)
SELECT S.Store_ID, S.Store_Name, S.Food_Category, S.Description
FROM Cooperates C, Store S
WHERE C.Dname = 'IT대학'
AND S.Store_ID = C.Store_ID
AND S.Store_ID IN (SELECT Store_ID 
                   FROM Store
                   WHERE Address LIKE '%산격로%')
ORDER BY S.Store_ID;

--  2. 주문 메뉴 수가 가장 많은 순서대로 학과 출력
SELECT C.Dname , count(*)
FROM Cooperates C, Orders O, Order_Menu M
WHERE C.Store_ID = O.Store_ID AND O.Order_ID = M.Order_ID
GROUP BY C.Dname 
ORDER BY count(*) DESC;

-- Type 9
-- 1. "사랑해요" Tier 에 있는 사용자 정보(User_ID, User_Name, 학과 이름) User_ID 순서대로 전체 조회
SELECT U2.User_ID, U2.User_Name, D.Dname
FROM Users U2, Department D
WHERE U2.User_ID in (SELECT U.User_ID
                     FROM Orders O, Users U
					 HAVING O.User_ID = U.User_ID
                     AND count(*) >= 4
					 GROUP BY U.User_ID)
AND U2.Dname = D.Dname
ORDER BY U2.User_ID;

-- 2. 주문 수가 50개 이상인 가게의 가게 정보와 제휴 학과 정보
SELECT C.Dname, S.Store_Name, S.Food_Category, S.Description
FROM Cooperates C, Store S
WHERE C.Store_ID = S.Store_ID
AND C.Store_ID in (SELECT O.Store_ID
                   from Orders O
                   GROUP BY O.Store_ID
                   HAVING COUNT(*) > 3);

-- Type 10
-- 1. '배달왕 호반우'에 가입했으나, 한번도 주문하지 않은 유저 정보 조회
SELECT U.User_ID, U.Password, U.Dname, U.Phone_Number
FROM Users U
MINUS
SELECT us.User_ID, us.Password, us.Dname, us.Phone_Number
FROM Users us
WHERE us.User_ID IN (SELECT O.User_ID
                     FROM Orders O);

-- 2. '산격로'에 위치한 가게 혹은 '아양로'에 위치한 가게에서 주문을 진행한 적 있는 유저 이름과 학과 조회
SELECT U.User_Name, U.Dname
FROM Users U, Orders O, Store S
WHERE U.User_ID = O.User_ID 
AND O.Store_ID = S.Store_ID 
AND S.Address LIKE '%산격로%'
UNION
SELECT U1.User_Name, U1.Dname
FROM Users U1, Orders O1, Store S1
WHERE U1.User_ID = O1.User_ID 
AND O1.Store_ID = S1.Store_ID 
AND S1.Address LIKE '%아양로%';
    