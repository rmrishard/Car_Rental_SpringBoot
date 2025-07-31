package com.rental.Car.controller;


import com.rental.Car.controller.request.CartCreateRequest;
import com.rental.Car.controller.request.CartUpdateRequest;
import com.rental.Car.controller.response.CartResponse;
import com.rental.Car.services.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService
                .findMyCart(userId)
                .stream().map(CartResponse::toResponse)
                .toList());

    }

    @PostMapping("/{userId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@PathVariable Long userId, @RequestBody @Valid CartCreateRequest cartRequest){
        cartService.createCart(userId, cartRequest.getCarId());

    }

    @PutMapping("/{userId}/cars/{carid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long carId, @RequestBody @Valid CartUpdateRequest cartRequest){
        cartService.updateCart(userId, carId, cartRequest.getDays());
    }

    
}
