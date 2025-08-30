# Car Rental API Endpoints for Postman Testing

## Base URL
`http://localhost:8080`

## Authentication
Most endpoints require JWT authentication. Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

---

## 0. Authentication Endpoints (`/api/auth`)

### User Login
- **Method:** `POST`
- **URL:** `/api/auth/login`
- **Authentication:** None (Public)
- **Body (JSON):**
```json
{
  "username": "john_doe",
  "password": "password123"
}
```
- **Response:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "userId": 1,
  "username": "john_doe",
  "role": "USER"
}
```

### Validate Token
- **Method:** `GET`
- **URL:** `/api/auth/validate?token=<jwt-token>`
- **Authentication:** None (Public)
- **Response:** `true` or `false`

---

## 1. Car Management Endpoints (`/api/cars`)

### Get All Cars
- **Method:** `GET`
- **URL:** `/api/cars`
- **Authentication:** None (Public)
- **Query Params:** `model` (optional) - filter by car model/brand
- **Response:** List of CarResponse objects

### Get All Cars (with filter)
- **Method:** `GET`
- **URL:** `/api/cars?model=Toyota`
- **Authentication:** None (Public)
- **Response:** Filtered list of cars by model

### Get Car by ID
- **Method:** `GET`
- **URL:** `/api/cars/{carId}`
- **Authentication:** None (Public)
- **Example:** `/api/cars/1`
- **Response:** Single CarResponse object

### Create Car
- **Method:** `POST`
- **URL:** `/api/cars`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Body (JSON):**
```json
{
  "make": "Toyota",
  "model": "Camry",
  "year": 2023,
  "price_per_day": 50.00,
  "type": "Sedan"
}
```

### Update Car
- **Method:** `PUT`
- **URL:** `/api/cars/{carId}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cars/1`
- **Body (JSON):**
```json
{
  "make": "Honda",
  "model": "Accord",
  "year": 2024,
  "price_per_day": 55.00,
  "type": "Sedan"
}
```

### Delete Car
- **Method:** `DELETE`
- **URL:** `/api/cars/{carId}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cars/1`
- **Response:** 204 No Content (success) or 404 Not Found

---

## 2. User Management Endpoints (`/api/users`)

### Get All Users
- **Method:** `GET`
- **URL:** `/api/users`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Response:** List of UserResponse objects

### Get User by ID
- **Method:** `GET`
- **URL:** `/api/users/{user_id}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/users/1`
- **Response:** Single UserResponse object

### Create User
- **Method:** `POST`
- **URL:** `/api/addUsers`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Body (JSON):**
```json
{
  "user_name": "john_doe",
  "first_name": "John",
  "last_name": "Doe",
  "email": "john.doe@email.com",
  "password": "password123",
  "created_at": "2024-08-07T18:00:00"
}
```

### Update User
- **Method:** `PUT`
- **URL:** `/api/users/{userId}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/users/1`
- **Body (JSON):**
```json
{
  "user_name": "john_updated",
  "first_name": "John",
  "last_name": "Smith",
  "email": "john.smith@email.com",
  "password": "newpassword123",
  "created_at": "2024-08-07T18:00:00"
}
```

### Delete User
- **Method:** `DELETE`
- **URL:** `/api/users/{user_id}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/users/1`
- **Response:** 204 No Content (success) or 404 Not Found

### Get User Profile
- **Method:** `GET`
- **URL:** `/api/users/profile`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Response:** Current user's profile information

### Update User Profile
- **Method:** `PUT`
- **URL:** `/api/users/profile`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Body (JSON):**
```json
{
  "first_name": "Updated",
  "last_name": "Name",
  "email": "updated@email.com"
}
```

---

## 3. Cart Management Endpoints (`/api/cart`)

### Get User's Cart
- **Method:** `GET`
- **URL:** `/api/cart/{userId}`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cart/1`
- **Response:** CartResponse object with cart items

### Add Item to Cart
- **Method:** `POST`
- **URL:** `/api/cart/{userId}/items`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cart/1/items`
- **Body (JSON):**
```json
{
  "carId": 1,
  "days": 3
}
```

### Update Cart Item
- **Method:** `PUT`
- **URL:** `/api/cart/{userId}/items/{carId}`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cart/1/items/1`
- **Body (JSON):**
```json
{
  "days": 5
}
```

