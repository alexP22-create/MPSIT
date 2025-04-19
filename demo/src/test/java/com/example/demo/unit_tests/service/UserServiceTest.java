package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByName() {
        String username = "user1";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findUserByUsername(username)).thenReturn(user);

        User result = userService.getUserByName(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).findUserByUsername(username);
    }

    @Test
    public void testSaveUser_NewUserWithExistingRole() {
        User user = new User();
        user.setUsername("user1");
        Role role = new Role();
        role.setRole("ROLE_USER");
        user.setRole(role);

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.findRoleByName(role.getRole())).thenReturn(role);
        when(userRepository.save(user)).thenReturn(user);

        userService.saveUser(user);

        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
        verify(userRepository, times(1)).findRoleByName(role.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testSaveUser_NewUserWithNonExistingRole() {
        User user = new User();
        user.setUsername("user1");
        Role role = new Role();
        role.setRole("ROLE_USER");
        user.setRole(role);

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.findRoleByName(role.getRole())).thenReturn(null);

        userService.saveUser(user);

        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
        verify(userRepository, times(1)).findRoleByName(role.getRole());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testSaveUser_ExistingUser() {
        User user = new User();
        user.setUsername("user1");

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        userService.saveUser(user);

        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
        verify(userRepository, never()).findRoleByName(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}

