package com.ironhack.sitesservice.controller.interfaces;

import com.ironhack.sitesservice.controller.dtos.SiteDTO;

import java.util.List;

public interface ISiteController {

    List<SiteDTO> getSiteByGroupId(Long id);
}
