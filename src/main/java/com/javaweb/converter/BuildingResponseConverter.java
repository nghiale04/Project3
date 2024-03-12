package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class BuildingResponseConverter {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO converterById(Long id){
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
}
