package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardDTO {

    @JsonIgnoreProperties({"games"})
    private List<Player> players;

    public LeaderBoardDTO(List<Player> players) {
        this.players = new ArrayList<>();
    }

    public LeaderBoardDTO() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
