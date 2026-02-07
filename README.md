# BetaKey - User Registration & Authentication System

A full-stack user authentication system built for IT342 Laboratory.

## 📁 Project Structure

```
BetaKey/
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

## 🔧 Backend Setup

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven

### Database Setup
```sql
CREATE DATABASE betakey_db;
```

### Configuration
Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/betakey_db
spring.datasource.username=root
spring.datasource.password=root
```

### Run Backend
```bash
cd backend
./mvnw spring-boot:run
```

Backend runs at: `http://localhost:8080`

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |
| GET | `/api/user/me` | Get current user profile |

## 🖥️ Web App Setup

```bash
cd web
npm install
npm run dev
```

Web app runs at: `http://localhost:5173`

## 📝 Features

- ✅ User Registration with email validation
- ✅ Secure Login with BCrypt password hashing
- ✅ Protected Dashboard/Profile page
- ✅ Logout functionality
- ✅ Responsive design

## 👤 Author

Ron Luigi F. Taghoy

## 📄 License

This project is for educational purposes only.
