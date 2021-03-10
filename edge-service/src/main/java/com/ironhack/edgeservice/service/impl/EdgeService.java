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
import java.util.Formatter;
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
        userDTO = userClient.register(userDTO);

        //Save the user as a member of group global (1)
        groupClient.saveUserAsMemberByGroupId(1L, userDTO.getId());

        //Encode username and password to return a token
        String encodedUsername = Base64.getEncoder().encodeToString(userDTO.getUsername().getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());

        //Add the user id to the tocken
        Formatter fmt = new Formatter();
        fmt.format("%04d", userDTO.getId());

        return new Tocken(fmt + encodedUsername + "@" + encodedPassword);
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

        //Add the user id to the tocken
        Formatter fmt = new Formatter();
        fmt.format("%04d", user.getId());

        return new Tocken(fmt + encodedUsername + "@" + encodedPassword);
    }

    //Return all the groups which the user belongs from the database
    public List<GroupDTO> getGroupsByUser(String tocken) {

        //Checks if username and password are valid
        UserDTO userDTO = checkLogin(tocken);
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        return groupClient.getGroupsByUser(userDTO.getId());
    }


    //Find all the sites associated with a group and return them
    public List<SiteDTO> getSiteByGroupId(Long id, String tocken) {

        //Checks if username and password are valid
        UserDTO userDTO = checkLogin(tocken);
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Check if the group exists
        try {
            groupClient.getGroupById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }

        //Checks if the user belongs to the group
        try {
            List<Long> userIdList = groupClient.getMembersByGroupId(id);
            if(!userIdList.contains(userDTO.getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The user doesn't belong to this group");
            }
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

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(siteDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Delete the tocken
        siteDTO.setTocken(null);

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

            e.printStackTrace();
        }
        System.out.println(reviewJSON);

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(reviewDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Delete the tocken
        reviewDTO.setTocken(null);
        //Set the userId
        reviewDTO.setUserId(userDTO.getId());

        siteClient.saveNewReview(reviewDTO);
    }

    //Creates a new group
    public GroupDTO saveNewGroup(String groupJSON) {
        //Convert JSON object to GroupDTO
        ObjectMapper objectMapper = new ObjectMapper();
        GroupDTO groupDTO = null;
        try {
            groupDTO = objectMapper.readValue(groupJSON, GroupDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(groupDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Delete the tocken
        groupDTO.setTocken(null);
        //Set the userId
        groupDTO.setGroupAdmin(userDTO.getId());
        System.out.println(groupDTO);

        return groupClient.saveNewGroup(groupDTO);
    }

    //Join a user to an existent group
    public GroupDTO joinGroup(String invitationCodeJSON) {
        //Convert JSON object to GroupDTO
        ObjectMapper objectMapper = new ObjectMapper();
        InvitationCodeDTO invitationCodeDTO = null;
        try {
            invitationCodeDTO = objectMapper.readValue(invitationCodeJSON, InvitationCodeDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(invitationCodeDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Check if the invitation code is valid
        Long groupId = (Long.getLong(invitationCodeDTO.getCode().split("#")[1]) - 247) / 34;
        GroupDTO groupDTO = groupClient.getGroupById(groupId);

        //Check if the user is already in the group
        List<GroupDTO> groupDTOList = groupClient.getGroupsByUser(userDTO.getId());
        for (GroupDTO groupDTOFor : groupDTOList) {
            if(groupDTOFor.getId().equals(groupId)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "The user is already in the group");
            }
        }

        //Save the user as a new member of the group
        groupClient.saveUserAsMemberByGroupId(groupId, userDTO.getId());

        return groupDTO;
    }

    //Checks if the username and the password are correct
    public UserDTO checkLogin(String tocken) {
        //Get username in the tocken
        String username = tocken.substring(4).split("@")[0];
        byte[] decodedUsername = Base64.getDecoder().decode(username);
        username = new String(decodedUsername);
        //Get password in the tocken
        String password = tocken.substring(4).split("@")[1];
        byte[] decodedPassword = Base64.getDecoder().decode(password);
        password = new String(decodedPassword);

        //Takes the user from the database by username
        UserDTO userDTO = userClient.findByUsername(username);

        if(!password.equals(userDTO.getPassword())) {
            userDTO = null;
        }

        return userDTO;
    }
}
