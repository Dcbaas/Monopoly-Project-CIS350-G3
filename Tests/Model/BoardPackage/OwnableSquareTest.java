package Model.BoardPackage;

import Model.GamePackage.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/*****************************************************************
 * JUnit tests for the Die Class
 *
 * @author Dylan Kernohan
 * @version 2/21/2018
 *****************************************************************/
public class OwnableSquareTest {

    OwnableSquare ownableSquare = new OwnableSquare("OwnMe", 2, 100, 400, 2);

    /*****************************************************************
     * Test the getPRICE method
     *****************************************************************/
    @Test
    public void getPRICE() {
        assertEquals(100, ownableSquare.getPRICE());
    }

    /*****************************************************************
     * Test the getMORTGAGE_VAL method
     *****************************************************************/
    @Test
    public void getMORTGAGE_VAL() {
        assertEquals(400, ownableSquare.getMORTGAGE_VAL());
    }

    /*****************************************************************
     * Test the isMortgaged method
     *****************************************************************/
    @Test
    public void isMortgaged() {
        assertFalse(ownableSquare.isMortgaged);
    }

    /*****************************************************************
     * Test the setMortgaged method
     *****************************************************************/
    @Test
    public void setMortgaged() {
        ownableSquare.setMortgaged(true);
        assertTrue(ownableSquare.isMortgaged);
    }

    /*****************************************************************
     * Test the getOwner method
     *****************************************************************/
    @Test
    public void getOwner() {
        assertNull(ownableSquare.getOwner());
    }

    /*****************************************************************
     * Test the setOwner1 method
     *****************************************************************/
    @Test
    public void setOwner() {
        Player player = new Player("Dylan", "The Top Hat", 1500);
        ownableSquare.setOwner(player);
        assertTrue(ownableSquare.getOwner().getDisplayName().equals("Dylan"));
    }

    /*****************************************************************
     * Test the getGROUP_NUMBER method
     *****************************************************************/
    @Test
    public void getGROUP_NUMBER() {
        assertEquals(2, ownableSquare.getGROUP_NUMBER());
    }
}