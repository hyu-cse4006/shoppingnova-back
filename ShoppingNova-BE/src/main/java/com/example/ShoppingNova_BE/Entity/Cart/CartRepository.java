package com.example.ShoppingNova_BE.Entity.Cart;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId); // 사용자별 장바구니 조회
}
