package com.bnta.Tic_tac_toe.controllers;

import com.bnta.Tic_tac_toe.models.LeaderBoardDTO;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable long id){
        Optional<Player> checkPlayer = playerService.getPlayerById(id);

        if (checkPlayer.isPresent()){
            return new ResponseEntity<>(checkPlayer.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/leaderboard")
    public ResponseEntity<LeaderBoardDTO> getLeaderBoard(){
        return new ResponseEntity<>(playerService.getLeaderBoard(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        playerService.addPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePlayerById(@PathVariable long id){
        Optional<Player> optionalPlayer = playerService.getPlayerById(id);

        if (optionalPlayer.isPresent()){
            playerService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Player> updatePlayerName(@PathVariable long id, @RequestBody String newPlayerName){
        Optional<Player> checkPlayer = playerService.getPlayerById(id);

        if (checkPlayer.isPresent()){
            return new ResponseEntity<>(playerService.updatePlayerName(id, newPlayerName), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
