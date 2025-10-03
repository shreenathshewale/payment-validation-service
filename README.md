# Secure Payment Validation Microservice

[![Language](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com)
[![Framework](https://img.shields.io/badge/Framework-Spring%20Boot-green.svg)](https://spring.io/projects/spring-boot)
[![Build](https://img.shields.io/badge/Build-Maven-red.svg)](https://maven.apache.org/)

This project is a robust and secure **Spring Boot microservice** designed to handle and validate payment requests.
Its primary focus is on ensuring the integrity, authenticity, and security of the API through a custom 
**HMAC (Hash-based Message Authentication Code) signature validation** mechanism, supplemented by RSA encryption principles.

It serves as a practical example of building a secure, backend RESTful service following industry best practices for microservice architecture and API security.

---

## 🚀 Key Features

* **HMAC Security:** A custom Spring Security filter (`HmacFilter.java`) intercepts and validates all incoming requests using an HmacSHA256 signature. This prevents tampering and ensures the request originates from a trusted client.
* **RESTful API:** Exposes a clean, resource-oriented API for payment validation. Tested and documented for use with tools like Postman.
* **Layered Architecture:** Adheres to a classic layered architecture pattern (**Controller -> Service -> Data Access**) for separation of concerns, maintainability, and scalability.
* **Custom Exception Handling:** Implements a global exception handler (`GlobalExceptionHandler.java`) using `@ControllerAdvice` to provide consistent and meaningful error responses.
* **DTO Pattern:** Utilizes Data Transfer Objects (`PaymentRequest.java`, `PaymentResponse.java`) to ensure a clean and decoupled API contract.

---

## 🛠️ Technology Stack

Language: Java

Framework: Spring Boot, Spring Security

Database: MySQL

API: REST (JSON)

Security: HMAC Signature Validation (HmacSHA256)

Build Tool: Maven

API TESTING TOOL: POSTMAN
---

## ⚙️ How to Set Up and Run

### Prerequisites

* Java Development Kit (JDK) 11 or newer
* Apache Maven
* A running MySQL database instance

### Steps

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd payment-validation-service
    ```

2.  **Configure the database:**
    * Create a database schema in your MySQL instance.
    * Open the `src/main/resources/application.properties` file.
    * Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your database configuration.

3.  **Configure Security Properties:**
    * In `application.properties`, set your own secret key for the HMAC signature generation.
    ```properties
    # Example property
    hmac.secret.key=your-super-secret-key
    ```

4.  **Build and run the application:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    The application will start on the configured port (default is 8080).

---

## 🧪 Testing the API with Postman

To send a valid request to the secured endpoints, you must generate an HMAC signature and include it in the request headers.

1.  **Endpoint:** `POST /api/payment/validate` (example)
2.  **Headers:**
    * `Content-Type`: `application/json`
    * `X-Signature`: The generated `HmacSHA256` signature of the request body.
3.  **Body:**
    * A JSON object matching the structure of `PaymentRequest.java`.

A client-side utility or a Postman pre-request script would be required to generate the signature dynamically before sending the request.

```json
{
    "amount": "100.00",
    "currency": "USD",
    "userId": "user123",
    "paymentMethod": "CREDIT_CARD"
}
