package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    /**
     * GET /api/user/me - Protected endpoint
     * Retrieves the current user's profile based on userId header
     * In a production app, this would use JWT token to identify the user
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("X-User-Id") Long userId) {
        return userRepo.findById(userId)
                .map(user -> ResponseEntity.ok(new UserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // DTO to avoid exposing password
    static class UserResponse {
        private Long id;
        private String email;
        private String username;
        private String role;
        private String betaKey;

        public UserResponse(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.role = user.getRole();
            this.betaKey = user.getBetaKey();
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

        public String getRole() {
            return role;
        }

        public String getBetaKey() {
            return betaKey;
        }
    }
}
