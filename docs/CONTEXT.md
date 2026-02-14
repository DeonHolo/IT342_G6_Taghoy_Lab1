# Project Context — IT342 Lab 1 & Lab 2

> This file exists to help future AI assistants (or humans) quickly understand the full state of this project.

## 🎯 Goal

**IT342 Group 6 — Ron Luigi F. Taghoy**

Build a **User Registration & Authentication System** across three platforms sharing one backend:

| Lab | Scope | Status |
|-----|-------|--------|
| Lab 1 | Spring Boot backend + React web app + docs | ✅ Complete |
| Lab 2 | Android Kotlin mobile app + backend finalization + docs update | ✅ Complete |

---

## 🏢 Lab Environment Constraints

> [!IMPORTANT]
> The university lab machines are **restricted to Java 11** and use **IntelliJ IDEA** as the IDE. All backend code must remain compatible with Java 11.

| Constraint | Lab | Home |
|------------|-----|------|
| **Java version** | Java 11 (only) | Java 11+ |
| **IDE** | IntelliJ IDEA (recommended by instructor) | Any (VS Code, IntelliJ, etc.) |
| **MySQL password** | _(empty / no password)_ | `root` |
| **Spring Boot** | 2.7.18 (last version supporting Java 11) | Same |

### `application.properties` — switching between lab and home

```properties
# At LAB (no MySQL password):
spring.datasource.password=

# At HOME (MySQL password is "root"):
spring.datasource.password=root
```

> **Remember to change `spring.datasource.password` when switching between lab and home machines.**

---

## 📁 Repository Structure

```
IT342_G6_Taghoy_Lab1/
├── /backend          # Spring Boot REST API (Java 11, Boot 2.7.18)
├── /web              # React + Vite web frontend
├── /mobile           # Android Kotlin mobile app (TaghoyMobile)
├── /docs             # Documentation (FRS PDF, this file)
├── README.md
└── TASK_CHECKLIST.md
```

---

## 🔧 Tech Stack & Versions

### Backend (Spring Boot)
| Component | Version |
|-----------|---------|
| Spring Boot | 2.7.18 |
| Java | 11 |
| Spring Security | 5.x (managed by Boot 2.7 BOM) |
| Spring Data JPA | (managed by Boot 2.7 BOM) |
| JPA imports | `javax.persistence.*` (not jakarta) |
| MySQL Connector | (runtime, managed by BOM) |
| Password Hashing | BCryptPasswordEncoder |
| Database | MySQL — `betakey_db` |
| Build Tool | Maven (mvnw wrapper) |

### Web Frontend (React)
| Component | Version |
|-----------|---------|
| React | ^19.2.0 |
| React DOM | ^19.2.0 |
| React Router DOM | ^7.13.0 |
| Framer Motion | ^12.33.0 |
| React Icons | ^5.5.0 |
| Vite | ^7.2.4 |
| @vitejs/plugin-react | ^5.1.1 |
| ESLint | ^9.39.1 |

### Mobile App (Android Kotlin)
| Component | Version |
|-----------|---------|
| Android Gradle Plugin (AGP) | 8.3.2 |
| Kotlin | 1.9.0 |
| compileSdk / targetSdk | 34 |
| minSdk | 24 (Android 7.0) |
| Retrofit | 2.9.0 |
| Gson Converter | 2.9.0 |
| Material Components | 1.10.0 |
| Package name | `com.example.taghoymobile` |
| Feature | View Binding (not Compose) |

### Database
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/betakey_db
spring.datasource.username=root
spring.datasource.password=       # empty at lab, "root" at home
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🌐 API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register new user (username, email, password) | Public |
| POST | `/api/auth/login` | Login → returns `{ status, message, userId }` | Public |
| POST | `/api/auth/logout` | Logout (client clears stored userId) | Public |
| GET | `/api/user/me` | Get current user profile | Requires `X-User-Id` header |

### Auth Mechanism
- **No JWT** — uses a simple `X-User-Id` header approach
- Login returns a `userId`; client stores it (web: `localStorage`, mobile: `SharedPreferences`)
- Protected endpoints read `X-User-Id` header to identify the user
- Logout = client clears stored userId + calls `/api/auth/logout`
- CORS configured for `http://localhost:5173` (web) and `http://10.0.2.2:8080` (Android emulator)

---

## 📂 Backend File Map

| File | Purpose |
|------|---------|
| `BetakeyApplication.java` | Spring Boot main class |
| `User.java` | JPA entity — `users` table. Fields: id, email, username, password (BCrypt), role. Uses `javax.persistence.*` |
| `UserRepository.java` | JPA repo — `findByEmail(String email)` |
| `AuthController.java` | `/api/auth` — register, login, logout. Input validation, consistent JSON responses |
| `UserController.java` | `/api/user` — `GET /me` with `X-User-Id` header, returns `UserResponse` DTO (no password) |
| `SecurityConfig.java` | `@EnableWebSecurity`, CSRF disabled, CORS global config, BCryptPasswordEncoder bean. Uses Boot 2.x method-chain DSL (`antMatchers`, `.and()`) |
| `GlobalExceptionHandler.java` | `@ControllerAdvice` — consistent `{status, message}` JSON error responses |
| `application.properties` | MySQL config, JPA auto-update |

## 📂 Web Frontend File Map

| File | Purpose |
|------|---------|
| `App.jsx` | Router: `/` → `/login`, `/register`, `/dashboard` |
| `Login.jsx` | Login form, calls `/api/auth/login`, stores userId in localStorage |
| `Register.jsx` | Register form (username, email, password) |
| `Dashboard.jsx` | Protected page, fetches profile with X-User-Id header, logout modal |

## 📂 Mobile App File Map

| File | Purpose |
|------|---------|
| `LoginActivity.kt` | Login form, auto-redirect if already logged in, saves userId to SharedPreferences |
| `RegisterActivity.kt` | Register form (username, email, password) |
| `DashboardActivity.kt` | Protected, fetches profile, logout with AlertDialog confirmation |
| `api/ApiService.kt` | Retrofit interface — login, register, logout, getProfile |
| `api/RetrofitClient.kt` | Singleton Retrofit → `http://10.0.2.2:8080/api/` |

---

## 🔑 Key Design Decisions

1. **Java 11** — Lab machines are restricted to Java 11 → Spring Boot 2.7.18 (last 2.x LTS)
2. **No JWT** — Kept simple `X-User-Id` header auth for consistency across web + mobile
3. **Android emulator** — Uses `10.0.2.2` to reach host machine's `localhost:8080`
4. **Password hashing** — BCrypt, encoded in `User.setPassword()` entity setter
5. **CORS** — Global config in `SecurityConfig` for both web and emulator origins
6. **Logout** — Client-side session clearing + a ceremonial backend endpoint
7. **View Binding** — Mobile app uses Android View Binding (not Compose)
8. **Retrofit + Gson** — HTTP client for Android, matching the REST API's JSON format
9. **IntelliJ IDEA** — Recommended by instructor for proper JDK/Maven integration in the lab
