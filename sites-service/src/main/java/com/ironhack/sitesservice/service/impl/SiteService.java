package com.ironhack.sitesservice.service.impl;

import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.model.Site;
import com.ironhack.sitesservice.repository.SiteRepository;
import com.ironhack.sitesservice.service.interfaces.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService implements ISiteService {

    @Autowired
    private SiteRepository siteRepository;

    //Find all the sites associated with a group and return them
    public List<SiteDTO> getSiteByGroupId(Long id) {

        //Retrieve the site list from the database
        List<Site> siteList = siteRepository.findByGroupId(id);

        //Convert to a dto list
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (Site site : siteList) {
            SiteDTO siteDTO = new SiteDTO(site.getId(), site.getName(), site.getGroupId(), site.getMapUrl());
            siteDTOList.add(siteDTO);
        }

        return siteDTOList;
    }

    //Save a new Site
    public void saveNewSite(SiteDTO siteDTO) {
        //Check if the Site already exists
        if()

        //Convert Site dto to a Site and save it
        siteRepository.save(new Site(siteDTO.getName(), siteDTO.getGroupId(), siteDTO.getMapUrl()));
    }
}
