/*
 * Tracking Customer Orders and Products
 */
-- 0 NF
CREATE TABLE orders_0NF (
	order_items VARCHAR(100),
	order_total decimal,
	order_date date,
	cust_name varchar(100),
	cust_address varchar(100),
	cust_email varchar(100),
	cust_pass varchar(100)
);

-- 1 NF : Atomic Data, Identifying Column (PK)
CREATE TABLE orders_1NF (
	order_id INTEGER PRIMARY KEY,
	item_quanity INTEGER,
	item_name VARCHAR(100),
	item_price decimal,
	order_total decimal,
	order_date date,
	cust_firstname varchar(100),
	cust_lastname varchar(100),
	cust_address varchar(100),
	cust_email varchar(100),
	cust_pass varchar(100)
);

-- 2 NF : All qualities of 1NF but also NO partial dependencies
CREATE TABLE orders_2NF (
	order_id INTEGER PRIMARY KEY,
	cust_id INTEGER REFERENCES customers_2NF(cust_id),
	order_total decimal,
	order_date date
);

CREATE TABLE order_items_2NF (
	item_id INTEGER PRIMARY KEY,
	order_id INTEGER REFERENCES orders_2NF(order_id),
	item_name VARCHAR(100),
	item_quanity INTEGER,
	item_price decimal
);

CREATE TABLE customers_2NF (
	cust_id INTEGER PRIMARY KEY,
	cust_firstname varchar(100),
	cust_lastname varchar(100),
	cust_address varchar(100),
	cust_email varchar(100),
	cust_pass varchar(100)
);

-- 3NF - 2NF + no transitive dependencies
CREATE TABLE orders_3NF (
	order_id INTEGER PRIMARY KEY,
	cust_id INTEGER REFERENCES customers_2NF(cust_id),
	order_total decimal,
	order_date date
);

CREATE TABLE order_items_3NF (
	item_id INTEGER REFERENCES products_3NF(item_id),
	order_id INTEGER REFERENCES orders_2NF(order_id),
	item_quanity INTEGER,
	CONSTRAINT order_item_pk PRIMARY KEY (item_id, order_id)
);

CREATE TABLE products_3NF (
	item_id INTEGER PRIMARY KEY,
	item_name VARCHAR(100),
	total_quantity INTEGER,
	item_price decimal	
);

CREATE TABLE customers_3NF (
	cust_id INTEGER PRIMARY KEY,
	cust_login INTEGER REFERENCES login_3NF(login_id),
	cust_firstname varchar(100),
	cust_lastname varchar(100),
	cust_address varchar(100)
);

CREATE TABLE login_3NF (
	login_id INTEGER PRIMARY KEY,
	cust_email varchar(100),
	cust_pass varchar(100)
);