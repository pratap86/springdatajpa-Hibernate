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

create table programmer(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(20),
salary int
);

create table project(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(20)
);

create table programmers_projects(
programmer_id int,
project_id int,
FOREIGN KEY (programmer_id)
REFERENCES programmer(id),
FOREIGN KEY (project_id)
REFERENCES project(id)
);

create table person(
id int PRIMARY KEY AUTO_INCREMENT,
first_name varchar(20),
last_name varchar(20),
age int
);

create table license(
id int PRIMARY KEY AUTO_INCREMENT,
type varchar(20),
valid_from date,
valid_to date,
person_id int,
FOREIGN KEY (person_id)
REFERENCES person(id)
);

CREATE TABLE doctor (
    id int NOT NULL ,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id,email)
);