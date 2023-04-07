USE db;

# USER ROWS
INSERT INTO USER VALUES
    (DEFAULT, 'Benz', 'Chua', 'Ro0T!Ro0T!', 'benzchua31@gmail.com', 256367826, '2003-05-11', 11,
     'Kentucky', 2689, 'NSE', 'Bonword', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Bob', 'Bob', 'Bob#12345', 'bob12345@gmail.com', 223659898, '2000-09-21', 31,
     'Bobby', 2099, 'NSE', 'Bobcook', 'Austrelie', TRUE);
# Store STAFF USER ROWS
INSERT INTO USER VALUES
    (DEFAULT, 'Owner', 'S', 'S1', 'Owner@email.com', 212359838, '1985-01-11', 34,
     'SS', 2199, 'NSE', 'SP', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Aa', 'Bb', 'S2', 'Aa@email.com', 211259838, '2000-03-15', 30,
     'SX', 2099, 'NSE', 'XP', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Ab', 'Bc', 'S3', 'Ab@email.com', 719259238, '2001-02-25', 28,
     'SG', 2069, 'NSE', 'XT', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Ac', 'Bd', 'S4', 'Ac@email.com', 111359338, '2000-05-10', 11,
     'SU', 2089, 'NSE', 'XJ', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Ad', 'Be', 'S5', 'Ad@email.com', 221204838, '2000-12-11', 51,
     'SI', 2009, 'NSE', 'XK', 'Austrelie', TRUE);
INSERT INTO USER VALUES
    (DEFAULT, 'Ae', 'Bf', 'S6', 'Ae@email.com', 191204838, '1999-10-01', 24,
     'SW', 2404, 'NSE', 'XQ', 'Austrelie', TRUE);

# APPSTAFF ROWS
INSERT INTO appstaff (UserID)
SELECT UserID FROM USER WHERE EMAIL = 'benzchua31@gmail.com';

# RES CATEGORY ROWS
INSERT INTO rcategory VALUES (DEFAULT,'Fast Food', 'fast food');
INSERT INTO rcategory VALUES (DEFAULT,'Faster Food', 'faster food');
INSERT INTO rcategory VALUES (DEFAULT,'Slow Food', 'slow food');

# RES ROWS
INSERT INTO restaurant VALUES
    (DEFAULT,'Obama Fried Chicken', 'ofc.png', 91, 'Obama',
     1728, 'NSE', 'Chinatown', 'Austrelie', 1, 11122233344, 'Benz Chua',
     345678, 928718);

INSERT INTO restaurant VALUES
    (DEFAULT,'Obama Fish Chips', 'ofc.png', 18, 'Obama',
     1578, 'NSE', 'Chinatown', 'Austrelie', 1, 12123233544, 'Benzz Chua',
     342978, 920978);

# RES-RCAT ROWS
INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
SELECT RC.RCategory_ID, Res.Restaurant_ID
FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Fast Food') AS RC
         CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 11122233344) AS RES;

INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
SELECT RC.RCategory_ID, Res.Restaurant_ID
FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Faster Food') AS RC
         CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 11122233344) AS RES;

INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
SELECT RC.RCategory_ID, Res.Restaurant_ID
FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Slow Food') AS RC
         CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 12123233544) AS RES;

#PrivilegeLists ROWS
INSERT INTO PrivilegeLists VALUES (0, 'Visit');
INSERT INTO PrivilegeLists VALUES (-1, 'Departure');
INSERT INTO PrivilegeLists VALUES (1, 'Check');
INSERT INTO PrivilegeLists VALUES (5, 'AddMenu');
INSERT INTO PrivilegeLists VALUES (5, 'EditMenu');
INSERT INTO PrivilegeLists VALUES (4, 'Closing');
INSERT INTO PrivilegeLists VALUES (3, 'PMCustomer');
INSERT INTO PrivilegeLists VALUES (6, 'Cancel Order');
INSERT INTO PrivilegeLists VALUES (7, 'Make a request');
INSERT INTO PrivilegeLists VALUES (2, 'ReplyComment');
INSERT INTO PrivilegeLists VALUES (8, 'Make a request');
INSERT INTO PrivilegeLists VALUES (9, 'View log');
INSERT INTO PrivilegeLists VALUES (10, 'RemoveEmp');
INSERT INTO PrivilegeLists VALUES (10, 'EditEmpPrivilege');
# STAFF ROWS
INSERT INTO Staff VALUES
    (null, 3, 1, 10, 'Store Owner');
INSERT INTO Staff VALUES
    (null, 4, 1, 8, 'Manager');
INSERT INTO Staff VALUES
    (null, 5, 1, 0, 'NewComer');
INSERT INTO Staff VALUES
    (null, 6, 1, 3, 'General staff');
INSERT INTO Staff VALUES
    (null, 7, 1, 3, 'General staff');

#Coupon
INSERT INTO Coupon VALUES (null, "Coupon1", 0, 0, DEFAULT, 10.2 , "No Description", "images/no-photo-available.jpeg");
INSERT INTO Coupon VALUES (null, "Coupon2", 1, 3, DEFAULT, 4.5 , "No Description", "images/no-photo-available.jpeg");
INSERT INTO Coupon VALUES (null, "Coupon3", 2, 9.5, DEFAULT, 2 , "No Description", "images/no-photo-available.jpeg");

INSERT INTO Coupon_R VALUES (2, 1);
INSERT INTO Coupon_R VALUES (3, 1);

INSERT INTO Coupon_Item VALUES (3, 1);
INSERT INTO Coupon_Item VALUES (3, 2);
