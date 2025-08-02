package com.rental.Car.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartCreateRequest {
    @NotNull
    private Long carId;
    @Min(1)
    private Integer days = 1;

    public Long getCarId() { return carId; }
    public Integer getDays() { return days; }

    public void setCarId(Long carId) { this.carId = carId; }
    public void setDays(Integer days) { this.days = days; }
}