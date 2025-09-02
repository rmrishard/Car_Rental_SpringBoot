package com.rental.Car.services;


import com.rental.Car.model.Car;
import com.rental.Car.repository.CarJpaRepository;
import com.rental.Car.repository.CartItemJPARepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class CarService {


    private final CarJpaRepository repository;
    private final CartItemJPARepository cartItemRepository;

    public CarService(CarJpaRepository repository, CartItemJPARepository cartItemRepository) {
        this.repository = repository;
        this.cartItemRepository = cartItemRepository;
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
                          Double price_per_day, String type, String imageUrl) {
        repository.save(new Car(make,model,year,price_per_day,type,imageUrl ));
    }

    public void updateCar (Long carId, @NonNull String make, String model, Integer year,
                           Double price_per_day, String type, String imageUrl) {
        Car car = repository.findById(carId).orElseThrow(()->new IllegalArgumentException("Car Not Found"));
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPrice_per_day(price_per_day);
        car.setType(type);
        car.setImageUrl(imageUrl);
        repository.save(car);
    }

    @Transactional
    public boolean deleteCar(Long carId) {
        if (repository.existsById(carId)) {
            Car car = repository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
            
            // Deletes all cart items that reference this car
            cartItemRepository.deleteByCar(car);
            repository.deleteById(carId);
            return true;
        }
        return false;
    }

}
