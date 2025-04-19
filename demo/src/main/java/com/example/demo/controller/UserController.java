package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = {"/addNewUser"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewUser(@RequestBody UserDTO dto) {

        try {
            User user = dto.generateUser();
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = {"/getUser"})
    public User getUser(@RequestParam String name) {
        return userService.getUserByName(name);
    }

}
