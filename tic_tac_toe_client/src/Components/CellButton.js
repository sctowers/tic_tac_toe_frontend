const CellButton = ({ cell }) => {

  const handleClick = () => {
    console.log(`Cell ${cell.cellNumber} has been clicked`)
  }

    return (

        <button onClick={handleClick}>{cell.value}</button>
      );
}

export default CellButton;