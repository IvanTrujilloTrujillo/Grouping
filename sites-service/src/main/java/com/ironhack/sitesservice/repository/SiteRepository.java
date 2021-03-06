package com.ironhack.sitesservice.repository;

import com.ironhack.sitesservice.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findByGroupId(Long id);
}
