package com.rental.Car.controller;

import com.rental.Car.controller.request.LoginRequest;
import com.rental.Car.controller.response.LoginResponse;
import com.rental.Car.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Authentication management API")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.authenticate(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            );
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest()
                .body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("An error occurred during authentication");
        }
    }

    @Operation(summary = "Validate token", description = "Check if JWT token is valid")
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }
}