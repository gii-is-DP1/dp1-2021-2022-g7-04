package org.springframework.samples.minesweeper.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cells")
public class Cell extends BaseEntity {
	@Column
	Boolean covered;
	Boolean flagged;
	Boolean hasMine;
	int minesAround;
    String type;
    @Range(min=0,max=16)
    int xPosition;
    @Range(min=0,max=30)
    int yPosition;
    
    @ManyToOne
    MinesweeperBoard minesweeperBoard;
    
    public Cell() {
    	this.type = "UNPRESSED";
    }
    
    public Integer getPositionXInPixels(Integer size) {
    	return (xPosition)*size;
    }
    
    public Integer getPositionYInPixels(Integer size) {
    	return (yPosition)*size;
    }
    
}