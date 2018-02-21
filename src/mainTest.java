import Model.BoardPackage.Board;
import Model.BoardPackage.BoardSquare;
import Model.CardPackage.Deck;
import Model.GamePackage.Player;

import java.util.ArrayList;
import java.util.List;


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
//        board.getSquaresList().forEach(property -> System.out.println(property.getName()));

        Deck chance = new Deck(System.getenv("CHANCE"),true);
//        chance.getDeck().forEach(card -> System.out.println(card.getCardDescription()));

        Deck comunitychest = new Deck(System.getenv("COMUNITYCHEST"),true);
//        comunitychest.getDeck().forEach(card -> System.out.println(card.getCardDescription()));




        Player currentPlayer = new Player("Alejo", "car", 1500 );

        currentPlayer.setPosition(23);
        int midPoint = board.getSquaresList().size() / 2;
        List<BoardSquare> boardSquares = board.getSquaresList();

        int negativeMoves = 1, positiveMoves = 1;
        int negativePointer = currentPlayer.getPosition() , positivePointer = currentPlayer.getPosition();
        BoardSquare closestSquare = new BoardSquare("NULL", 999) {
        };
        while (negativeMoves <= midPoint && positiveMoves <= midPoint){
            negativePointer = (negativePointer - 1 == 0)? board.getSquaresList().size() - 1: negativePointer;
            positivePointer = (positivePointer + 1 == board.getSquaresList().size() - 1)? 0: positivePointer;

            System.out.println("Negative pointer at: " + boardSquares.get(negativePointer).getPOSITION() + " " + boardSquares.get(negativePointer).getName());
            System.out.println("Positive pointer ar: " + boardSquares.get(positivePointer).getPOSITION() + " " + boardSquares.get(positivePointer).getName());
            if(boardSquares.get(negativePointer).getType() == 1){
                closestSquare = boardSquares.get(negativePointer);
                break;
            }
            if(boardSquares.get(positivePointer).getType() == 1){
                closestSquare = boardSquares.get(positivePointer);
                break;
            }
            positivePointer ++;
            negativePointer --;
        }

            System.out.println(closestSquare.getName());
//
        }


}
