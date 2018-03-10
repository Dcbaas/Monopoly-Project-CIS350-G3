package Model.GamePackage;

import Model.BoardPackage.OwnableSquare;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*****************************************************************
 * JUnit tests for the Bank Class
 *
 * @author Dylan Kernohan
 * @version 2/24/2018
 *****************************************************************/
public class BankTest {

    OwnableSquare ownableSquare = new OwnableSquare("GVSU", 3, 100, 200, 3);
    ArrayList<OwnableSquare> properties = new ArrayList<OwnableSquare>(){{add(ownableSquare);}};
    Bank bank = new Bank(properties);

    /*****************************************************************
     * Test the giveProperty method
     *****************************************************************/
    @Test
    public void givePropertyTest() {
        bank.giveProperty(bank.getPropertiesOwned().get(0));
        assertEquals(new ArrayList<OwnableSquare>(), bank.getPropertiesOwned());
    }

    /*****************************************************************
     * Test the receiveProperty method
     *****************************************************************/
    @Test
    public void receivePropertyTest() {
        bank.receiveProperty(ownableSquare);
        assertTrue(bank.getPropertiesOwned().get(0).getName().equals("GVSU"));
    }

    /*****************************************************************
     * Test the giveHouse method
     *****************************************************************/
    @Test
    public void giveHouseTest() {
        bank.setNumHouses(32);
        bank.giveHouse();
        assertEquals(31, bank.getNumHouses());
    }

    /*****************************************************************
     * Test the giveHotel method
     *****************************************************************/
    @Test
    public void giveHoteTest() {
        bank.setNumHotels(12);
        bank.giveHote();
        assertEquals(11, bank.getNumHotels());
    }

    /*****************************************************************
     * Test the receiveHouse method
     *****************************************************************/
    @Test
    public void receiveHouseTest() {
        bank.setNumHouses(31);
        bank.receiveHouse();
        assertEquals(32, bank.getNumHouses());
    }

    /*****************************************************************
     * Test the receiveHotel method
     *****************************************************************/
    @Test
    public void receiveHotelTest() {
        bank.setNumHotels(11);
        bank.receiveHotel();
        assertEquals(12, bank.getNumHotels());

    }

    /*****************************************************************
     * Test the getNumHouses method
     *****************************************************************/
    @Test
    public void getNumHousesTest() {
        bank.setNumHouses(32);
        assertEquals(32, bank.getNumHouses());
    }

    /*****************************************************************
     * Test the setNumHouses method
     *****************************************************************/
    @Test
    public void setNumHousesTest() {
        bank.setNumHouses(5);
        assertEquals(5, bank.getNumHouses());
    }

    /*****************************************************************
     * Test the getNumHotels method
     *****************************************************************/
    @Test
    public void getNumHotelsTest() {
        bank.setNumHotels(5);
        assertEquals(5, bank.getNumHotels());
    }

    /*****************************************************************
     * Test the setNumHotels method
     *****************************************************************/
    @Test
    public void setNumHotelsTest() {
        bank.setNumHotels(12);
        assertEquals(12, bank.getNumHotels());
    }

    /*****************************************************************
     * Test the getPropertiesOwned method
     *****************************************************************/
    @Test
    public void getPropertiesOwnedTest() {
        assertEquals(ownableSquare, bank.getPropertiesOwned().get(0));
    }

    /*****************************************************************
     * Test the setPropertiesOwned method
     *****************************************************************/
    @Test
    public void setPropertiesOwnedTest() {
        ArrayList<OwnableSquare> properties = new ArrayList<OwnableSquare>(){{add(ownableSquare);add(ownableSquare);}};
        bank.setPropertiesOwned(properties);
        assertEquals(ownableSquare, bank.getPropertiesOwned().get(0));
        assertEquals(ownableSquare, bank.getPropertiesOwned().get(1));
    }

    /*****************************************************************
     * Test the getPropertiesMortgaged method
     *****************************************************************/
    @Test
    public void getPropertiesMortgagedTest() {
        assertEquals(new ArrayList<OwnableSquare>(), bank.getPropertiesMortgaged());
    }

    /*****************************************************************
     * Test the setPropertiesMortgaged method
     *****************************************************************/
    @Test
    public void setPropertiesMortgagedTest() {
        ArrayList<OwnableSquare> properties = new ArrayList<OwnableSquare>(){{add(ownableSquare);add(ownableSquare);}};
        bank.setPropertiesMortgaged(properties);
        assertEquals(ownableSquare, bank.getPropertiesMortgaged().get(0));
        assertEquals(ownableSquare, bank.getPropertiesMortgaged().get(1));
    }
}