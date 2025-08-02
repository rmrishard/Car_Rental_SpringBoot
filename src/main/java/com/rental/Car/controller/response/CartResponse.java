package com.rental.Car.controller.response;

import com.rental.Car.model.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class CartResponse {
    private Long cartId;
    private UserResponse user;
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;

    public CartResponse(Long cartId, UserResponse user, List<CartItemResponse> items, BigDecimal totalAmount) {
        this.cartId = cartId;
        this.user = user;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public static CartResponse toResponse(Cart cart) {
        List<CartItemResponse> itemResponses = cart.getCartItems()
                .stream()
                .map(CartItemResponse::toResponse)
                .toList();

        return new CartResponse(
                cart.getCart_id(),
                UserResponse.toResponse(cart.getUser()),
                itemResponses,
                cart.getTotalAmount()
        );
    }
}