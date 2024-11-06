package com.example.ShoppingNova_BE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "tbl_memo")
public class demo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "memo_text", nullable = false)
    private String memoText;

    // 기본 생성자
    public demo() {}

    // 매개변수 있는 생성자
    public demo(String memoText) {
        this.memoText = memoText;
    }

    // Getter 및 Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    @Override
    public String toString() {
        return "DemoMemo{" +
                "id=" + id +
                ", memoText='" + memoText + '\'' +
                '}';
    }
}

