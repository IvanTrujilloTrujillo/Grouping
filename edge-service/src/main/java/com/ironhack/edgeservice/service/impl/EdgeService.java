package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.client.GroupClient;
import com.ironhack.edgeservice.client.SiteClient;
import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import com.ironhack.edgeservice.controller.dtos.SiteDTO;
import com.ironhack.edgeservice.service.interfaces.IEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EdgeService implements IEdgeService {

    @Autowired
    private SiteClient siteClient;

    @Autowired
    private GroupClient groupClient;

    //Return all the groups from the database
    public List<GroupDTO> getAllGroups() {
        return groupClient.getAllGroups();
    }

    //Find all the sites associated with a group and return them
    public List<SiteDTO> getSiteByGroupId(Long id) {

        //Check if the group exists
        try {
            groupClient.getGroupById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }

        return siteClient.getSiteByGroupId(id);
    }

    //Save a new Site
    public void saveNewSite(SiteDTO siteDTO) {
        siteClient.saveNewSite(siteDTO);
    }
}
