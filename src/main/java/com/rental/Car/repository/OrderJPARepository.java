package com.rental.Car.repository;

import com.rental.Car.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long>{

}
