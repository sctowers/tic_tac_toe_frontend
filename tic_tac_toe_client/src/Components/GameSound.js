import React, { useState } from "react";
import Sound from "react-sound";
import Music from "../Assets/music.mp3";

const GameSound = () => {
  const [volume, setVolume] = useState(0);
  const [playState, setPlayState] = useState(Sound.status.STOPPED)

  // const muteButton = document.querySelector("muteButton")
  // muteButton.addEventListener('click', () => {
  //   const playback = ();
  // })

  const mute = () => {
    setVolume(prevVolume => (prevVolume === 0 ? 50 : 0));
    setPlayState(Sound.status.PAUSED) || setPlayState(Sound.status.STOPPED) ? setPlayState(Sound.status.PAUSED) : setPlayState(Sound.status.PLAYING);
  };

  

  return (
    <div>
      <button id="muteButton" onClick={mute}>
        {(volume === 0) ? '🔇' : '🔈'}
      </button>

      
      <Sound
        url={Music}
        playStatus={playState}
        playFromPosition={0}
        loop={true}
        volume={volume}
      />


    </div>
  );
};

export default GameSound;
