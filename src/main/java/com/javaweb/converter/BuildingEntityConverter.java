package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class BuildingEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    public void toBuildingEntityConverter(BuildingDTO buildingDTO){
        BuildingEntity building = new BuildingEntity();
        building = modelMapper.map(buildingDTO,BuildingEntity.class);
        List<String> typeCode = buildingDTO.getTypeCode();
        String typeCodeFinal = typeCode.stream().map(it -> it.toString()).collect(Collectors.joining(","));
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
}
