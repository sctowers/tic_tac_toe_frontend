import { useState } from "react";
import { useNavigate } from "react-router-dom";


const StartGameForm = ({startGame}) => {

    const navigate = useNavigate();

    //FORM handler
    const handleFormSubmit = (event) => {
        event.preventDefault();
        console.log(stateDifficulty);
        console.log(stateId);
        startGame(stateDifficulty,stateId)
        console.log(startGame(stateDifficulty,stateId));
        navigate("/gameboard");
        
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
        {/* // Start Game Form */}
        <form id="game-form" onSubmit={handleFormSubmit}>
            <h3>Start New Game</h3>

            {/* Select Difficulty */}
            <label id="difficulty-label" htmlFor="difficulty">Difficulty: </label>
            <select 
                id="difficulty" 
                name="difficulty"
                defaultValue="Pick-your-difficulty"
                onChange={handleDifficultyChange}
                value={stateDifficulty}
                // onChange={handleChange}
            >
                <option disabled value="Pick-your-difficulty">--</option>
                <option value="EASY">Easy</option>
                <option value="HARD">Hard</option>
            </select>

              {/* Select Id (Avatar) */}
            <label id="player-label"htmlFor="Id">Pick your player: </label>
            <select 
                id="player" 
                name="avatar"
                defaultValue="pick-player"
                value={stateId}
                onChange={handleIdChange}
                // onChange={handleChange}
            >
                <option disabled value="pick-player" >--</option>
                <option value="1">Zsolt</option>
                <option value="2">Saima</option>
                <option value="3">Emmanuel</option>
                <option value="4">Gisele</option>
                <option value="5">Kacper</option>

            </select>

            <input id ="submit-form"type="submit" value="Start Game"/>        
        </form>
        </>
    );
}

export default StartGameForm;