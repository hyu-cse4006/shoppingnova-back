package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        model.addAttribute("users", user);
        return "testUserShow";
    }

    // 모든 사용자 조회
    @GetMapping("/all")
    public String testUserShowALL(Model model) {
        List<User> users = userService.getAllUsers(); // 모든 사용자를 가져오는 서비스 메서드 호출
        model.addAttribute("users", users);
        return "testUserShow"; // users.html 파일로 반환
    }

    // 회원가입 및 로그인 페이지 로드
    @GetMapping("/form")
    public String showLoginRegisterForm() {
        return "testLoginRegister";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            model.addAttribute("user", user);
            return "testLoginSuccess";  // 로그인 성공 시 별도의 URL로 리디렉션
        } else {
            return "redirect:/users/form?error=true";  // 로그인 실패 시 로그인 페이지로 리디렉션
        }
    }

    @GetMapping("/success")
    public String showLoginSuccess(Model model) {
        return "testLoginSuccess";  // loginSuccess.html 파일을 렌더링
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully";
    }
}

