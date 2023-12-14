
import { Link, Outlet } from "react-router-dom";
const Navigation = () => {
    return (  

        <>
        <div className="navigationContainer">
            <Link id="linkToForm" to = "/game">🕹️ START NEW GAME 🕹️</Link>
            <Link id="linkToLeaderboard" to="/players/leaderboard">🏆 LEADERBOARD 🏆</Link>
        </div>
        <Outlet />
        </>

        
    );
}

export default Navigation;