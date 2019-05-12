drop table EMP_DATA_MASTER if exists;
create table EMP_DATA_MASTER (
    emp_no INTEGER IDENTITY NOT NULL PRIMARY KEY,
    emp_dept_id VARCHAR(15) NOT NULL,
    emp_name VARCHAR(50) NOT NULL,
    emp_gender VARCHAR(2),
    emp_phone_no VARCHAR(15),
    emp_address VARCHAR(100),
    emp_age INTEGER,
    create_time timestamp(0),
    update_time timestamp(0),
    PRIMARY KEY (emp_no)
);

drop table EMP_DEPT if exists;
create table EMP_DEPT(
    emp_dept_id INTEGER IDENTITY NOT NULL PRIMARY KEY,
    emp_dept_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (emp_dept_id)
);

insert into EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
values ('1', 'IT', 'Zyaire', 'M', '0912345600', 'taipei', '32', sysdate, sysdate);
insert into EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
values ('12', 'IX', 'Neveah', 'F', '0912345605', 'taipei', '30', sysdate, sysdate);
insert into EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
values ('196', 'PA', 'Leyla', 'F', '0912345609', 'tainan', '28', sysdate, sysdate);