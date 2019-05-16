DROP TABLE EMP_DATA_MASTER IF EXISTS;
CREATE TABLE EMP_DATA_MASTER (
    emp_no INTEGER IDENTITY NOT NULL PRIMARY KEY,
    emp_dept_id VARCHAR(15) NOT NULL COMMENT '部門ID',
    emp_name VARCHAR(50) NOT NULL COMMENT '員工姓名',
    emp_gender VARCHAR(10) COMMENT '性別',
    emp_phone_no VARCHAR(15) COMMENT '電話',
    emp_address VARCHAR(150) COMMENT '地址',
    emp_age INTEGER COMMENT '年齡',
    create_time timestamp(0) COMMENT '建立時間',
    update_time timestamp(0) COMMENT '最後修改時間',
    PRIMARY KEY (emp_no)
);

DROP TABLE EMP_DEPT IF EXISTS;
CREATE TABLE EMP_DEPT(
    emp_dept_id VARCHAR(15) NOT NULL PRIMARY KEY,
    emp_dept_name VARCHAR(50) NOT NULL COMMENT '部門名稱',
    PRIMARY KEY (emp_dept_id)
);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('1', 'IT', 'Zyaire', 'MALE', '0912345600', 'Taipei', '32', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('12', 'IX', 'Neveah', 'FEMALE', '0912345605', 'Taipei', '30', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('19', 'IX', 'Leyla', 'FEMALE', '0912345609', 'Tainan', '28', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('31', 'IT', 'Abraham', 'MALE', '0912345600', 'Taipei', '41', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('42', 'IX', 'Alva', 'FEMALE', '0912345605', 'Taipei', '30', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('46', 'PA', 'Leyla', 'FEMALE', '0912345609', 'Tainan', '18', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('51', 'IT', 'Alonso', 'MALE', '0912345600', 'Taipei', '32', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('72', 'IX', 'Alexandra', 'FEMALE', '0912345605', 'Taipei', '30', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('86', 'PA', 'Beryl', 'FEMALE', '0912345609', 'Tainan', '28', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('104', 'IT', 'Barton', 'MALE', '0912345600', 'Taipei', '22', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('122', 'IX', 'Daisy', 'FEMALE', '0912345605', 'Taipei', '30', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('159', 'PA', 'Doris', 'FEMALE', '0912345609', 'Tainan', '28', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('172', 'IT', 'Clifford', 'MALE', '0912345600', 'Taipei', '46', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('188', 'IX', 'Flora', 'FEMALE', '0912345605', 'Taipei', '30', sysdate, sysdate);

INSERT INTO EMP_DATA_MASTER (emp_no, emp_dept_id, emp_name, emp_gender, emp_phone_no, emp_address, emp_age, create_time, update_time)
VALUES ('196', 'PA', 'Griselda', 'FEMALE', '0912345609', 'Tainan', '28', sysdate, sysdate);

INSERT INTO EMP_DEPT (emp_dept_id, emp_dept_name) VALUES ('IT','科技魔法部');
INSERT INTO EMP_DEPT (emp_dept_id, emp_dept_name) VALUES ('IX','隱密機動部');
INSERT INTO EMP_DEPT (emp_dept_id, emp_dept_name) VALUES ('PA','對外通訊部');