package com.rental.Car.service;

import com.rental.Car.model.Car;
import com.rental.Car.repository.CarJpaRepository;
import com.rental.Car.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Optional;

import static org.mockito.Mockito.when;



@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class CarServiceTest {


    @Autowired
    //@InjectMocks
    private CarService carService;

    @MockitoBean
    //@Mock
    private CarJpaRepository carJpaRepository;


    @Test
    public void carService_FindCarById_thenReturnCar() {
        Car car = new Car();


        when(carJpaRepository.findById(21L)).thenReturn(Optional.of(car));

        Optional<Car> result = carService.getCar(21L);
        assertTrue(result.isPresent());
        result.ifPresent(value->assertEquals(car, value));
    }
}
