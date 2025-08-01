package com.rental.Car.controller.response;


import com.rental.Car.model.CartItem;

public class CartItemResponse {
    Long cart_item_id;
    CarResponse car;
    int days;
    double daily_rate;


    public static CartItemResponse toResponse(CartItem item) {
        return new CartItemResponse(
                item.getItem_id(),
                CarResponse.toResponse(item.getCar()),
                item.getDays(),
                item.getDaily_rate());
    }


    public CartItemResponse(Long item_id, CarResponse car, int days, double daily_rate) {
        this.cart_item_id = item_id;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;
    }

    public Long getItem_id() {
        return cart_item_id;
    }

    public CarResponse getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public CartItemResponse() {
    }


}
