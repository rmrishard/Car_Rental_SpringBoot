package com.rental.Car.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor



public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @JsonProperty("user_name")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            insertable = true,
            columnDefinition = "timestamp default CURRENT_TIMESTAMP"
    )
    private Timestamp created_at;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cart> carts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public User(@NonNull String user_name, String firstName, String lastName, String email, String password) {
        this.userName = user_name;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.created_at = new Timestamp(System.currentTimeMillis());}

    public Long getId() {
        return user_id;
    }

    public String getUser_name() {
        return userName;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User() {}

    public User(String user_name, String first_name, String last_name, String email, String password, Timestamp created_at) {
        this.userName = user_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.created_at = created_at;
    }
}
