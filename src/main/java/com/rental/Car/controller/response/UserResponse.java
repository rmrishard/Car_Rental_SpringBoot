package com.rental.Car.controller.response;


import com.rental.Car.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor

//@AllArgsConstructor
public class UserResponse {
    private String userName;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Timestamp created_at;




    public UserResponse(String first_name, String email) {
        this.first_name = first_name;
        this.email = email;
    }



//    public static UserResponse toResponse(User user) {
//        return new UserResponse(
//                String.format("%s %s", user.getFirst_name(), user.getLast_name()),
//                user.getEmail()
//        );
//    }


    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUser_name(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated_at());
    }



    public UserResponse(String user_name, String first_name, String last_name,
                        String email, String password, Timestamp created_at) {
        this.userName = user_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
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
}
