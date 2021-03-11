package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dtos.*;
import com.ironhack.edgeservice.controller.interfaces.IEdgeController;
import com.ironhack.edgeservice.service.interfaces.IEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class EdgeController implements IEdgeController {

    @Autowired
    private IEdgeService edgeService;

    @PostMapping("/new-user")
    @ResponseStatus(HttpStatus.CREATED)
    public Tocken register(@RequestBody String userJSON) {
        return edgeService.register(userJSON);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Tocken login(@RequestBody String userJSON) {
        return edgeService.login(userJSON);
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> getGroupsByUser(@RequestBody String tocken) {
        return edgeService.getGroupsByUser(tocken);
    }

    @PostMapping("/sites/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SiteDTO> getSiteByGroupId(@PathVariable("id") Long id, @RequestBody String tocken) {
        return edgeService.getSiteByGroupId(id, tocken);
    }

    @PostMapping("/sites")
    @ResponseStatus(HttpStatus.CREATED)
    public SiteDTO saveNewSite(@RequestBody String siteJSON) {
        return edgeService.saveNewSite(siteJSON);
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewReview(@RequestBody String reviewJSON) {
        edgeService.saveNewReview(reviewJSON);
    }

    @PostMapping("/new-group")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO saveNewGroup(@RequestBody String groupJSON) {
        return edgeService.saveNewGroup(groupJSON);
    }

    @PostMapping("/join-group")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public GroupDTO joinGroup(@RequestBody String invitationCodeJSON) {
        return edgeService.joinGroup(invitationCodeJSON);
    }

    @PostMapping("/reviews-by-group/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<ReviewDTO> getReviews(@PathVariable("groupId") Long groupId, @RequestBody String siteJSON) {
        return edgeService.getReviews(groupId, siteJSON);
    }

    @PostMapping("/mean-reviews/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Double meanReviews(@PathVariable("groupId") Long groupId, @RequestBody String siteJSON) {
        return edgeService.meanReviews(groupId, siteJSON);
    }
}
