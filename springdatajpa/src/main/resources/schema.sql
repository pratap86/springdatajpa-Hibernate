CREATE TABLE PRODUCT(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
description varchar(100),
price decimal(8,3) 
);

CREATE TABLE EMPLOYEE(
id int PRIMARY KEY,
first_name varchar(20),
last_name varchar(20)
);

create table student(
id int PRIMARY KEY AUTO_INCREMENT,
lname varchar(20),
fname varchar(20),
score int
);

create table payment(
id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
amount decimal(8,3)
);

create table card(
id int,
cardnumber varchar(20),
FOREIGN KEY (id) REFERENCES payment(id)
);

create table bankcheck(
id int,
checknumber varchar(20),
FOREIGN KEY (id) REFERENCES payment(id)
);

create table employeecom(
id int,
fname varchar(20),
lname varchar(20),
streetaddress varchar(30),
city varchar(20),
state varchar(20),
zipcode varchar(20),
country varchar(20)
);

create table customer(
id int PRIMARY KEY AUTO_INCREMENT,
fname varchar(20),
lname varchar(20)
);

create table phone_number(
id int AUTO_INCREMENT PRIMARY KEY,
customer_id int,
number varchar(20),
type varchar(20),
 FOREIGN KEY (customer_id)
REFERENCES customer(id)
);