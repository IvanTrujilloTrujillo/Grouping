package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import com.ironhack.edgeservice.controller.dtos.SiteDTO;

import java.util.List;

public interface IEdgeService {

    List<GroupDTO> getAllGroups();

    List<SiteDTO> getSiteByGroupId(Long id);
}
