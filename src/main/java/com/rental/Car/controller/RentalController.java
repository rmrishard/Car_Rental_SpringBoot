package com.rental.Car.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rental")
@CrossOrigin(origins = "http://localhost:3000")
public class RentalController {


    public RentalController() {

    }
    @GetMapping
    public ResponseEntity<String> getRental() {
        return ResponseEntity.ok("Dummpy Rental");
    }
}
