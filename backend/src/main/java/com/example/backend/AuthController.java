package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow React (Vite)
public class AuthController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // In real app: check if email exists first
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        User user = userRepo.findByEmail(body.get("email"))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.get("password"), user.getPassword())) {
            // "Antigravity" Token: Just sending the ID for now
            return Map.of("message", "Login Success", "userId", String.valueOf(user.getId()));
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}