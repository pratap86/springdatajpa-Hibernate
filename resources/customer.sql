use mydb

CREATE TABLE customer (
    id int NOT NULL ,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id,email)
);

select * from customer


drop table customer
