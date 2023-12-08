package com.bnta.Tic_tac_toe.repositories;

import com.bnta.Tic_tac_toe.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository <Player, Long> {

    List<Player> findAllByOrderByPointsDesc();
}
