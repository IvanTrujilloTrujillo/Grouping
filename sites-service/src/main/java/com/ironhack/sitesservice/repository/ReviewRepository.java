package com.ironhack.sitesservice.repository;

import com.ironhack.sitesservice.model.Review;
import com.ironhack.sitesservice.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByGroupIdAndSiteAndUserId(Long groupId, Site site, Long userId);
    List<Review> findByGroupIdAndUserId(Long groupId, Long userId);
    List<Review> findByGroupIdAndSite(Long groupId, Site site);
}
