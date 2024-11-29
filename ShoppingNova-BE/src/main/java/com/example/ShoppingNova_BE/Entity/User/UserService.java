package com.example.ShoppingNova_BE.Entity.User;

import com.example.ShoppingNova_BE.S3.S3Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, S3Service s3Service,
            ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
        this.objectMapper = objectMapper;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String email, String password) {
        // 이메일로 사용자 조회
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // 비밀번호 검증
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @Transactional
    public void saveAllJsonFilesInUser(String folderPath) {
        try {
            // 폴더 내 파일 리스트 가져오기
            List<String> fileNames = s3Service.listFilesInFolder(folderPath);
            System.out.println("user: " + fileNames.toString());
            for (String fileName : fileNames) {
                // "user_{id}.json" 형식만 처리
                if (fileName.matches("user/user_\\d+\\.json")) { // 정규식을 사용하여 파일 이름 검사
                    String jsonData = s3Service.readJsonFile(fileName);

                    // JSON 데이터를 User 객체 리스트로 변환
                    List<User> users = objectMapper.readValue(jsonData, new TypeReference<>() {});

                    // DB에 저장
                    userRepository.saveAll(users);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("S3 폴더의 데이터를 DB에 저장하는 중 오류가 발생했습니다.");
        }
    }

}

