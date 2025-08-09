package com.rental.Car.controller;

import com.rental.Car.controller.response.OrderResponse;
import com.rental.Car.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Order",description = "Order management API")
@CrossOrigin(origins = "http://localhost:3000")
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



}
