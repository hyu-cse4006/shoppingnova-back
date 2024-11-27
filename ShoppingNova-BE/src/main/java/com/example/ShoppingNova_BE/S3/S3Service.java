package com.example.ShoppingNova_BE.S3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class S3Service {

    @Value("${aws.region}")
    private String region;

    @Value("${s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_2) // 강제로 리전 지정
                .build();
    }

    // S3의 퍼블릭 URL 생성
    public String getPublicFileUrl(String fileName) {
        return String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", bucketName, fileName);
    }
}

