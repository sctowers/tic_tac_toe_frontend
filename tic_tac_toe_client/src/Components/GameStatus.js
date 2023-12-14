import Confetti from 'react-confetti';
import { useWindowSize } from 'react-use';
import { useState, useEffect } from 'react';
const GameStatus = ({game}) => {
    const [confetti, setConfetti] = useState(false);

    const{width , height}= useWindowSize();
    useEffect(() => {
        if (game.result === "WIN") {
          setConfetti(true);
        } else setConfetti(false);
    }, [game.result]);

    const getResultColour = () => {
      if (game.result === 'WIN') {
        return {color: 'green'}
      } else if (game.result === 'LOSS') {
        return {color: 'red'}
      } else {
        return {color: '#385170'}
      }
    }

    return ( 
        <div>
        <p id="gameResult" style={getResultColour()}>
          Game Result: {game.result}</p>
        {confetti && <Confetti width={width} height={height} /> }
        </div>
    );
}

export default GameStatus;