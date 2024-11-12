package com.example.ShoppingNova_BE.Entity.TvDetail;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TvRepository extends JpaRepository<TvDetail, Long> {
    Optional<TvDetail> findByProductId(int productId);
}

