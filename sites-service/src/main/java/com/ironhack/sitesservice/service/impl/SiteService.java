package com.ironhack.sitesservice.service.impl;

import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.model.Review;
import com.ironhack.sitesservice.model.Site;
import com.ironhack.sitesservice.repository.ReviewRepository;
import com.ironhack.sitesservice.repository.SiteRepository;
import com.ironhack.sitesservice.service.interfaces.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService implements ISiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    //Find all the sites associated with a group and return them
    public List<SiteDTO> getSiteByGroupId(Long id) {

        //First, we must retrieve in reviewRepository all the reviews because only the reviews has the GroupId
        List<Review> reviewList = reviewRepository.findAll();

        //Now, we go over the list, checking the reviews which has the groupId we want and saving the site associated
        List<Site> siteList = new ArrayList<>();
        for (Review review : reviewList) {
            if(review.getGroupId().equals(id)) {
                //If a site has more than one review of the same group, we want add it only once
                if(!siteList.contains(review.getSite())) {
                    siteList.add(review.getSite());
                }
            }
        }

        //Convert to a dto list
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (Site site : siteList) {
            SiteDTO siteDTO = new SiteDTO(site.getId(), site.getName(), site.getMapUrl());
            siteDTOList.add(siteDTO);
        }

        return siteDTOList;
    }

    //Save a new Site
    public SiteDTO saveNewSite(SiteDTO siteDTO) {
        //Check if the Site's name is empty
        if(siteDTO.getName().equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The name can't be empty");
        }

        //Check if the Site already exists searching by the name
        if(siteRepository.findByName(siteDTO.getName()).isPresent()) {

            Site site = siteRepository.findByName(siteDTO.getName()).get();
            siteDTO.setId(site.getId());
        } else {
            //Convert SiteDTO to a Site and save it
            Site site = siteRepository.save(new Site(siteDTO.getName(), siteDTO.getMapUrl()));

            //We need to return the SiteDTO with the id
            siteDTO.setId(site.getId());
        }

        return siteDTO;
    }

    //Get all sites from the database
    public List<SiteDTO> getAllSites() {
        //Get the site list
        List<Site> siteList = siteRepository.findAll();

        //Convert to DTO list
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (Site site : siteList) {
            siteDTOList.add(new SiteDTO(site.getId(), site.getName(), site.getMapUrl()));
        }

        return siteDTOList;
    }
}
