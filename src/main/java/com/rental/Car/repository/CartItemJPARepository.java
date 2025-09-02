package com.rental.Car.repository;

import com.rental.Car.model.Cart;
import com.rental.Car.model.CartItem;
import com.rental.Car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemJPARepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findByCartAndCar(Cart cart, Car car);
    List<CartItem> findByCar(Car car);
    void deleteByCart(Cart cart);
    void deleteByCar(Car car);
}