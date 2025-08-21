package com.rental.Car.controller;


import com.rental.Car.controller.request.CarRequest;
import com.rental.Car.controller.request.CreateGroup;
import com.rental.Car.controller.request.UpdateGroup;
import com.rental.Car.controller.response.CarResponse;
import com.rental.Car.services.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Car",description = "Car management API")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

   @GetMapping
   public ResponseEntity<List<CarResponse>> getCars(@RequestParam(name="model", required = false)String model) {
        if (model == null||model.isEmpty())
            return ResponseEntity.ok(carService.getCars()
                .stream()
                .map(CarResponse::toResponse)
                .toList());


       return ResponseEntity.ok(carService.getCarByBrand(model)
                .stream()
                .map(CarResponse::toResponse)
                .toList());
    }
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCar(carId)
                .map(CarResponse::toResponse)
                .orElse(null));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody @Validated(CreateGroup.class) CarRequest carRequest) {
        carService.createCar(
                carRequest.getMake(),
                carRequest.getModel(),
                carRequest.getYear(),
                carRequest.getPrice_per_day(),
                carRequest.getType(),
                carRequest.getImageUrl());
    }

    @PutMapping("/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCar(@PathVariable Long carId,
                          @RequestBody
                          @Validated(UpdateGroup.class) CarRequest carRequest) {
        carService.updateCar(carId,
                carRequest.getMake(),
                carRequest.getModel(),
                carRequest.getYear(),
                carRequest.getPrice_per_day(),
                carRequest.getType(),
                carRequest.getImageUrl());

    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        boolean deleted = carService.deleteCar(carId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    }
