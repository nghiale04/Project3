package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingEntity;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {return id;}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BuildingEntity getBuilding() {
        return buildingEntity;
    }

    public void setBuilding(BuildingEntity building) {
        this.buildingEntity = building;
    }
}