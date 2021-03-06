package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import com.ironhack.edgeservice.controller.dtos.SiteDTO;

import java.util.List;

public interface IEdgeController {

    List<GroupDTO> getAllGroups();

    List<SiteDTO> getSiteByGroupId(Long id);

    void saveNewSite(SiteDTO siteDTO);
}
