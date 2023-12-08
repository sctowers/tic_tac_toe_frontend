package com.bnta.Tic_tac_toe.components;

import com.bnta.Tic_tac_toe.models.Difficulty;
import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import com.bnta.Tic_tac_toe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        Player zsolt = new Player("Zsolt");
        zsolt.setPoints(500);
        playerRepository.save(zsolt);

        Player saima = new Player("Saima");
        saima.setPoints(1000);
        playerRepository.save(saima);

        Player emmanuel = new Player("Emmanuel");
        emmanuel.setPoints(-400);
        playerRepository.save(emmanuel);

        Player gisele = new Player("Gisele");
        gisele.setPoints(2500);
        playerRepository.save(gisele);

        Player kacper = new Player("Kacper");
        kacper.setPoints(1200);
        playerRepository.save(kacper);
        
        Game game1 = gameService.startNewGame(zsolt.getId(), Difficulty.EASY);

    }


}
