package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.S3.S3Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/api/files/{fileName}") // s3의 url 넘겨주기
    public String getFileUrl(@PathVariable String fileName) {
        return s3Service.getPublicFileUrl(fileName);
    }

    @GetMapping("/html/{fileName}") // test 방식이다.
    public String getHtmlWithImage(@PathVariable String fileName) {
        String imageUrl = s3Service.getPublicFileUrl(fileName);
        return "<html>" +
                "<body>" +
                "<h1>Test Image</h1>" +
                "<img src='" + imageUrl + "' alt='Test Image'>" +
                "</body>" +
                "</html>";
    }
}

