package Model.BoardPackage;

import Model.GamePackage.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*****************************************************************
 * JUnit tests for the Die Class
 *
 * @author Dylan Kernohan
 * @version 2/24/2018
 *****************************************************************/
public class BoardTest {

    Board board = new Board("res/board.txt");

    /*****************************************************************
     * Test the getLocationType method
     *****************************************************************/
    @Test
    public void getLocationTypeTest() {
        assertEquals(0, board.getLocationType(1));
    }

    /*****************************************************************
     * Test the getOwnableSquare method
     *****************************************************************/
    @Test
    public void getOwnableSquareTest() {
        assertTrue(board.getOwnableSquare(1).getName().equals("Mediterranean Ave"));
    }

    /*****************************************************************
     * Test the getPlayerPosition method
     *****************************************************************/
    @Test
    public void setPlayerPositionTest() {
        Player player = new Player("Dylan", "The Top Hat", 1500);
        board.setPlayerPosition(player, 10);
        assertTrue(board.getSquaresList().get(10).getOccupiedBy().get(0).getDisplayName().equals("Dylan"));
    }

    /*****************************************************************
     * Test the getOwnableSquares method
     *****************************************************************/
    @Test
    public void getOwnableSquaresTest() {
        Board board1 = new Board("res/testBoard.txt");
        GoSquare go = new GoSquare("GO", 200);
        PropertySquare kent = new PropertySquare("Old Kent Road", 1,60,30,2,
                10,30,90,160,250,0,
                50,50,1);
        RailRoadSquare kings = new RailRoadSquare("Kings Cross Station", 5,200,25,
                50,100,200,100,10);
        FreeParkingSquare free = new FreeParkingSquare("FREE PARKING");

        ArrayList<BoardSquare> squares = new ArrayList<>();
        squares.add(kent);
        squares.add(kings);

        assertTrue(board1.getOwnableSquares().get(0).getName().equals(squares.get(0).getName()));
        assertTrue(board1.getOwnableSquares().get(1).getName().equals(squares.get(1).getName()));

    }

    /*****************************************************************
     * Test the getSquaresList method
     *****************************************************************/
    @Test
    public void getSquaresListTest() {
        Board board1 = new Board("res/testBoard.txt");
        GoSquare go = new GoSquare("GO", 200);
        PropertySquare kent = new PropertySquare("Old Kent Road", 1,60,30,2,
                10,30,90,160,250,0,
                50,50,1);
        RailRoadSquare kings = new RailRoadSquare("Kings Cross Station", 5,200,25,
                50,100,200,100,10);
        FreeParkingSquare free = new FreeParkingSquare("FREE PARKING");

        ArrayList<BoardSquare> squares = new ArrayList<>();
        squares.add(go);
        squares.add(kent);
        squares.add(kings);
        squares.add(free);

        assertTrue(board1.getSquaresList().get(1).getName().equals(squares.get(1).getName()));
    }

    /*****************************************************************
     * Test the sendTojail method
     *****************************************************************/
    @Test
    public void sendToJailTest() {
        Player player = new Player("Dylan", "The Top Hat", 1500);
        board.sendToJail(player);
        assertTrue(board.getSquaresList().get(10).getOccupiedBy().get(0).getDisplayName().equals("Dylan"));
    }

    /*****************************************************************
     * Test the getGroup method
     *****************************************************************/
    @Test
    public void getGroupTest() {
        assertTrue(board.getGroup(1).get(0).getName().equals("Mediterranean Ave"));
        assertTrue(board.getGroup(1).get(1).getName().equals("Baltic Ave"));

    }
}