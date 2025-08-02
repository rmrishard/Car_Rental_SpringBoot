# Cart/CartItem/Order Refactoring Summary

## Overview
This document summarizes the changes made to fix the logical errors in the Cart, CartItem, and Order models after updating the database schema in PGAdmin.

## Database Schema Changes Made
```sql
-- Remove incorrect relationship
ALTER TABLE cart_items DROP COLUMN order_id;

-- Add correct relationship  
ALTER TABLE cart_items ADD COLUMN cart_id BIGINT NOT NULL;
ALTER TABLE cart_items ADD CONSTRAINT fk_cart_items_cart 
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id);

-- Fix data types for precision
ALTER TABLE cart_items ALTER COLUMN daily_rate TYPE DECIMAL(10,2);
ALTER TABLE orders ALTER COLUMN total_amount TYPE DECIMAL(10,2);

-- Add missing fields
ALTER TABLE cart_items ADD COLUMN subtotal DECIMAL(10,2);
```

## Code Changes Made

### 1. CartItem Model (`CartItem.java`)
**Fixed:**
- ✅ Changed `@JoinColumn(name = "order_id")` → `@JoinColumn(name = "cart_id")`
- ✅ Updated relationship: `Order order` → `Cart cart`
- ✅ Added `BigDecimal subtotal` field with auto-calculation
- ✅ Updated all constructors and getters/setters
- ✅ Uses `BigDecimal` for monetary values (precision fixed)

**Key Methods Added:**
```java
public void setDays(int days) {
    this.days = days;
    this.subtotal = daily_rate.multiply(BigDecimal.valueOf(days));
}

public BigDecimal getSubtotal() {
    return subtotal;
}
```

### 2. Cart Model (`Cart.java`)
**Fixed:**
- ✅ Added `@OneToMany(mappedBy = "cart")` relationship to CartItems
- ✅ Removed individual car fields (cars now accessed via CartItems)
- ✅ Removed `days`, `daily_rate`, `subtotal` (moved to CartItem)
- ✅ Added `getTotalAmount()` method to sum all cart item subtotals
- ✅ Simplified to one cart per user containing multiple items

**Key Methods Added:**
```java
public BigDecimal getTotalAmount() {
    return cartItems.stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}
```

### 3. New Repository (`CartItemJPARepository.java`)
**Created:**
```java
public interface CartItemJPARepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndCar(Cart cart, Car car);
    void deleteByCart(Cart cart);
}
```

### 4. Cart Repository Updated (`CartJPARepository.java`)
**Fixed:**
- ✅ Removed `findByUserAndCar()` method (no longer valid)
- ✅ Changed to `Optional<Cart> findByUser(User user)` (one cart per user)

### 5. CartService Completely Rewritten (`CartService.java`)
**New Methods:**
- `findMyCart(Long user_id)` - Get or create user's cart
- `addToCart(Long user_id, Long carId, int days)` - Add item to cart
- `updateCartItem(Long user_id, Long carId, int days)` - Update item quantity
- `removeFromCart(Long user_id, Long carId)` - Remove specific item
- `clearCart(Long user_id)` - Clear entire cart

**Logic Fixed:**
- One cart per user containing multiple CartItems
- Proper item management with quantity updates
- Auto-calculation of subtotals

### 6. CartController Updated (`CartController.java`)
**New Endpoints:**
- `GET /api/carts/{userId}` - Get user's cart (returns single cart)
- `POST /api/carts/{userId}/items` - Add item to cart
- `PUT /api/carts/{userId}/items/{carId}` - Update cart item
- `DELETE /api/carts/{userId}/items/{carId}` - Remove item
- `DELETE /api/carts/{userId}/clear` - Clear cart

### 7. Response Classes Fixed

#### CartResponse (`CartResponse.java`)
**Updated Structure:**
```java
public class CartResponse {
    private Long cartId;
    private UserResponse user;
    private List<CartItemResponse> items;  // List of items
    private BigDecimal totalAmount;        // Calculated total
}
```

#### CartItemResponse (`CartItemResponse.java`) 
**Fixed:**
- ✅ Uses `BigDecimal` instead of `double`
- ✅ Added `subtotal` field
- ✅ Proper field naming

```java
public class CartItemResponse {
    private Long cartItemId;
    private CarResponse car;
    private int days;
    private BigDecimal dailyRate;
    private BigDecimal subtotal;
}
```

### 8. Request Classes Fixed

#### CartCreateRequest (`CartCreateRequest.java`)
**Fixed:**
- ✅ Removed `cartId` field (auto-generated)
- ✅ Only requires `carId` and `days`

### 9. Car Model Enhanced (`Car.java`)
**Added:**
```java
public BigDecimal getDailyRate() {
    return price_per_day != null ? BigDecimal.valueOf(price_per_day) : BigDecimal.ZERO;
}
```

## API Endpoints

### Cart Management
| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/carts/{userId}` | Get user's cart with all items | None |
| POST | `/api/carts/{userId}/items` | Add item to cart | `{"carId": 1, "days": 3}` |
| PUT | `/api/carts/{userId}/items/{carId}` | Update item quantity | `{"days": 5}` |
| DELETE | `/api/carts/{userId}/items/{carId}` | Remove specific item | None |
| DELETE | `/api/carts/{userId}/clear` | Clear entire cart | None |

### Example API Responses

#### GET /api/carts/1
```json
{
  "cartId": 1,
  "user": {
    "id": 1,
    "name": "John Doe"
  },
  "items": [
    {
      "cartItemId": 1,
      "car": {
        "id": 1,
        "make": "Toyota",
        "model": "Camry",
        "pricePerDay": 50.00
      },
      "days": 3,
      "dailyRate": 50.00,
      "subtotal": 150.00
    }
  ],
  "totalAmount": 150.00
}
```

## Data Flow (Fixed)
```
User adds car to cart
    ↓
Cart.findByUser() or create new Cart
    ↓
CartItem created/updated with Cart reference
    ↓
CartItem.subtotal auto-calculated (days × dailyRate)
    ↓
Cart.getTotalAmount() sums all CartItem subtotals
```

## Known Issues & Future Work

### ⚠️ Order Checkout Issue
The Order model still references CartItem, but CartItem now only references Cart. 

**Options to fix:**
1. **Create OrderItem entity** (recommended)
2. **Modify Order to reference Cart directly**
3. **Allow CartItem to reference both Cart and Order**

**Current Status:** Checkout functionality commented out to prevent compilation errors.

## Benefits of Changes

✅ **Logical Data Model:** Cart → CartItems → (future: Order)  
✅ **Precision Fixed:** BigDecimal for all monetary calculations  
✅ **Proper Relationships:** Each entity has clear responsibilities  
✅ **RESTful API:** Clean, intuitive endpoints  
✅ **Scalable:** One cart per user with multiple items  
✅ **Maintainable:** Clear separation of concerns  

## Testing Recommendations

1. Test cart creation for new users
2. Test adding multiple items to cart
3. Test quantity updates and subtotal calculations
4. Test item removal and cart clearing
5. Test total amount calculations
6. Verify database constraints work correctly

---
**Generated:** $(date)  
**Status:** ✅ Core functionality complete, Order checkout pending design decision