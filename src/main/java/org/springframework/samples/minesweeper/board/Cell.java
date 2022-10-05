package org.springframework.samples.minesweeper.board;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cells")
public class Cell extends BaseEntity {
	
	@Range(min = 0, max = 8)
	int minesAround;
	
	String type;
	
	@Range(min = 0, max = 29)
	int xPosition;
	
	@Range(min = 0, max = 29)
	int yPosition;
	boolean isMine;

	@ManyToOne
	MinesweeperBoard minesweeperBoard;

	public Cell() {
		this.type = "UNPRESSED";
	}

	public Integer getPositionXInPixels(Integer size) {
		return (xPosition) * size;
	}

	public Integer getPositionYInPixels(Integer size) {
		return (yPosition) * size;
	}
}