package com.rental.Car.controller.response;


import com.rental.Car.model.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Setter
public class CartResponse {
    private UserResponse user;
    private CarResponse car;
    private int days;

    public CartResponse(UserResponse user, CarResponse car, int days) {
        this.user = user;
        this.car = car;
        this.days = days;
    }

    public UserResponse getUser() {
        return user;
    }

    public CarResponse getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public static CartResponse toResponse(Cart cart) {
        return new CartResponse(
                UserResponse.toResponse(cart.getUser()),
                CarResponse.toResponse(cart.getCar()),
                cart.getDays()
        );



    }
}
