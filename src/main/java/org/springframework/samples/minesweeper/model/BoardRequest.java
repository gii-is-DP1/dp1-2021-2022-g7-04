package org.springframework.samples.minesweeper.model;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.minesweeper.board.DifficultyLevel;

import lombok.Getter;
import lombok.Setter;

/*
 * GAME LEVELS
 * -----------
 * 
 * - Beginner: 8 × 8 		/ 10 mines.
 * - Medium: 16 × 16 		/ 40 mines.
 * - Ace: 16 × 30 			/ 99 mines.
 * - Custom: 8-16 x 8-30 	/ 10-99 mines.
 */

@Getter
@Setter
public class BoardRequest {
	@NotEmpty
	private String playerName;

	@NotNull
	@Min(8)
	@Max(16)
	private int rows;

	@NotNull
	@Min(8)
	@Max(30)
	private int columns;

	@NotNull
	@Min(10)
	@Max(99)
	private int mines;

	@NotEmpty
	private DifficultyLevel level;
	
	/**
	 * Set a board request by difficulty (For Beginner, Medium and Ace levels)
	 *
	 * @param level must not be {@literal null}.
	 * @param username must not be {@literal null}.
	 * @return a board request entity for initialize a game.
	 */
	public BoardRequest(DifficultyLevel level, String username) {
		switch (level) {
			case BEGINNER:
				this.rows = 8;
				this.columns = 8;
				this.mines = 10;
				break;
			case MEDIUM:
				this.rows = 16;
				this.columns = 16;
				this.mines = 40;
				break;
			case ACE:
				this.rows = 16;
				this.columns = 30;
				mines = 99;
				break;
			default:
				this.rows = 8;
				this.columns = 8;
				this.mines = 10;
		}
		this.level = level;
		this.playerName = "player";
		//this.playerName = username;
	}
	
	/**
	 * Set a board request by rows, columns and number of mines (Only for Custom level)
	 *
	 * @param rows must not be {@literal null}.
	 * @param cols must not be {@literal null}.
	 * @param mines must not be {@literal null}.
	 * @param username must not be {@literal null}.
	 * @return a board request entity for initialize a game.
	 */
	public BoardRequest(int rows, int cols, int mines, String username) {
		this.rows = rows;
		this.columns = cols;
		this.mines = mines;
		this.playerName = username;		
	}

}
