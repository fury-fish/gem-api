-- Insert example users with BCrypt hashed passwords
INSERT INTO users (name, email, password) VALUES
    ('John Doe', 'john@example.com', '$2a$10$Ot6oJ3MZjBQJuH5XVkXMhOJqGd9qH6yLmPyVpIgtzAZxnpX5Qg9Gy'),
    ('Jane Smith', 'jane@example.com', '$2a$10$Ot6oJ3MZjBQJuH5XVkXMhOJqGd9qH6yLmPyVpIgtzAZxnpX5Qg9Gy'),
    ('Mike Johnson', 'mike@example.com', '$2a$10$Ot6oJ3MZjBQJuH5XVkXMhOJqGd9qH6yLmPyVpIgtzAZxnpX5Qg9Gy');

-- Insert product categories
INSERT INTO categories (name, description) VALUES
    ('Electronics', 'Electronic devices and accessories'),
    ('Clothing', 'Fashion apparel and accessories'),
    ('Books', 'Books and publications'),
    ('Home & Garden', 'Home improvement and garden supplies');

-- Insert products
INSERT INTO products (name, description, price, stock, category_id, image_url, video_url) VALUES
    ('iPhone 14 Pro', 'Latest Apple iPhone with advanced features', 999.99, 50, 1, 
     'https://example.com/images/iphone14pro.jpg',
     'https://www.youtube.com/watch?v=tfZi4vVB2vM'),
    
    ('Samsung Galaxy S23', 'Premium Android smartphone', 899.99, 45, 1,
     'https://example.com/images/galaxys23.jpg',
     'https://www.youtube.com/watch?v=WrGI9oHxWnY'),
    
    ('Wireless Earbuds', 'High-quality wireless earphones', 149.99, 100, 1,
     'https://example.com/images/earbuds.jpg',
     'https://www.youtube.com/watch?v=example3'),
    
    ('Cotton T-Shirt', 'Comfortable cotton t-shirt', 19.99, 200, 2,
     'https://example.com/images/tshirt.jpg',
     null),
    
    ('Denim Jeans', 'Classic blue denim jeans', 49.99, 150, 2,
     'https://example.com/images/jeans.jpg',
     null),
    
    ('Running Shoes', 'Professional running shoes', 79.99, 80, 2,
     'https://example.com/images/shoes.jpg',
     'https://www.youtube.com/watch?v=example6'),
    
    ('Java Programming', 'Complete guide to Java programming', 39.99, 60, 3,
     'https://example.com/images/java-book.jpg',
     null),
    
    ('Python Basics', 'Introduction to Python programming', 29.99, 75, 3,
     'https://example.com/images/python-book.jpg',
     null),
    
    ('Garden Tools Set', 'Complete set of essential garden tools', 89.99, 30, 4,
     'https://example.com/images/garden-tools.jpg',
     'https://www.youtube.com/watch?v=example9'),
    
    ('Plant Pots', 'Decorative plant pots set', 24.99, 120, 4,
     'https://example.com/images/plant-pots.jpg',
     'https://www.youtube.com/watch?v=example10');

-- Insert orders
INSERT INTO orders (user_id, order_date, total_amount, status) VALUES
    (1, '2024-03-01 10:00:00', 1149.98, 'COMPLETED'),
    (2, '2024-03-02 11:30:00', 269.97, 'PROCESSING'),
    (3, '2024-03-03 14:15:00', 114.98, 'PENDING');

-- Insert order details
INSERT INTO order_details (order_id, product_id, quantity, unit_price) VALUES
    (1, 1, 1, 999.99),
    (1, 3, 1, 149.99),
    (2, 4, 2, 19.99),
    (2, 7, 1, 39.99),
    (3, 8, 1, 29.99),
    (3, 10, 2, 24.99);

-- Insert audit logs
INSERT INTO audit_logs (action, entity_name, entity_id, details, user_id, user_email, timestamp, status) VALUES
    ('CREATE_USER', 'UserService', '1', 'Created new user: John Doe', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '3 days', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '1', 'Created new order with total amount: $1079.98', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '2 days', 'SUCCESS'),
    ('UPDATE_PRODUCT', 'ProductService', '1', 'Updated product stock: 50 -> 49', 1, 'john@example.com', CURRENT_TIMESTAMP - INTERVAL '2 days', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '2', 'Created new order with total amount: $119.98', 2, 'jane@example.com', CURRENT_TIMESTAMP - INTERVAL '1 day', 'SUCCESS'),
    ('CREATE_ORDER', 'OrderService', '3', 'Created new order with total amount: $269.97', 3, 'mike@example.com', CURRENT_TIMESTAMP, 'SUCCESS'); 