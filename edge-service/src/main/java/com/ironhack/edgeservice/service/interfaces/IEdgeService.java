package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.controller.dtos.*;

import java.util.List;

public interface IEdgeService {

    Tocken register(String userJSON);

    Tocken login(String userJSON);

    List<GroupDTO> getAllGroups();

    List<SiteDTO> getSiteByGroupId(Long id, String tocken);

    SiteDTO saveNewSite(String siteJSON);

    void saveNewReview(String reviewJSON);
}
