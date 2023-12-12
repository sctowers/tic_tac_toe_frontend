import React, { useState } from 'react';
import Sound from 'react-sound';
import Music from '../Assets/music.mp3'


// Function Example from:https://dev.to/daveguz97/adding-sound-to-a-react-project-51m3 //
const GameSound = (
    handleSongLoading,
    handleSongPlaying,
    handleSongFinishedPlaying
    ) => {
        const[isPlaying, setIsPlaying] = useState(false);
        const[volume, setVolume] = useState(50);

        function mute(){
            if (volume === 50) {
                setVolume(0);
            }
            else {
                setVolume(50);
            }
           
        }
        // const[playPosition, setPlayPosition] = useState();
        let position = 0;
    return (
        <div>
            
            <button onClick={() => setIsPlaying(!isPlaying)}>{!isPlaying ? 'Play' : 'Stop'}
            </button>
           <button onClick={mute}>
            Mute
            </button>
           

            <Sound
                url={Music}
                playStatus={isPlaying ? Sound.status.PLAYING : Sound.status.STOPPED}
                playFromPosition={position}
              
                loop={true}
                volume={volume}
            />
        </div>

    );
}

export default GameSound;