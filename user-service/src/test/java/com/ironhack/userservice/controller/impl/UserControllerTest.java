package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.dtos.UserDTO;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user1 = new User("Iván Trujillo", "ivantllo", "1234");
        user2 = new User("María Pérez", "mariap", "9876");

        userRepository.saveAll(List.of(user1, user2));
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void register_ValidUserDTO_UserDTO() throws Exception {
        UserDTO userDTO = new UserDTO(1L, "Manuel González", "manuelg", "5555");
        String body = objectMapper.writeValueAsString(userDTO);

        MvcResult result = mockMvc.perform(post("/users").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        //System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("manuelg"));
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("5555"));
        assertFalse(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(":1"));
    }

    @Test
    public void register_NotValidUserDTO_BadRequest() throws Exception {
        UserDTO userDTO = new UserDTO(1L, "", "", "");
        String body = objectMapper.writeValueAsString(userDTO);

        MvcResult result = mockMvc.perform(post("/users").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void register_ExistingUsername_Conflict() throws Exception {
        UserDTO userDTO = new UserDTO(1L, "Iván", "ivantllo", "5555");
        String body = objectMapper.writeValueAsString(userDTO);

        MvcResult result = mockMvc.perform(post("/users").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isConflict())
                .andReturn();
    }

    @Test
    public void findByUsername_ExistingUsername_UserDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/" + "ivantllo")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Iván Trujillo"));
    }

    @Test
    public void findByUsername_NotExistingUsername_NotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/" + "ivantlloooooooooooo")
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void findNameByUserId_ExistingId_Name() throws Exception {
        List<User> userList = userRepository.findAll();
        Long id = userList.get(1).getId();

        MvcResult result = mockMvc.perform(get("/users/name/" + id)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Iván Trujillo"));
    }

    @Test
    public void findNameByUserId_NotExistingId_NotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/name/" + 10000)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}