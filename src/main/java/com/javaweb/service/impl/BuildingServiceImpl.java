package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.impl.BuildingRepositoryCustomImpl;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingRepositoryCustomImpl buildingRepositoryCustom;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get() ; // trả về một building id
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF"); // trả về những staff đã được cấp quyền
        List<AssignmentBuildingEntity> temp = building.getAssignmentBuildingEntities();
        List<UserEntity> staffAssignment = new ArrayList<>();
        for (AssignmentBuildingEntity item : temp){
            UserEntity it = userRepository.getOne(item.getUser().getId());
            staffAssignment.add(it);
        }
//        List<UserEntity> staffAssignment = building.getUserEntities(); // trả về một list user
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it :staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<BuildingSearchResponse>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = buildingDTOConverter.toBuildingDTO(item);
            result.add(building);
        }
        return result;
    }


}
