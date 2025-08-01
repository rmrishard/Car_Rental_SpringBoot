package com.rental.Car.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;



    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private int days;
    private double daily_rate;



    public Long getItem_id() {
        return cart_item_id;
    }

    public Order getOrder() {
        return order;
    }

    public Car getCar() {
        return car;
    }

    public int getDays() {
        return days;
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public CartItem(Long item_id, Order order, Car car, int days, double daily_rate) {
        this.cart_item_id = item_id;
        this.order = order;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;

    }

    public CartItem() {
        // JPA requires this
    }
}
