package com.example.ShoppingNova_BE.Initializer;

import com.example.ShoppingNova_BE.Entity.Category.CategoryService;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ProductService productService;
    private final CategoryService categoryService;

    public DataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void initializeData() {
        categoryService.saveCategoryJson("category.json");

        String folderPath = "product_data/";
        productService.saveAllJsonFilesInFolder(folderPath);
        System.out.println("Product Initializer 실행");
    }
}

