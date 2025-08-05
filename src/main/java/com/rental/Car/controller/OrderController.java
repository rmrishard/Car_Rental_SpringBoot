package com.rental.Car.controller;

import com.rental.Car.controller.response.OrderResponse;
import com.rental.Car.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders()
                .stream().map(OrderResponse::toResponse)
                .toList());

    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId)
                .map(OrderResponse::toResponse)
                .orElse(null));
    }

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody.Order)

}
