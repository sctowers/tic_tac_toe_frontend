import Cell from "./Cell";
import GameStatus from "./GameStatus";
const Gameboard = ({ game, onCellClick }) => {

    // Emmanuel Face
    // let playerName = game.player.playerName;
    // console.log(playerName)

    let mappedCells = game.board.flatMap((row, rowIndex) => row.map((cell, colIndex) => (
    <Cell
        key={rowIndex * game.board.length + colIndex}
        value={cell}
        onClick={() => onCellClick(rowIndex, colIndex, cell)}
        // player={playerName}

    />
    
    ))
);

return (
    <>
    <div className="cellWrapper">
    <div className="cells"> {mappedCells}</div>
    </div>
    <GameStatus game={game} />
    
    </>
);

}

export default Gameboard;