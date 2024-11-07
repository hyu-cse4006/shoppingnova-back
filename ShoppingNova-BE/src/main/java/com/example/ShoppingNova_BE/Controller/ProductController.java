package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        model.addAttribute("product", product);
        return "testProductShow";
    }

}

