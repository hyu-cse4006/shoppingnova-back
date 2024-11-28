package com.example.ShoppingNova_BE.Initializer;

import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ProductService productService;

    public DataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void initializeData() {
        String folderPath = "json/";
        productService.saveAllJsonFilesInFolder(folderPath);
        System.out.println("DataInitializer 실행");
    }
}

