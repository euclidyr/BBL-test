package com.bbl.demo.service;

import com.bbl.demo.dto.UserRequest;
import com.bbl.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void getAllUsers_test() {
        List<User> users = userService.findAll();

        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void createUser_test() {
        UserRequest request = new UserRequest();
        request.setName("John");
        request.setUsername("john");
        request.setEmail("john@gmail.com");
        request.setPhone("054588999880");
        request.setWebsite("www.john.com");

        User user = userService.createUser(request);
        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("john", user.getUsername());
        assertEquals("john@gmail.com", user.getEmail());
    }
}
