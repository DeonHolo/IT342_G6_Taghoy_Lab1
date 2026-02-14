package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");

        // Validation
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (password == null || password.isBlank()) {
            throw new RuntimeException("Password is required");
        }
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Username is required");
        }

        // Check duplicate email
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userRepo.save(user);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        // Validation
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (password == null || password.isBlank()) {
            throw new RuntimeException("Password is required");
        }

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Login successful",
                    "userId", String.valueOf(user.getId())));
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("status", "success", "message", "Logged out successfully"));
    }
}