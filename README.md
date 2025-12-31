# User Service

A Spring Boot microservice for user management with JWT authentication.

## Features

- User management (CRUD operations)
- JWT-based authentication
- Spring Security integration
- MySQL database support
- RESTful API
- Swagger/OpenAPI documentation
- Actuator endpoints for monitoring

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or compatible database)
- Docker (optional, for containerized deployment)

## Setup Instructions

### 1. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE userdb;
```

### 2. Configuration

Copy the local configuration template and update with your database credentials:

The application uses `application-local.yml` for local development. Update the following properties:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC
    username: your_username
    password: your_password
```

### 3. Environment Variables (Optional)

You can override JWT settings using environment variables:

- `JWT_SECRET`: Secret key for JWT token signing (default: change-me-to-a-long-random-string)
- `JWT_EXPIRATION_MS`: Token expiration time in milliseconds (default: 3600000 = 1 hour)

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/user-service-0.0.1-SNAPSHOT.jar
```

The application will start on port `8081` (as configured in `application-local.yml`).

## API Documentation

Once the application is running, access the Swagger UI at:

```
http://localhost:8081/swagger-ui/index.html
```

Or alternatively:

```
http://localhost:8081/swagger-ui.html
```

## Actuator Endpoints

Health and info endpoints are available at:

```
http://localhost:8081/actuator/health
http://localhost:8081/actuator/info
```

## Docker

Build and run with Docker:

```bash
docker build -t user-service .
docker run -p 8081:8081 user-service
```

## Project Structure

```
src/
├── main/
│   ├── java/com/user/
│   │   ├── client/          # Feign clients for external services
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── entity/          # JPA entities
│   │   ├── exception/       # Exception handlers
│   │   ├── repository/      # JPA repositories
│   │   ├── security/        # Security and JWT utilities
│   │   └── service/         # Business logic
│   └── resources/
│       ├── application.yml          # Main configuration
│       └── application-local.yml    # Local development configuration
└── test/                    # Test files
```

## Technologies Used

- Spring Boot 4.0.1
- Spring Security
- Spring Data JPA
- MySQL Connector
- JWT (JJWT)
- Lombok
- SpringDoc OpenAPI (Swagger)
- Spring Boot Actuator

## License

This project is part of the Astrology microservices architecture.
