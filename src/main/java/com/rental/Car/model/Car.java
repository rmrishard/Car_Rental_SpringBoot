package com.rental.Car.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="cars") //table name in the database
@Getter @Setter
//@NoArgsConstructor




public class Car {

    @Id //primary key in the DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private Integer year;
    private Double price_per_day;
    private String type;


    @ManyToMany
    @JoinTable(
            name = "car_category",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns  = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new
            HashSet<>();



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

    public Car(String make, String model, Integer year, Double price_per_day, String type) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price_per_day = price_per_day;
        this.type = type;
    }
    public Car() {
    }


    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPrice_per_day(Double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public void setType(String type) {
        this.type = type;
    }



}

