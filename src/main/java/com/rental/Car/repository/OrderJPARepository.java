package com.rental.Car.repository;

import com.rental.Car.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJPARepository extends JpaRepository<Order, Long>{
    List<Order> findByUser_Id(Long userId);
}
