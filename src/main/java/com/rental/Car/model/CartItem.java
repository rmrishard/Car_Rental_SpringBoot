package com.rental.Car.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter


public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_item_id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private int days;
    private BigDecimal daily_rate;
    private BigDecimal subtotal;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    public Long getItem_id() {
        return cart_item_id;
    }

    public Cart getCart() {
        return cart;
    }

    public Car getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setDays(int days) {
        this.days = days;
        this.subtotal = daily_rate.multiply(BigDecimal.valueOf(days));
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
        this.subtotal = daily_rate.multiply(BigDecimal.valueOf(days));
    }

    public CartItem(Long item_id, Cart cart, Car car, int days, BigDecimal daily_rate) {
        this.cart_item_id = item_id;
        this.cart = cart;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;
        this.subtotal = daily_rate.multiply(BigDecimal.valueOf(days));
    }

    public CartItem(Cart cart, Car car, int days, BigDecimal daily_rate) {
        this.cart = cart;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;
        this.subtotal = daily_rate.multiply(BigDecimal.valueOf(days));
    }

    public CartItem() {
        // JPA requires this
    }
}
