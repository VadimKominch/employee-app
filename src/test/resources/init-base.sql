CREATE TABLE  if not exists employee
(
    employee_id IDENTITY NOT NULL PRIMARY KEY ,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    department_id integer NOT NULL,
    job_title varchar NOT NULL,
    gender varchar NOT NULL,
    date_of_birth date NOT NULL
);


insert into employee (first_name, last_name, department_id, job_title, gender, date_of_birth) values ('Vadim','Kominch',2,'engineer','male','1998-05-21');
insert into employee (first_name, last_name, department_id, job_title, gender, date_of_birth) values ('Vadim','Kominch',2,'engineer','male','1998-05-21');
insert into employee (first_name, last_name, department_id, job_title, gender, date_of_birth) values ('Vadim','Kominch',2,'engineer','male','1998-05-21');