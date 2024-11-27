package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.S3.S3Service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/files/{fileName}") // s3의 url 넘겨주기
    public String getFileUrl(@PathVariable String fileName) {
        return s3Service.getPublicFileUrl(fileName);
    }

    // 이미지 1개 API -> URL 리턴
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/{folderName}/{fileName}")
    public ResponseEntity<Map<String, String>> getImageUrl(
            @PathVariable String folderName,
            @PathVariable String fileName) {
        String imageUrl = s3Service.getPublicFileUrl(folderName + "/" + fileName);

        // JSON 형태로 URL 반환
        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);

        return ResponseEntity.ok(response);
    }
}

