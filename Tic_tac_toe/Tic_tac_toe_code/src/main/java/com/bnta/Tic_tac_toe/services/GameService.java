package com.bnta.Tic_tac_toe.services;

import com.bnta.Tic_tac_toe.models.*;
import com.bnta.Tic_tac_toe.repositories.CellRepository;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CellRepository cellRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(long id){
        return gameRepository.findById(id);
    }

    public void makeCells(int numberOfCells, Game game){
        // initialising
        for (int i = 0; i < numberOfCells; i++) {
            Cell cell = new Cell(Value.EMPTY);
            cell.setCellNumber(i+1);
            cell.setGame(game);
            cellRepository.save(cell);
            game.addCell(cell);
        }
    }

    @Transactional
    public Game startNewGame(long playerId, Difficulty difficulty) {
        Player player = playerRepository.findById(playerId).get();
        Game game = new Game(player);
        game.setDifficulty(difficulty);
        gameRepository.save(game);

        makeCells(9, game);
        gameRepository.save(game);
        return game;
    }

    public void deleteGameById(long gameId){
        Game game = gameRepository.findById(gameId).get();
        for (Cell cell : game.getCells()){
            cellRepository.delete(cell);
        }
        gameRepository.delete(game);
    }

    public boolean isCellFull(Cell cell){
        if (cell.getValue() == Value.EMPTY){
            return false;
        } else{return true;}
    }

    public boolean isBoardFull(List<Cell> cells){
        for (Cell cell : cells){
            if (!isCellFull(cell)){
                return false;
            }
        }
        return true;
    }

    public void makePlayerMove(Cell cell){
        cell.setValue(Value.X);
        cellRepository.save(cell);
    }

    public void makeComputerMove(List<Cell> cells){
//        Create a list of all empty cells
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++){
            if (!isCellFull(cells.get(i))){
                emptyCells.add(i);
            }
        }
//        Make a computer guess in a random unoccupied cell
        Random random = new Random();
        int randomNumber = random.nextInt(emptyCells.size());
        int computerGuess = emptyCells.get(randomNumber);
        cells.get(computerGuess).setValue(Value.O);
        cellRepository.save(cells.get(computerGuess));
    }

    public Cell checkTwoInARow(List<Cell> cells, Value value){
//        check if computer/player has two in a row. If so, return the third cell to complete/block 3 in a row
        int count = 0;
        Cell potentialCell = null;
        for (Cell cell : cells){
            if (cell.getValue().equals(value)){
                count++;
            } else if (cell.getValue().equals(Value.EMPTY)) {
                potentialCell = cell;
            }
        }
        
        if (count == 2 && potentialCell != null){
            return potentialCell;
        }
        return null;
    }
    
    public List<Cell> reactiveMoveComputer(List<Cell> cells){
        List<Cell> cellsToReturn = new ArrayList<>();
        int count = 0;
        for (Cell cell : cells){
            if (cell.getValue() == Value.O){
                count ++;
            } else if (cell.getValue() == Value.EMPTY) {
                cellsToReturn.add(cell);
            }
        }
        if (count == 1 && cellsToReturn.size() == 2){
            return cellsToReturn;
        }
        return null;
    }

    public void makeComputerMoveHard(List<Cell> cells) {
//        Computer makes an intelligent move based on gameState
        List<List<Cell>> cellCombinations = getCellCombinations(cells);
        List<Cell> playerWinningPossibilities = new ArrayList<>();
        List<Cell> computerWinningPossibilites = new ArrayList<>();
//        list of intelligent computer moves if there is no possible 3 in a row to make/block
        List<Cell> computerPossibleMoves = new ArrayList<>();

        boolean isSaved = false;

        // Check for User, computer 2 in a row and set up conditions
//        Create a list of moves that would complete a three in a row for player and computer
        for (List<Cell> cellList : cellCombinations) {
            playerWinningPossibilities.add(checkTwoInARow(cellList, Value.X));
            computerWinningPossibilites.add(checkTwoInARow(cellList, Value.O));
        }

        // Changes different cells depending on conditions
        if (isListContainingACell(computerWinningPossibilites)) {
            List<Cell> validCells = getValidCells(computerWinningPossibilites);
            makeComputerMove(validCells);
            isSaved = true;
        }

        if (!isSaved && isListContainingACell(playerWinningPossibilities)) {
            List<Cell> validCells = getValidCells(playerWinningPossibilities);
            makeComputerMove(validCells);
            isSaved = true;
        }

        if (!isSaved) {
            for (List<Cell> cellList : cellCombinations) {
                List<Cell> reactiveMoveResult = reactiveMoveComputer(cellList);
                if (reactiveMoveResult != null) {
                    computerPossibleMoves.addAll(reactiveMoveResult);
                }
            }

            List<Cell> validCells = getValidCells(computerPossibleMoves);
            if (!validCells.isEmpty()) {
                makeComputerMove(validCells);
                isSaved = true;
            }
        }

        // default if for some reason the conditions aren't met
        if (!isSaved) {
            makeComputerMove(cells);
        }
    }


    private boolean isListContainingACell(List<Cell> cells){
        for (Cell cell : cells){
            if (cell != null){
                return true;
            }
        }
        return false;
    }

    private List<Cell> getValidCells(List<Cell> cells){
        List<Cell> validCells = new ArrayList<>();
        for (Cell cell : cells){
            if(cell != null){
                validCells.add(cell);
            }
        }
        return validCells;
    }

