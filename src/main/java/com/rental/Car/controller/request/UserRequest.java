package com.rental.Car.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public class UserRequest {

    @JsonProperty("user_name")
    @NotBlank
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 letters")
    private String userName;


    @NotNull(groups = CreateGroup.class)
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 letters")
    private String first_name;

    @NotNull(groups = CreateGroup.class)
    private String last_name;

    @NotNull(groups = CreateGroup.class)
    private String email;

    @NotNull(groups = CreateGroup.class)
    private String password;

    private Timestamp created_at;






    public String getUser_name() {
        return userName;
    }
    public void getUser_name(String user_name) {
        this.userName = user_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
