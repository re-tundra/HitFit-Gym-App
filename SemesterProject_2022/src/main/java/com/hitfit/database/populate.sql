INSERT INTO customers (first_name, last_name, email, phone_number, password, username, gender, weight, height, dob, membership, is_active, address)
VALUES
('John', 'Doe', 'john.doe@example.com', '1234567890', 'password123', 'johndoe', 'Male', 70.5, 1.75, '1990-05-15', 1000, 0, '123 Main St, City'),
('Jane', 'Doe', 'jane.doe@example.com', '0987654321', 'password456', 'janedoe', 'Female', 60.2, 1.65, '1992-08-20', 3000, 0, '456 Elm St, Town'),
('Alice', 'Smith', 'alice.smith@example.com', '5551234567', 'password789', 'alicesmith', 'Female', 55.0, 1.60, '1985-10-10', 4500, 0, '789 Oak St, Village'),
('Bob', 'Johnson', 'bob.johnson@example.com', '7778889999', 'passwordabc', 'bobjohnson', 'Male', 80.3, 1.80, '1988-12-25', 1000, 0, '321 Pine St, Country'),
('Emily', 'Williams', 'emily.williams@example.com', '2223334444', 'passworddef', 'emilywilliams', 'Female', 65.7, 1.70, '1995-03-30', 3000, 0, '654 Maple St, County'),
('Michael', 'Brown', 'michael.brown@example.com', '6665554444', 'passwordghi', 'michaelbrown', 'Male', 75.2, 1.78, '1979-07-05', 4500, 0, '987 Cedar St, Citytown'),
('Sarah', 'Jones', 'sarah.jones@example.com', '1112223333', 'passwordjkl', 'sarahjones', 'Female', 58.9, 1.63, '1983-09-12', 1000, 0, '741 Birch St, Hamlet'),
('David', 'Martinez', 'david.martinez@example.com', '3334445555', 'passwordmno', 'davidmartinez', 'Male', 68.4, 1.72, '1998-11-18', 3000, 0, '852 Willow St, Suburb'),
('Jessica', 'Garcia', 'jessica.garcia@example.com', '8889990000', 'passwordpqr', 'jessicagarcia', 'Female', 63.1, 1.68, '1982-06-23', 4500, 0, '369 Pineapple St, Tropical'),
('William', 'Hernandez', 'william.hernandez@example.com', '1231231234', 'passwordstu', 'williamhernandez', 'Male', 85.0, 1.85, '1976-04-07', 1000, 0, '258 Orange St, Sunshine'),
('Amanda', 'Lopez', 'amanda.lopez@example.com', '4564564567', 'passwordvwx', 'amandalopez', 'Female', 61.5, 1.67, '1991-01-14', 3000, 0, '147 Lemon St, Orchard'),
('Daniel', 'Gonzalez', 'daniel.gonzalez@example.com', '7897897890', 'passwordyz', 'danielgonzalez', 'Male', 72.8, 1.79, '1987-02-28', 4500, 0, '963 Grape St, Vineyard'),
('Olivia', 'Rodriguez', 'olivia.rodriguez@example.com', '9876543210', 'password123', 'oliviarodriguez', 'Female', 67.3, 1.71, '1989-10-03', 1000, 0, '753 Banana St, Tropics'),
('Ethan', 'Perez', 'ethan.perez@example.com', '1239876543', 'password456', 'ethanperez', 'Male', 78.6, 1.82, '1993-07-16', 3000, 0, '369 Pine St, Rainforest'),
('Sophia', 'Sanchez', 'sophia.sanchez@example.com', '4561237890', 'password789', 'sophiasanchez', 'Female', 62.9, 1.69, '1997-05-21', 4500, 0, '852 Forest St, Jungle'),
('James', 'Ramirez', 'james.ramirez@example.com', '7894561230', 'passwordabc', 'jamesramirez', 'Male', 76.4, 1.77, '1984-03-09', 1000, 0, '147 Pine St, Mountains'),
('Mia', 'Torres', 'mia.torres@example.com', '3216549870', 'passworddef', 'miatorres', 'Female', 64.2, 1.66, '1996-08-27', 3000, 0, '963 Hill St, Valley'),
('Benjamin', 'Flores', 'benjamin.flores@example.com', '6549873210', 'passwordghi', 'benjaminflores', 'Male', 73.7, 1.81, '1986-09-13', 4500, 0, '753 Ridge St, Canyon'),
('Charlotte', 'Gomez', 'charlotte.gomez@example.com', '2583691470', 'passwordjkl', 'charlottegomez', 'Female', 59.8, 1.64, '1981-11-24', 1000, 0, '369 Valley St, Desert'),
('Alexander', 'Reyes', 'alexander.reyes@example.com', '1472583690', 'passwordmno', 'alexanderreyes', 'Male', 79.1, 1.83, '1994-12-31', 3000, 0, '852 Oasis St, Dunes');

DECLARE @StartDate DATE = DATEADD(MONTH, -1, GETDATE())

INSERT INTO transactions (created_date, amount, p_method, account_owner_name, fk_customer_id, status)
VALUES
(DATEADD(DAY, -3, GETDATE()), 1000.00, 'cash', 'John Doe', 1, 0),
(DATEADD(DAY, -5, GETDATE()), 3000.00, 'online payment', 'Jane Doe', 2, 0),
(DATEADD(DAY, -10, GETDATE()), 4500.00, 'cash', 'Alice Smith', 3, 0),
(DATEADD(DAY, -2, GETDATE()), 1000.00, 'online payment', 'Bob Johnson', 4, 0),
(DATEADD(DAY, -4, GETDATE()), 3000.00, 'cash', 'Emily Williams', 5, 0),
(DATEADD(DAY, -7, GETDATE()), 4500.00, 'online payment', 'Michael Brown', 6, 0),
(DATEADD(DAY, -1, GETDATE()), 1000.00, 'cash', 'Sarah Jones', 7, 0),
(DATEADD(DAY, -6, GETDATE()), 3000.00, 'online payment', 'David Martinez', 8, 0),
(DATEADD(DAY, -9, GETDATE()), 4500.00, 'cash', 'Jessica Garcia', 9, 0),
(DATEADD(DAY, -8, GETDATE()), 1000.00, 'online payment', 'William Hernandez', 10, 0),
(DATEADD(DAY, -11, GETDATE()), 3000.00, 'cash', 'Amanda Lopez', 11, 0),
(DATEADD(DAY, -14, GETDATE()), 4500.00, 'online payment', 'Daniel Gonzalez', 12, 0),
(DATEADD(DAY, -13, GETDATE()), 1000.00, 'cash', 'Olivia Rodriguez', 13, 0),
(DATEADD(DAY, -12, GETDATE()), 3000.00, 'online payment', 'Ethan Perez', 14, 0),
(DATEADD(DAY, -15, GETDATE()), 4500.00, 'cash', 'Sophia Sanchez', 15, 0),
(DATEADD(DAY, -18, GETDATE()), 1000.00, 'online payment', 'James Ramirez', 16, 0),
(DATEADD(DAY, -16, GETDATE()), 3000.00, 'cash', 'Mia Torres', 17, 0),
(DATEADD(DAY, -17, GETDATE()), 4500.00, 'online payment', 'Benjamin Flores', 18, 0),
(DATEADD(DAY, -20, GETDATE()), 1000.00, 'cash', 'Charlotte Gomez', 19, 0),
(DATEADD(DAY, -19, GETDATE()), 3000.00, 'online payment', 'Alexander Reyes', 20, 0);