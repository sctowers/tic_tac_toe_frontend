import { useState, useEffect } from "react";
import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameFrom";
import Navigation from "../Components/Navigation";

const HomePage = () => {
    const [game, setGame] = useState([]);

    const startGame = async (difficulty, playerId) => {
        const response = await fetch("http://localhost:8080/games", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({difficulty, playerId}),

        })
        const newGame = await response.json();
        setGame(newGame);
        
        console.log(newGame);
    }

    useEffect(() => {
        startGame("EASY", 1);
    }, [])


    
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