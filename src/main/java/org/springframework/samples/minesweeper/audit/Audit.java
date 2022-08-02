package org.springframework.samples.minesweeper.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "audits")
public class Audit extends BaseEntity {
	@Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date startDate;
	
	@Column(name = "end_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date endDate;
	
	@NotNull
	private String player;
	
	@Column(name = "game_status")
	private String gameStatus;
	
	private String difficulty;
	
	private boolean isFinished;
	
	private int minesweeperBoardId;
}