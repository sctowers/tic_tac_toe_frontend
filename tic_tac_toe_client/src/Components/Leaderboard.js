const Leaderboard = ({players}) => {

    return (
        <div className="leaderboard">
            <h2>Leaderboard</h2>
        <table>
        <thead>
            <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Points</th>
            </tr>
        </thead>
        <tbody>
            {players.map((player) => (
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
}

export default Leaderboard;