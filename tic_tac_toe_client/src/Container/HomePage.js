import { useState } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameForm";
import Navigation from "../Components/Navigation";
import Leaderboard from "../Components/Leaderboard";

const HomePage = () => {
  const [game, setGame] = useState({ board: [] });
  const [darkMode, setDarkMode] = useState(false);

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
        const response = await fetch(`http://localhost:8080/games/${game.id}`, {
          method: "PATCH",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ position: +position }),
        });
        const newMove = await response.json();
        setGame((gameState) => ({ ...gameState, ...newMove }));
        console.log(newMove);
      } catch (error) {
        console.log(error);
      }
    }
  };

  const gameRoutes = createBrowserRouter([
    {
      path: "/",
      element: (
        <>
          <Navigation />
        </>
      ),
      children: [
        {
          path: "/game",
          element: <StartGameForm startGame={startGame} />,
        },
        {
          path: "/gameboard",
          element: <Gameboard onCellClick={onCellClick} game={game} />,
        },
        {
          path: "/players/leaderboard",
          element: <Leaderboard />,
        },
      ],
    },
  ]);

  // function to toggle dark mode
  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
    let face = document.getElementById("darkModeButton");
    face.innerText === "🌝" ? (face.innerText = "🌚") : (face.innerText = "🌝");
  };

  return (
    <div className={`container ${darkMode ? "dark-mode" : "light-mode"}`}>
      <h1>
        <span id="animate">⭕ TicTacToe ❌</span>
      </h1>
      <div id="headingBottomBorder"></div>
      <RouterProvider router={gameRoutes} />

      <footer>
        <div className="dark-mode-toggle">
          <label>
            <span id="darkModeButton">🌝</span>
            <input
              id="dardModeCheckbox"
              type="checkbox"
              checked={darkMode}
              onChange={toggleDarkMode}
            />
          </label>
        </div>
      </footer>
      <p className="copyright">&copy; 2023 by The Tic Tac Titans 🎮</p>
    </div>
  );
};

export default HomePage;
