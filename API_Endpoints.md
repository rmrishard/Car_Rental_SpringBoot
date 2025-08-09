# Car Rental API Endpoints for Postman Testing

## Base URL
`http://localhost:8080`

## 1. Car Management Endpoints (`/api/cars`)

### Get All Cars
- **Method:** `GET`
- **URL:** `/api/cars`
- **Query Params:** `model` (optional) - filter by car model/brand
- **Response:** List of CarResponse objects

### Get All Cars (with filter)
- **Method:** `GET`
- **URL:** `/api/cars?model=Toyota`
- **Response:** Filtered list of cars by model

### Get Car by ID
- **Method:** `GET`
- **URL:** `/api/cars/{carId}`
- **Example:** `/api/cars/1`
- **Response:** Single CarResponse object

### Create Car
- **Method:** `POST`
- **URL:** `/api/cars`
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

---

## 2. User Management Endpoints (`/api/users`)

### Get All Users
- **Method:** `GET`
- **URL:** `/api/users`
- **Response:** List of UserResponse objects

### Get User by ID
- **Method:** `GET`
- **URL:** `/api/users/{user_id}`
- **Example:** `/api/users/1`
- **Response:** Single UserResponse object

### Create User
- **Method:** `POST`
- **URL:** `/api/addUsers`
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
- **URL:** `/{userId}`
- **Example:** `/1`
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
- **Example:** `/api/users/1`
- **Response:** 204 No Content (success) or 404 Not Found

---

## 3. Cart Management Endpoints (`/api/carts`)

### Get User's Cart
- **Method:** `GET`
- **URL:** `/api/carts/{userId}`
- **Example:** `/api/carts/1`
- **Response:** CartResponse object with cart items

### Add Item to Cart
- **Method:** `POST`
- **URL:** `/api/carts/{userId}/items`
- **Example:** `/api/carts/1/items`
- **Body (JSON):**
```json
{
  "carId": 1,
  "days": 3
}
```

### Update Cart Item
- **Method:** `PUT`
- **URL:** `/api/carts/{userId}/items/{carId}`
- **Example:** `/api/carts/1/items/1`
- **Body (JSON):**
```json
{
  "days": 5
}
```

### Remove Item from Cart
- **Method:** `DELETE`
- **URL:** `/api/carts/{userId}/items/{carId}`
- **Example:** `/api/carts/1/items/1`
- **Response:** 204 No Content

### Clear Entire Cart
- **Method:** `DELETE`
- **URL:** `/api/carts/{userId}/clear`
- **Example:** `/api/carts/1/clear`
- **Response:** 204 No Content

---

## 4. Order Management Endpoints (`/api/orders`)

### Get All Orders
- **Method:** `GET`
- **URL:** `/api/orders`
- **Response:** List of OrderResponse objects

### Get Order by ID
- **Method:** `GET`
- **URL:** `/api/orders/{orderId}`
- **Example:** `/api/orders/1`
- **Response:** Single OrderResponse object

### Create Order from Cart
- **Method:** `POST`
- **URL:** `/api/orders`
- **Body (JSON):**
```json
{
  "userID": 1
}
```
- **Response:** OrderResponse object with created order details

---

## 5. Rental Endpoint (`/api/rental`)

### Get Rental Info (Dummy)
- **Method:** `GET`
- **URL:** `/api/rental`
- **Response:** "Dummy Rental" string

---

## Complete Testing Flow for Cart to Order Transaction

### Step 1: Create a User
```
POST /api/addUsers
{
  "user_name": "test_user",
  "first_name": "Test",
  "last_name": "User",
  "email": "test@email.com",
  "password": "password123",
  "created_at": "2024-08-07T18:00:00"
}
```

### Step 2: Create Cars
```
POST /api/cars
{
  "make": "Toyota",
  "model": "Camry",
  "year": 2023,
  "price_per_day": 50.00,
  "type": "Sedan"
}
```

### Step 3: Add Items to Cart
```
POST /api/carts/1/items
{
  "carId": 1,
  "days": 3
}
```

### Step 4: Check Cart Contents
```
GET /api/carts/1
```

### Step 5: Create Order from Cart
```
POST /api/orders
{
  "userID": 1
}
```

### Step 6: Verify Order Created
```
GET /api/orders/1
```

### Step 7: Verify Cart is Empty
```
GET /api/carts/1
```

This flow tests the complete Cart to Order transaction implementation.