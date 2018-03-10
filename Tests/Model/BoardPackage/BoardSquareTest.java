package Model.BoardPackage;

import Model.GamePackage.Player;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

/*****************************************************************
 * JUnit tests for the BoardSquare Class
 *
 * @author Dylan Kernohan
 * @version 2/24/2018
 *****************************************************************/
public class BoardSquareTest {

    OwnableSquare ownableSquare = new OwnableSquare("GVSU", 3, 100, 200, 5);

    /*****************************************************************
     * Test the getName method
     *****************************************************************/
    @Test
    public void getNameTest() {
        assertTrue(ownableSquare.getName().equals("GVSU"));
    }

    /*****************************************************************
     * Test the setName method
     *****************************************************************/
    @Test
    public void setNameTest() {
        ownableSquare.setName("Allendale");
        assertTrue(ownableSquare.getName().equals("Allendale"));

    }

    /*****************************************************************
     * Test the getOccupiedBy method
     *****************************************************************/
    @Test
    public void getOccupiedByTest() {
        ArrayList<Player> players= new ArrayList<Player>();
        assertEquals(players, ownableSquare.getOccupiedBy());
    }

    /*****************************************************************
     * Test the setOccupiedBY method
     *****************************************************************/
    @Test
    public void setOccupiedByTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        ArrayList<Player> players = new ArrayList<Player>(){{add(player);}};
        ownableSquare.setOccupiedBy(players);
        assertEquals(player, ownableSquare.getOccupiedBy().get(0));
    }

    /*****************************************************************
     * Test the addPlayer method
     *****************************************************************/
    @Test
    public void addPlayerTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        ownableSquare.addPlayer(player);
        assertEquals(player, ownableSquare.getOccupiedBy().get(0));

    }

    /*****************************************************************
     * Test the removePlayer method
     *****************************************************************/
    @Test
    public void removePlayerTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        ArrayList<Player> players = new ArrayList<Player>(){{add(player);}};
        ownableSquare.setOccupiedBy(players);
        ownableSquare.removePlayer(player);
        assertEquals(new ArrayList<Player>(), ownableSquare.getOccupiedBy());
    }

    /*****************************************************************
     * Test the getPOSITION method
     *****************************************************************/
    @Test
    public void getPOSITIONTest() {
        assertEquals(3, ownableSquare.getPOSITION());
    }

    /*****************************************************************
     * Test the getType method
     *****************************************************************/
    @Test
    public void getTypeTest() {
        assertEquals(2, ownableSquare.getType());
    }

    /*****************************************************************
     * Test the setType method
     *****************************************************************/
    @Test
    public void setTypeTest() {
        ownableSquare.setType(3);
        assertEquals(3, ownableSquare.getType());
    }
}