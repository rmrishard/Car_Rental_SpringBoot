package com.rental.Car.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter


public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;



    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Car car;

    private int days;
    private double daily_rate;



    public Long getItem_id() {
        return item_id;
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

    public OrderItem(Long item_id, Order order, Car car, int days, double daily_rate) {
        this.item_id = item_id;
        this.order = order;
        this.car = car;
        this.days = days;
        this.daily_rate = daily_rate;

    }

    public OrderItem() {
        // JPA requires this
    }
}
