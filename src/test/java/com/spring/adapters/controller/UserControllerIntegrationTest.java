package com.spring.adapters.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import utils.UserMockedValues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSaveUserSuccessfully() throws Exception {

        UserRequestVO request = UserMockedValues.getUserRequestVO();

        MvcResult result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated()).andReturn();

        String response = result.getResponse().getContentAsString();

        User user = objectMapper.readValue(response, User.class);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(request.getName(), user.getName());
        assertEquals(request.getEmail(), user.getEmail());

    }


}
