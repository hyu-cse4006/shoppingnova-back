package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.S3.S3Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final S3Service s3Service;

    @Autowired
    public ProductController(ProductService productService,S3Service s3Service) {
        this.productService = productService;
        this.s3Service = s3Service;
    }

    // 상품 1개에 대해서 //
    @GetMapping("/product/{id}") // id에 해당하는 모든 데이터
    public Product getProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }

    @GetMapping("/product/{id}/intro") // id에 해당하는 간단한 데이터 -> 상품 hover와 cart
    public Map<String, Object> getProductIntro(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        // 필요한 필드만 포함한 Map 생성
        Map<String, Object> productIntro = new HashMap<>();
        productIntro.put("id", product.getId());
        productIntro.put("name", product.getName());
        productIntro.put("price", product.getPrice());
        productIntro.put("rating", product.getRating());
        productIntro.put("image_url1", product.getImage_url1());

        return productIntro;
    }

    // 특정 카테고리에 해당하는 상품에 대해 //
    @GetMapping("/products/{category_id}") // category_id=n인 상품 모두 가져오기
    public List<Product> getProductsInCategory(@PathVariable int category_id) {
        List<Product> products = productService.getProductsByCategoryId(category_id);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found in category_id: " + category_id);
        }

        return products;
    }

}

