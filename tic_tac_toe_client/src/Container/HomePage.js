import { useState } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Gameboard from "../Components/Gameboard";
import StartGameForm from "../Components/StartGameForm";
import Navigation from "../Components/Navigation";
import GameSound from "../Components/GameSound";
import Leaderboard from "../Components/Leaderboard";

const HomePage = () => {
  const [game, setGame] = useState({ board: [] });
  const [darkMode, setDarkMode] = useState(false);
  const [leaderboard, setLeaderboard] = useState([]);
  const [showLeaderboard, setShowLeaderboard] = useState(false)

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

    const leaderboardResponse = await fetch('http://localhost:8080/players/leaderboard')
    const leaderboardData = await leaderboardResponse.json();
    setLeaderboard(leaderboardData.players);
  };

  const updateLeaderboard = async () => {
    const leaderboardResponse = await fetch('http://localhost:8080/players/leaderboard')
    const leaderboardData = await leaderboardResponse.json();
    setLeaderboard(leaderboardData.players);
  }

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
      element: (
                <>
                <Navigation />
                {/* <GameSound /> */}
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
      ],
    },
  ])

  // function to toggle dark mode
  const toggleDarkMode = () => {
    setDarkMode(!darkMode)
    let face = document.getElementById("darkModeButton")
    face.innerText === '🌝' ?  face.innerText = '🌚' : face.innerText = '🌝'

  }

  // function to show/hide leaderboard
  const toggleLeaderboard = () => {
    setShowLeaderboard(!showLeaderboard)
  }

  return (
    <div className={`container ${darkMode ? 'dark-mode' : 'light-mode'}`}>
        <h3>welcome to:</h3>
      <h1><span id="animate">TicTacToe🕹️</span></h1>
      <div id='headingBottomBorder'></div>

      <button onClick={toggleLeaderboard}>
        {showLeaderboard ? 'Hide Leaderboard' : 'Show Leaderboard'}
      </button>
      <button onClick={updateLeaderboard}>Update Leaderboard</button>
      {showLeaderboard && <Leaderboard players={leaderboard}/>}
      <RouterProvider router={gameRoutes} />
     
      
      <div className='dark-mode-toggle'>
                    <label>
                        <span id="darkModeButton">🌝</span>
                        <input id="dardModeCheckbox"
                            type='checkbox'
                            checked={darkMode}
                            onChange={toggleDarkMode}
                        />
                    </label>
                </div>
      
    </div>
  );
};

export default HomePage;
