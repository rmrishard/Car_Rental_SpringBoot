package com.rental.Car.controller.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CartUpdateRequest {

    @PositiveOrZero
    private int days;

    public CartUpdateRequest(int days) {
        this.days = days;
    }

    @PositiveOrZero
    public int getDays() {
        return days;
    }

    public void setDays(@PositiveOrZero int days) {
        this.days = days;
    }
}
