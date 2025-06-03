-- Insert sample users
-- Password: 123456 (BCrypt encoded)
INSERT INTO users (name, email, password, role, phone_number, created_at, updated_at)
VALUES 
('Admin User', 'admin@example.com', '$2a$10$rS.FGQsKGzRYZyh1TKh1YOJuHf5ZYLhWGR.mGWOoUVe9Y1YzGQXyy', 'ADMIN', '1234567890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Moderator User', 'mod@example.com', '$2a$10$rS.FGQsKGzRYZyh1TKh1YOJuHf5ZYLhWGR.mGWOoUVe9Y1YzGQXyy', 'MOD', '0987654321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Customer User', 'customer@example.com', '$2a$10$rS.FGQsKGzRYZyh1TKh1YOJuHf5ZYLhWGR.mGWOoUVe9Y1YzGQXyy', 'CUSTOMER', '5555555555', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample categories
INSERT INTO categories (name, description, image_url, created_at, updated_at)
VALUES 
('Electronics', 'Electronic devices and accessories', 'electronics.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Clothing', 'Fashion and apparel', 'clothing.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Books', 'Books and literature', 'books.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample partners
INSERT INTO partners (name, description, contact_person, contact_email, contact_phone, website_url, created_at, updated_at)
VALUES 
('TechCorp', 'Leading technology manufacturer', 'John Tech', 'john@techcorp.com', '1112223333', 'https://techcorp.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fashion Hub', 'Premium fashion retailer', 'Mary Style', 'mary@fashionhub.com', '4445556666', 'https://fashionhub.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Book World', 'Major book publisher', 'Bob Reader', 'bob@bookworld.com', '7778889999', 'https://bookworld.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample products
INSERT INTO products (name, description, price, stock_quantity, category_id, partner_id, image_url, created_at, updated_at)
VALUES 
('Smartphone X', 'Latest smartphone with advanced features', 999.99, 50, 1, 1, 'smartphone.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Designer Jeans', 'Premium quality designer jeans', 89.99, 100, 2, 2, 'jeans.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Programming Guide', 'Comprehensive programming guide', 49.99, 200, 3, 3, 'book.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample sale events
INSERT INTO sale_events (name, description, discount_percent, start_date, end_date, active)
VALUES 
('Summer Sale', 'Big summer discounts', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '30 days', true),
('Flash Sale', 'Limited time offers', 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '7 days', true);

-- Insert sample product sales
INSERT INTO product_sales (product_id, sale_event_id, active)
VALUES 
(1, 1, true),
(2, 2, true);

-- Insert sample reviews
INSERT INTO reviews (user_id, product_id, rating, comment, created_at, updated_at)
VALUES 
(3, 1, 5, 'Great smartphone, very satisfied!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2, 4, 'Good quality jeans', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample blogs
INSERT INTO blogs (title, content, author_id, published, view_count, created_at, updated_at)
VALUES 
('Tech Trends 2024', 'Exploring the latest technology trends...', 1, true, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fashion Guide', 'Ultimate fashion guide for the season...', 2, true, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample blog comments
INSERT INTO blog_comments (blog_id, user_id, content, created_at, updated_at)
VALUES 
(1, 3, 'Very informative article!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 3, 'Great fashion tips!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample notifications
INSERT INTO notifications (user_id, title, message, type, created_at)
VALUES 
(3, 'Order Confirmation', 'Your order has been confirmed', 'ORDER', CURRENT_TIMESTAMP),
(3, 'Sale Alert', 'New sale items available', 'PROMOTION', CURRENT_TIMESTAMP); 