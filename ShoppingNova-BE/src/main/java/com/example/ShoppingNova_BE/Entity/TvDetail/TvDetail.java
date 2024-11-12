package com.example.ShoppingNova_BE.Entity.TvDetail;

import com.example.ShoppingNova_BE.Entity.Category.Category;
import com.example.ShoppingNova_BE.Entity.Product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "tv_detail")
public class TvDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                        // 상품(TV) 상세정보 고유 ID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_TV_PRODUCT"))
    private Product product;               // 상품(TV) ID (외래 키 역할)
    @Column
    private String resolution;             // 상품(TV) 해상도
    @Column
    private int resolution1;               // 상품(TV) 해상도 가로 픽셀
    @Column
    private int resolution2;               // 상품(TV) 해상도 세로 픽셀
    @Column
    private float weight;                  // 상품(TV) 무게
    @Column
    private String processor;              // 상품(TV) AI 프로세서
    @Column
    private String sound;                  // 상품(TV) 스피커 출력
    @Column
    private String plugin;                 // 상품(TV) 전원
    @Column(name = "size_x")
    private int sizeX;                     // 상품(TV) 폭
    @Column(name = "size_y")
    private int sizeY;                     // 상품(TV) 높이
    @Column(name = "size_z")
    private int sizeZ;                     // 상품(TV) 두께

    public void Tv() {}

    // 생성자
    public void Tv(Product product, String resolution, int resolution1, int resolution2, float weight, String processor, String sound, String plugin, int sizeX, int sizeY, int sizeZ) {
        this.product = product;
        this.resolution = resolution;
        this.resolution1 = resolution1;
        this.resolution2 = resolution2;
        this.weight = weight;
        this.processor = processor;
        this.sound = sound;
        this.plugin = plugin;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }


    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getResolution1() {
        return resolution1;
    }

    public void setResolution1(int resolution1) {
        this.resolution1 = resolution1;
    }

    public int getResolution2() {
        return resolution2;
    }

    public void setResolution2(int resolution2) {
        this.resolution2 = resolution2;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ(int sizeZ) {
        this.sizeZ = sizeZ;
    }
}


