import { Link, Outlet } from "react-router-dom";
const Navigation = () => {
    return (  

        <>
        <Link id="linkToForm" to = "/game">Start new game </Link>
        <Outlet />
        </>

        
    );
}
 
export default Navigation;