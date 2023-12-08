package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String playerName;

    @Column
    private long points;

    @Column
    private Rank rank;

    @OneToMany(mappedBy = "player")
    @JsonIgnoreProperties({"player"})
    private List<Game> games;


    public Player(String playerName) {
        this.playerName = playerName;
        this.games = new ArrayList<>();
        this.points = 0;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public void addPoints(long points){
        this.points+=points;
    }

    public Rank getRank() {
        if (this.points >= 2000){
            return Rank.ROCKSTAR;
        }

        if (this.points >= 1500){
            return Rank.ZSOLT;
        }

        if (this.points >= 1000){
            return Rank.TIC_TAC_TITAN;
        }
        if(this.points >= 500){
            return Rank.CROSSWORD_CRUSADER;
        }
        return Rank.NOVICE_NOUGHT_NINJA;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
