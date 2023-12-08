package com.bnta.Tic_tac_toe.models;

public class GameDTO {

    private int position;

    public GameDTO(int position){
        this.position = position;
    }

    public GameDTO(){}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
