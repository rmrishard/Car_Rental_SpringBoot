package com.rental.Car.controller;


import com.rental.Car.controller.request.CartCreateRequest;
import com.rental.Car.controller.request.CartUpdateRequest;
import com.rental.Car.controller.response.CartResponse;
import com.rental.Car.model.UserAuth;
import com.rental.Car.services.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        return userAuth.getUserId();
    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<CartResponse> getCart() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartResponse> addToCart(@RequestBody @Valid CartCreateRequest cartRequest){
        Long userId = getCurrentUserId();
        cartService.addToCart(userId, cartRequest.getCarId(), cartRequest.getDays());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    @PutMapping("/items/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CartResponse> updateCartItem(@PathVariable Long carId, @RequestBody @Valid CartUpdateRequest cartRequest){
        Long userId = getCurrentUserId();
        cartService.updateCartItem(userId, carId, cartRequest.getDays());
        return ResponseEntity.ok(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    @DeleteMapping("/items/{carId}")
    public ResponseEntity<CartResponse> removeFromCart(@PathVariable Long carId){
        Long userId = getCurrentUserId();
        cartService.removeFromCart(userId, carId);
        return ResponseEntity.ok(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartResponse> clearCart(){
        Long userId = getCurrentUserId();
        cartService.clearCart(userId);
        return ResponseEntity.ok(CartResponse.toResponse(cartService.findMyCart(userId)));
    }

    
}
