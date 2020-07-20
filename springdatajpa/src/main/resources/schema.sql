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