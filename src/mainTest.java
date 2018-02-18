import Model.BoardPackage.Board;


/**
 * Created by unclear on 2/16/18.
 */
public class mainTest {
    public static void main(String[] args) {
        Board board = new Board(System.getenv("FILENAME"));
        board.getSquaresList().forEach(property -> System.out.println(property.getName()));
    }
}
