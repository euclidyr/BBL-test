package com.bbl.demo.controller;

import com.bbl.demo.dto.UserRequest;
import com.bbl.demo.model.User;
import com.bbl.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers_test() throws Exception {
        List<User> users = List.of(
                new User(1L, "Mike",
                        "mike", "mike@example.com",
                        "446464", "www.mike.com")
        );

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mike"));
    }

    @Test
    void createUser_test() throws Exception {
        User user = new User(
                4L,
                "John Doe",
                "john",
                "john@gmail.com",
                "123456",
                "www.john.com"
        );
        when(userService.createUser(any(UserRequest.class)))
                .thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "John Doe",
                        "username": "john",
                        "email": "john@gmail.com",
                        "phone": "123456",
                        "website": "www.john.com"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
