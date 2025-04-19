package com.example.demo.dto;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;

    private String password;

    private String email;

    private String aboutMe;

    private String favoriteWatch;

    private float myBuget;

    private String role;

    public User generateUser() {
        Role role = new Role(this.role);
        return new User(username, password, email, aboutMe, favoriteWatch, myBuget, role);
    }


}
