package com.rental.Car.services;

import com.rental.Car.controller.response.LoginResponse;
import com.rental.Car.model.User;
import com.rental.Car.model.UserAuth;
import com.rental.Car.repository.UserJPARepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserJPARepository userRepository;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserJPARepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse authenticate(String username, String password) throws AuthenticationException {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        // Get user details
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        User user = userRepository.findByUserName(username);
        
        // Generate JWT token
        String token = jwtService.generateJWTToken(userAuth);
        
        // Return login response
        return new LoginResponse(
            token,
            userAuth.getUserId(),
            user.getUserName(),
            user.getRole().name()
        );
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtService.getUserNameFromToken(token);
    }
}