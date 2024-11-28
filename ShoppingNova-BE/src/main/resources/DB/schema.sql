CREATE DATABASE NOVA;
USE NOVA;

CREATE TABLE IF NOT EXISTS Category (
                                        id INT AUTO_INCREMENT PRIMARY KEY,       -- 카테고리 고유 ID
                                        name varchar(255),                       -- 카테고리 이름
                                        parent_id INT                            -- 상위 카테고리 ID
);

CREATE TABLE IF NOT EXISTS Product (
                                       id INT AUTO_INCREMENT PRIMARY KEY,       -- 상품 고유 ID
                                       name VARCHAR(255) NOT NULL,              -- 상품 이름
                                       price INT NOT NULL,                      -- 상품 가격 (한국 원화, 소수점 없음)
                                       rating DECIMAL(2,1),                     -- 상품 평점
                                       rate_num INT,                            -- 상품 리뷰 개수
                                       category_id INT,                         -- 카테고리 ID (외래 키 역할)
                                       release_date INT,                        -- 상품 출시 년도
                                       image_url varchar(500),                  -- 상품 이미지 url
                                       weight float,                            -- 상품 무게
                                       size_x INT,                              -- 상품 폭
                                       size_y INT,                              -- 상품 높이
                                       size_z INT,                              -- 상품 깊이(냉장고)/두께(TV)
                                       color varchar(50),                       -- 상품(냉장고) 색깔
                                       energy varchar(50),                      -- 상품(냉장고) 에너지소비효율등급
                                       door_count INT,                          -- 상품(냉장고) 문 개수
                                       volume_cold INT,                         -- 상품(냉장고) 냉장 용량
                                       volume_freeze INT,                       -- 상품(냉장고) 냉동 용량
                                       resolution varchar(30),                  -- 상품(TV) 해상도
                                       resolution1 INT,                         -- 상품(TV) 해상도 가로 픽셀
                                       resolution2 INT,                         -- 상품(TV) 해상도 세로 픽셀
                                       processor varchar(100),                  -- 상품(TV) AI 프로세서
                                       sound varchar(50),                       -- 상품(TV) 스피커 출력
                                       plugin varchar(50)                      -- 상품(TV) 전원
);
