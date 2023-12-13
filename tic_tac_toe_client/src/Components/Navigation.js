import GameSound from "./GameSound";
import { Link, Outlet } from "react-router-dom";
const Navigation = () => {
    return (  

        <>
        <Link id="linkToForm" to = "/game">Click here to start a new game </Link>
        <GameSound />
        <Outlet />
        </>

        
    );
}
 
export default Navigation;