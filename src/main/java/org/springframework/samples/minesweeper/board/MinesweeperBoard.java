package org.springframework.samples.minesweeper.board;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    String background;
    @Positive
    int width;
    @Positive
    int height;

    public MinesweeperBoard(){
    	this.background="resources/images/minesweeper.jpg";
        this.width=800;
        this.height=800;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "minesweeperBoard",fetch = FetchType.EAGER)
    List<Cell> cells; 
}
