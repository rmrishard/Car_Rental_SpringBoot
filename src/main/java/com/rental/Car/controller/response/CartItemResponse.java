package com.rental.Car.controller.response;

import com.rental.Car.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter

public class CartItemResponse {
    private Long cartItemId;
    private CarResponse car;
    private int days;
    private BigDecimal dailyRate;
    private BigDecimal subtotal;

    public static CartItemResponse toResponse(CartItem item) {
        return new CartItemResponse(
                item.getItem_id(),
                CarResponse.toResponse(item.getCar()),
                item.getDays(),
                item.getDaily_rate(),
                item.getSubtotal());
    }

    public CartItemResponse(Long cartItemId, CarResponse car, int days, BigDecimal dailyRate, BigDecimal subtotal) {
        this.cartItemId = cartItemId;
        this.car = car;
        this.days = days;
        this.dailyRate = dailyRate;
        this.subtotal = subtotal;
    }

    public CartItemResponse() {
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public CarResponse getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }


    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setCar(CarResponse car) {
        this.car = car;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }



}