package com.rental.Car.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarRequest {

    @NotBlank
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 letters")
    private String make;


    @NotNull(groups = CreateGroup.class)
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 letters")
    private String model;

    @NotNull(groups = CreateGroup.class)
    private Integer year;

    @NotNull(groups = CreateGroup.class)
    private Double price_per_day;

    @NotNull(groups = CreateGroup.class)
    private String type;

    private String imageUrl;

    public @NotBlank @Size(min = 2, max = 100, message = "Title must be between 2 and 100 letters") String getMake() {
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

    public String getImageUrl() {
        return imageUrl;
    }
}
