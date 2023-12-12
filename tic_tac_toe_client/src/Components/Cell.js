const Cell = ({ value, onClick, }) => {
    return ( 
        <div  onClick={onClick}>
            {value}
        </div>
    );
}

export default Cell;