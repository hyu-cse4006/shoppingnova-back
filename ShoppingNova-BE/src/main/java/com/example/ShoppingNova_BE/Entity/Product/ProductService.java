package com.example.ShoppingNova_BE.Entity.Product;

import com.example.ShoppingNova_BE.S3.S3Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, S3Service s3Service) {
        this.productRepository = productRepository;
        this.s3Service = s3Service;
        this.objectMapper = new ObjectMapper();
    }

    // ID로 상품 조회
    public Product getProductById(int id) {
        return productRepository.findById((long) id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // S3 폴더의 모든 JSON 파일을 DB에 저장
    public void saveAllJsonFilesInFolder(String folderPath) {
        try {
            // 폴더 내 파일 리스트 가져오기
            List<String> fileNames = s3Service.listFilesInFolder(folderPath);

            for (String fileName : fileNames) {
                if (fileName.endsWith(".json")) { // JSON 파일만 처리
                    String jsonData = s3Service.readJsonFile(fileName);

                    // JSON 데이터를 Product 객체 리스트로 변환
                    List<Product> products = objectMapper.readValue(jsonData, new TypeReference<List<Product>>() {});

                    // DB에 저장
                    productRepository.saveAll(products);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("S3 폴더의 데이터를 DB에 저장하는 중 오류가 발생했습니다.");
        }
    }
}
