package com.rental.Car.repository;


import com.rental.Car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarJpaRepository extends JpaRepository<Car, Long> {
    List<Car> findByModel(String model);
    List<Car> findByMake(String make);
    List<Car> findByMakeContaining(String make);

}