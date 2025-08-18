# Student Management System

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![License](https://img.shields.io/badge/license-MIT-blue)]()
[![Java](https://img.shields.io/badge/Java-17-orange)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)]()

A simple **Student Management System** built with **Spring Boot 3**, **H2 database**, **Hibernate/JPA**, and **RESTful APIs**.  
Provides CRUD operations on students and a simple HTML view.

---

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [License](#license)

---

## Features

- CRUD operations on students.
- RESTful API endpoints with proper HTTP status codes.
- Simple HTML view for listing all students.
- H2 in-memory database with pre-populated sample data.
- Validation for student fields (`name` not blank, `age >= 18`).
- Exception handling for resource not found (404).
- OpenAPI/Swagger UI integration for API documentation.

---

## Technologies

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA / Hibernate**
- **H2 Database (in-memory)**
- **Spring Validation**
- **Lombok**
- **OpenAPI / Swagger UI**
- **JUnit & Spring Boot Test**

---

## Project Structure

```
student-management/
├─ src/main/java/com/shekoofe/student_management/
│  ├─ controller/      # REST controllers
│  ├─ model/           # JPA entities
│  ├─ repository/      # Spring Data JPA repositories
│  ├─ service/         # Service layer
│  └─ exception/       # Custom exceptions
├─ src/main/resources/
│  ├─ application.properties  # Spring Boot configuration
│  └─ import.sql              # Initial data for H2 database
├─ src/test/java/...           # Unit and integration tests
└─ pom.xml                     # Maven build file
```
---

## Setup & Installation

1. Clone the repository:

    git clone https://github.com/shkBostan/student-management.git
cd student-management

2. Build the project using Maven:

    mvn clean package

3. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

---

## Running the Application

mvn spring-boot:run

- The application will start on http://localhost:8080
- HTML view of students: http://localhost:8080/api/students/view
- Swagger/OpenAPI documentation: http://localhost:8080/swagger-ui.html

---

## API Endpoints

| Method | URL                     | Description                  |
|--------|-------------------------|------------------------------|
| GET    | /api/students           | List all students           |
| GET    | /api/students/{id}      | Get a student by ID         |
| POST   | /api/students           | Create a new student        |
| PUT    | /api/students/{id}      | Update an existing student  |
| DELETE | /api/students/{id}      | Delete a student            |
| GET    | /api/students/view      | HTML view of all students   |

**Example JSON for POST/PUT:**
{
"name": "Ali",
"major": "Math",
"age": 20
}

---

## Testing

- Unit & integration tests with JUnit 5 and MockMvc.
- Run tests: mvn test

---

## License

MIT License © 2025 S. Bostan  
Free to use, modify, and distribute, **as long as the original author’s name is preserved**. No warranty is provided.
