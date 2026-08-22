# Student Microservice

A Spring Boot microservice for managing student data with integrated service communication, circuit breaker pattern, and cloud-native features.

## Overview

The Student Service is part of a microservices architecture that handles student-related operations. It communicates with the Address Service to provide complete student information including address details.

## Tech Stack

- **Java**: 21
- **Spring Boot**: 4.1.0
- **Spring Cloud**: 2025.1.2
- **Database**: MySQL
- **Build Tool**: Maven

## Key Features

- **Service Discovery**: Netflix Eureka Client for dynamic service registration and discovery
- **Inter-service Communication**: OpenFeign for declarative REST client communication with Address Service
- **Circuit Breaker**: Resilience4j for fault tolerance and resilience
- **Configuration Management**: Spring Cloud Config Server integration
- **Load Balancing**: Spring Cloud LoadBalancer for client-side load balancing
- **Monitoring**: Spring Boot Actuator for health checks and metrics
- **Data Persistence**: Spring Data JPA with MySQL

## Dependencies

- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-starter-webflux
- spring-cloud-starter-openfeign
- spring-cloud-starter-netflix-eureka-client
- spring-cloud-starter-loadbalancer
- spring-cloud-starter-config
- spring-boot-starter-actuator
- spring-cloud-starter-circuitbreaker-resilience4j
- mysql-connector-j

## Configuration

- **Server Port**: 8081
- **Application Name**: student-service
- **Config Server**: http://localhost:8888
- **Active Profile**: dev

## API Endpoints

### Create Student
```
POST /api/student/create
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "addressId": 1
}
```

### Get Student by ID
```
GET /api/student/getById/{id}
```

## Circuit Breaker

The service uses Resilience4j Circuit Breaker pattern to handle failures when communicating with the Address Service.

### Circuit Breaker Configuration
- **Name**: address-service-cb
- **Fallback Method**: fallbackGetAddressById

### Circuit Breaker Status Endpoint
```
http://localhost:8081/actuator/circuitbreakers
```

This endpoint provides the current state of all circuit breakers in the application, including:
- Circuit state (OPEN, CLOSED, HALF_OPEN)
- Failure rate
- Success rate
- Number of buffered calls
- Number of failed calls
- Number of not permitted calls

### How It Works

1. When the Address Service is unavailable or slow, the circuit breaker opens after a configured failure threshold
2. Once open, subsequent calls immediately fallback to the fallback method without attempting the actual call
3. After a configured wait time, the circuit transitions to HALF_OPEN state to test if the service has recovered
4. If successful, the circuit closes; if failed, it remains open

## Actuator Endpoints

All actuator endpoints are exposed for monitoring and management:

- **Health Check**: http://localhost:8081/actuator/health
- **Metrics**: http://localhost:8081/actuator/metrics
- **Circuit Breakers**: http://localhost:8081/actuator/circuitbreakers
- **Info**: http://localhost:8081/actuator/info
- **Beans**: http://localhost:8081/actuator/beans

## Service Communication

The Student Service communicates with the Address Service using OpenFeign:

```java
@FeignClient(value = "address-service", path = "/api/address")
public interface AddressFeignClient {
    @GetMapping("/getById/{id}")
    AddressResponse getAddressById(@PathVariable Long id);
}
```

The service discovery is handled by Eureka, allowing the Address Service to be dynamically located.

## Running the Application

### Prerequisites
- Java 21 installed
- MySQL database running
- Config Server running on port 8888
- Eureka Server running (if using service discovery)
- Address Service running

### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

## Database Schema

The database schema is located in `src/main/resources/db-scripts/student.sql`

## Architecture

```
┌─────────────────┐
│  Client Request │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Student Service │
│   (Port 8081)   │
└────────┬────────┘
         │
         ├──► Circuit Breaker (Resilience4j)
         │
         ▼
┌─────────────────┐
│ Address Service │
│   (via Feign)   │
└─────────────────┘
```

## Future Enhancements

- Add API documentation with Swagger/OpenAPI
- Implement distributed tracing with Spring Cloud Sleuth
- Add authentication and authorization with Spring Security
- Implement caching for frequently accessed data
- Add comprehensive unit and integration tests
