-- Active: 1662196093279@@127.0.0.1@3306@db
DROP DATABASE IF EXISTS db;
CREATE DATABASE db;

USE db;

DROP TABLE IF EXISTS User;
CREATE TABLE User
(
    UserID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    First_Name VARCHAR(10) NOT NULL,
    Last_Name VARCHAR(10) NOT NULL,
    `Password` VARCHAR(10) NOT NULL,
    Email VARCHAR(20) UNIQUE NOT NULL,
    PhoneNo INT NOT NULL,
    DOB DATE, /* YYYY-MM-DD */
    Street_Number INT NOT NULL,
    Street_Name VARCHAR(20) NOT NULL,
    Postcode INT NOT NULL,
    `State` VARCHAR(20) NOT NULL,
    Suburb VARCHAR(20) NOT NULL,
    Country VARCHAR(20) NOT NULL,
    Activated BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (UserID)
);

DROP TABLE IF EXISTS Restaurant;
CREATE TABLE Restaurant
(
    Restaurant_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    Restaurant_Name VARCHAR(20) NOT NULL,
    Image_Reference VARCHAR(100),
    Street_Number INT NOT NULL,
    Street_Name VARCHAR(20) NOT NULL,
    Postcode INT NOT NULL,
    `State` VARCHAR(20) NOT NULL,
    Suburb VARCHAR(20) NOT NULL,
    Country VARCHAR(20) NOT NULL,
    Activated BOOLEAN NOT NULL DEFAULT FALSE,
    ABN BIGINT NOT NULL,
    Account_Name VARCHAR(20) NOT NULL,
    BSB INT NOT NULL,
    Account_Number INT NOT NULL,
    PRIMARY KEY (Restaurant_ID)
);

DROP TABLE IF EXISTS PrivilegeLists;
CREATE TABLE PrivilegeLists
(
    Privilege INT NOT NULL,
    Actions VARCHAR(255) NOT NULL,
    PRIMARY KEY (Privilege, Actions)
);

DROP TABLE IF EXISTS Staff;
CREATE TABLE Staff
(
    Staff_ID INT NOT NULL AUTO_INCREMENT,
    UserID INT UNSIGNED NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    Privilege INT NOT NULL DEFAULT 0,
    Position VARCHAR(100),
    PRIMARY KEY (Staff_ID),
    FOREIGN KEY (UserID) REFERENCES `User`(UserID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID),
    FOREIGN KEY (Privilege) REFERENCES PrivilegeLists(Privilege)
);

DROP TABLE IF EXISTS AppStaff;
CREATE TABLE AppStaff
(
    A_Staff_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    UserID INT UNSIGNED NOT NULL,
    PRIMARY KEY  (A_Staff_ID),
    FOREIGN KEY (UserID) REFERENCES `User`(UserID)
);

