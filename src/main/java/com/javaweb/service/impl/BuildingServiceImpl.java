package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.impl.BuildingRepositoryCustomImpl;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
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

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity buildingEntity =  buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentArea();
        String[] values = buildingEntity.getType().split(",");
        String rentType = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        List<String> typeCode = new ArrayList<>();
        for(String it : values){
            typeCode.add(it);
        }
        buildingDTO.setTypeCode(typeCode);
        buildingDTO.setRentArea(rentType);
        return buildingDTO;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
            BuildingEntity building = new BuildingEntity();
                building = modelMapper.map(buildingDTO,BuildingEntity.class);
                List<String> typeCode = buildingDTO.getTypeCode();
                String typeCodeFinal = typeCode.stream().map(it -> it.toString()).collect(Collectors.joining(", "));
                building.setType(typeCodeFinal);
                buildingRepository.save(building);

                String rentArea =buildingDTO.getRentArea();
                String[] values = rentArea.split(",");
                List<RentAreaEntity> rentAreaEntities =new ArrayList<>();
                for (String it : values){
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(Integer.parseInt(it));
                    rentAreaEntity.setBuilding(building);
                    rentAreaEntities.add(rentAreaEntity);
                }
                List<RentAreaEntity> delete = rentAreaRepository.findAllByBuildingEntityId(building.getId());
                rentAreaRepository.deleteAll(delete);
                rentAreaRepository.saveAll(rentAreaEntities);
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {
        for(Long it : ids){
            List<RentAreaEntity> delete = rentAreaRepository.findAllByBuildingEntityId(it);
            rentAreaRepository.deleteAll(delete);
            buildingRepository.deleteById(it);
        }
    }

    @Override
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
    BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
    List<UserEntity> user = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
    for(UserEntity it: user){
        AssignmentBuildingEntity assignmentBuilding = new AssignmentBuildingEntity();
        assignmentBuilding.setBuilding(building);
        assignmentBuilding.setUser(it);
        assignmentBuildingRepository.save(assignmentBuilding);
    }
    }
}
