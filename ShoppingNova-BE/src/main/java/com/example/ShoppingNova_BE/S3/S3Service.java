package com.example.ShoppingNova_BE.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class S3Service {

    @Value("${s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_2) // 강제로 리전 지정
                .credentialsProvider(AnonymousCredentialsProvider.create())
                .build();
    }

    // S3의 퍼블릭 URL 생성
    public String getPublicFileUrl(String fileName) {
        return String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", bucketName, fileName);
    }

    // S3에서 특정 폴더의 파일 리스트 가져오기
    public List<String> listFilesInFolder(String folderPath) {
        try {
            // 폴더 경로가 '/'로 끝나지 않으면 추가
            if (!folderPath.endsWith("/")) {
                folderPath = folderPath + "/";
            }

            // S3 요청 생성
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName) // 버킷 이름
                    .prefix(folderPath) // 폴더 경로 (prefix)
                    .build();

            // S3에서 파일 목록 가져오기
            ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsRequest);

            // 파일 키(key) 목록 생성
            List<String> fileNames = response.contents().stream()
                    .map(S3Object::key)
                    .collect(Collectors.toList());

            return fileNames;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("S3 폴더의 파일을 가져오는 중 오류 발생: " + e.getMessage());
        }
    }


    // S3에서 JSON 파일을 읽어 문자열로 반환
    public String readJsonFile(String fileName) {
        try {
            // S3 객체 요청 생성
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName) // 버킷 이름
                    .key(fileName) // 파일 키
                    .build();
            // S3 객체 가져오기
            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest);

            // 객체 데이터를 문자열로 변환
            String jsonData = new BufferedReader(new InputStreamReader(s3Object))
                    .lines()
                    .collect(Collectors.joining("\n"));
            System.out.println("S3에서 읽은 JSON 데이터: " + jsonData);

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("S3 파일 읽기 중 오류 발생: " + e.getMessage());
        }
    }

}

