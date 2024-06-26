CREATE DATABASE gym_database;
USE gym_database;

CREATE TABLE customers (
    id INT PRIMARY KEY IDENTITY(1,1),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20),
    password VARCHAR(255),
    username VARCHAR(255),
    gender VARCHAR(10),
    weight FLOAT,
    height FLOAT,
    dob DATE,
    membership INT,
    is_active BIT,
    address VARCHAR(MAX)
);

CREATE TABLE transactions (
    id INT PRIMARY KEY IDENTITY(1,1),
    created_date DATE,
    amount INT,
    p_method VARCHAR(255),
    account_owner_name VARCHAR(255),
    fk_customer_id INT,
    status BIT,
    FOREIGN KEY (fk_customer_id) REFERENCES customers(id)
);

CREATE TABLE employees (
    id INT PRIMARY KEY IDENTITY(1,1),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    -- designation VARCHAR(255),
    -- nic_number VARCHAR(20),
    -- salary INT,
    -- gender VARCHAR(10),
    -- phone_number VARCHAR(20),
    -- joining_date DATE,
    username VARCHAR(255),
    password VARCHAR(255),
    -- salt VARCHAR(255),
    -- access INT,
    email VARCHAR(255)
);

-- CREATE TABLE expenses (
--     id INT PRIMARY KEY,
--     description VARCHAR(255),
--     created_date DATE,
--     amount INT,
--     month VARCHAR(20),
--     year VARCHAR(4),
--     fk_employee_id INT,
--     selected_date DATE,
--     FOREIGN KEY (fk_employee_id) REFERENCES employees(id)
-- );

-- CREATE TABLE queries (
--     id INT PRIMARY KEY,
--     heading VARCHAR(255),
--     email VARCHAR(255),
--     description TEXT,
--     created_date DATE,
--     username VARCHAR(255)
-- );

-- CREATE TABLE bmi (
--     id INT PRIMARY KEY,
--     weight FLOAT,
--     recorded_date DATE,
--     fk_customer_id INT,
--     recorded_month VARCHAR(20),
--     height FLOAT,
--     bmi_value FLOAT,
--     FOREIGN KEY (fk_customer_id) REFERENCES customers(id)
-- );

-- CREATE TABLE revenues (
--     id INT PRIMARY KEY,
--     for_month VARCHAR(20),
--     for_year VARCHAR(4),
--     updated_date DATE,
--     amount INT
-- );
