import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link, Outlet } from "react-router-dom";


const StartGameForm = ({startGame}) => {

    const navigate = useNavigate();

    //FORM handler
    const handleFormSubmit = (event) => {
        event.preventDefault();
        console.log(stateDifficulty);
        console.log(stateId);
        startGame(stateDifficulty,stateId)
        console.log(startGame(stateDifficulty,stateId));
        // setStateDifficulty(null);
        // setStateId(null);
        navigate("/game");
        
    }

    //STATE difficulty handler
    const handleDifficultyChange = (event) => {
        let value = event.target.value
        setStateDifficulty(value);
    }
     //STATE Id handler
     const handleIdChange = (event) => {
        let value = event.target.value
        setStateId(value);
    }

    //state for difficulty
    const [stateDifficulty, setStateDifficulty] = useState(null);
    //state for id
    const [stateId, setStateId] = useState(null);


    return ( 
       <>
        <Outlet />

        {/* // Start Game Form */}
        <form id="game-form" onSubmit={handleFormSubmit}>
            <h3>Start New Game</h3>

            {/* Select Difficulty */}
            <label htmlFor="difficulty">Difficulty: </label>
            <select 
                id="difficulty" 
                name="difficulty"
                // defaultValue=".."
                onChange={handleDifficultyChange}
                value={stateDifficulty}
                // onChange={handleChange}
            >
                 <option value="">--</option>
                <option value="EASY">Easy</option>
                <option value="HARD">Hard</option>
            </select>

              {/* Select Id (Avatar) */}
              <label htmlFor="Id">Pick your player: </label>
            <select 
                id="Id" 
                name="avatar"
                // defaultValue="choose"
                value={stateId}
                onChange={handleIdChange}
                // onChange={handleChange}
            >
                <option value="">--</option>
                <option value="1">Zsolt</option>
                <option value="2">Naught Ninja</option>
                <option value="3">Grid Gladiator</option>
                <option value="4">Square Sensei</option>
                <option value="5">Tic-Tac-Titan</option>

            </select>

            <input type="submit" value="Start Game"/>        
        </form>
        </>
     );
}
 
export default StartGameForm;