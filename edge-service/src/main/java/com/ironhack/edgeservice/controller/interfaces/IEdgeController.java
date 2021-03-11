package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.controller.dtos.*;

import java.util.List;

public interface IEdgeController {

    Tocken register(String userJSON);

    Tocken login(String userJSON);

    List<GroupDTO> getGroupsByUser(String tocken);

    List<SiteWithReviewsDTO> getSiteByGroupId(Long id, String tocken);

    SiteDTO saveNewSite(String siteJSON);

    void saveNewReview(String reviewJSON);

    GroupDTO saveNewGroup(String groupJSON);

    GroupDTO joinGroup(String invitationCodeJSON);

    List<ReviewDTO> getReviews(Long groupId, String siteJSON);

    List<SiteDTO> getAllSites(String tocken);

}
