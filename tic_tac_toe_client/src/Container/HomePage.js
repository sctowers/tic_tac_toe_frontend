import { useState, useEffect } from "react";
import { createBrowserRouter, RouterProvider, Routes } from "react-router-dom";
import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameForm";
import Navigation from "../Components/Navigation";

const HomePage = () => {
    const [game, setGame] = useState([]);

    const startGame = async (difficulty, playerId) => {
        const response = await fetch("http://localhost:8080/games", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ difficulty, playerId }),

        })
        const newGame = await response.json();
        setGame(newGame);

        console.log(newGame);
    }

    useEffect(() => {
        startGame("EASY", 1);
    }, [])

    const gameRoutes = createBrowserRouter([
        {
            path: "/",
            element: <Navigation/>,
            children: [
                {
                    path: "/game",
                    element: (
                        <StartGameForm startGame={startGame}/>
                    ),
                },
            {
                    path:"/gameboard",
                    element:(<Gameboard game={game}/>),
            }
        ]
        }
]);

    return (
        <>
            <h1>TicTacToe🕹️</h1>
            <RouterProvider router={gameRoutes}/>
        
        </>

    );

}

export default HomePage;