INSERT INTO users (first_name, last_name, phone_number, email, username, password)
VALUES
('John', 'Doe', '123456789', 'john.doe@cydeo.com', 'john_doe', 'password123'),
('Jane', 'Doe', '987654321', 'jane.doe@cydeo.com', 'jane_doe', 'password456'),
('Alice', 'Smith', '345678901', 'alice.smith@cydeo.com', 'alice_smith', 'password789'),
('Bob', 'Johnson', '456789012', 'bob.johnson@cydeo.com', 'bob_johnson', 'passwordabc'),
('Emily', 'Wilson', '567890123', 'emily.wilson@cydeo.com', 'emily_wilson', 'passwordxyz');

INSERT INTO accounts (account_number, account_type, base_currency, balance, user_id)
VALUES
(1234567890, 'CHECKING', 'EUR', 5000.00, 1),
( 0987654321, 'SAVING', 'EUR', 10000.00, 1),
( 2345678901, 'CHECKING', 'EUR', 3000.00, 2),
(9876543210, 'SAVING', 'EUR', 7500.00, 2),
(3456789012, 'CHECKING', 'EUR', 7000.00, 3),
( 8765432109, 'SAVING', 'EUR', 12000.00, 3),
(4567890123, 'CHECKING', 'EUR', 4500.00, 4),
( 7654321098, 'SAVING', 'EUR', 8000.00, 4),
( 5678901234, 'CHECKING', 'EUR', 6000.00, 5),
( 6543210987, 'SAVING', 'EUR', 9500.00, 5);