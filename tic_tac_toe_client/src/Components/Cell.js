const Cell = ({ value, onClick, }) => {
    return ( 
        <div className={value} onClick={onClick}>
            {value}
        </div>
    );
}

export default Cell;