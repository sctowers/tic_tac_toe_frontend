
import { Link, Outlet } from "react-router-dom";
const Navigation = () => {
    return (  

        <>
        <div className="navigationContainer">
        <Link id="linkToForm" to = "/game">Click here to start a new game </Link>
        </div>
        <Outlet />
        </>

        
    );
}
 
export default Navigation;