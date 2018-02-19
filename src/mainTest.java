import Model.BoardPackage.Board;
import Model.CardPackage.Deck;


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

        Deck chance = new Deck(System.getenv("CHANCE"),true);
        chance.getDeck().forEach(card -> System.out.println(card.getCardDescription()));

        Deck comunitychest = new Deck(System.getenv("COMUNITYCHEST"),true);
        comunitychest.getDeck().forEach(card -> System.out.println(card.getCardDescription()));
    }
}
