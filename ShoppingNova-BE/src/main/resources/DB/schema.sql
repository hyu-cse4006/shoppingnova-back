CREATE DATABASE IF NOT EXISTS NOVA;
USE NOVA;

-- product 테이블 생성
CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 상품 고유 ID
    name VARCHAR(255) NOT NULL,              -- 상품 이름
    price INT NOT NULL,                      -- 상품 가격 (한국 원화, 소수점 없음)
    image_url VARCHAR(500),                  -- 상품 이미지 URL
    rating DECIMAL(2,1),                     -- 상품 평점
    category_id INT                          -- 카테고리 ID (외래 키 역할)
    );