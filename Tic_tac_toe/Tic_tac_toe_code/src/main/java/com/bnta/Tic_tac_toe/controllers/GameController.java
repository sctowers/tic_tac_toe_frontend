package com.bnta.Tic_tac_toe.controllers;

import com.bnta.Tic_tac_toe.models.*;
import com.bnta.Tic_tac_toe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> allGames = gameService.getAllGames();
        return new ResponseEntity<>(allGames, HttpStatus.OK);
    }

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable long gameId){
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(optionalGame.isPresent()){
            Game game = optionalGame.get();
            return new ResponseEntity<>(game, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/game-state/{gameId}")
    public ResponseEntity<GameStateDTO> getGameState(@PathVariable long gameId){
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(optionalGame.isPresent()){
            Game game = optionalGame.get();
            GameStateDTO gameStateDTO = new GameStateDTO(game.getId(), game.getPlayer(), game.isComplete(), game.getResult());
            gameStateDTO.setBoard(gameService.getGameState(game.getCells()));
            return new ResponseEntity<>(gameStateDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Game> startNewGame(@RequestBody NewGameDTO newGameDTO){
        Game game = gameService.startNewGame(newGameDTO.getPlayerId(), newGameDTO.getDifficulty());
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{gameId}")
    public ResponseEntity<ReplyDTO> updateGame(@PathVariable long gameId, @RequestBody GameDTO gameDTO){
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if (optionalGame.isPresent() && gameDTO.getPosition() > 0 && gameDTO.getPosition() <10) {
            ReplyDTO replyDTO = gameService.processTurn(gameDTO, gameId);
            if(replyDTO.isValidMove()){
                return new ResponseEntity<>(replyDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(replyDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGameById(@PathVariable long id){
        Optional<Game> optionalGame = gameService.getGameById(id);

        if (optionalGame.isPresent()){
            gameService.deleteGameById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

}
