package com.javaweb.model.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO{
    private Long id;
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String structure;
    private String priceDecription;
    private String street;
    private Long numberOfBasement;
    private Long serviceFee;
    private String direction;
    private Long level;
    private Long area;
    private Long rentPrice;
    private String managerName;
    private String managerPhone;
    private List<String> typeCode;
    private Long motorbikefee;
    private Long overtimefee;
    private Long electricityfee;
    private Long deposit;
    private Long payment;
    private String renttime;
    private String decorationtime;
    private Long brokeragefee;
    private String note;

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getMotorbikefee() {
        return motorbikefee;
    }

    public void setMotorbikefee(Long motorbikefee) {
        this.motorbikefee = motorbikefee;
    }

    public Long getOvertimefee() {
        return overtimefee;
    }

    public void setOvertimefee(Long overtimefee) {
        this.overtimefee = overtimefee;
    }

    public Long getElectricityfee() {
        return electricityfee;
    }

    public void setElectricityfee(Long electricityfee) {
        this.electricityfee = electricityfee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getRenttime() {
        return renttime;
    }

    public void setRenttime(String renttime) {
        this.renttime = renttime;
    }

    public String getDecorationtime() {
        return decorationtime;
    }

    public void setDecorationtime(String decorationtime) {
        this.decorationtime = decorationtime;
    }

    public Long getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Long brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getPriceDecription() {
        return priceDecription;
    }

    public void setPriceDecription(String priceDecription) {
        this.priceDecription = priceDecription;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }


    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }
}