package com.rental.Car.repository;


import com.rental.Car.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)

public class CarRepositoryTest {
    @Autowired
    CarJpaRepository repository;



   /* @Test
    public void carRepo_FindByMakeContaining_ReturnCar(){
        Car car1 = new Car(
                "Teslaz1",
                "Model-Y",
                2024,
                95.00,
                "Sedan");

        Car car2 = new Car(
                "Teslaz2",
                "Model-Y",
                2024,
                95.00,
                "Sedan");

        repository.save(car1);
        repository.save(car2);


        List<Car> cars = repository.findByMakeContaining("Teslaz");
    }
    @Test
    public void carRepo_FindByMake_ReturnCar(){
        Car car1 = new Car(
                "Teslaz",
                "Model-Y",
                2024,
                95.00,
                "Sedan");

        repository.save(car1);



        List<Car> cars = repository.findByMake("Teslaz");
        assertEquals(1, cars.size());
    }
    @Test
    public void carRepo_FindByModel_ReturnCar(){
        Car car1 = new Car(
                "Volt",
                "Model-Z",
                2024,
                95.00,
                "Sedan");

        repository.save(car1);



        List<Car> cars = repository.findByModel("Model-Z");
        assertEquals(1, cars.size());
    }*/

}
