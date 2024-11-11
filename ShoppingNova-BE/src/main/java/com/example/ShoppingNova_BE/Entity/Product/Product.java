package com.example.ShoppingNova_BE.Entity.Product;

import com.example.ShoppingNova_BE.Entity.Category.Category;
import com.example.ShoppingNova_BE.Entity.TvDetail.TvDetail;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;               // 상품 고유 ID

    @Column(nullable = false, length = 255)
    private String name;          // 상품 이름

    @Column(nullable = false)
    private int price;            // 상품 가격 (한국 원화, 소수점 없음)

    @Column
    private double rating;        // 상품 평점

    @Column(name = "rate_num")
    private int rateNum;          // 상품 리뷰 개수

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;    // 카테고리 (외래 키)

    @Column(name = "release_date")
    private int releaseDate;      // 상품 출시 연도 (LocalDate로 변경)

    @Column(name = "location_x")
    private int locationX;        // 상품 좌표 x

    @Column(name = "location_y")
    private int locationY;        // 상품 좌표 y

    @Column(name = "location_z")
    private int locationZ;        // 상품 좌표 z

    @Column(name = "image_url1", length = 500)
    private String imageUrl1;     // 상품 이미지 url 1

    @Column(name = "image_url2", length = 500)
    private String imageUrl2;     // 상품 이미지 url 2

    @Column(name = "image_url3", length = 500)
    private String imageUrl3;     // 상품 이미지 url 3

    @Column(name = "image_url4", length = 500)
    private String imageUrl4;     // 상품 이미지 url

    // 기본 생성자
    public Product() {}

    // 생성자
    public Product(String name, int price, double rating, int rateNum, Category category,
                   int releaseDate, int locationX, int locationY, int locationZ,
                   String imageUrl1, String imageUrl2, String imageUrl3, String imageUrl4) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.rateNum = rateNum;
        this.category = category;
        this.releaseDate = releaseDate;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
        this.imageUrl4 = imageUrl4;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getRateNum() { return rateNum; }

    public void setRateNum(int rateNum) { this.rateNum = rateNum; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public int getReleaseDate() { return releaseDate; }

    public void setReleaseDate(int releaseDate) { this.releaseDate = releaseDate; }

    public int getLocationX() { return locationX; }

    public void setLocationX(int locationX) { this.locationX = locationX; }

    public int getLocationY() { return locationY; }

    public void setLocationY(int locationY) { this.locationY = locationY; }

    public int getLocationZ() { return locationZ; }

    public void setLocationZ(int locationZ) { this.locationZ = locationZ; }

    public String getImageUrl1() { return imageUrl1; }

    public void setImageUrl1(String imageUrl1) { this.imageUrl1 = imageUrl1; }

    public String getImageUrl2() { return imageUrl2; }

    public void setImageUrl2(String imageUrl2) { this.imageUrl2 = imageUrl2; }

    public String getImageUrl3() { return imageUrl3; }

    public void setImageUrl3(String imageUrl3) { this.imageUrl3 = imageUrl3; }

    public String getImageUrl4() { return imageUrl4; }

    public void setImageUrl4(String imageUrl4) { this.imageUrl4 = imageUrl4; }
}

