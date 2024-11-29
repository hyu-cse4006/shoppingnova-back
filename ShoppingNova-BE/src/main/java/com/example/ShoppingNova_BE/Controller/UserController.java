package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.User.User;
import com.example.ShoppingNova_BE.Entity.User.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    // 모든 사용자 조회
    @GetMapping("/all")
    public String testUserShowALL(Model model) {
        List<User> users = userService.getAllUsers(); // 모든 사용자를 가져오는 서비스 메서드 호출
        model.addAttribute("users", users);
        return "testUserShow"; // users.html 파일로 반환
    }
     */

    // 로그인 기능
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        try {
            User user = userService.login(email, password);

            // 필요한 필드만 포함한 응답 데이터 생성
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "grade", user.getGrade()
            ));

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}

