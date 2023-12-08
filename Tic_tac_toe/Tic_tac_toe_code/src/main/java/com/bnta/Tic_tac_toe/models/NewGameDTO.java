package com.bnta.Tic_tac_toe.models;

public class NewGameDTO {

    private Difficulty difficulty;
    private long playerId;
    public NewGameDTO(Difficulty difficulty, long playerId) {
        this.difficulty = difficulty;
        this.playerId = playerId;
    }

    public NewGameDTO() {
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
