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
    Address VARCHAR(1000) NOT NULL,
    Food_Category VARCHAR(1000) NOT NULL,
    Store_Name VARCHAR(1000) NOT NULL,
    Phone_Number VARCHAR(1000) NOT NULL,
    Description VARCHAR(1000),
    Delivery_Fee NUMBER NOT NULL,
    Image VARCHAR(1000),
    Business_Hour NUMBER NOT NULL,
    PRIMARY KEY(Store_ID),
    UNIQUE(Address)
);

CREATE TABLE Menu(
    Menu_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Mname VARCHAR(1000) NOT NULL,
    Description VARCHAR(1000),
    Image VARCHAR(1000),
    Price NUMBER NOT NULL,
    PRIMARY KEY(Menu_ID)
);

CREATE TABLE Department(
    Dname VARCHAR(1000) NOT NULL,
    PRIMARY KEY(Dname)
);

CREATE TABLE Users(
    User_ID NUMBER NOT NULL,
    User_Name VARCHAR(1000) NOT NULL,
    Dname VARCHAR(1000) NOT NULL,
    Password VARCHAR(1000) NOT NULL,
    Phone_Number VARCHAR(1000) NOT NULL,
    --Membership_Tier VARCHAR(5) NOT NULL,
    PRIMARY KEY(User_ID),
    UNIQUE(Phone_Number)
);

CREATE TABLE User_Address(
    User_ID NUMBER NOT NULL,
    UAddress VARCHAR(1000) NOT NULL,
    PRIMARY KEY(User_ID, UAddress)
);

CREATE TABLE Coupon(
    Coupon_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Discount_Amount VARCHAR(1000) NOT NULL,
    Expiration_Date DATE NOT NULL,
    Minimum_Order_Amount VARCHAR(1000) NOT NULL,
    State VARCHAR(1000) NOT NULL,
    PRIMARY KEY(Coupon_ID)
);

CREATE TABLE Review(
    Review_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Star_Rating NUMBER NOT NULL,
    Comments VARCHAR(1000),
    Created_At DATE NOT NULL,
    PRIMARY KEY(Review_ID)
);

CREATE TABLE Orders(
    Order_ID NUMBER NOT NULL,
    User_ID NUMBER NOT NULL,
    Store_ID NUMBER NOT NULL,
    Payment VARCHAR(1000) NOT NULL,
    State VARCHAR(1000) NOT NULL,
    Order_Date DATE NOT NULL,
    PRIMARY KEY(Order_ID)
);

CREATE TABLE Order_Menu(
    Order_Menu_ID NUMBER NOT NULL,
    Order_ID NUMBER NOT NULL,
    Menu_Name VARCHAR(1000) NOT NULL,
    Menu_Image VARCHAR(1000),
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
    Dname VARCHAR(1000) NOT NULL,
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