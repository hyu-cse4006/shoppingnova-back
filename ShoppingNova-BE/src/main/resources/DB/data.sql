USE NOVA;

INSERT INTO product (name, price, image_url, rating, category_id) VALUES
('Product 1', 10000, 'https://example.com/product1.jpg', 4.5, 1),
('Product 2', 20000, 'https://example.com/product2.jpg', 4.3, 2),
('Product 3', 30000, 'https://example.com/product3.jpg', 4.7, 3),
('Product 4', 40000, 'https://example.com/product4.jpg', 4.8, 1),
('Product 5', 50000, 'https://example.com/product5.jpg', 4.9, 2);

INSERT INTO `User` (name, email, age, grade, password) VALUES
('홍길동', 'hong@example.com', 25, 'Silver', '12345'),
('김영희', 'kimyh@example.com', 30, 'Gold', '23456'),
('박철수', 'parkcs@example.com', 28, 'Bronze', '34567'),
('이민지', 'leemj@example.com', 22, 'Platinum', '45678'),
('최수민', 'choism@example.com', 35, 'Diamond', '56789');


