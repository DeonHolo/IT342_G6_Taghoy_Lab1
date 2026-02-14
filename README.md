# User Registration & Authentication System

A full-stack user authentication system built for IT342 Laboratory.

## 📁 Project Structure

```
IT342_G6_Taghoy_Lab1/
├── /backend        # Spring Boot REST API
├── /web            # React + Vite Web Application
├── /mobile         # Android Kotlin Mobile App
├── /docs           # Documentation (FRS, ERD, UML, Context)
├── README.md
└── TASK_CHECKLIST.md
```

## 🚀 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot 2.7.18, Spring Security, JPA, Java 11 |
| Database | MySQL |
| Web Frontend | React 19 + Vite 7 |
| Mobile | Android Kotlin, Retrofit 2.9, Material 3 |

## 🔧 How to Run

### 1. Database Setup
```sql
CREATE DATABASE betakey_db;
```

### 2. Run Backend
```bash
cd backend
./mvnw spring-boot:run
```
Backend runs at: `http://localhost:8080`

### 3. Run Web App
```bash
cd web
npm install
npm run dev
```
Web app runs at: `http://localhost:5173`

### 4. Run Mobile App
1. Open the `mobile/` folder in **Android Studio**
2. Wait for Gradle sync to complete
3. Make sure the Spring Boot backend is running on your machine
4. Run on an **Android emulator** (the app connects via `10.0.2.2:8080`)
5. **Note:** For physical device testing, update `BASE_URL` in `RetrofitClient.kt` to your machine's local IP

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/logout` | User logout |
| GET | `/api/user/me` | Get current user profile (requires `X-User-Id` header) |

## 📝 Features

- ✅ User Registration with email validation
- ✅ Secure Login with BCrypt password hashing
- ✅ Protected Dashboard/Profile page
- ✅ Logout functionality with confirmation
- ✅ Responsive web design
- ✅ Android mobile app with same functionality
- ✅ Input validation and error handling
- ✅ Consistent JSON API responses

## 👤 Author

Ron Luigi F. Taghoy

## 📄 License

This project is for educational purposes only.
