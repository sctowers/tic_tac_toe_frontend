import Confetti from 'react-confetti';
import { useState, useEffect } from 'react';
const GameStatus = ({game}) => {
    const [confetti, setConfetti] = useState(false);

    useEffect(() => {
      
        if (game.result === "WIN") {
          setConfetti(true);
      
       
        } else setConfetti(false);
    }, [game.result]);
      
    return ( 
        <div>
        <p id="gameResult">Game Result: {game.result}</p>
        {confetti && <Confetti /> }
        </div>
     );
}
 
export default GameStatus;