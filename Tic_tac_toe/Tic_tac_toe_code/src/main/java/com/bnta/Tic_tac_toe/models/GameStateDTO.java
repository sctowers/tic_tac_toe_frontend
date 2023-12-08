package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class GameStateDTO {

    private Long id;
    @JsonIgnoreProperties({"games"})
    private Player player;
    private boolean isComplete;
    private Result result;

    private List<List<Value>> board;

    public GameStateDTO(Long id, Player player, boolean isComplete, Result result) {
        this.id = id;
        this.player = player;
        this.isComplete = isComplete;
        this.result = result;
    }

    public GameStateDTO() {
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

    public List<List<Value>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Value>> board) {
        this.board = board;
    }
}
