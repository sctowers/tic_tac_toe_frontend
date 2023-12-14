import { useState, useEffect } from "react";

const Leaderboard = () => {
    const [leaderboard, setLeaderboard] = useState([]);

    const fetchLeaderboard = async () => {
    try {
        const leaderboardResponse = await fetch('http://localhost:8080/players/leaderboard');
        const leaderboardData = await leaderboardResponse.json();
        setLeaderboard(leaderboardData.players);
    } catch (error) {
        console.error('Error fetching leaderboard:', error);
    }
    };

    return (
    <div className="leaderboard">
        <h2 className="leaderboardTitle">Leaderboard
        <button onClick={fetchLeaderboard}>🔄</button>
        </h2>
        <table className="leaderboardTable">
        <thead>
            <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Points</th>
            </tr>
        </thead>
        <tbody>
            {leaderboard.map((player) => (
            <tr key={player.id}>
                <td>{player.rank}</td>
                <td>{player.playerName}</td>
                <td>{player.points}</td>
            </tr>
            ))}
        </tbody>
        </table>
    </div>
    );
};

export default Leaderboard;
