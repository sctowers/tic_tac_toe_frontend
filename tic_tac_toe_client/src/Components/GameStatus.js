import Confetti from "react-confetti";
import { useWindowSize } from "react-use";
import { useState, useEffect } from "react";
import Sound from "react-sound";
import Win from "../Assets/win2.wav";
import Lose from "../Assets/Win.wav";
const GameStatus = ({ game }) => {
  const [confetti, setConfetti] = useState(false);
  const [playState, setPlayState] = useState(Sound.status.STOPPED)

  const { width, height } = useWindowSize();
  useEffect(() => {
    if (game.result === "WIN") {
      setConfetti(true);
      setPlayState(Sound.status.PLAYING)
    } else {
      setConfetti(false)
      setPlayState(Sound.status.STOPPED) 
    };
  }, [game.result]);

  const getResultColour = () => {
    if (game.result === "WIN") {
      return { color: "green" };
    } else if (game.result === "LOSS") {
      return { color: "red" };
    } else {
      return { color: "#385170" };
    }
  };

  return (
    <div>
      <Sound
        url={Win}
        playStatus={playState}
        playFromPosition={0}
        // loop={true}
        volume={50}
      />
  
      
      <p id="gameResult" style={getResultColour()}>
        Game Result: {game.result}
      </p>
      {confetti && <Confetti width={width} height={height} />}
    </div>
  );
};

export default GameStatus;
