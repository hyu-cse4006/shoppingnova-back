CREATE DATABASE IF NOT EXISTS NOVA;
USE NOVA;

CREATE TABLE IF NOT EXISTS Category (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 카테고리 고유 ID
    name varchar(255),                       -- 카테고리 이름
    parent varchar(50),                      -- 카테고리 상위 이름
    location_x INT,                          -- 카테고리 좌표 x
    location_y INT,                          -- 카테고리 좌표 y
    location_z INT                           -- 카테고리 좌표 z
);

CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 사용자 고유 ID
    name VARCHAR(255) NOT NULL,              -- 사용자 이름
    email VARCHAR(255) NOT NULL UNIQUE,      -- 사용자 이메일 (고유 값)
    age INT,                                 -- 사용자 나이
    grade VARCHAR(50),                       -- 사용자 등급
    password varchar(255),                   -- 사용자 비밀번호
    created_at DATETIME                      -- 사용자 가입 날짜
);

CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 상품 고유 ID
    name VARCHAR(255) NOT NULL,              -- 상품 이름
    price INT NOT NULL,                      -- 상품 가격 (한국 원화, 소수점 없음)
    rating DECIMAL(2,1),                     -- 상품 평점
    rate_num INT,                            -- 상품 리뷰 개수
    category_id INT,                         -- 카테고리 ID (외래 키 역할)
    release_date INT,                        -- 상품 출시 년도
    location_x INT,                          -- 상품 좌표 x
    location_y INT,                          -- 상품 좌표 y
    location_z INT,                          -- 상품 좌표 z
    image_url1 varchar(500),                 -- 상품 이미지 url 1
    image_url2 varchar(500),                 -- 상품 이미지 url 2
    image_url3 varchar(500),                 -- 상품 이미지 url 3
    image_url4 varchar(500),                 -- 상품 이미지 url 4
    FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE SET NULL ON UPDATE CASCADE  -- category_id 외래 키
    );

CREATE TABLE IF NOT EXISTS fridge_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 상품(냉장고) 상세정보 고유 ID
    product_id INT,                          -- 상품(냉장고) ID (외래 키 역할)
    color varchar(50),                       -- 상품(냉장고) 색깔
    energy varchar(50),                      -- 상품(냉장고) 에너지소비효율등급
    door_count INT,                          -- 상품(냉장고) 문 개수
    weight float,                              -- 상품(냉장고) 무게
    volume_cold INT,                         -- 상품(냉장고) 냉장 용량
    volume_freeze INT,                       -- 상품(냉장고) 냉동 용량
    size_x INT,                              -- 상품(냉장고) 폭
    size_y INT,                              -- 상품(냉장고) 높이
    size_z INT,                               -- 상품(냉장고) 깊이
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tv_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 상품(TV) 상세정보 고유 ID
    product_id INT,                          -- 상품(TV) ID (외래 키 역할)
    resolution varchar(30),                  -- 상품(TV) 해상도
    resolution1 INT,                         -- 상품(TV) 해상도 가로 픽셀
    resolution2 INT,                         -- 상품(TV) 해상도 세로 픽셀
    weight float,                            -- 상품(TV) 무게
    processor varchar(100),                  -- 상품(TV) AI 프로세서
    sound varchar(50),                       -- 상품(TV) 스피커 출력
    plugin varchar(50),                      -- 상품(TV) 전원
    size_x INT,                              -- 상품(TV) 폭
    size_y INT,                              -- 상품(TV) 높이
    size_z INT,                               -- 상품(TV) 두께
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- 주문 고유 ID
    product_id INT,                          -- 상품 ID (외래 키 역할)
    user_id INT,                             -- 사용자 ID (외래 키 역할)
    quantity INT,                            -- 주문량
    total_price INT,                         -- 총 가격
    order_date DATETIME,                     -- 주문 날짜
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE SET NULL ON UPDATE CASCADE
);