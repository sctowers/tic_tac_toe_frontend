package com.bnta.Tic_tac_toe.models;

import java.util.List;

public class ReplyDTO {
    private  String message;
    private List<List<Value>> board;
    private boolean isValidMove;
    private Result result;

    public ReplyDTO(String message, List<List<Value>> board, boolean isValidMove) {
        this.message = message;
        this.board = board;
        this.isValidMove = isValidMove;
    }

    public ReplyDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<Value>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Value>> board) {
        this.board = board;
    }

    public boolean isValidMove() {
        return isValidMove;
    }

    public void setValidMove(boolean validMove) {
        isValidMove = validMove;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
