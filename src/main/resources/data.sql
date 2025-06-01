-- Insert example users with BCrypt hashed passwords
INSERT INTO users (name, email, password) VALUES
    ('John Doe', 'john@example.com', '$2a$10$6hg/QH/dxXd0UdNXYzj3B.nUvZHiMxX7HkqT.QGdCqKzdt6NZKYta'),  -- password123
    ('Jane Smith', 'jane@example.com', '$2a$10$HXwzR3vYeQHVam.QHsm0/.4xy0QHUXqOBwR.F9GA8ICvWxSqPOPCi'),  -- password456
    ('Mike Johnson', 'mike@example.com', '$2a$10$YF.qtGGhhwYF3ccvxGRJZO4ZAj/X.qME6mxdVb.9kZRQVFGBpHhCi');  -- password789

-- Insert product categories
INSERT INTO categories (name, description) VALUES
    ('Electronics', 'Electronic devices and accessories'),
    ('Clothing', 'Fashion apparel and accessories'),
    ('Books', 'Physical and digital books'),
    ('Home & Garden', 'Home improvement and garden supplies');

-- Insert products
INSERT INTO products (name, description, price, stock, category_id) VALUES
    ('iPhone 14 Pro', 'Latest Apple smartphone with advanced features', 999.99, 50, 1),
    ('Samsung Galaxy S23', 'High-end Android smartphone', 899.99, 45, 1),
    ('Wireless Earbuds', 'Bluetooth 5.0 wireless earphones', 79.99, 100, 1),
    ('Cotton T-Shirt', 'Comfortable casual wear', 19.99, 200, 2),
    ('Denim Jeans', 'Classic blue jeans', 49.99, 150, 2),
    ('Running Shoes', 'Professional sports footwear', 89.99, 75, 2),
    ('Java Programming', 'Complete guide to Java development', 39.99, 30, 3),
    ('Python Basics', 'Introduction to Python programming', 29.99, 40, 3),
    ('Garden Tools Set', 'Essential gardening tools', 149.99, 25, 4),
    ('Plant Pots', 'Ceramic pots for indoor plants', 24.99, 60, 4);

-- Insert orders
INSERT INTO orders (order_date, total_amount, status, user_id) VALUES
    (CURRENT_TIMESTAMP - INTERVAL '2 days', 1079.98, 'COMPLETED', 1),
    (CURRENT_TIMESTAMP - INTERVAL '1 day', 119.98, 'PROCESSING', 2),
    (CURRENT_TIMESTAMP, 269.97, 'PENDING', 3);

-- Insert order details
INSERT INTO order_details (order_id, product_id, quantity, price, subtotal) VALUES
    (1, 1, 1, 999.99, 999.99),
    (1, 3, 1, 79.99, 79.99),
    (2, 4, 2, 19.99, 39.98),
    (2, 5, 1, 49.99, 49.99),
    (3, 7, 1, 39.99, 39.99),
    (3, 8, 1, 29.99, 29.99),
    (3, 10, 8, 24.99, 199.99);

-- Insert audit logs
INSERT INTO audit_logs (action, entity_name, entity_id, details, user_id, user_email, timestamp, status) VALUES
    ('CREATE_USER', 'UserService', '1', 'Created new user: John Doe', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '3 days', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '1', 'Created new order with total amount: $1079.98', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '2 days', 'SUCCESS'),
    ('UPDATE_PRODUCT', 'ProductService', '1', 'Updated product stock: 50 -> 49', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '2 days', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '2', 'Created new order with total amount: $119.98', 2, 'jane@example.com', CURRENT_TIMESTAMP - INTERVAL '1 day', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '3', 'Created new order with total amount: $269.97', 3, 'mike@example.com', CURRENT_TIMESTAMP, 'SUCCESS'); 