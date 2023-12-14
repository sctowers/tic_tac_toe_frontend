const Cell = ({ value, onClick, player }) => {

    let valueName = Array.from(value)[0];

    // Emmanuel Face
    // if (player === "Emmanuel") {
    //     valueName = "A"
    // }

    return ( 
        <div className={valueName} onClick={onClick}>
            {value}
        </div>
    );
}

export default Cell;