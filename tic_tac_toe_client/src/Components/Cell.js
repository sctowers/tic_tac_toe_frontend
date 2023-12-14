const Cell = ({ value, onClick, }) => {

    let valueName = Array.from(value)[0];
    return ( 
        <div className={valueName} onClick={onClick}>
            {value}
        </div>
    );
}

export default Cell;