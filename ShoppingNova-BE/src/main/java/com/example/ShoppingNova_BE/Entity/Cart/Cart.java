package com.example.ShoppingNova_BE.Entity.Cart;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int quantity; // 수량

    private int price; // 제품 개별 가격

    private LocalDateTime createdAt; // 추가 날짜

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Getter, Setter
    public Cart() {
        this.createdAt = LocalDateTime.now();
    }
}
