package org.springframework.samples.minesweeper.board;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.samples.minesweeper.model.BaseEntity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "minesweeper_board")
public class MinesweeperBoard extends BaseEntity {
	@NotEmpty
	private String playerName;
	String background;
	@Positive
	int width;
	@Positive
	int height;

	public MinesweeperBoard() {
	}

	public MinesweeperBoard(String playerName) {
		this.background = null;
		this.width = 800;
		this.height = 400;
		this.playerName = playerName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "minesweeperBoard", fetch = FetchType.EAGER)
	List<Cell> cells;
}