package com.rental.Car.controller;


import com.rental.Car.controller.request.CartCreateRequest;
import com.rental.Car.controller.request.CartUpdateRequest;
import com.rental.Car.controller.response.CartResponse;
import com.rental.Car.services.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart",description = "Cart management API")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    @PostMapping("/{userId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@PathVariable Long userId, @RequestBody @Valid CartCreateRequest cartRequest){
        cartService.addToCart(userId, cartRequest.getCarId(), cartRequest.getDays());
    }

    @PutMapping("/{userId}/items/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long carId, @RequestBody @Valid CartUpdateRequest cartRequest){
        cartService.updateCartItem(userId, carId, cartRequest.getDays());
    }

    @DeleteMapping("/{userId}/items/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromCart(@PathVariable Long userId, @PathVariable Long carId){
        cartService.removeFromCart(userId, carId);
    }

    @DeleteMapping("/{userId}/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable Long userId){
        cartService.clearCart(userId);
    }

    
}
