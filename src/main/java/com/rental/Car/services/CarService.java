package com.rental.Car.services;


import com.rental.Car.model.Car;
import com.rental.Car.repository.CarJpaRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CarService {


    private final CarJpaRepository repository;

    public CarService(CarJpaRepository repository) {
        this.repository = repository;
    }
//Get all cars
    public List<Car> getCars() {
        return repository.findAll();
    }
//Find by ID
  public Optional<Car> getCar(Long carId) {
       return repository.findById(carId);
    }
//Find by brand
    public List<Car> getCarByBrand(String model) {
        return repository.findByModel(model);

    }

    public void createCar(@NonNull String make, String model, Integer year,
                          Double price_per_day, String type) {
        repository.save(new Car(make,model,year,price_per_day,type ));
    }

    public void updateCar (Long carId, @NonNull String make, String model, Integer year,
                           Double price_per_day, String type) {
        Car car = repository.findById(carId).orElseThrow(()->new IllegalArgumentException("Car Not Found"));
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPrice_per_day(price_per_day);
        car.setType(type);
        repository.save(car);
    }

}
