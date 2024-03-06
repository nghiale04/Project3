package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentArea = new ArrayList<>();

    public List<RentAreaEntity> getRentArea() {
        return rentArea;
    }
    public void setRentArea(List<RentAreaEntity> rentArea) {
        this.rentArea = rentArea;
    }

    @OneToMany(mappedBy =  "building", fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity>  assignmentBuildingEntities = new ArrayList<>();

    public List<AssignmentBuildingEntity> getAssignmentBuildingEntities() {
        return assignmentBuildingEntities;
    }

    public void setAssignmentBuildingEntities(List<AssignmentBuildingEntity> assignmentBuildingEntities) {
        this.assignmentBuildingEntities = assignmentBuildingEntities;
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name= "assignmentbuilding",
//        joinColumns = @JoinColumn(name = "buildingid",nullable = false),
//        inverseJoinColumns = @JoinColumn(name = "staffid",nullable = false))
//    private List<UserEntity> userEntities = new ArrayList<>();
//
//    public List<UserEntity> getUserEntities() {
//        return userEntities;
//    }
//    public void setUserEntities(List<UserEntity> userEntities) {
//        this.userEntities = userEntities;
//    }

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "managername")
    private String managerName;
    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "brokeragetee")
    private String brokeragetee;

    @Column(name = "type")
    private String type;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBrokeragetee() {
        return brokeragetee;
    }

    public void setBrokeragetee(String brokeragetee) {
        this.brokeragetee = brokeragetee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public Long getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public Long getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
    public String getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }



}