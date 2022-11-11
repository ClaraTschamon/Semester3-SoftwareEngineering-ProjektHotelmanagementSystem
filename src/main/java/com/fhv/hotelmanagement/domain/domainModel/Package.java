package com.fhv.hotelmanagement.domain.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.PackageEntity;

import java.math.BigDecimal;

public class Package {

    private PackageEntity entity;
    private String name;
    private BigDecimal pricePerNight;

    public Package(PackageEntity entity){
        this.entity = entity;
        this.name = entity.getName();
        this.pricePerNight = entity.getPricePerNight();
    }

    public PackageEntity getEntity(){
        return entity;
    }

    public String getName(){
        return name;
    }


}
