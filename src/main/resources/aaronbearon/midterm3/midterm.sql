SHOW DATABASES; # e.g. sys, javabook
CREATE DATABASE northwind;
USE northwind;

SHOW TABLES;

SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'northwind' AND TABLE_NAME = 'orders';

SELECT * FROM categories;
SELECT * FROM customers;
SELECT * FROM employees;
SELECT * FROM `order details`;
SELECT * FROM orders;
SELECT * FROM products;
SELECT * FROM shippers;
SELECT * FROM suppliers;

SELECT *
	FROM `order details`, orders
    WHERE `order details`.OrderID = orders.OrderID AND `order details`.OrderID = 10248;




-- Use these 4 below.

-- #1
SELECT SUM(Quantity * (UnitPrice - Discount)) AS `Order Total`
	FROM `order details`
	WHERE OrderID = 10250;

-- #2
SELECT o.OrderDate, o.Freight, p.ProductName, p.UnitPrice AS 'Product Unit Price', od.Quantity, od.UnitPrice AS 'Order Unit Price', od.Discount
	FROM `order details` od, orders o, products p
	WHERE o.OrderID = od.OrderID
		AND od.ProductID = p.ProductID
        AND o.OrderID = 10248;

-- #3
SELECT ContactName, City
	FROM customers
    WHERE Country = 'USA' AND Region = 'OR'
    ORDER BY City;

-- #4
SELECT FirstName, LastName
	FROM employees
    WHERE YEAR(BirthDate) = 1958
    ORDER BY lastName;
