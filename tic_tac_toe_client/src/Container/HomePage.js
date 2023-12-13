import { useState } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameForm";
import Navigation from "../Components/Navigation";
import GameSound from "../Components/GameSound";

const HomePage = () => {
  const [game, setGame] = useState({ board: [] });
//   const [move, setMove] = useState({ board: [] });

  const startGame = async (difficulty, playerId) => {
    const response = await fetch("http://localhost:8080/games", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ difficulty, playerId }),
    });
    const newGame = await response.json();
    const gameStateResponse = await fetch(
      `http://localhost:8080/games/game-state/${newGame.id}`
    );
    const gameWithBoard = await gameStateResponse.json();

    setGame(gameWithBoard);
  };

  const onCellClick = async (rowIndex, colIndex, cell) => {
    if (cell === "EMPTY") {
      let position = rowIndex * 3 + colIndex + 1;
      console.log(position);
      console.log(game.id);

      try {
        const response = await fetch(
        `http://localhost:8080/games/${game.id}`,
        {
          method: "PATCH",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ position: +position }),
        }
      );
      const newMove = await response.json();
      setGame((gameState) => ({...gameState, ...newMove,})) 
      console.log(newMove);
        
      } catch (error) {
        console.log(error)
      }
      
    }
  };

  const gameRoutes = createBrowserRouter([
    {
      path: "/",
      element: <Navigation />,
      children: [
        {
          path: "/game",
          element: <StartGameForm startGame={startGame} />,
        },
        {
          path: "/gameboard",
          element: <Gameboard onCellClick={onCellClick} game={game} />,
        },
      ],
    },
  ]);

  return (
    <>
      <h1>TicTacToe🕹️</h1>
      <hr></hr>
      <RouterProvider router={gameRoutes} />
      <GameSound />
    </>
  );
};

export default HomePage;
