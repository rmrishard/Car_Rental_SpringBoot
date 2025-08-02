package com.rental.Car.repository;

import com.rental.Car.model.Cart;
import com.rental.Car.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJPARepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
