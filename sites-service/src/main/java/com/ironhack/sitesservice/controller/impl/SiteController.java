package com.ironhack.sitesservice.controller.impl;

import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.controller.interfaces.ISiteController;
import com.ironhack.sitesservice.service.interfaces.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SiteController implements ISiteController {

    @Autowired
    private ISiteService siteService;

    @GetMapping("/sites/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SiteDTO> getSiteByGroupId(@PathVariable("id") Long id) {
        return siteService.getSiteByGroupId(id);
    }
}
