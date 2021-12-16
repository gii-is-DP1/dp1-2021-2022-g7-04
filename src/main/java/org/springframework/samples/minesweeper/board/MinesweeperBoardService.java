package org.springframework.samples.minesweeper.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MinesweeperBoardService {

	@Autowired 
	MinesweeperBoardRepository boardRepo;
	
	public Optional<MinesweeperBoard> findById(Integer id){
		return boardRepo.findById(id);
	}
}
