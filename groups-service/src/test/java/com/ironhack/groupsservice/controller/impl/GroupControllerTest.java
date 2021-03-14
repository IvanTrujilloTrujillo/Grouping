package com.ironhack.groupsservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.groupsservice.controller.dtos.GroupDTO;
import com.ironhack.groupsservice.model.Group;
import com.ironhack.groupsservice.model.GroupMember;
import com.ironhack.groupsservice.repository.GroupMemberRepository;
import com.ironhack.groupsservice.repository.GroupRepository;
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
class GroupControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Group group1;
    private Group group2;

    private GroupMember groupMember1;
    private GroupMember groupMember2;
    private GroupMember groupMember3;
    private GroupMember groupMember4;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        group1 = new Group("Global", 0L);
        group2 = new Group("Friends", 1L);

        group1 = groupRepository.save(group1);
        group2 = groupRepository.save(group2);

        groupMember1 = new GroupMember(1L, group1);
        groupMember2 = new GroupMember(1L, group2);
        groupMember3 = new GroupMember(2L, group1);
        groupMember4 = new GroupMember(2L, group2);

        groupMemberRepository.saveAll(List.of(groupMember1, groupMember2, groupMember3, groupMember4));
    }

    @AfterEach
    public void tearDown() {
        groupMemberRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void getGroupsByUser_ExistingUserId_ListOfGroupsDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/groups-by-user/" + 1L)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Global"));
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Friends"));
    }

    @Test
    public void getGroupById_ExistingGroupId_GroupDTO() throws Exception {
        List<Group> groupList = groupRepository.findAll();
        Long id = groupList.get(1).getId();

        MvcResult result = mockMvc.perform(get("/groups/" + id)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Global"));
    }

    @Test
    public void getGroupById_NotExistingGroupId_NotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/groups/" + 1000)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void saveNewGroup_ValidGroupDTO_GroupDTO() throws Exception {
        GroupDTO groupDTO = new GroupDTO("Family", 3L);
        String body = objectMapper.writeValueAsString(groupDTO);

        MvcResult result = mockMvc.perform(post("/new-group").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Family"));
    }
}