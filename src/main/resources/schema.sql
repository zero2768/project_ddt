DROP TABLE EMP_DATA_MASTER IF EXISTS;
CREATE TABLE EMP_DATA_MASTER (
    emp_no INTEGER IDENTITY NOT NULL PRIMARY KEY,
    emp_dept_id VARCHAR(15) NOT NULL COMMENT '部門ID',
    emp_name VARCHAR(50) NOT NULL COMMENT '員工姓名',
    emp_sex VARCHAR(2) COMMENT '性別',
    emp_phone_no VARCHAR(15) COMMENT '電話',
    emp_address VARCHAR(100) COMMENT '地址',
    emp_age INTEGER COMMENT '年齡',
    create_time timestamp(0) COMMENT '建立時間',
    update_time timestamp(0) COMMENT '更新時間',
    PRIMARY KEY (emp_no)
);

DROP TABLE EMP_DEPT IF EXISTS;
CREATE TABLE EMP_DEPT(
    emp_dept_id INTEGER IDENTITY NOT NULL PRIMARY KEY,
    emp_dept_name VARCHAR(50) NOT NULL COMMENT '部門名稱',
    PRIMARY KEY (emp_dept_id)
);