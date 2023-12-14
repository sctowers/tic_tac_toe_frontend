
import { Link, Outlet } from "react-router-dom";
const Navigation = () => {
    return (  

        <>
        <div className="navigationContainer">
            <Link id="linkToForm" to = "/game">Click here to start a new game </Link>
            <Link id="linkToLeaderboard" to="/players/leaderboard">Click here to see the Leaderboard</Link>
        </div>
        <Outlet />
        </>

        
    );
}

export default Navigation;