package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Board;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BoardEntity;

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
        BoardEntity entity = PersistenceFacade.instance().entityManager.find(BoardEntity.class, name);
        if(entity != null){
            Board board = createBoard(entity);
            return Optional.of(board);
        }
        return Optional.empty();
    }

    public static ArrayList<Board> getAll(){
        ArrayList<BoardEntity> entities = (ArrayList<BoardEntity>) PersistenceFacade.instance().entityManager.createQuery("from BoardEntity").getResultList();
        ArrayList<Board> boards = new ArrayList<>();
        for(BoardEntity b : entities){
            boards.add(createBoard(b));
        }
        return boards;
    }


    //update
    public void store(Board board){
        PersistenceFacade.instance().entityManager.merge(createBoardEntity(board));
    }

    protected static BoardEntity createBoardEntity(Board board) {
        if (board == null) {
            return null;
        }
        return new BoardEntity(board.getName(), board.getPricePerNight());
    }

    protected static Board createBoard(BoardEntity boardEntity) {
        return new Board(boardEntity.getName(), boardEntity.getPricePerNight());
    }
}
