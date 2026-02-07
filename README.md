# BetaKey - User Registration & Authentication System

A full-stack user authentication system built for IT342 Laboratory.

## 📁 Project Structure

```
IT342_G6_Taghoy_Lab1/
├── /backend        # Spring Boot REST API
├── /web            # React + Vite Web Application
├── /mobile         # Mobile Application (coming soon)
├── /docs           # Documentation (FRS, ERD, UML)
├── README.md
└── TASK_CHECKLIST.md
```

## 🚀 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot, Spring Security, JPA |
| Database | MySQL |
| Web Frontend | React + Vite |
| Mobile | (Next Lab Session) |

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

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |
| GET | `/api/user/me` | Get current user profile |

## 📝 Features

- ✅ User Registration with email validation
- ✅ Secure Login with BCrypt password hashing
- ✅ Auto-generated Steam-style Beta Key (XXXXX-XXXXX-XXXXX)
- ✅ Protected Dashboard/Profile page
- ✅ Logout confirmation modal
- ✅ Valorant-inspired UI design

## 👤 Author

Ron Luigi F. Taghoy

## 📄 License

This project is for educational purposes only.
