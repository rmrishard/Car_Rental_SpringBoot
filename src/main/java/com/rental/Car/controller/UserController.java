package com.rental.Car.controller;

import com.rental.Car.controller.request.CreateGroup;
import com.rental.Car.controller.request.UpdateGroup;
import com.rental.Car.controller.request.UserRequest;
import com.rental.Car.controller.response.UserResponse;
import com.rental.Car.model.Role;
import com.rental.Car.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.rental.Car.model.UserAuth;

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
    public ResponseEntity<?> deleteUser(@PathVariable Long user_id){
        try {
            if (user_id == null) {
                return ResponseEntity.badRequest().body("User ID cannot be null");
            }
            
            boolean deleted = service.deleteUserByUserId(user_id);
            if (deleted){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user: " + e.getMessage());
        }
    }

    @PostMapping("/api/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUser(@RequestBody @Validated(CreateGroup.class) UserRequest userRequest, 
                                   Authentication authentication) {
        try {
            Role requestedRole = userRequest.getRole() != null ? userRequest.getRole() : Role.USER;
            
            // Check role-based permissions
            if (requestedRole == Role.ADMIN) {
                // Only authenticated admins can create admin users
                if (authentication == null || 
                    !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Only administrators can create admin users");
                }
            }
            
            userService.addUser(
                    userRequest.getUser_name(),
                    userRequest.getFirst_name(),
                    userRequest.getLast_name(),
                    userRequest.getEmail(),
                    userRequest.getPassword(),
                    userRequest.getCreated_at(),
                    requestedRole);
            
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error creating user: " + e.getMessage());
        }
    }

    @PutMapping("/api/users/{userId}")
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

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserResponse> getUserProfile(Authentication authentication) {
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            Long userId = userAuth.getUserId();
            
            return ResponseEntity.ok(
                service.findUserByUserId(userId)
                    .map(UserResponse::toResponse)
                    .orElseThrow(() -> new RuntimeException("User not found"))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/api/users/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody @Validated(UpdateGroup.class) UserRequest userRequest,
                                             Authentication authentication) {
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            Long userId = userAuth.getUserId();
            
            userService.updateUser(userId,
                userRequest.getUser_name(),
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getCreated_at());
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error updating profile: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/users/profile")
    public ResponseEntity<?> deleteUserProfile(Authentication authentication) {
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            Long userId = userAuth.getUserId();
            
            boolean deleted = service.deleteUserByUserId(userId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error deleting account: " + e.getMessage());
        }
    }

}
