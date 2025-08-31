package com.rental.Car.services;


import com.rental.Car.model.Role;
import com.rental.Car.model.User;
import com.rental.Car.repository.UserJPARepository;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserJPARepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserJPARepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserByUserId(Long user_id) {
        return repository.findById(user_id);
    }


    @Transactional
    public boolean deleteUserByUserId(Long user_id) {
        if (repository.existsById(user_id)) {
            repository.deleteById(user_id);
            return true;
        }
        return false;
    }

    public void addUser(@NonNull String user_name,
                        String first_name,
                        String last_name,
                        String email,
                        String password,
                        Timestamp created_at)            {
        repository.save(new User(user_name, first_name, last_name, email, password, created_at));
    }
    
    public void addUser(@NonNull String user_name,
                        String first_name,
                        String last_name,
                        String email,
                        String password,
                        Timestamp created_at,
                        Role role) {
        User user = new User(user_name, first_name, last_name, email, passwordEncoder.encode(password), created_at);
        user.setRole(role);
        repository.save(user);
    }


    public void updateUser(Long user_id, @NonNull String user_name, String first_name, String last_name,
                           String email, String password, Timestamp created_at) {
        User user = repository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        user.setUserName(user_name);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreated_at(created_at);
        repository.save(user);


    }
}