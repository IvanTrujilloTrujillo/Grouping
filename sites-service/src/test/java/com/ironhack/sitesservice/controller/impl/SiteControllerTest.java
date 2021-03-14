package com.ironhack.sitesservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.model.Review;
import com.ironhack.sitesservice.model.Site;
import com.ironhack.sitesservice.repository.ReviewRepository;
import com.ironhack.sitesservice.repository.SiteRepository;
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
class SiteControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SiteRepository siteRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Site site1;
    private Site site2;

    private Review review1;
    private Review review2;
    private Review review3;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        site1 = new Site("Guachinche El Origen", "https://www.google.es/maps/place/El+origen/@28.3797822,-16.5360545,17z/data=!4m5!3m4!1s0xc6a81c09f6b805b:0x5e5f999ec44445c!8m2!3d28.3797586!4d-16.535561");
        site2 = new Site("Dulcería El Rayo", "https://www.google.es/maps/place/Dulcer%C3%ADa+El+Rayo/@28.5048005,-16.3053472,17.42z/data=!4m12!1m6!3m5!1s0xc6a81c09f6b805b:0x5e5f999ec44445c!2sEl+origen!8m2!3d28.3797586!4d-16.535561!3m4!1s0x0:0xee05c552bd270984!8m2!3d28.5040581!4d-16.3049729");

        site1 = siteRepository.save(site1);
        site2 = siteRepository.save(site2);

        review1 = new Review(1L, site1, 1L, 5, "My favourite restaurant");
        review2 = new Review(1L, site1, 2L, 4, "The meat here is delicious");
        review3 = new Review(1L, site2, 1L, 2, "I don't like desserts");

        reviewRepository.saveAll(List.of(review1, review2, review3));
    }

    @AfterEach
    public void tearDown() {
        reviewRepository.deleteAll();
        siteRepository.deleteAll();
    }

    @Test
    public void getSiteByGroupId_GroupId_ListOfSiteDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/sites/group/" + 1L)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Guachinche El Origen"));
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Dulcería El Rayo"));
    }

    @Test
    public void saveNewSite_ValidSiteDTO_SiteDTO() throws Exception {
        SiteDTO siteDTO = new SiteDTO("Guachinche Como En Casa", "https://www.google.es/maps/place/Guachinche+Como+En+Casa/@28.490136,-16.3687639,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x8fb395ab44e49bfc!8m2!3d28.4901316!4d-16.3665752");
        String body = objectMapper.writeValueAsString(siteDTO);

        MvcResult result = mockMvc.perform(post("/sites").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Guachinche Como En Casa"));
    }

    @Test
    public void saveNewSite_NotValidSiteDTO_BadRequest() throws Exception {
        SiteDTO siteDTO = new SiteDTO("", "");
        String body = objectMapper.writeValueAsString(siteDTO);

        MvcResult result = mockMvc.perform(post("/sites").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void getAllSites_NoParams_ListOfSiteDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/all-sites")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Guachinche El Origen"));
        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Dulcería El Rayo"));
    }
}