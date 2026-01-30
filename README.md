# Spring Security Authentication System

A Spring Boot application that demonstrates a complete authentication and authorization flow using **Spring Security**, **Spring Data JPA**, **Thymeleaf**, and **MySQL**, with **email-based account verification**.

This project was built as a learning-focused but production-minded implementation of core security concepts commonly used in enterprise Java applications.

---

## 🚀 Features

- User registration with encrypted passwords
- Login & logout using Spring Security
- Email-based account verification using confirmation tokens
- Custom `UserDetailsService`
- Role-ready architecture (extensible)
- Thymeleaf server-side rendered views
- MySQL persistence with Spring Data JPA
- CSRF protection enabled
- Clean separation of concerns (controller, service, repository)

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 4**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **MySQL**
- **Spring Mail (Gmail SMTP)**
- **Maven**

---

## 🎥 Demo

▶️ **Loom video walkthrough:**  
https://www.loom.com/share/9adc841db5bf44c9b3064d1ff2b481f0

---

## ⚙️ Configuration

Sensitive configuration (email credentials, database passwords) is excluded from version control using Spring profiles.

Create a local config file:




## 🧪 Running the Project

1. **Clone the repository**  
   ```bash
   git clone <your-repo-url>
   cd <repo-folder>
mvn spring-boot:run
http://localhost:8080


📌 Notes

Email verification is implemented using confirmation tokens

SSL/TLS issues with Gmail SMTP are handled via proper mail configuration

The project is designed to be reusable as a base authentication template for future Spring Boot applications

👨‍💻 Author

Tanaka Zuze
Aspiring Java / Backend Engineer with a strong interest in enterprise software development.

📄 License

This project is for educational and portfolio purposes.
