package com.rental.Car.controller;

import com.rental.Car.controller.request.CreateGroup;
import com.rental.Car.controller.request.UpdateGroup;
import com.rental.Car.controller.request.UserRequest;
import com.rental.Car.controller.response.UserResponse;
import com.rental.Car.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "User",description = "User management API")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    public final UserService service;
    private final UserService userService;

    public UserController(UserService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
                service.findAllUsers()
                        .stream()
                        .map(UserResponse::toResponse)
                        .toList()
        );
    }

    @GetMapping("/api/users/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long user_id){
        return ResponseEntity.ok(
                service.findUserByUserId(user_id)
                        .map(UserResponse::toResponse)
                        .orElse(null)
        );
    }

    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id){
        boolean deleted = service.deleteUserByUserId(user_id);
        if (deleted){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Validated(CreateGroup.class) UserRequest userRequest) {
        userService.addUser(
                userRequest.getUser_name(),
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getCreated_at());
    }

    @PutMapping("/api/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId,
                          @RequestBody
                          @Validated(UpdateGroup.class) UserRequest userRequest) {
        userService.updateUser(userId,
                userRequest.getUser_name(),
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getCreated_at());

    }

}
