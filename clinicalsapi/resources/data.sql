insert into patient(id, first_name, last_name, age) values
(1,'Pratap','Narayan',52),
(2,'Siva','Shankar',32),
(3,'Sanjay','Nishad',22),
(4,'Bruce','Sanhurst',33),
(5,'Abhram','Mani',55),
(6,'Gandhi','Singh',12),
(7,'Nirmal','Singh',27),
(8,'Simba','White',24),
(9,'Rose','Tanic',29),
(10,'Shankar','Goel',49);



insert into clinicaldata(id, patient_id, component_name, component_value, measured_date_time)  values
('1', '1', 'bp', '67/119', '2018-07-09 19:34:24'),
('2', '2', 'bp', '63/115', '2018-06-19 19:34:24'),
('3', '3', 'bp', '72/129', '2018-07-26 19:34:24'),
('4', '4', 'bp', '74/139', '2018-08-03 19:34:24'),
('5', '5', 'bp', '67/119', '2018-08-29 19:34:24'),
('6', '6', 'bp', '62/109', '2018-07-12 19:34:24'),
('7', '7', 'bp', '55/102', '2018-06-13 19:34:24'),
('8', '8', 'bp', '47/90', '2018-08-02 19:34:24');