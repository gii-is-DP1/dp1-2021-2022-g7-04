package org.springframework.samples.minesweeper.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player_stats")
public class PlayerStats extends BaseEntity {
	
	private String player;

	@Column(name = "activated_mines")
	@Digits(fraction = 0, integer = 10)
	@Min(0)
	private Integer numberActivatedMines;
	
	@Column(name = "guessed_mines")
	@Digits(fraction = 0, integer = 10)
	@Min(0)
	private Integer numberGuessedMines;
	
	@Column(name = "total_flags")
	@Digits(fraction = 0, integer = 10)
	@Min(0)
	private Integer numberTotalFlags;
	
	@Column(name = "cells_clicked")
	@Digits(fraction = 0, integer = 15)
	@Min(0)
	private Integer numberCellsClicked;

}
