package org.springframework.samples.minesweeper.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardRequestService {

	@Autowired 
	BoardRequestRepository boardRequestRepo;
	
	public boolean existsRequestBoardForPlayer(String playerName){
		boolean res = false;
		if(this.findByPlayer(playerName)!=null)
			res = true;
		return res;
	}
	
	public BoardRequest findByPlayer(String playerName){
		return boardRequestRepo.findByPlayer(playerName);
	}
	
	@Transactional
	public void saveRequest(BoardRequest boardRequest) throws DataAccessException {
		boardRequestRepo.save(boardRequest);
	}
	
}
