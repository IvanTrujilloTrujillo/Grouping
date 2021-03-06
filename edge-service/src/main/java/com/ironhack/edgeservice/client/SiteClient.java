package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.SiteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("site-service")
public interface SiteClient {

    @GetMapping("/sites/group/{id}")
    List<SiteDTO> getSiteByGroupId(@PathVariable("id") Long id);
}
