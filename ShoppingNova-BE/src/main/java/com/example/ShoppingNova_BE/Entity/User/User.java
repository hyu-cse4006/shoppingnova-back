package com.example.ShoppingNova_BE.Entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    private int id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Positive
    private int age;

    @NotBlank
    private String grade;

    // 기본 생성자
    public User() {
    }

    // 모든 속성을 받는 생성자
    public User(String name, String email, int age, String grade) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.grade = grade;
    }
}
