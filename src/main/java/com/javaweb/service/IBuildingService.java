package com.javaweb.service;

import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public interface IBuildingService {
    ResponseDTO listStaffs(Long buildingId);

    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
}
