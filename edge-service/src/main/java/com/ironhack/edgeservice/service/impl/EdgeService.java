package com.ironhack.edgeservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.GroupClient;
import com.ironhack.edgeservice.client.SiteClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.controller.dtos.*;
import com.ironhack.edgeservice.service.interfaces.IEdgeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class EdgeService implements IEdgeService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private SiteClient siteClient;

    @Autowired
    private GroupClient groupClient;

    //Create a new User
    public Tocken register(String userJSON) {
        //Convert JSON in UserDTO
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            userDTO = objectMapper.readValue(userJSON, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Hash the password
        userDTO.setPassword(DigestUtils.md5Hex(userDTO.getUsername()).toUpperCase());

        //Save the user on the database
        userClient.register(userDTO);

        //Encode username and password to return a token
        String encodedUsername = Base64.getEncoder().encodeToString(userDTO.getUsername().getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());

        return new Tocken(encodedUsername + "@" + encodedPassword);
    }

    //Check if the user exists with username and password and return it from the database
    public Tocken login(String userJSON) {
        //Convert JSON in UserDTO
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            userDTO = objectMapper.readValue(userJSON, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Find the user from the database by username
        UserDTO user = userClient.findByUsername(userDTO.getUsername());

        String passwordHashed = DigestUtils.md5Hex(user.getUsername()).toUpperCase();
        if(!user.getPassword().equals(passwordHashed)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is incorrect");
        }

        //Encode username and password to return a token
        String encodedUsername = Base64.getEncoder().encodeToString(user.getUsername().getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());

        return new Tocken(encodedUsername + "@" + encodedPassword);
    }

    //Return all the groups from the database
    public List<GroupDTO> getAllGroups() {
        return groupClient.getAllGroups();
    }


    //Find all the sites associated with a group and return them
    public List<SiteDTO> getSiteByGroupId(Long id, String tocken) {

        //Checks if username and password are valid
        if(!checkLogin(tocken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Checks if the user belongs to the group


        //Check if the group exists
        try {
            groupClient.getGroupById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }

        return siteClient.getSiteByGroupId(id);
    }

    //Save a new Site
    public SiteDTO saveNewSite(String siteJSON) {
        //Convert JSON object to SiteDTO
        ObjectMapper objectMapper = new ObjectMapper();
        SiteDTO siteDTO = null;
        try {
            siteDTO = objectMapper.readValue(siteJSON, SiteDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(siteJSON);
            e.printStackTrace();
        }

        return siteClient.saveNewSite(siteDTO);
    }

    //Save a new Review
    public void saveNewReview(String reviewJSON) {
        //Convert JSON object to ReviewDTO
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewDTO reviewDTO = null;
        try {
            reviewDTO = objectMapper.readValue(reviewJSON, ReviewDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(reviewJSON);
            e.printStackTrace();
        }

        siteClient.saveNewReview(reviewDTO);
    }

    //Checks if the username and the password are correct
    public boolean checkLogin(String tocken) {
        boolean correctLogin = false;

        //Get username in the tocken
        String username = tocken.split("@")[0];
        byte[] decodedUsername = Base64.getDecoder().decode(username);
        username = new String(decodedUsername);
        //Get password in the tocken
        String password = tocken.split("@")[1];
        byte[] decodedPassword = Base64.getDecoder().decode(password);
        password = new String(decodedPassword);

        //Takes the user from the database by username
        UserDTO userDTO = userClient.findByUsername(username);

        System.out.println(Arrays.toString(tocken.split("@")));
        System.out.println(password);
        System.out.println(userDTO.getPassword());
        //String passwordHashed = DigestUtils.md5Hex(userDTO.getUsername()).toUpperCase();
        if(password.equals(userDTO.getPassword())) {
            correctLogin = true;
        }

        return correctLogin;
    }
}
