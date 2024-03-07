package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;

    @RequestMapping (value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buidlingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        buildingService.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest);
        mav.addObject("buildingList",responseList);
        return mav;
    }

    @RequestMapping (value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buidlingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }

    @RequestMapping (value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buidlingEdit(@PathVariable("id") Long Id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setName("ACM Building");
        buildingDTO.setId(Id);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }
}
//        List<BuildingSearchResponse> responseList = new ArrayList<>();
//        BuildingSearchResponse item1 = new BuildingSearchResponse();
//        item1.setId(3L);
//        item1.setName("ACM Building");
//        item1.setAddress("130 Quang Trung, Pham Ngũ Lão, Quận 1");
//        item1.setNumberOfBasement(2L);
//        item1.setManagerName("Anh Long");
//        item1.setManagerPhone("0900000001");
//        item1.setRentArea("200,300");
//        BuildingSearchResponse item2 = new BuildingSearchResponse();
//        item2.setId(4L);
//        item2.setName("Building MA");
//        item2.setAddress("Nguyễn Huệ, Tân Mai, Quận 3");
//        item2.setNumberOfBasement(3L);
//        item2.setManagerName("Anh Hải");
//        item2.setManagerPhone("0900000002");
//        item2.setRentArea("200,300,500");
//        responseList.add(item1);
//        responseList.add(item2);
//        mav.addObject("buildingList",responseList);