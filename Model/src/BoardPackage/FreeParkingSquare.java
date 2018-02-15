package BoardPackage;

/*********************************************************************
 * The FreeParkingSquare is a board square on the Monopoly game board
 * where when you land on it nothing happens. This class exist however
 * for the possiblity of adding functionality for this board square
 * later.
 */
public class FreeParkingSquare extends BoardSquare {

    /*****************************************************************
     * Constructor instantiates the variables and sets the name of the
     * FreeParkingSquare to what is input as a parameter.
     *
     * @param name String that will be the name of the BoardSquare.
     ****************************************************************/
    public FreeParkingSquare(String name) {
        super(name);
    }
}
