package com.example.ShoppingNova_BE.Entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 모든 상품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ID로 상품 조회
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // 새로운 상품 추가
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 상품 업데이트
    public Product updateProduct(int id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setImageUrl(productDetails.getImageUrl());
        product.setRating(productDetails.getRating());
        product.setCategoryId(productDetails.getCategoryId());
        return productRepository.save(product);
    }

    // 상품 삭제
    public void deleteProduct(int id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}

