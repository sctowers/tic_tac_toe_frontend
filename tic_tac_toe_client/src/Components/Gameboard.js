import CellButton from "./CellButton";
import GameStatus from "./GameStatus";
const Gameboard = ({game }) => {

    //const gameCells = []

    const cells = (game && game.cell || []);
    console.log(game)
    console.log(cells)


    const mappedCells = cells.map((cell) => {
        return <CellButton key={cell.cellNumber} cell={cell}/>

    })

    return ( 
        <>
        {mappedCells}
        <GameStatus />
        </>
    );
}

export default Gameboard;