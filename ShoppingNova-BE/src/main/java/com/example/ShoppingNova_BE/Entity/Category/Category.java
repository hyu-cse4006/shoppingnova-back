package com.example.ShoppingNova_BE.Entity.Category;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;               // 카테고리 고유 ID

    @Column(nullable = false, length = 255)
    private String name;          // 카테고리 이름

    @Column(length = 50)
    private String parent;        // 상위 카테고리 이름

    @Column(name = "location_x")
    private int locationX;        // 카테고리 좌표 x

    @Column(name = "location_y")
    private int locationY;        // 카테고리 좌표 y

    @Column(name = "location_z")
    private int locationZ;        // 카테고리 좌표 z

    @OneToMany(mappedBy = "category")
    private List<Product> products; // 해당 카테고리에 속한 상품들

    // 기본 생성자
    public Category() {
    }

    // 생성자
    public Category(int id, String name, String parent, int locationX, int locationY, int locationZ, List<Product> products) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.products = products;
    }

    // Getter 및 Setter 메서드
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(int locationZ) {
        this.locationZ = locationZ;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
