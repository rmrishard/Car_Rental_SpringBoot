package com.rental.Car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="cart")
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    private Timestamp created_at;


    public Long getCart_id() {
        return cart_id;
    }

    public User getUser() {
        return user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public BigDecimal getTotalAmount() {
        return cartItems.stream()
                .map(CartItem::getSubtotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cart(User user) {
        this.user = user;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }
    public Cart() {
    }

    public Cart(Long cart_id, User user, Set<CartItem> cartItems, Timestamp created_at) {
        this.cart_id = cart_id;
        this.user = user;
        this.cartItems = cartItems;
        this.created_at = created_at;
    }
}
