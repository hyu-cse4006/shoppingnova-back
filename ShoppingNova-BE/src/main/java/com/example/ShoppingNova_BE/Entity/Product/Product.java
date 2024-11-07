package com.example.ShoppingNova_BE.Entity.Product;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;               // 상품 고유 ID

    @Column(nullable = false, length = 255)
    private String name;          // 상품 이름

    @Column(nullable = false)
    private int price;            // 상품 가격 (한국 원화, 소수점 없음)

    @Column(length = 500)
    private String imageUrl;      // 상품 이미지 URL

    @Column
    private double rating;        // 상품 평점 (scale 제거)

    @Column
    private int categoryId;       // 카테고리 ID (외래 키 역할)

    // 기본 생성자
    public Product() {}

    // 생성자
    public Product(String name, int price, String imageUrl, double rating, int categoryId) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
