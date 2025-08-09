package com.rental.Car.services;


import com.rental.Car.model.*;
import com.rental.Car.repository.CartJPARepository;
import com.rental.Car.repository.OrderJPARepository;
import com.rental.Car.repository.UserJPARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.*;

@Service

public class OrderService {
    private final OrderJPARepository orderRepository;
    private final UserJPARepository userRepository;
    private final CartService cartService;
    private final UserJPARepository userJPARepository;
    private final CartJPARepository cartJPARepository;

    private final String USER_NOT_FOUND_ERROR = "User Not Found";


    public OrderService(OrderJPARepository orderRepository, UserJPARepository userRepository, CartService cartService, UserJPARepository userJPARepository, CartJPARepository cartJPARepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.userJPARepository = userJPARepository;
        this.cartJPARepository = cartJPARepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder (Long order_id){

        return orderRepository.findById(order_id);
    }


    @Transactional
    public Order createOrderFromCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartService.findMyCart(userId);

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot create order from empty cart");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotal_amount(cart.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setOrder_date(new Date());
        order.setCreated_at(new Timestamp(System.currentTimeMillis()));

        order = orderRepository.save(order);

        Set<CartItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setOrder(order);  // Associate with order
            cartItem.setCart(null);    // Remove cart association
            orderItems.add(cartItem);
        }
        order.setOrderItems(orderItems);

        cart.getCartItems().clear();
        cartJPARepository.save(cart);

        return orderRepository.save(order);
    }

}
