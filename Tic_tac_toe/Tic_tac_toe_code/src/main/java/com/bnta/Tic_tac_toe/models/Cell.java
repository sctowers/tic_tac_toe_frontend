package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "cells")
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties({"cells"})
    private Game game;

    @Column
    private Value value;

    @Column
    private int cellNumber;

    public Cell(Value value) {
        this.value = value;
    }

    public Cell(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public int getCellNumber() {return cellNumber;}

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }
}
