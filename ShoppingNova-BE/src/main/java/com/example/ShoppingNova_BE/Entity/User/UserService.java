package com.example.ShoppingNova_BE.Entity.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

