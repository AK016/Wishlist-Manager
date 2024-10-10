# Wishlist Manager

## Spring Boot Wishlist Management System

This project is a simple Spring Boot application that implements user authentication and allows authenticated users to manage their wishlist. It uses Spring Security for authentication, Spring Data JPA for database interactions, and JWT for securing RESTful API endpoints.

## Features

- User Authentication (Sign up and Log in)
- Wishlist Management (Add, Retrieve, and Delete wishlist items)
- Secure API endpoints with JWT
- Database integration with Spring Data JPA

## Prerequisites

- JDK 1.8 or later
- Maven 3.2+
- MySQL Server 5.6 or later (or any other relational database with appropriate changes to `application.properties`)

## Setting Up the Application

1. **Clone the repository**:

```sh
git clone https://github.com/yourusername/wishlist-management-system.git
cd wishlist-management-system
```

2. **Configure Database**

Edit `src/main/resources/application.properties` to set your database connection properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/wishlist_db
spring.datasource.username=<your_db_username>
spring.datasource.password=<your_db_password>
```

Ensure that the database `wishlist_db` exists in your MySQL server.

3. **Run the Application**

You can start the application using Maven:

```sh
mvn spring-boot:run
```

The application will start, by default, on port `8080`.

## Testing the Application

You can test the application's functionality using Postman or any other API testing tool.

### Sign Up

- **URL**: `/users/register`
- **Method**: `POST`
- **Body**:

```json
{
  "username": "newuser",
  "password": "password"
}
```

### Log In

- **URL**: `/users/login`
- **Method**: `POST`
- **Body**:

```json
{
  "username": "newuser",
  "password": "password"
}
```

Upon successful login, you'll receive a JWT token in the response. Use this token for authentication when accessing protected endpoints.

### Add Wishlist Item

- **URL**: `/api/wishlists`
- **Method**: `POST`
- **Headers**: `Authorization: Bearer <your_jwt_token>`
- **Body**:

```json
{
  "itemName": "New Wishlist Item"
}
```

### Retrieve Wishlist Items

- **URL**: `/api/wishlists`
- **Method**: `GET`
- **Headers**: `Authorization: Bearer <your_jwt_token>`

### Delete Wishlist Item

- **URL**: `/api/wishlists/{id}`
- **Method**: `DELETE`
- **Headers**: `Authorization: Bearer <your_jwt_token>`

Replace `{id}` with the ID of the wishlist item you wish to delete.

## Running Unit Tests

To run the unit tests, execute the following command:

```sh
mvn test
```

## Deployment

For deployment, ensure that you have properly configured your database settings and other environment-specific configurations in `application.properties` or through environment variables.

## Assumptions

- The application uses JWT for authentication and securing endpoints.
- It's assumed that the database and any necessary schemas are already created before running the application.

## Comments

- Ensure to replace `<your_jwt_token>` with the actual token received upon logging in.
- For any additional setup or configurations, refer to the Spring Boot and Spring Security documentation.

---
