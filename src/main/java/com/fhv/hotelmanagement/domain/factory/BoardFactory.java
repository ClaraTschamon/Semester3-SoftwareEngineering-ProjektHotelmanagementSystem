//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Board;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.BoardDTO;

import java.util.ArrayList;

public class BoardFactory {
    private static ArrayList<Board> boards;

    public static ArrayList<BoardDTO> getAllBoards() {
        if (boards == null) {
            refreshBoards();
        }

        ArrayList<BoardDTO> boardDTOs = new ArrayList<>();
        for (Board b: boards) {
            boardDTOs.add(createBoardDTO(b));
        }
        return boardDTOs;
    }

    private static void refreshBoards() {
        boards = new ArrayList<>();
        for (Board b: PersistenceFacade.getAllBoards()) {
            boards.add(b);
        }
    }

    protected static BoardDTO createBoardDTO(Board board) {
        return new BoardDTO(board.getName(), board.getPricePerNight());
    }

    protected static Board createBoard(BoardDTO boardDTO){
        return new Board(boardDTO.getName(), boardDTO.getPricePerNight());
    }

    protected static boolean checkBoard(BoardDTO boardDTO) {
        return getAllBoards().contains(boardDTO);
    }
}
