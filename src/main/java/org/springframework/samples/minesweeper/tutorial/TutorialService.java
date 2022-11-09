package org.springframework.samples.minesweeper.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorialService {
	
	private TutorialRepository tutorialRepository;
	
	
	@Autowired
	public TutorialService(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}
	
	@Transactional
	public Tutorial findTutorial() {
		return this.tutorialRepository.findAll().iterator().next();
	}
	
}