package com.ironhack.edgeservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.GroupClient;
import com.ironhack.edgeservice.client.SiteClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.controller.dtos.*;
import com.ironhack.edgeservice.service.interfaces.IEdgeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        UserDTO userDTO = getUserDTO(userJSON);

        //Hash the password
        userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()).toUpperCase());

        //Save the user on the database
        userDTO = userClient.register(userDTO);

        //System.out.println(userDTO.getId());
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
        UserDTO userDTO = getUserDTO(userJSON);

        //Find the user from the database by username
        UserDTO user = userClient.findByUsername(userDTO.getUsername());

        String passwordHashed = DigestUtils.md5Hex(userDTO.getPassword()).toUpperCase();
        //System.out.println(passwordHashed);
        //System.out.println(user.getPassword());
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
    public List<GroupWithMembersDTO> getGroupsByUser(String tocken) {

        //Checks if username and password are valid
        UserDTO userDTO = checkLogin(tocken);
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Get the group list for the user
        List<GroupDTO> groupDTOList = groupClient.getGroupsByUser(userDTO.getId());

        //Create the list of the groups with the number of member for each one
        List<GroupWithMembersDTO> groupWithMembersDTOList = new ArrayList<>();
        for (GroupDTO groupDTO : groupDTOList) {
            GroupWithMembersDTO groupWithMembersDTO = new GroupWithMembersDTO(
                    groupDTO.getId(),
                    groupDTO.getName(),
                    groupDTO.getGroupAdmin(),
                    groupClient.getMembersByGroupId(groupDTO.getId()).size());
            groupWithMembersDTOList.add(groupWithMembersDTO);
        }

        //System.out.println(groupWithMembersDTOList);
        //Return the list
        return groupWithMembersDTOList;
    }

    //Find all the sites associated with a group and return them
    public List<SiteWithReviewsDTO> getSiteByGroupId(Long id, String tocken) {
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

        List<SiteDTO> siteDTOList = siteClient.getSiteByGroupId(id);
        List<SiteWithReviewsDTO> siteWithReviewsDTOList = new ArrayList<>();
        for (SiteDTO siteDTO : siteDTOList) {
            siteWithReviewsDTOList.add(new SiteWithReviewsDTO(siteDTO.getId(), siteDTO.getName(), siteDTO.getMapUrl(), meanReviews(id, siteDTO)));
        }
        //System.out.println(siteWithReviewsDTOList);

        return siteWithReviewsDTOList;
    }

    //Save a new Site
    public SiteDTO saveNewSite(String siteJSON) {
        //Convert JSON object to SiteDTO
        ObjectMapper objectMapper = new ObjectMapper();
        SiteDTO siteDTO = getSiteDTO(siteJSON);

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
        ReviewDTO reviewDTO = getReviewDTO(reviewJSON);

        //System.out.println(reviewJSON);

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
        GroupDTO groupDTO = getGroupDTO(groupJSON);

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
        InvitationCodeDTO invitationCodeDTO = getInvitationCodeDTO(invitationCodeJSON);

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(invitationCodeDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //System.out.println(invitationCodeDTO.getCode().split("#")[1]);
        //Check if the invitation code is valid
        Long groupId = (Long.parseLong(invitationCodeDTO.getCode().split("#")[1]) - 247) / 34;
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

    //Get all the reviews from a Site and a Group
    public List<ReviewWithUserNameDTO> getReviews(Long groupId, String siteJSON) {
        //Check if the group exists and get it
        GroupDTO groupDTO = groupClient.getGroupById(groupId);

        //Convert JSON object to SiteDTO
        SiteDTO siteDTO = getSiteDTO(siteJSON);

        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(siteDTO.getTocken());
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        //Delete the tocken
        siteDTO.setTocken(null);

        //Get the review list and return it
        List<ReviewDTO> reviewDTOList = siteClient.getReviews(groupId, siteDTO);

        //System.out.println(reviewDTOList);

        //Convert into a review with the user name
        List<ReviewWithUserNameDTO> reviewWithUserNameDTOList = new ArrayList<>();
        for (ReviewDTO reviewDTO : reviewDTOList) {
           ReviewWithUserNameDTO reviewWithUserNameDTO = new ReviewWithUserNameDTO(
                   reviewDTO.getId(),
                   reviewDTO.getGroupId(),
                   reviewDTO.getSite(),
                   reviewDTO.getUserId(),
                   reviewDTO.getRating(),
                   reviewDTO.getComment(),
                   userClient.findNameByUserId(reviewDTO.getUserId()));
           reviewWithUserNameDTOList.add(reviewWithUserNameDTO);
        }

        //And return it
        return reviewWithUserNameDTOList;
    }

    //Calculate the mean of the reviews of a site in a group
    @HystrixCommand(fallbackMethod = "meanReviewsFallBack")
    public Double meanReviews(Long groupId, SiteDTO siteDTO) {
        //Get the review list of the Site and the group
        List<ReviewDTO> reviewDTOList = siteClient.getReviews(groupId, siteDTO);
        Long sum = 0L;
        for (ReviewDTO reviewDTO : reviewDTOList) {
            sum+= reviewDTO.getRating();
        }

        //Calculate the average
        Double mean = sum.doubleValue() / reviewDTOList.size();

        return mean;
    }

    //Get all sites in the database
    @HystrixCommand(fallbackMethod = "getAllSitesFallBack")
    public List<SiteDTO> getAllSites(String tocken) {
        //Check if the tocken is correct
        UserDTO userDTO = checkLogin(tocken);
        if(userDTO == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The credentials aren't correct");
        }

        return siteClient.getAllSites();
    }

    /* ----------- Utility Methods ----------- */

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

    private UserDTO getUserDTO(String userJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            userDTO = objectMapper.readValue(userJSON, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    private SiteDTO getSiteDTO(String siteJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        SiteDTO siteDTO = null;
        try {
            siteDTO = objectMapper.readValue(siteJSON, SiteDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return siteDTO;
    }

    private GroupDTO getGroupDTO(String groupJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        GroupDTO groupDTO = null;
        try {
            groupDTO = objectMapper.readValue(groupJSON, GroupDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return groupDTO;
    }

    private ReviewDTO getReviewDTO(String reviewJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewDTO reviewDTO = null;
        try {
            reviewDTO = objectMapper.readValue(reviewJSON, ReviewDTO.class);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return reviewDTO;
    }

    private InvitationCodeDTO getInvitationCodeDTO(String invitationCodeJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        InvitationCodeDTO invitationCodeDTO = null;
        try {
            invitationCodeDTO = objectMapper.readValue(invitationCodeJSON, InvitationCodeDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return invitationCodeDTO;
    }

    /* ----------- FallBack Methods ----------- */

    public Double meanReviewsFallBack(Long groupId, SiteDTO siteDTO) {

        return 5.0;
    }

    public List<SiteDTO> getAllSitesFallBack(String tocken) {

        return new ArrayList<>();
    }
}
