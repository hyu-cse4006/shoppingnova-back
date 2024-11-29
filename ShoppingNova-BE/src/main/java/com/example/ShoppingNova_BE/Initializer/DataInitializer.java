package com.example.ShoppingNova_BE.Initializer;

import com.example.ShoppingNova_BE.Entity.Category.CategoryService;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.Entity.User.UserService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public DataInitializer(ProductService productService, CategoryService categoryService,
            UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostConstruct
    public void initializeData() {
        categoryService.saveCategoryJson("category.json");

        String productFolderPath = "product_data/";
        productService.saveAllJsonFilesInFolder(productFolderPath);

        String userFolderPath = "user/";
        userService.saveAllJsonFilesInUser(userFolderPath);

        System.out.println("Product Initializer 실행");
    }
}

