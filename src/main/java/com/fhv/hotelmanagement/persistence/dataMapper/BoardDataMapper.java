package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Board;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.PackageEntity;

import java.util.ArrayList;
import java.util.Optional;

public class BoardDataMapper {

    private BoardDataMapper(){}

    private static BoardDataMapper _instance = new BoardDataMapper();

    public static BoardDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Board> get(final String name){
        PackageEntity entity = PersistenceFacade.instance().entityManager.find(PackageEntity.class, name);
        if(entity != null){
            Board mypackage = new Board(entity);
            return Optional.of(mypackage);
        }
        return Optional.empty();
    }

    //TODO: generisch in persistence facade
    public static ArrayList<Board> getAll(){
        ArrayList<PackageEntity> entities = (ArrayList<PackageEntity>) PersistenceFacade.instance().entityManager.createQuery("from PackageEntity").getResultList();
        ArrayList<Board> boards = new ArrayList<>();
        for(PackageEntity p : entities){
            boards.add(new Board(p));
        }
        return boards;
    }


    //update
    public void store(Board myBoard){
        PersistenceFacade.instance().entityManager.merge(myBoard.getEntity());
    }
}
