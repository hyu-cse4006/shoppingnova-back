package com.example.ShoppingNova_BE.Entity.Product;

import com.example.ShoppingNova_BE.Entity.User.User;
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

    // ID로 상품 조회
    public Product getProductById(int id) {
        return productRepository.findById((long) id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 새로운 상품 추가
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 상품 업데이트
    public Product updateProduct(int id, Product productDetails) {
        Product product = getProductById(id);

        // 수정할 필드들을 새로운 필드명으로 설정
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setRating(productDetails.getRating());
        product.setRateNum(productDetails.getRateNum());
        product.setCategory(productDetails.getCategory());
        product.setReleaseDate(productDetails.getReleaseDate());
        product.setLocationX(productDetails.getLocationX());
        product.setLocationY(productDetails.getLocationY());
        product.setLocationZ(productDetails.getLocationZ());

        // 여러 이미지 URL을 받아서 수정
        product.setImageUrl1(productDetails.getImageUrl1());
        product.setImageUrl2(productDetails.getImageUrl2());
        product.setImageUrl3(productDetails.getImageUrl3());
        product.setImageUrl4(productDetails.getImageUrl4());

        return productRepository.save(product);
    }

    // 상품 삭제
    public void deleteProduct(int id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
