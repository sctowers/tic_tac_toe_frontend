package com.bnta.Tic_tac_toe.repositories;

import com.bnta.Tic_tac_toe.models.Cell;
import com.bnta.Tic_tac_toe.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository  extends JpaRepository<Game, Long> {

}
