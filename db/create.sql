-- Active: 1662196093279@@127.0.0.1@3306@db

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

CREATE TABLE PrivilegeLists
(
    Privilege INT NOT NULL,
    Actions VARCHAR(255) NOT NULL,
    PRIMARY KEY (Privilege, Actions)
);

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

CREATE TABLE AppStaff
(
    A_Staff_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    UserID INT UNSIGNED NOT NULL,
    PRIMARY KEY  (A_Staff_ID),
    FOREIGN KEY (UserID) REFERENCES `User`(UserID)
);

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

CREATE TABLE RCategory
(
    RCategory_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    RCategory_Name VARCHAR(20) NOT NULL,
    RCategory_Description VARCHAR(100),
    PRIMARY KEY (RCategory_ID)
);

CREATE TABLE Restaurant_RCategory
(
    RCategory_ID INT UNSIGNED NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    PRIMARY KEY (RCategory_ID, Restaurant_ID),
    FOREIGN KEY (RCategory_ID) REFERENCES RCategory(RCategory_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

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

CREATE TABLE Coupon
(
    Coupon_ID INT PRIMARY KEY AUTO_INCREMENT,
    Coupon_Name VARCHAR(255) NOT NULL,
    Coupon_Scope INT NOT NULL,
    Coupon_Type INT NOT NULL,
    Coupon_Min_Money INT,
    Created_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Coupon_Value DOUBLE NOT NULL,
    Coupon_Description VARCHAR(255) NOT NULL,
    Coupon_Image VARCHAR(255) NOT NULL
);

CREATE TABLE Coupon_Item
(
    Coupon_ID INT NOT NULL,
    Item_ID INT NOT NULL,
    PRIMARY KEY (Coupon_ID, Item_ID),
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID),
    FOREIGN KEY (Item_ID) REFERENCES Menu_Item(Item_ID)
);

CREATE TABLE Distribution_Rule
(
    D_Rule_ID INT PRIMARY KEY AUTO_INCREMENT,
    Rule_Description VARCHAR(255) NOT NULL,
    Additional_Conditions VARCHAR(255)
);

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
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID),
    FOREIGN KEY (D_Rule_ID) REFERENCES Distribution_Rule(D_Rule_ID),
    FOREIGN KEY (User_ID) REFERENCES `User`(UserID)
);

CREATE TABLE C_Batch_Customer
(
    C_Batch_ID INT NOT NULL,
    Customer_ID INT NOT NULL,
    Coupon_Status INT NOT NULL,
    Receive_Date TIMESTAMP NOT NULL,
    PRIMARY KEY (C_Batch_ID, Customer_ID),
    FOREIGN KEY (C_Batch_ID) REFERENCES Coupon_Batch(C_Batch_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

CREATE TABLE Coupon_R
(
    Coupon_ID INT NOT NULL,
    Restaurant_ID INT UNSIGNED NOT NULL,
    PRIMARY KEY (Coupon_ID, Restaurant_ID),
    FOREIGN KEY (Coupon_ID) REFERENCES Coupon(Coupon_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

CREATE TABLE Driver
(
    Driver_ID INT NOT NULL AUTO_INCREMENT,
    User_ID INT UNSIGNED NOT NULL,
    Number_Plate VARCHAR(10) NOT NULL UNIQUE,
    Vehicle_Description VARCHAR(20) NOT NULL,
    Rating FLOAT,
    D_Account_Name VARCHAR(20) NOT NULL,
    D_BSB INT NOT NULL,
    D_Account_Number INT NOT NULL,
    PRIMARY KEY (Driver_ID),
    FOREIGN KEY (User_ID) REFERENCES User(UserID)
);

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

CREATE TABLE Order_Item
(
    Order_ID INT NOT NULL,
    Item_ID INT NOT NULL,
    Quantity INT,
    Comment VARCHAR(100),
    FOREIGN KEY (Order_ID) REFERENCES db.Order(Order_ID),
    FOREIGN KEY (Item_ID) REFERENCES Menu_Item(Item_ID)
);

