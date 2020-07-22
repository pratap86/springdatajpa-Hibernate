use mydb

drop table patients_doctors

drop table appointment

drop table patient

drop table doctor


create table patient(
id int PRIMARY KEY AUTO_INCREMENT,
first_name varchar(20),
last_name varchar(30),
phone varchar(30),
provider_name varchar(30),
copay decimal(10,5)
)

create table doctor(
id int PRIMARY KEY AUTO_INCREMENT,
first_name varchar(20),
last_name varchar(30),
speciality varchar(30)
)

create table patients_doctors(
patient_id int,
doctor_id int,
FOREIGN KEY (patient_id)
REFERENCES patient(id),
FOREIGN KEY (doctor_id)
REFERENCES doctor(id)
)

create table appointment(
id int PRIMARY KEY AUTO_INCREMENT,
patient_id int,
doctor_id int,
appointment_time datetime,
started TINYINT(1),
ended TINYINT(1),
reason varchar(200),
FOREIGN KEY (patient_id)
REFERENCES patient(id),
FOREIGN KEY (doctor_id)
REFERENCES doctor(id)
)

select * from patient

select * from doctor

select * from appointment

select * from patients_doctors
