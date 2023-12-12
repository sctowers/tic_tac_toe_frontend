import Cell from "./Cell";
import GameStatus from "./GameStatus";
const Gameboard = ({ game, onCellClick }) => {

    console.log(game.board);

    let mappedCells = game.board.flatMap((row, rowIndex) => row.map((cell, colIndex) => (
    <Cell
        key={rowIndex * game.board.length + colIndex}
        value={cell}
        onClick={() => onCellClick(rowIndex, colIndex, cell)}
    />
    
    ))
);
console.log(mappedCells)

return (
    <>
    {mappedCells}
    <GameStatus />
    </>
);

}

export default Gameboard;