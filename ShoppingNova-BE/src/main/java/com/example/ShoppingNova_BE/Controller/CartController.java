package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Cart.CartService;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.S3.S3Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final S3Service s3Service;
    private final ProductService productService;

    public CartController(CartService cartService, S3Service s3Service,
            ProductService productService) {
        this.cartService = cartService;
        this.s3Service = s3Service;
        this.productService = productService;
    }

    @GetMapping("/{user_id}")
    public List<Map<String, Object>> getUserCart(@PathVariable int user_id) {
        // S3 경로 설정
        String fileName = "user_" + user_id + "_cart.json";
        String filePath = "user/";

        try {
            // S3에서 파일 읽기
            String jsonData = s3Service.readJsonFile(filePath + fileName);

            // JSON 데이터를 List로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cartData = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});

            return cartData; // 배열 형식으로 반환
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch cart data for userId: " + user_id, e);
        }
    }

    @GetMapping("/{user_id}/intro")
    public List<Map<String, Object>> getUserCartIntro(@PathVariable int user_id) {
        // S3 경로 설정
        String fileName = "user_" + user_id + "_cart.json";
        String filePath = "user/";

        try {
            // S3에서 파일 읽기
            String jsonData = s3Service.readJsonFile(filePath + fileName);

            // JSON 데이터를 List로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cartData = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});

            // 각 product_id로 상품 정보 가져오기
            List<Map<String, Object>> cartDetails = new ArrayList<>();
            for (Map<String, Object> item : cartData) {
                int productId = (int) item.get("product_id");
                // ProductController의 getProductIntro 호출
                Map<String, Object> productIntro = productService.getProductIntro(productId);
                // 장바구니의 수량 추가
                productIntro.put("quantity", item.get("quantity"));
                cartDetails.add(productIntro);
            }

            return cartDetails; // 상품 세부 정보 포함한 리스트 반환
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch cart data for userId: " + user_id, e);
        }
    }

}
