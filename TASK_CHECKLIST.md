# Task Checklist - User Registration & Authentication

## DONE

### Backend (Spring Boot)
- [x] Project setup with Spring Boot `7b5f490`
- [x] MySQL database connection configured `7b5f490`
- [x] User entity with BCrypt password encryption `7b5f490`
- [x] `POST /api/auth/register` - User registration endpoint `7b5f490`
- [x] `POST /api/auth/login` - User login endpoint `7b5f490`
- [x] `GET /api/user/me` - Protected user profile endpoint `2457797`
- [x] Security configuration with CORS support `2457797`

### Backend Finalization (Lab 2)
- [x] `POST /api/auth/logout` - Logout endpoint
- [x] Global exception handler with consistent JSON error responses
- [x] Input validation on register/login (blank fields, duplicate email)
- [x] CORS updated for Android emulator (`10.0.2.2`)

### Web Application (ReactJS)
- [x] Project setup with Vite + React `2457797`
- [x] Register page with animations `2457797`
- [x] Login page with animations `2457797`
- [x] Dashboard/Profile page (protected) `2457797`
- [x] Logout functionality with confirmation modal `56fb8a2`

### Mobile Application (Android Kotlin)
- [x] Android project setup with Kotlin + Gradle
- [x] Retrofit + Gson for API calls
- [x] Login screen (LoginActivity)
- [x] Register screen (RegisterActivity)
- [x] Dashboard/Profile screen (DashboardActivity) - protected
- [x] Logout with AlertDialog confirmation
- [x] SharedPreferences for session management
- [x] Connection to same Spring Boot backend via `10.0.2.2`

### Documentation
- [x] ERD diagram (draw.io) `56fb8a2`
- [x] Use Case diagram `56fb8a2`
- [x] Class diagram `56fb8a2`
- [x] Activity diagram `56fb8a2`
- [x] Sequence diagram `56fb8a2`
- [x] Web UI screenshots `56fb8a2`

---

## TODO

### Documentation (Final)
- [ ] Mobile UI screenshots added to FRS PDF
- [ ] Diagram revisions if needed
- [ ] Commit hashes added to Lab 2 tasks once committed
