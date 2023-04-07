-- Active: 1666500888416@@127.0.0.1@3306
USE db;

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
VALUES(321321, 'Burger', 303030, 10.00, 200, 'Chicken Burger', 'Chicken Burger', 'Fried Chicken, Buns, Lettuce, Ketchup, Mayo', 'None', 100);

--Drink
INSERT INTO menu_item (Restaurant_ID, Item_Type, Servings, Price, Calories, Image, Description, Ingredients, Allergy, Stock)
VALUES(321321, 'Drink', 303030, 2.00, 10, 'Coca cola', 'Coca cola', 'Coca cola', 'None', 100);

-- Side
INSERT INTO menu_item (Restaurant_ID, Item_Type, Servings, Price, Calories, Image, Description, Ingredients, Allergy, Stock)
VALUES(321321, 'Side', 303030, 5.00, 100, 'Chips', 'Chips', 'Potato, Salt', 'None', 100);

--Order
INSERT INTO db.Order (Customer_ID, Restaurant_ID, Order_Type, Coupon_ID, Status, Food_Rating, Food_Instructions, Food_Feedback)
VALUES(989899, 321321, 'Delivery', NULL, 'Accepted', NULL, NULL, NULL);

--Order Item
INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(1, 1, 1, NULL);

INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(1, 2, 2, NULL);

INSERT INTO order_item (Order_ID, Item_ID, Quantity, Comment)
VALUES(1, 3, 1, NULL);

-- SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1

-- Select * from db.Order;

SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1;

SELECT * FROM Order_ITEM WHERE Order_ID = 1;


-- Set ROWC 1
-- DELETE FROM ORDER_ITEM WHERE Order_ID = 88 AND Item_ID=1
-- Set row 0;

-- 88 order 103 item 1

-- DELETE FROM db.order WHERE Order_ID = 1;

