package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import com.ironhack.edgeservice.controller.dtos.SiteDTO;
import com.ironhack.edgeservice.controller.interfaces.IEdgeController;
import com.ironhack.edgeservice.service.interfaces.IEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class EdgeController implements IEdgeController {

    @Autowired
    private IEdgeService edgeService;

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> getAllGroups() {
        return edgeService.getAllGroups();
    }

    @GetMapping("/sites/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SiteDTO> getSiteByGroupId(@PathVariable("id") Long id) {
        return edgeService.getSiteByGroupId(id);
    }

    @PostMapping("/sites")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewSite(@RequestBody SiteDTO siteDTO) {
        edgeService.saveNewSite(siteDTO);
    }

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewReview(@RequestBody ) {
        edgeService.saveNewReview();
    }
}
