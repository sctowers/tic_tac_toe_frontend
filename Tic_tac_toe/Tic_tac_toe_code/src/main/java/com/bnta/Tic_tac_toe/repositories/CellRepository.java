package com.bnta.Tic_tac_toe.repositories;

import com.bnta.Tic_tac_toe.models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell, Long> {
    Cell findByCellNumberAndGameId(int cellNumber, long gameId);
}
