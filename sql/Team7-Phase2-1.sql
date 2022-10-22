drop table store cascade constraints;
drop table menu cascade constraints;
drop table department cascade constraints;
drop table users cascade constraints;
drop table user_address cascade constraints;
drop table coupon cascade constraints;
drop table review cascade constraints;
drop table orders cascade constraints;
drop table order_menu cascade constraints;
drop table contains cascade constraints;
drop table cooperates cascade constraints;

-- create relations
CREATE TABLE Store(
    Store_ID NUMBER NOT NULL,
    Address VARCHAR2(45) NOT NULL,
    Food_Category VARCHAR2(30) NOT NULL,
    Store_Name VARCHAR2(30) NOT NULL,
    Phone_Number VARCHAR2(14) NOT NULL,
    Description VARCHAR2(200),
    Delivery_Fee NUMBER NOT NULL,
    Image VARCHAR2(100),
    Business_Hour NUMBER NOT NULL,
    PRIMARY KEY(Store_ID),
    UNIQUE(Address)
);

CREATE TABLE Menu(
    Menu_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Mname VARCHAR2(50) NOT NULL,
    Description VARCHAR2(500),
    Image VARCHAR2(100),
    Price NUMBER NOT NULL,
    PRIMARY KEY(Menu_ID)
);

CREATE TABLE Department(
    Dname VARCHAR2(24) NOT NULL,
    PRIMARY KEY(Dname)
);

CREATE TABLE Users(
    User_ID NUMBER NOT NULL,
    User_Name VARCHAR2(30) NOT NULL,
    Dname VARCHAR2(24) NOT NULL,
    Password VARCHAR2(15) NOT NULL,
    Phone_Number VARCHAR2(14) NOT NULL,
    Membership_Tier VARCHAR2(20) NOT NULL,
    PRIMARY KEY(User_ID),
    UNIQUE(Phone_Number)
);

CREATE TABLE User_Address(
    User_ID NUMBER NOT NULL,
    UAddress VARCHAR2(100) NOT NULL,
    PRIMARY KEY(User_ID, UAddress)
);

CREATE TABLE Coupon(
    Coupon_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Discount_Amount NUMBER NOT NULL,
    Expiration_Date DATE NOT NULL,
    Minimum_Order_Amount NUMBER NOT NULL,
    State VARCHAR2(15) NOT NULL,
    PRIMARY KEY(Coupon_ID)
);

CREATE TABLE Review(
    Review_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Star_Rating NUMBER NOT NULL,
    Comments VARCHAR2(500),
    Created_At DATE NOT NULL,
    PRIMARY KEY(Review_ID)
);

CREATE TABLE Orders(
    Order_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Payment VARCHAR2(100) NOT NULL,
    State VARCHAR2(100) NOT NULL,
    Order_Date DATE NOT NULL,
    PRIMARY KEY(Order_ID)
);

CREATE TABLE Order_Menu(
    Order_Menu_ID NUMBER NOT NULL,
    Order_ID NUMBER NOT NULL,
    Menu_Name VARCHAR2(50) NOT NULL,
    Menu_Image VARCHAR2(100),
    Menu_Price NUMBER NOT NULL,
    Quantity NUMBER NOT NULL,
    PRIMARY KEY(Order_Menu_ID)
);

CREATE TABLE Contains(
    Order_Menu_ID NUMBER NOT NULL,
    Review_ID NUMBER NOT NULL,
    PRIMARY KEY(Order_Menu_ID, Review_ID)
);

CREATE TABLE Cooperates(
    Store_ID NUMBER NOT NULL,
    Dname VARCHAR2(30) NOT NULL,
    PRIMARY KEY(Store_ID, Dname)
);

-- foreign key
ALTER TABLE Menu ADD FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
ON DELETE CASCADE;

ALTER TABLE Cooperates ADD FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
ON DELETE CASCADE;

ALTER TABLE Review ADD FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
ON DELETE CASCADE;

ALTER TABLE Orders ADD FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
ON DELETE SET NULL;

ALTER TABLE Contains ADD FOREIGN KEY (Order_Menu_ID) REFERENCES Order_Menu(Order_Menu_ID);

ALTER TABLE Users ADD FOREIGN KEY (Dname) REFERENCES Department(Dname)
ON DELETE SET NULL;

ALTER TABLE Cooperates ADD FOREIGN KEY (Dname) REFERENCES Department(Dname)
ON DELETE SET NULL;

ALTER TABLE Orders ADD FOREIGN KEY (User_ID) REFERENCES Users(User_ID);

ALTER TABLE Review ADD FOREIGN KEY (User_ID) REFERENCES Users(User_ID);

ALTER TABLE Coupon ADD FOREIGN KEY (User_ID) REFERENCES Users(User_ID);

ALTER TABLE User_Address ADD FOREIGN KEY (User_ID) REFERENCES Users(User_ID)
ON DELETE CASCADE;

ALTER TABLE Contains ADD FOREIGN KEY (Review_ID) REFERENCES Review(Review_ID)
ON DELETE CASCADE;

ALTER TABLE Order_Menu ADD FOREIGN KEY (Order_ID) REFERENCES Orders(Order_ID)
ON DELETE CASCADE;

-- 학과명 변경시 해당 학과를 참조하고 있는 모든 릴레이션 또한 갱신
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

-- 영업 시간이 지난 Store에 Order 넣을 시 발생
CREATE OR REPLACE TRIGGER BusinessHour_VIOLATION
BEFORE INSERT ON Orders
FOR EACH ROW
DECLARE
   Business_Hour NUMBER;
BEGIN
    SELECT S.business_hour INTO Business_Hour
    FROM Store S
    WHERE S.Store_ID = :NEW.Store_ID;
    IF (TO_NUMBER(TO_CHAR(:NEW.Order_Date, 'HH24')) >= Business_Hour) THEN
        RAISE_APPLICATION_ERROR(-20007, '영업 시간 종료로 인한 주문 불가');
    END IF;
END;
/

ALTER TRIGGER Dname_change DISABLE;
ALTER TRIGGER BUSINESSHOUR_VIOLATION DISABLE;