### Remove Item from Cart
- **Method:** `DELETE`
- **URL:** `/api/cart/{userId}/items/{carId}`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cart/1/items/1`
- **Response:** 204 No Content

### Clear Entire Cart
- **Method:** `DELETE`
- **URL:** `/api/cart/{userId}/clear`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/cart/1/clear`
- **Response:** 204 No Content

---

## 4. Order Management Endpoints (`/api/orders`)

### Get All Orders
- **Method:** `GET`
- **URL:** `/api/orders`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Response:** List of OrderResponse objects

### Get Order by ID
- **Method:** `GET`
- **URL:** `/api/orders/{orderId}`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/orders/1`
- **Response:** Single OrderResponse object

### Create Order from Cart
- **Method:** `POST`
- **URL:** `/api/orders`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Body (JSON):**
```json
{
  "userID": 1
}
```
- **Response:** OrderResponse object with created order details

### Update Order Status
- **Method:** `PUT`
- **URL:** `/api/orders/{orderId}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/orders/1`
- **Body (JSON):**
```json
{
  "status": "CONFIRMED"
}
```

### Delete Order
- **Method:** `DELETE`
- **URL:** `/api/orders/{orderId}`
- **Authentication:** Required (ADMIN only)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Example:** `/api/orders/1`
- **Response:** 204 No Content

---

## 5. Rental Endpoint (`/api/rental`)

### Get Rental Info (Dummy)
- **Method:** `GET`
- **URL:** `/api/rental`
- **Authentication:** Required (USER or ADMIN)
- **Headers:** `Authorization: Bearer <jwt-token>`
- **Response:** "Dummy Rental" string

---

## Complete Testing Flow with Authentication

### Step 1: Create an Admin User (Database or Admin endpoint)
```
POST /api/addUsers
Headers: Authorization: Bearer <admin-jwt-token>
{
  "user_name": "admin_user",
  "first_name": "Admin",
  "last_name": "User", 
  "email": "admin@email.com",
  "password": "admin123",
  "role": "ADMIN",
  "created_at": "2024-08-07T18:00:00"
}
```

### Step 2: Create a Regular User
```
POST /api/addUsers  
Headers: Authorization: Bearer <admin-jwt-token>
{
  "user_name": "test_user",
  "first_name": "Test",
  "last_name": "User",
  "email": "test@email.com",
  "password": "password123",
  "created_at": "2024-08-07T18:00:00"
}
```

### Step 3: Login as Admin to get JWT Token
```
POST /api/auth/login
{
  "username": "admin_user",
  "password": "admin123"
}
Response: {
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "userId": 1,
  "username": "admin_user", 
  "role": "ADMIN"
}
```

### Step 4: Create Cars (Admin Required)
```
POST /api/cars
Headers: Authorization: Bearer <admin-jwt-token>
{
  "make": "Toyota",
  "model": "Camry", 
  "year": 2023,
  "price_per_day": 50.00,
  "type": "Sedan"
}
```

### Step 5: Login as Regular User
```
POST /api/auth/login
{
  "username": "test_user", 
  "password": "password123"
}
Response: {
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "userId": 2,
  "username": "test_user",
  "role": "USER"
}
```

### Step 6: Add Items to Cart (User Required)
```
POST /api/cart/2/items
Headers: Authorization: Bearer <user-jwt-token>
{
  "carId": 1,
  "days": 3
}
```

### Step 7: Check Cart Contents (User Required)
```
GET /api/cart/2
Headers: Authorization: Bearer <user-jwt-token>
```

### Step 8: Create Order from Cart (User Required)
```
POST /api/orders
Headers: Authorization: Bearer <user-jwt-token>
{
  "userID": 2
}
```

### Step 9: Verify Order Created (User Required)
```
GET /api/orders/1
Headers: Authorization: Bearer <user-jwt-token>
```

### Step 10: Verify Cart is Empty (User Required)
```
GET /api/cart/2
Headers: Authorization: Bearer <user-jwt-token>
```

## JWT Token Usage Notes

1. **Get JWT Token**: First login via `/api/auth/login`
2. **Include in Headers**: Add `Authorization: Bearer <token>` to all authenticated requests
3. **Token Expiration**: Tokens expire after 24 hours by default
4. **Role-based Access**: 
   - **PUBLIC**: Car viewing, login, token validation
   - **USER/ADMIN**: Cart operations, orders, profile management
   - **ADMIN ONLY**: Car/user management, order administration

This flow tests the complete authenticated Cart to Order transaction implementation.