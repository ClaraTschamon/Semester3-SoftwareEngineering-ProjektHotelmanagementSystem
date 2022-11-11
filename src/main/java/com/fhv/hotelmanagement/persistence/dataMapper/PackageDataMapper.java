package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Package;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.PackageEntity;

import java.util.ArrayList;
import java.util.Optional;

public class PackageDataMapper {

    private PackageDataMapper(){}

    private static PackageDataMapper _instance = new PackageDataMapper();

    public static PackageDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Package> get(final String name){
        PackageEntity entity = PersistenceFacade.instance().entityManager.find(PackageEntity.class, name);
        if(entity != null){
            Package mypackage = new Package(entity);
            return Optional.of(mypackage);
        }
        return Optional.empty();
    }

    //TODO: generisch in persistence facade
    public static ArrayList<Package> getAll(){
        ArrayList<PackageEntity> entities = (ArrayList<PackageEntity>) PersistenceFacade.instance().entityManager.createQuery("from PackageEntity").getResultList();
        ArrayList<Package> packages = new ArrayList<>();
        for(PackageEntity p : entities){
            packages.add(new Package(p));
        }
        return packages;
    }


    //update
    public void store(Package myPackage){
        PersistenceFacade.instance().entityManager.merge(myPackage.getEntity());
    }
}
