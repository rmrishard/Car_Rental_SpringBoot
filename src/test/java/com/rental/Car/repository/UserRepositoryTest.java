package com.rental.Car.repository;


import com.rental.Car.model.Car;
import com.rental.Car.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)

public class UserRepositoryTest {
    @Autowired
    UserJPARepository repository;




    @Test
    public void UserRepo_findByUserName_ReturnUser(){
        User user1 = new User(
                "TestUser",
                "Test",
                "User",
                "test@test.com",
                "test123");


        repository.save(user1);


        User users = repository.findByUserName("TestUser");
    }
}
