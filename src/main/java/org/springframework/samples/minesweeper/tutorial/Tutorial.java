package org.springframework.samples.minesweeper.tutorial;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tutorial")
public class Tutorial extends BaseEntity {

	@Column
	private String goal;
	
	@Column(columnDefinition="TEXT")
	private String players;
	
	@Column(columnDefinition="TEXT")
	private String levels;
	
	@Column(columnDefinition="TEXT")
	private String concepts;
	
	@Column(columnDefinition="TEXT")
	private String howToPlay;
	
}