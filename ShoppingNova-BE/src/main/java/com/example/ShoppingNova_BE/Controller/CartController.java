package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Cart.CartService;
import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.S3.S3Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{user_id}/intro")
    public List<Map<String, Object>> getUserCartIntro(@PathVariable int user_id) {
        // S3에서 장바구니 데이터를 읽음
        String fileName = "user_" + user_id + "_cart.json";
        String filePath = "user/";

        try {
            String jsonData = s3Service.readJsonFile(filePath + fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cartData = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});

            // 각 cart item에 대한 product 정보 추가
            for (Map<String, Object> item : cartData) {
                int productId = (int) item.get("product_id");
                Product product = productService.getProductById(productId); // product 정보 조회

                item.put("name", product.getName());
                item.put("price", product.getPrice());
                item.put("image_url1", product.getImage_url1());
                item.put("rating", product.getRating());
            }

            return cartData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch cart data for userId: " + user_id, e);
        }
    }

    @PostMapping("/{user_id}/add_cart")
    public String addToCart(@PathVariable int user_id, @RequestParam int product_id) {
        // S3 경로 설정
        String fileName = "user_" + user_id + "_cart.json";
        String filePath = "user/";

        try {
            // S3에서 기존 장바구니 데이터 읽기
            String jsonData = "";
            try {
                jsonData = s3Service.readJsonFile(filePath + fileName);
            } catch (Exception e) {
                // 파일이 없으면 빈 배열로 초기화
                jsonData = "[]";
            }

            // JSON 데이터를 List로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cartData = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});

            // 새로운 상품 추가
            if(product_id < 1 || product_id > 176)
                throw new Exception();

            Map<String, Object> newItem = new HashMap<>();
            newItem.put("id", cartData.size() + 1); // id는 기존 데이터의 크기 + 1로 설정
            newItem.put("product_id", product_id);
            newItem.put("user_id", user_id);
            newItem.put("quantity", 1); // 기본 수량 1

            cartData.add(newItem);

            // 업데이트된 데이터를 JSON으로 변환
            String updatedJsonData = objectMapper.writeValueAsString(cartData);

            // S3에 업로드
            s3Service.uploadJsonFile(filePath + fileName, updatedJsonData);

            return "Product added to cart successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add product to cart for userId: " + user_id, e);
        }
    }

    @DeleteMapping("/{user_id}/del_cart")
    public String removeFromCart(@PathVariable int user_id, @RequestParam int id) {
        // S3 경로 설정
        String fileName = "user_" + user_id + "_cart.json";
        String filePath = "user/";

        try {
            // S3에서 기존 장바구니 데이터 읽기
            String jsonData = "";
            try {
                        jsonData = s3Service.readJsonFile(filePath + fileName);
            } catch (Exception e) {
                // 파일이 없으면 상품이 없다는 메시지 반환
                return "Cart is empty for userId: " + user_id;
            }

            // JSON 데이터를 List로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cartData = objectMapper.readValue(jsonData, new TypeReference<>() {});

            // id에 해당하는 상품 삭제
            boolean itemRemoved = cartData.removeIf(item -> item.get(id).equals(id));

            if (!itemRemoved) {
                return "Product with ID " + id + " not found in the cart for userId: " + user_id;
            }

            // 업데이트된 데이터를 JSON으로 변환
            String updatedJsonData = objectMapper.writeValueAsString(cartData);

            // S3에 업로드
            s3Service.uploadJsonFile(filePath + fileName, updatedJsonData);

            return "Product removed from cart successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove product from cart for userId: " + user_id, e);
        }
    }


}
