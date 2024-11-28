package com.example.ShoppingNova_BE.Entity.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);

    @Query("SELECT p FROM Product p WHERE p.category_id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") int category_id);
}
