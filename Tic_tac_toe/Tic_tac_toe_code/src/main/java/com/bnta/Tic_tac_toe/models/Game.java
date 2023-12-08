package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnoreProperties({"games"})
    private Player player;

    @Column
    private boolean isComplete;

    @Column
    private Result result;

    @Column
    private Difficulty difficulty;

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties({"game"})
    @JsonIgnore
    private List<Cell> cells;

    public Game(Player player) {
        this.player = player;
        this.isComplete = false;
        this.cells = new ArrayList<>();
        this.difficulty = Difficulty.EASY;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Cell> getCells() {

//        Orders cells before they are returned
        Collections.sort(this.cells, new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
//              This takes the second cell's cellNumber and subtracts it from the first. If negative, cell 2 is bigger and vice versa. Look into Cocktail Sort for more information
                return cell1.getCellNumber() - cell2.getCellNumber();
            }
        });
        return this.cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell){
        this.cells.add(cell);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
