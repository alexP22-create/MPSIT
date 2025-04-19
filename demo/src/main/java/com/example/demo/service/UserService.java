package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByName(String name) {
        return  userRepository.findUserByUsername(name);
    }

    public void saveUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) != null)
                return;

        Role role = userRepository.findRoleByName(user.getRole().getRole());
        if (role != null) {
            user.setRole(role);
        } else {
            return;
        }

        userRepository.save(user);
    }

}
