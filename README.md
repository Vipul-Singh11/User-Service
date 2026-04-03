# 👤 User Service

User Service is a core microservice of the **Stock Trading Simulation Platform**.  
It handles user registration, authentication, and wallet management using JWT-based security.

---

## 🚀 Features

- 🔐 User Registration & Login
- 🔑 JWT Authentication
- 🔒 Password Encryption (BCrypt)
- 💰 Wallet Initialization (₹100,000)
- 🧑 Role-based structure (USER, future ADMIN)
- 🛡 Stateless Security using Spring Security

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL (Docker)
- JWT (JSON Web Token)
- Lombok

---

## 📂 Project Structure

```
com.stock.userservice
│
├── controller         # REST APIs
├── service            # Business logic
│   └── impl
├── repository         # JPA Repositories
├── entity             # Database entities
├── dto                # Request/Response objects
├── security           # JWT + authentication logic
├── config             # Security configuration
├── exception          # Global exception handling
└── UserServiceApplication.java
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```
git clone <your-repo-url>
cd user-service
```

---

### 2️⃣ Start MySQL using Docker

```
docker run --name stock-mysql \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=stockdb \
-p 3307:3306 \
-d mysql:8
```

---

### 3️⃣ Configure application.yml

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/stockdb
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: your_secret_key
  expiration: 86400000
```

---

### 4️⃣ Run the Application

```
mvn spring-boot:run
```

---

## 🔌 API Endpoints

### 📝 Register User

```
POST /api/auth/register
```

Request Body:

```json
{
  "username": "vipul",
  "email": "vipul@test.com",
  "password": "password123"
}
```

---

### 🔐 Login

```
POST /api/auth/login
```

Request Body:

```json
{
  "email": "vipul@test.com",
  "password": "password123"
}
```

Response:

```json
{
  "token": "jwt_token_here"
}
```

---

### 👤 Get Current User

```
GET /api/users/me
```

Header:

```
Authorization: Bearer <token>
```

---

## 🧠 Key Concepts Implemented

- JWT Authentication Flow
- Custom UserDetailsService
- Security Filter Chain
- Stateless Session Management
- Password Hashing with BCrypt

---

## 🧪 Testing

Use Postman or any API client:

1. Register a user  
2. Login to get JWT token  
3. Use token to access protected endpoints  

---

## 🐳 Docker Notes

- MySQL runs in Docker container
- Service connects via port `3307`
- Ensure container is running before starting app

---

## 📌 Future Improvements

- Refresh Token implementation
- Role-based authorization (ADMIN)
- API Gateway integration
- Inter-service communication (Feign Client)

---

## 👨‍💻 Author

Vipul Singh
