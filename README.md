# Gem API

A Spring Boot CRUD API project for an e-commerce system with user management, product catalog, order processing, and audit logging capabilities.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 15 or higher
- Git

## Database Setup

1. Make sure PostgreSQL is installed and running
2. Create a PostgreSQL user (if not exists):
   ```bash
   createuser -s mac
   ```

3. Initialize the database and sample data:
   ```bash
   # Navigate to project root
   cd gem-api

   # Create database schema and tables
   psql -U mac -f src/main/resources/schema.sql

   # Load sample data
   psql -U mac -d gemstore -f src/main/resources/data.sql
   ```

## Building and Running the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd gem-api
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Documentation

### Products API

#### Get All Products
```http
GET /api/products?page=1&size=10&sortBy=id&direction=asc
```

Query Parameters:
- `page` (optional): Page number, starting from 1 (default: 1)
- `size` (optional): Number of items per page (default: 10)
- `sortBy` (optional): Field to sort by (default: "id")
- `direction` (optional): Sort direction, "asc" or "desc" (default: "asc")

Response:
```json
{
    "content": [
        {
            "id": 1,
            "name": "Product Name",
            "description": "Product Description",
            "price": 99.99,
            "stock": 100,
            "categoryId": 1
        }
    ],
    "pageNumber": 1,
    "pageSize": 10,
    "totalElements": 100,
    "totalPages": 10,
    "first": true,
    "last": false
}
```

#### Get Products by Category
```http
GET /api/products/category/{categoryId}?page=1&size=10&sortBy=id&direction=asc
```

Query Parameters:
- `page` (optional): Page number, starting from 1 (default: 1)
- `size` (optional): Number of items per page (default: 10)
- `sortBy` (optional): Field to sort by (default: "id")
- `direction` (optional): Sort direction, "asc" or "desc" (default: "asc")

### Categories API

#### Get All Categories
```http
GET /api/categories?page=1&size=10&sortBy=id&direction=asc
```

Query Parameters:
- `page` (optional): Page number, starting from 1 (default: 1)
- `size` (optional): Number of items per page (default: 10)
- `sortBy` (optional): Field to sort by (default: "id")
- `direction` (optional): Sort direction, "asc" or "desc" (default: "asc")

Response:
```json
{
    "content": [
        {
            "id": 1,
            "name": "Category Name",
            "description": "Category Description"
        }
    ],
    "pageNumber": 1,
    "pageSize": 10,
    "totalElements": 50,
    "totalPages": 5,
    "first": true,
    "last": false
}
```

## API Endpoints

### User Management
- Create User: `POST /api/users`
- Update User: `PUT /api/users/{id}`
- Get User by ID: `GET /api/users/{id}`
- Get All Users: `GET /api/users`
- Delete User: `DELETE /api/users/{id}`
- Get User by Email: `GET /api/users/email/{email}`

### Product Management
- Create Product: `POST /api/products`
- Get Product by ID: `GET /api/products/{id}`
- Get All Products: `GET /api/products`
  - Supports pagination and sorting:
    ```
    GET /api/products?page=0&size=10&sortBy=name&direction=asc
    ```
- Get Products by Category: `GET /api/products/category/{categoryId}`
  - Supports pagination and sorting:
    ```
    GET /api/products/category/1?page=0&size=10&sortBy=price&direction=desc
    ```
- Delete Product: `DELETE /api/products/{id}`

### Category Management
- Create Category: `POST /api/categories`
- Get Category by ID: `GET /api/categories/{id}`
- Get All Categories: `GET /api/categories`
  - Supports pagination and sorting:
    ```
    GET /api/categories?page=0&size=10&sortBy=name&direction=asc
    ```
- Check Category Exists: `GET /api/categories/exists/{name}`
- Delete Category: `DELETE /api/categories/{id}`

## Pagination and Sorting

The API supports pagination and sorting for list endpoints. You can use the following query parameters:

- `page`: Page number (0-based, defaults to 0)
- `size`: Number of items per page (defaults to 10)
- `sortBy`: Field to sort by (defaults to "id")
- `direction`: Sort direction ("asc" or "desc", defaults to "asc")

Example response format for paginated endpoints:
```json
{
  "content": [
    {
      "id": 1,
      "name": "Example Item",
      // ... other fields
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 100,
  "totalPages": 10,
  "first": true,
  "last": false
}
```

### Sample Data

The application comes with pre-loaded sample data:

1. Users:
   - John Doe (john@example.com)
   - Jane Smith (jane@example.com)
   - Mike Johnson (mike@example.com)

2. Product Categories:
   - Electronics
   - Clothing
   - Books
   - Home & Garden

3. Products:
   - Electronics: iPhone 14 Pro, Samsung Galaxy S23, Wireless Earbuds
   - Clothing: Cotton T-Shirt, Denim Jeans, Running Shoes
   - Books: Java Programming, Python Basics
   - Home & Garden: Garden Tools Set, Plant Pots

## Security

- All passwords are hashed using BCrypt before storing in the database
- CSRF protection is disabled for API access
- Currently configured for open access (no authentication required)

## Database Configuration

The application uses PostgreSQL with the following default configuration:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gemstore
spring.datasource.username=mac
spring.datasource.password=
```

## Technologies Used

- Spring Boot 3.2.3
- Spring Data JPA
- Spring Security
- PostgreSQL
- Lombok
- Spring AOP for Audit Logging
- Maven for dependency management

## Project Structure

- `src/main/java/com/gemapi/`
  - `config/` - Configuration classes
  - `controller/` - REST controllers
  - `dto/` - Data Transfer Objects
  - `entity/` - JPA entities
  - `repository/` - Spring Data repositories
  - `service/` - Business logic
  - `exception/` - Custom exceptions
  - `aspect/` - AOP aspects for audit logging

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request
