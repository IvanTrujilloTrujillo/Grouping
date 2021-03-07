package com.ironhack.sitesservice.repository;

import com.ironhack.sitesservice.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    Optional<Site> findByName(String name);
    Optional<Site> findByMapUrl(String mapUrl);
}
