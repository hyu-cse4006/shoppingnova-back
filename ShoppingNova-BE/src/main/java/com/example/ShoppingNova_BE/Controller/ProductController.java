package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.S3.S3Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + id);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}/intro") // id에 해당하는 간단한 데이터
    public ResponseEntity<?> getProductIntro(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + id);
        }

        // 필요한 필드만 포함한 Map 생성
        Map<String, Object> productIntro = new HashMap<>();
        productIntro.put("id", product.getId());
        productIntro.put("name", product.getName());
        productIntro.put("price", product.getPrice());
        productIntro.put("rating", product.getRating());

        return ResponseEntity.ok(productIntro);
    }


    @GetMapping("/images/{id}/{cnt}") // id에 해당하는 이미지 1개
    public ResponseEntity<Map<String, String>> getImageUrl(@PathVariable int id,@PathVariable int cnt) {
        String fileName = "product_";
        if(id >= 1 && id<= 84)
            fileName += "tv_"+ id + "_" + cnt + ".png";
        if(id >= 85 && id<= 176)
            fileName += "fridge_"+ id + "_" + cnt + ".png";

        String imageUrl = s3Service.getPublicFileUrl("image" + "/" + fileName);
        System.out.println("url : "+imageUrl);
        // JSON 형태로 URL 반환
        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}/all")
    public ResponseEntity<List<Map<String, String>>> getAllImageUrls(@PathVariable int id) {
        List<Map<String, String>> imageUrls = new ArrayList<>();

        String baseName = "product_";
        if (id >= 1 && id <= 84) {
            baseName += "tv_";
        } else if (id >= 85 && id <= 176) {
            baseName += "fridge_";
        }
        for (int cnt = 1; cnt <= 4; cnt++) {
            String fileName = baseName + id + "_" + cnt + ".png";

            // 이미지 URL 가져오기
            String imageUrl = s3Service.getPublicFileUrl("image" + "/" + fileName);

            // JSON 응답 데이터 구성
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            imageUrls.add(response);
        }

        // URL 리스트 반환
        return ResponseEntity.ok(imageUrls);
    }
    // 상품 1개에 대해서 //

    // 특정 카테고리에 해당하는 상품에 대해 //
    @GetMapping("/products/{category_id}") // category_id=n인 상품 모두 가져오기
    public ResponseEntity<?> getProductsInCategory(@PathVariable int category_id) {
        // MySQL 쿼리를 사용해 데이터베이스에서 검색
        List<Product> products = productService.getProductsByCategoryId(category_id);

        // 결과가 없으면 404 반환
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No products found in category_id: " + category_id);
        }

        return ResponseEntity.ok(products);
    }

}

