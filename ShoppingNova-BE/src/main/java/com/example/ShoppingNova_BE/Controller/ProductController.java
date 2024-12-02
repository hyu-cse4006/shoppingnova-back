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

    @GetMapping("/products/{category_id}/intro") // category_id=n인 상품 모두 가져오기
    public List<Map<String, Object>> getProductsInCategoryIntro(@PathVariable int category_id) {
        // 카테고리에 속하는 모든 상품을 가져오기
        List<Product> products = productService.getProductsByCategoryId(category_id);

        // 상품이 없을 경우 예외 처리
        if (products.isEmpty()) {
            throw new RuntimeException("No products found in category_id: " + category_id);
        }

        // 간단한 데이터만 포함한 리스트 생성
        List<Map<String, Object>> productIntroList = products.stream().map(product -> {
            Map<String, Object> productIntro = new HashMap<>();
            productIntro.put("id", product.getId());
            productIntro.put("name", product.getName());
            productIntro.put("price", product.getPrice());
            productIntro.put("rating", product.getRating());
            productIntro.put("image_url1", product.getImage_url1());
            return productIntro;
        }).toList();

        return productIntroList;
    }

    @GetMapping("/products/sort_rating/{category_id}")
    public List<Product> getProductsInCategoryRating(@PathVariable int category_id) {
        List<Product> products = productService.getProductsByCategoryId(category_id);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found in category_id: " + category_id);
        }

        // rating 기준으로 내림차순 정렬
        products.sort((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));

        return products;
    }

    @GetMapping("/products/sort_price/{category_id}")
    public List<Product> getProductsInCategoryPrice(@PathVariable int category_id) {
        List<Product> products = productService.getProductsByCategoryId(category_id);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found in category_id: " + category_id);
        }

        // price 기준으로 내림차순 정렬
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));

        return products;
    }

}

