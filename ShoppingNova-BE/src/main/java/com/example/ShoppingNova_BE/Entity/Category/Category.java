package com.example.ShoppingNova_BE.Entity.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 매핑
    private Integer id;

    @Column(nullable = false) // name은 null이 될 수 없음
    private String name;

    @JoinColumn(name = "parent_id") // self-referencing foreign key
    private Integer parent_id; // 상위 카테고리
}
