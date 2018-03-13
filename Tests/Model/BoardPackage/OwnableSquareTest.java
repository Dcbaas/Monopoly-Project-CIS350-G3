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

    PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
            100, 200, 300, 400, 500,
            600, 2, 100, 500, 2);

    /*****************************************************************
     * Test the getPRICE method
     *****************************************************************/
    @Test
    public void getPRICE() {
        assertEquals(200, propertySquare.getPRICE());
    }

    /*****************************************************************
     * Test the getMORTGAGE_VAL method
     *****************************************************************/
    @Test
    public void getMORTGAGE_VAL() {
        assertEquals(400, propertySquare.getMORTGAGE_VAL());
    }

    /*****************************************************************
     * Test the isMortgaged method
     *****************************************************************/
    @Test
    public void isMortgaged() {
        assertFalse(propertySquare.isMortgaged);
    }

    /*****************************************************************
     * Test the setMortgaged method
     *****************************************************************/
    @Test
    public void setMortgaged() {
        propertySquare.setMortgaged(true);
        assertTrue(propertySquare.isMortgaged);
    }

    /*****************************************************************
     * Test the getOwner method
     *****************************************************************/
    @Test
    public void getOwner() {
        assertNull(propertySquare.getOwner());
    }

    /*****************************************************************
     * Test the setOwner1 method
     *****************************************************************/
    @Test
    public void setOwner() {
        Player player = new Player("Dylan", "The Top Hat", 1500);
        propertySquare.setOwner(player);
        assertTrue(propertySquare.getOwner().getDisplayName().equals("Dylan"));
    }

    /*****************************************************************
     * Test the getGROUP_NUMBER method
     *****************************************************************/
    @Test
    public void getGROUP_NUMBER() {
        assertEquals(2, propertySquare.getGROUP_NUMBER());
    }
}