package org.springframework.samples.minesweeper.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievements")
public class Achievements extends BaseEntity {
	
	@Column(name = "level_achievement") 
	@NotBlank
	private String levelAchievement;

	@Digits(fraction = 0, integer = 10)
	@Min(1)
	private Integer games;

}
