package com.rental.Car.services;


import com.rental.Car.model.Order;
import com.rental.Car.repository.OrderJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OrderService {
    private final OrderJPARepository orderRepository;

    public OrderService(OrderJPARepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder (Long order_id){
        return orderRepository.findById(order_id);
    }
}
