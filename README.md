# Car Rental Service

A comprehensive car rental management system built with Spring Boot, providing RESTful APIs for managing cars, users, shopping carts, and rental orders.


## Admin Login
## User name : admin
## Password  : password



## Features

- **Car Management**: CRUD operations for car inventory
- **User Management**: User registration and profile management
- **Shopping Cart**: Add cars to cart with rental duration
- **Order Processing**: Convert cart items to rental orders
- **Security**: Spring Security integration for secure endpoints
- **Database**: PostgreSQL database with JPA/Hibernate
- **API Documentation**: Swagger/OpenAPI documentation
- **Validation**: Request validation with Hibernate Validator

## Technology Stack

- **Framework**: Spring Boot 3.5.3
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Security**: Spring Security
- **Documentation**: SpringDoc OpenAPI 3
- **Build Tool**: Maven
- **Testing**: JUnit 5 with Spring Boot Test

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Test
- PostgreSQL Driver
- Lombok
- Hibernate Validator
- Apache Commons Lang3
- SpringDoc OpenAPI

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12+



## API Endpoints

### Car Management (`/api/cars`)
- `GET /api/cars` - Get all cars (with optional model filter)
- `GET /api/cars/{carId}` - Get car by ID
- `POST /api/cars` - Create new car
- `PUT /api/cars/{carId}` - Update car
- `DELETE /api/cars/{carId}` - Delete car

### User Management (`/api/users`)
- `GET /api/users` - Get all users
- `GET /api/users/{userId}` - Get user by ID
- `POST /api/addUsers` - Create new user
- `PUT /{userId}` - Update user
- `DELETE /api/users/{userId}` - Delete user

### Cart Management (`/api/carts`)
- `GET /api/carts/{userId}` - Get user's cart
- `POST /api/carts/{userId}/items` - Add item to cart
- `PUT /api/carts/{userId}/items/{carId}` - Update cart item
- `DELETE /api/carts/{userId}/items/{carId}` - Remove item from cart
- `DELETE /api/carts/{userId}/clear` - Clear entire cart

### Order Management (`/api/orders`)
- `GET /api/orders` - Get all orders
- `GET /api/orders/{orderId}` - Get order by ID
- `POST /api/orders` - Create order from cart


