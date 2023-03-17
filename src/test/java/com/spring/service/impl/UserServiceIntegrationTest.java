package com.spring.service.impl;

import com.spring.domain.entity.User;
import com.spring.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utils.UserMockedValues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void createUser(){
        User user = userService.create(UserMockedValues.getUserRequestVO());

        assertNotNull(user.getId());
        assertEquals("integration test user", user.getName());
    }

}


