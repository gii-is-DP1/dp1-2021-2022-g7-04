package org.springframework.samples.minesweeper.model;

import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "board_requests")
public class BoardRequest extends BaseEntity {
	
	private static final int MIN_ROWS = 8;
	private static final int MAX_ROWS = 16;
	
	private static final int MIN_COLUMNS = 8;
	private static final int MAX_COLUMNS = 30;
	
	private static final int MIN_MINES = 10;
	private static final int MAX_MINES = 99;
	
	private static final int BEGINNER_ROWS = 8;
	private static final int BEGINNER_COLUMNS = 8;
	private static final int BEGINNER_MINES = 10;
	
	private static final int MEDIUM_ROWS = 16;
	private static final int MEDIUM_COLUMNS = 16;
	private static final int MEDIUM_MINES = 40;
	
	private static final int ACE_ROWS = 16;
	private static final int ACE_COLUMNS = 30;
	private static final int ACE_MINES = 99;
	
	@NotEmpty
	private String playerName;

	@NotNull
	@Min(MIN_ROWS)
	@Max(MAX_ROWS)
	private int rows;

	@NotNull
	@Min(MIN_COLUMNS)
	@Max(MAX_COLUMNS)
	private int columns;

	@NotNull
	@Min(MIN_MINES)
	@Max(MAX_MINES)
	private int mines;
	
	private int timer;

	private DifficultyLevel level;

	public BoardRequest() {
	}

	/**
	 * Set a board request by difficulty (Beginner, Medium and Ace levels)
	 *
	 * @param level    must not be {@literal null}.
	 * @param username must not be {@literal null}.
	 * @return a board request entity for initialize a game.
	 */
	public BoardRequest(DifficultyLevel level, String username) {
		switch (level) {
		case BEGINNER:
			this.rows = 	BEGINNER_ROWS;
			this.columns = 	BEGINNER_COLUMNS;
			this.mines = 	BEGINNER_MINES;
			break;
		case MEDIUM:
			this.rows = 	MEDIUM_ROWS;
			this.columns = 	MEDIUM_COLUMNS;
			this.mines = 	MEDIUM_MINES;
			break;
		case ACE:
			this.rows = 	ACE_ROWS;
			this.columns = 	ACE_COLUMNS;
			mines = 		ACE_MINES;
			break;
		default:
			this.rows = 	BEGINNER_ROWS;
			this.columns = 	BEGINNER_COLUMNS;
			this.mines = 	BEGINNER_MINES;
		}
		this.level = level;
		this.playerName = username;
	}

	/**
	 * Set a board request by rows, columns and number of mines (Only for Custom
	 * level)
	 *
	 * @param rows     must not be {@literal null}.
	 * @param cols     must not be {@literal null}.
	 * @param mines    must not be {@literal null}.
	 * @param username must not be {@literal null}.
	 * @return a board request entity for initialize a game.
	 */
	public BoardRequest(int rows, int cols, int mines, String username) {
		this.rows = rows;
		this.columns = cols;
		this.mines = mines;
		this.playerName = username;
		this.level = DifficultyLevel.CUSTOM;
	}
}