-- Active: 1662196093279@@127.0.0.1@3306@db

--For testing
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
INSERT INTO DRIVER VALUES(454545, 989898, "AXY562", "Black Toyota", NULL, "CommBank", 117268, 45128935);

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

SELECT ORDER_ITEM.ORDER_ID, MENU_ITEM.RESTAURANT_ID, ORDER_ITEM.Item_ID, ORDER_ITEM.Quantity, ORDER_ITEM.Comment, MENU_ITEM.price
FROM MENU_ITEM
INNER JOIN ORDER_ITEM ON MENU_ITEM.ITEM_ID= ORDER_ITEM.ITEM_ID;