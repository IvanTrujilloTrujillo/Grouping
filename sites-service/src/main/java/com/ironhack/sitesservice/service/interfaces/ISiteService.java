package com.ironhack.sitesservice.service.interfaces;

import com.ironhack.sitesservice.controller.dtos.SiteDTO;

import java.util.List;

public interface ISiteService {
    List<SiteDTO> getSiteByGroupId(Long id);

    SiteDTO saveNewSite(SiteDTO siteDTO);
}
