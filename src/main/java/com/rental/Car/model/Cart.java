package com.rental.Car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name ="cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private Integer days;
    private BigDecimal daily_rate;
    private BigDecimal subtotal;
    private Timestamp added_at;


    public Long getCart_id() {
        return cart_id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public Integer getDays() {
        return days;
    }


    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public Timestamp getAdded_at() {
        return added_at;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Cart(User user, Car car) {
        this.user = user;
        this.car = car;
        this.days = 1;
    }
}
