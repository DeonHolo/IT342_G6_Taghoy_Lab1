package com.example.backend;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Random;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String username;
    private String password;
    private String role = "TESTER"; // Default role for BetaKey

    @Column(unique = true)
    private String betaKey; // Steam-style key: XXXXX-XXXXX-XXXXX

    // Auto-generate Steam-style beta key on creation (XXXXX-XXXXX-XXXXX)
    @PrePersist
    public void generateBetaKey() {
        if (this.betaKey == null) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random = new Random();
            StringBuilder key = new StringBuilder();

            for (int group = 0; group < 3; group++) {
                if (group > 0)
                    key.append("-");
                for (int i = 0; i < 5; i++) {
                    key.append(chars.charAt(random.nextInt(chars.length())));
                }
            }

            this.betaKey = key.toString();
        }
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getBetaKey() {
        return betaKey;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBetaKey(String betaKey) {
        this.betaKey = betaKey;
    }

    // Important: Encrypt password when setting it
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}