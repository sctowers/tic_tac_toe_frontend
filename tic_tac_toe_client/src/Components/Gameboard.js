import React from "react";
import { useState } from "react";
import Cell from "./Cell";
import GameStatus from "./GameStatus";

const Gameboard = ({ game, onCellClick, onResetGame }) => {

    console.log(game.board);

    let mappedCells = game.board.flatMap((row, rowIndex) => row.map((cell, colIndex) => (
    <Cell
        key={rowIndex * game.board.length + colIndex}
        value={cell}
        onClick={() => onCellClick(rowIndex, colIndex, cell)}
    />
    
    ))
);
console.log(mappedCells);

const resetGame = (gameState) => {
    // onResetGame();
    const [game, setGame] = useState(true);
    const [GameState, setGameState] = useState(false);
}

return (
    <>
    <div className="cellWrapper">
    <div className="cells"> {mappedCells}</div>
    </div>
    <GameStatus game={game} />
    
    <button onClick={resetGame}>Reset Game 🔄</button>

    </>
);

}

export default Gameboard;