package com.rental.Car.controller.response;


import com.rental.Car.model.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
//@AllArgsConstructor
public class CarResponse {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private Double price_per_day;
    private String type;


    public static CarResponse toResponse(Car car) {
        return new CarResponse(car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getPrice_per_day(),
                car.getType());
    }
    public CarResponse(Long id, String make, String model, Integer year, Double price_per_day, String type) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price_per_day = price_per_day;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Double getPrice_per_day() {
        return price_per_day;
    }

    public String getType() {
        return type;
    }
}