//    Returns a (2D array) of all possible rows/columns/diagonals
    private static List<List<Cell>> getCellCombinations(List<Cell> cells) {
        List<Cell> cellsRow1 = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(1), cells.get(2)));
        List<Cell> cellsRow2 = new ArrayList<>(Arrays.asList(cells.get(3), cells.get(4), cells.get(5)));
        List<Cell> cellsRow3 = new ArrayList<>(Arrays.asList(cells.get(6), cells.get(7), cells.get(8)));

        List<Cell> cellsDiagonal1  = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(4), cells.get(8)));
        List<Cell> cellsDiagonal2 = new ArrayList<>(Arrays.asList(cells.get(2), cells.get(4), cells.get(6)));

        List<Cell> cellsColumn1 = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(3), cells.get(6)));
        List<Cell> cellsColumn2 = new ArrayList<>(Arrays.asList(cells.get(1), cells.get(4), cells.get(7)));
        List<Cell> cellsColumn3 = new ArrayList<>(Arrays.asList(cells.get(2), cells.get(5), cells.get(8)));

        List<List<Cell>> cellCombinations = new ArrayList<>(Arrays.asList(cellsRow1, cellsRow2, cellsRow3, cellsDiagonal1, cellsDiagonal2, cellsColumn1, cellsColumn2, cellsColumn3));
        return cellCombinations;
    }

    public boolean checkLine(List<Cell> cells) {

        Value cellValue = cells.get(0).getValue();

        if (!cellValue.equals(Value.EMPTY)){
            for (int i = 1; i < cells.size(); i++) {
                if (!cellValue.equals(cells.get(i).getValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkWinner(List<Cell> cells){
//        Find all possible ways of winning
        List<List<Cell>> cellCombinations = getCellCombinations(cells);

//        Check each combination to check for a winner
        for (List<Cell> cellList : cellCombinations){
            if(checkLine(cellList)){
                return true;
            }
        }

        return false;
    }

    public List<List<Value>> getGameState(List<Cell> cells){
        List<Value> cellsRow1Values = new ArrayList<>(Arrays.asList(cells.get(0).getValue(), cells.get(1).getValue(), cells.get(2).getValue()));
        List<Value> cellsRow2Values = new ArrayList<>(Arrays.asList(cells.get(3).getValue(), cells.get(4).getValue(), cells.get(5).getValue()));
        List<Value> cellsRow3Values = new ArrayList<>(Arrays.asList(cells.get(6).getValue(), cells.get(7).getValue(), cells.get(8).getValue()));

        List<List<Value>> board = new ArrayList<>(Arrays.asList(cellsRow1Values, cellsRow2Values, cellsRow3Values));

        return board;
    }

    public ReplyDTO processTurn(GameDTO gameDTO, long gameId){
        Game game = gameRepository.findById(gameId).get();
        Cell chosenCell = cellRepository.findByCellNumberAndGameId(gameDTO.getPosition(), gameId);
        Player player = game.getPlayer();

        List<Cell> cells = game.getCells();

//        Check the player move is valid
        if (game.isComplete()){
            return new ReplyDTO("Invalid move, Game is already complete", getGameState(cells), false);
        }
        if (isBoardFull(cells)){
            return new ReplyDTO("Invalid move, board is full",getGameState(cells), false);
        }
        if(isCellFull(chosenCell)){
            return new ReplyDTO("Invalid move, chosen cell is occupied", getGameState(cells), false);
        }
        else{
            makePlayerMove(chosenCell);
        }

//        Check if player won the game with this move
        if(checkWinner(cells)){
            ReplyDTO replyDTO = new ReplyDTO("You won", getGameState(cells), true);
            replyDTO.setResult(Result.WIN);
            game.setResult(Result.WIN);
            game.setComplete(true);
            gameRepository.save(game);
            player.addPoints(500);
            playerRepository.save(player);
            return replyDTO;
        }
//        Check if the board is now full but there was no winner, the game is a draw
        if(isBoardFull(cells)){
            ReplyDTO replyDTO = new ReplyDTO("Game complete, game ended in a draw", getGameState(cells), true);
            replyDTO.setResult(Result.DRAW);
            game.setResult(Result.DRAW);
            game.setComplete(true);
            gameRepository.save(game);

            player.addPoints(200);
            playerRepository.save(player);
            return replyDTO;
        }
//        Computer makes move based on game difficulty
        else {
            if (game.getDifficulty() == Difficulty.EASY){
                makeComputerMove(cells);
            } else if (game.getDifficulty() == Difficulty.HARD) {
                makeComputerMoveHard(cells);
            }
        }

//        If there is now a winner, it must be the computer, so player loses
        if(checkWinner(cells)){
            ReplyDTO replyDTO = new ReplyDTO("You lost", getGameState(cells), true);
            replyDTO.setResult(Result.LOSS);
            game.setResult(Result.LOSS);
            game.setComplete(true);
            gameRepository.save(game);

            player.addPoints(-200);
            playerRepository.save(player);
            return replyDTO;
        }

        else {
            return new ReplyDTO("turn processed", getGameState(cells), true);
        }
    }
}
