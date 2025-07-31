package com.rental.Car.controller.response;


import com.rental.Car.model.OrderItem;

public class OrderItemResponse {
    Long item_id;
    CarResponse car;
    int days;
    double daily_rate;


    public static OrderItemResponse toResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getItem_id(),
                CarResponse.toResponse(item.getCar()),
                item.getDays(),
                item.getDaily_rate());
    }


    public OrderItemResponse(Long item_id, CarResponse car, int days, double daily_rate) {
        this.item_id = item_id;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;
    }

    public Long getItem_id() {
        return item_id;
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

    public OrderItemResponse() {
    }


}
