package com.rental.Car.controller.response;


import com.rental.Car.model.Order;

import java.util.List;

public class OrderResponse {
    UserResponse user;
    double total;
    String status;
    List<CartItemResponse> orderItems;



    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(UserResponse.toResponse(order.getUser()),
                order.getTotal_amount(),
                order.getStatus().toString(),
                order.getOrderItems().stream().map(CartItemResponse::toResponse).toList());
    }



    public UserResponse getUser() {
        return user;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public List<CartItemResponse> getOrderItems() {
        return orderItems;
    }

    public OrderResponse() {
    }

    public OrderResponse(UserResponse user, double total, String status, List<CartItemResponse> orderItems) {
        this.user = user;
        this.total = total;
        this.status = status;
        this.orderItems = orderItems;
    }
}