DROP TABLE IF EXISTS Request;
CREATE TABLE Request
(
    Request_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    Restaurant_ID INT UNSIGNED NOT NULL,
    Request_Type VARCHAR(10) NOT NULL,
    Request_Date DATETIME NOT NULL DEFAULT NOW(),
    Request_Status INT NOT NULL DEFAULT 0,
    PRIMARY KEY (Request_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

DROP TABLE IF EXISTS RCategory;
CREATE TABLE RCategory
(
    RCategory_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    RCategory_Name VARCHAR(20) NOT NULL,
    RCategory_Description VARCHAR(100),
    PRIMARY KEY (RCategory_ID)
);

DROP TABLE IF EXISTS Restaurant_RCategory;
CREATE TABLE Restaurant_RCategory
(
    RCategory_ID INT UNSIGNED NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    PRIMARY KEY (RCategory_ID, Restaurant_ID),
    FOREIGN KEY (RCategory_ID) REFERENCES RCategory(RCategory_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer
(
    Customer_ID INT PRIMARY KEY AUTO_INCREMENT,
    User_ID INT UNSIGNED NOT NULL,
    Card_Number BIGINT,
    Card_Expiration DATE,
    Card_Pin INT,
    Card_Name VARCHAR(20),
    FOREIGN KEY (User_ID) REFERENCES `User`(UserID)
);

DROP TABLE IF EXISTS Menu_Item;
CREATE TABLE Menu_Item
(
    Item_ID INT PRIMARY KEY AUTO_INCREMENT,
    Restaurant_ID INT UNSIGNED NOT NULL,
    Item_Type Varchar(10) NOT NULL,
    Servings INT NOT NULL,
    Price FLOAT NOT NULL,
    Calories INT NOT NULL,
    Image VARCHAR(200) NOT NULL,
    `Description` VARCHAR(100) NOT NULL,
    Ingredients VARCHAR(100) NOT NULL,
    Allergy VARCHAR(50),
    Stock INT NOT NULL,
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

DROP TABLE IF EXISTS Coupon;
CREATE TABLE Coupon
(
    Coupon_ID INT PRIMARY KEY AUTO_INCREMENT,
    Coupon_Name VARCHAR(255) NOT NULL,
    Coupon_Scope INT NOT NULL,
    Coupon_Min_Money DOUBLE NOT NULL,
    Created_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Coupon_Value DOUBLE NOT NULL,
    Coupon_Description VARCHAR(255) NOT NULL,
    Coupon_Image VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Coupon_Item;
CREATE TABLE Coupon_Item
(
    Coupon_ID INT NOT NULL,
    Item_ID INT NOT NULL,
    PRIMARY KEY (Coupon_ID, Item_ID),
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID) ON DELETE CASCADE,
    FOREIGN KEY (Item_ID) REFERENCES Menu_Item(Item_ID)
);

DROP TABLE IF EXISTS Distribution_Rule;
CREATE TABLE Distribution_Rule
(
    D_Rule_ID INT PRIMARY KEY AUTO_INCREMENT,
    Rule_Description VARCHAR(255) NOT NULL,
    Additional_Conditions VARCHAR(255)
);

DROP TABLE IF EXISTS Coupon_Batch;
CREATE TABLE Coupon_Batch
(
    C_Batch_ID INT PRIMARY KEY AUTO_INCREMENT,
    Coupon_ID INT NOT NULL,
    D_Rule_ID INT NOT NULL,
    Total_D_Count INT NOT NULL,
    C_Batch_Status INT NOT NULL,
    Applicable_Customers INT NOT NULL,
    Created_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Valid_Days INT NOT NULL,
    Start_Date DATE NOT NULL,
    End_Date DATE NOT NULL,
    Distribution_Time TIMESTAMP NOT NULL,
    User_ID INT UNSIGNED NOT NULL,
    Receive_Num INT NOT NULL,
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID) ON DELETE CASCADE,
    FOREIGN KEY (D_Rule_ID) REFERENCES Distribution_Rule(D_Rule_ID),
    FOREIGN KEY (User_ID) REFERENCES `User`(UserID)
);

DROP TABLE IF EXISTS C_Batch_Customer;
CREATE TABLE C_Batch_Customer
(
    C_Batch_ID INT NOT NULL,
    Customer_ID INT NOT NULL,
    Coupon_Status INT NOT NULL,
    Receive_Date TIMESTAMP NOT NULL,
    PRIMARY KEY (C_Batch_ID, Customer_ID),
    FOREIGN KEY (C_Batch_ID) REFERENCES Coupon_Batch(C_Batch_ID) ON DELETE CASCADE,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

DROP TABLE IF EXISTS Coupon_R;
CREATE TABLE Coupon_R
(
    Coupon_ID INT NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    PRIMARY KEY (Coupon_ID, Restaurant_ID),
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID) ON DELETE CASCADE,
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

DROP TABLE IF EXISTS Driver;
CREATE TABLE Driver
(
    Driver_ID INT NOT NULL AUTO_INCREMENT,
    User_ID INT UNSIGNED NOT NULL,
    Number_Plate VARCHAR(10) NOT NULL UNIQUE,
    Vehicle_Description VARCHAR(20) NOT NULL,
    D_Account_Name VARCHAR(20) NOT NULL,
    D_BSB INT NOT NULL,
    D_Account_Number INT NOT NULL,
    PRIMARY KEY (Driver_ID),
    FOREIGN KEY (User_ID) REFERENCES User(UserID)
);

DROP TABLE IF EXISTS db.Order;
CREATE TABLE db.Order
(
    Order_ID INT NOT NULL AUTO_INCREMENT,
    Customer_ID INT NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    Order_Type VARCHAR(10),
    Coupon_ID INT,
    Status VARCHAR(15) NOT NULL,
    Food_Rating INT,
    Food_Instructions VARCHAR(100),
    Food_Feedback VARCHAR(100),
    PRIMARY KEY (Order_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID),
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID)
);

DROP TABLE IF EXISTS Delivery;
CREATE TABLE Delivery
(
    Delivery_ID INT NOT NULL AUTO_INCREMENT,
    Order_ID INT NOT NULL UNIQUE,
    Driver_ID INT,
    Delivery_Street VARCHAR(100),
    Delivery_Suburb VARCHAR(15),
    Delivery_State CHAR(3),
    Delivery_Postal VARCHAR(10),
    Delivery_Fee FLOAT,
    Driver_Rating INT,
    Driver_Instructions VARCHAR(100),
    Driver_Feedback VARCHAR(100),
    Driver_Tip FLOAT,
    PRIMARY KEY (Delivery_ID),
    FOREIGN KEY (Order_ID) REFERENCES db.Order(Order_ID),
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID)
);

DROP TABLE IF EXISTS Order_Item;
CREATE TABLE Order_Item
(
    Order_ID INT NOT NULL,
    Item_ID INT NOT NULL,
    Quantity INT,
    Comment VARCHAR(100),
    FOREIGN KEY (Order_ID) REFERENCES db.Order(Order_ID),
    FOREIGN KEY (Item_ID) REFERENCES Menu_Item(Item_ID)
);

#For testing
INSERT INTO USER VALUES(989898, "MINH QUAN", "TRAN", "ABC", "ASLDA", 12131, NULL, 131, "141", 1341, "SAD", "RLQK", "ASDKLJ", 1);
INSERT INTO CUSTOMER VALUES(202020, 989898, NULL, NULL, NULL, NULL);
INSERT INTO RESTAURANT VALUES(303030, "Testing", NULL, 123, "abc", 1234, "NSW", "North Ryde", "Australia", TRUE, 12345678, "test", 123456, 123);
INSERT INTO PRIVILEGELISTS VALUES(1, "Something");
INSERT INTO STAFF VALUES(123123, 989898, 303030, 1, "");
INSERT INTO MENU_ITEM VALUES(321321, 303030, "Main", 1, 15.90, 2000, "https://papparich.com.my/pr/wp-content/uploads/2016/07/1-pappa-chicken-rice-1.jpg", "Hainan Steamed Chicken Rice", "Rice, Chicken, Bean Sprouts, Chilli, Dark Soya Sauce", "None", 100);
INSERT INTO DB.ORDER VALUES(101010, 202020, 303030, "Delivery", NULL, "Order Received", NULL, "", NULL);
INSERT INTO ORDER_ITEM VALUES(101010, 321321, 1, "");
INSERT INTO DB.ORDER VALUES(111111, 202020, 303030, "Delivery", NULL, "Preparing", NULL, "Give me your money", NULL);

INSERT INTO ORDER_ITEM VALUES(111111, 321321, 2, "No Chili");
INSERT INTO DRIVER VALUES(454545, 989898, "AXY562", "Black Toyota", "CommBank", 117268, 45128935);

--User
INSERT INTO user (First_Name, Last_Name, Password, Email, PhoneNo, DOB, Street_Number, Street_Name, Postcode, State, Suburb, Country, Activated)
VALUES ('Timothy', 'Chan', '1234', 'tc@gmail.com', 1234567890, '2000-01-01', 1, 't Street', 2000, 'NSW', 'City', 'Australia', TRUE);

--Customer
INSERT INTO customer (User_ID, Card_Number, Card_Expiration, Card_Pin, Card_Name)
VALUES(989899, 3456789, '2002-01-01', 123, 'T Chan');

--Restaraunt
INSERT INTO restaurant (Restaurant_Name, Image_Reference, Street_Number, Street_Name, Postcode, State, Suburb, Country, Activated, ABN, Account_Name, BSB, Account_Number)
VALUES('OFC', 'ofc.png', 11, 'Hungry', 2000, 'NSW', 'City', 'Australia', TRUE, 1000000, 'OFC Street', 3333, 2222);

--Menu item

--Burger 
INSERT INTO menu_item (Restaurant_ID, Item_Type, Servings, Price, Calories, Image, Description, Ingredients, Allergy, Stock)
VALUES(303031, 'Burger', 303030, 10.00, 200, 'Chicken Burger', 'Chicken Burger', 'Fried Chicken, Buns, Lettuce, Ketchup, Mayo', 'None', 100);

--Drink
INSERT INTO menu_item (Restaurant_ID, Item_Type, Servings, Price, Calories, Image, Description, Ingredients, Allergy, Stock)
VALUES(303031, 'Drink', 303030, 2.00, 10, 'Coca cola', 'Coca cola', 'Coca cola', 'None', 100);

-- Side
INSERT INTO menu_item (Restaurant_ID, Item_Type, Servings, Price, Calories, Image, Description, Ingredients, Allergy, Stock)
VALUES(303031, 'Side', 303030, 5.00, 100, 'Chips', 'Chips', 'Potato, Salt', 'None', 100);

--Order
INSERT INTO db.Order (Customer_ID, Restaurant_ID, Order_Type, Coupon_ID, Status, Food_Rating, Food_Instructions, Food_Feedback)
VALUES(202021, 303031, 'Delivery', NULL, 'Accepted', NULL, NULL, NULL);

--Order Item
INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(111112, 321321, 1, NULL);

INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(111112, 321321, 2, NULL);

INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(111112, 321321, 1, NULL);

-- SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1

-- Select * from db.Order;

SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1;

SELECT * FROM Order_ITEM WHERE Order_ID = 1;