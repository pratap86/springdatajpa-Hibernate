use mydb;

create table customer(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(20)
);

create table phone_number(
id int PIRMARY KEY AUTO_INCREMENT,
customer_id int,
number varchar(20),
type varchar(20),
 FOREIGN KEY (customer_id)
REFERENCES customer(id)
)

select * from customer

select * from phone_number
