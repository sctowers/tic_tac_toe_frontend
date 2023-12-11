import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameFrom";
import Navigation from "../Components/Navigation";

const HomePage = () => {
    return ( 

        <>
        <h1>TicTacToe🕹️</h1>
        <Navigation />
        <Gameboard />
        <StartGameForm />
        </>

     );
}
 
export default HomePage;