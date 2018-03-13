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

    PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
            100, 200, 300, 400, 500,
            600, 2, 100, 500, 2);

    /*****************************************************************
     * Test the getName method
     *****************************************************************/
    @Test
    public void getNameTest() {
        assertTrue(propertySquare.getName().equals("GVSU"));
    }

    /*****************************************************************
     * Test the setName method
     *****************************************************************/
    @Test
    public void setNameTest() {
        propertySquare.setName("Allendale");
        assertTrue(propertySquare.getName().equals("Allendale"));

    }

    /*****************************************************************
     * Test the getOccupiedBy method
     *****************************************************************/
    @Test
    public void getOccupiedByTest() {
        ArrayList<Player> players= new ArrayList<Player>();
        assertEquals(players, propertySquare.getOccupiedBy());
    }

    /*****************************************************************
     * Test the setOccupiedBY method
     *****************************************************************/
    @Test
    public void setOccupiedByTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        ArrayList<Player> players = new ArrayList<Player>(){{add(player);}};
        propertySquare.setOccupiedBy(players);
        assertEquals(player, propertySquare.getOccupiedBy().get(0));
    }

    /*****************************************************************
     * Test the addPlayer method
     *****************************************************************/
    @Test
    public void addPlayerTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        propertySquare.addPlayer(player);
        assertEquals(player, propertySquare.getOccupiedBy().get(0));

    }

    /*****************************************************************
     * Test the removePlayer method
     *****************************************************************/
    @Test
    public void removePlayerTest() {
        Player player = new Player("Dylan", "The Top hat", 1500);
        ArrayList<Player> players = new ArrayList<Player>(){{add(player);}};
        propertySquare.setOccupiedBy(players);
        propertySquare.removePlayer(player);
        assertEquals(new ArrayList<Player>(), propertySquare.getOccupiedBy());
    }

    /*****************************************************************
     * Test the getPOSITION method
     *****************************************************************/
    @Test
    public void getPOSITIONTest() {
        assertEquals(5, propertySquare.getPOSITION());
    }

    /*****************************************************************
     * Test the getType method
     *****************************************************************/
    @Test
    public void getTypeTest() {
        assertEquals(0, propertySquare.getType());
    }

    /*****************************************************************
     * Test the setType method
     *****************************************************************/
    @Test
    public void setTypeTest() {
        propertySquare.setType(3);
        assertEquals(3, propertySquare.getType());
    }
}