import Model.BoardPackage.Board;


/**
 * the only purpouse of this class is to emuralte basic engine functionality.
 *
 * @author Santiago Quiroga
 * @version 2/18/2018
 */
public class mainTest {

    /*********************************************************************************************
     * Main method
     * @param args
     ********************************************************************************************/
    public static void main(String[] args) {
        Board board = new Board(System.getenv("FILENAME"));
        board.getSquaresList().forEach(property -> System.out.println(property.getName()));

    }
}