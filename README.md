## About The Project

A backend REST API for a movie ticket booking system built with Spring Boot.  
The project covers real-world concepts like JWT-based stateless authentication,  
BCrypt password encoding, role-based authorization, global exception handling,  
and bean validation.

### Features
- User signup and login with JWT token authentication
- BCrypt password encryption
- Role-based access control (USER / ADMIN)
- Book multiple seats in a single request
- Seat duplicate validation at both application and database level
- Cancel ticket with movie start time validation
- Global exception handling with proper HTTP status codes
- Bean validation on all incoming requests

### Tech Stack
- Java 17
- Spring Boot 4.0
- Spring Security
- Spring Data JPA
- MySQL
- JJWT 0.11.5
- Lombok
- Maven
