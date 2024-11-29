package com.example.ShoppingNova_BE.Entity.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {


    //@GeneratedValue(strategy = GenerationType.IDENTITY) - 이거 해두면 S3에서 못읽어옴
    @Id
    private Integer id;

    private String name;

    private Integer price;

    private Float rating;

    @JsonProperty("rate_num") // JSON의 rate_num과 매핑
    @Column(name = "rate_num")
    private Integer rateNum;

    @Column(name = "category_id") // DB 컬럼 이름을 명시적으로 매핑
    private Integer category_id;

    private String image_url1;
    private String image_url2;
    private String image_url3;
    private String image_url4;

    @JsonProperty("release_date") // JSON의 release_date와 매핑
    private Integer release_date;

    private Float weight;

    @Column(name = "size_x")
    @JsonProperty("size_x")
    private Integer sizeX;

    @Column(name = "size_y")
    @JsonProperty("size_y")
    private Integer sizeY;

    @JsonProperty("size_z")
    @Column(name = "size_z")
    private Integer sizeZ;

    private String resolution; // TV에 해당 (null 가능)
    private Integer resolution1; // TV 해상도 가로
    private Integer resolution2; // TV 해상도 세로

    private String plugin; // TV 전원
    private String processor; // TV AI 프로세서
    private String sound; // TV 스피커 출력

    private String color; // 냉장고 색상
    private String energy; // 냉장고 에너지 효율 등급

    @JsonProperty("door_count")
    private Integer door_count;

    @JsonProperty("volume_cold")
    private Integer volume_cold;

    @JsonProperty("volume_freeze")
    private Integer volume_freeze;

    // Getter 및 Setter 생략
    // 필요한 경우 Lombok(@Getter/@Setter/@Data) 사용 가능
}
