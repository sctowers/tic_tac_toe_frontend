import CellButton from "./CellButton";
import Navigation from "./Navigation";
import GameStatus from "./GameStatus";
import StartGameForm from "./StartGameFrom";
const Gameboard = () => {

    return ( 
        <>
        <Navigation />
        < StartGameForm />
        <CellButton />
        <GameStatus />
        </>
     );
}
 
export default Gameboard;