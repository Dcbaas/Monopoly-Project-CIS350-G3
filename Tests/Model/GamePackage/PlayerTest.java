package Model.GamePackage;

import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import Model.CardPackage.Card;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/*****************************************************************
 * JUnit tests for the Player Class
 *
 * @author Dylan Kernohan
 * @version 2/20/2018
 *****************************************************************/
public class PlayerTest {

    Player player = new Player("Dylan", "The Top Hat", 1500);

    /*****************************************************************
     * Test the getDisplayName method
     *****************************************************************/
    @Test
    public void getDisplayNameTest() {
        assertEquals("Dylan", player.getDisplayName());
    }

    /*****************************************************************
     * Test the getBankrupt method
     *****************************************************************/
    @Test
    public void getTokenTest() {
        assertEquals("The Top Hat", player.getToken());
    }

    /*****************************************************************
     * Test the isBankrupt method
     *****************************************************************/
    @Test
    public void isBankruptTest() {
        assertFalse(player.isBankrupt());
    }

    /*****************************************************************
     * Test the setBankrupt method
     *****************************************************************/
    @Test
    public void setBankruptTest() {
        player.setBankrupt(true);
        assertTrue(player.isBankrupt());
    }

    /*****************************************************************
     * Test the getWallet method
     *****************************************************************/
    @Test
    public void getWalletTest() {
        assertEquals(1500, player.getWallet());
    }

    /*****************************************************************
     * Test the setWallet method
     *****************************************************************/
    @Test
    public void setWalletTest() {
        player.setWallet(10);
        assertEquals(10, player.getWallet());
    }

    /*****************************************************************
     * Test the getPropertiesOwned method
     *****************************************************************/
    @Test
    public void getPropertiesOwnedTest() {
        ArrayList<OwnableSquare> tmp = new ArrayList<>();
        assertEquals(tmp, player.getPropertiesOwned());
    }

    /*****************************************************************
     * Test the setPropertiesOwned method
     *****************************************************************/
    @Test
    public void setPropertiesOwnedTest() {
        PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
                100, 200, 300, 400, 500,
                600, 2, 100, 500, 2);
        ArrayList<OwnableSquare> tmp = new ArrayList<>();
        tmp.add(propertySquare);
        player.setPropertiesOwned(tmp);
        assertEquals("GVSU", tmp.get(0).getName());
    }

    /*****************************************************************
     * Test the getCardsHeld method
     *****************************************************************/
    @Test
    public void getCardsHeldTest() {
        ArrayList<OwnableSquare> tmp = new ArrayList<>();
        assertEquals(tmp, player.getCardsHeld());
    }

    /*****************************************************************
     * Test the serCardsHeld method
     *****************************************************************/
    @Test
    public void setCardsHeldTest() {
        Card card = new Card("This is a card", true, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9} );
        ArrayList<Card> tmp = new ArrayList<>();
        tmp.add(card);
        player.setCardsHeld(tmp);
        assertEquals("This is a card", tmp.get(0).getCardDescription());
    }

    /*****************************************************************
     * Test the getPosition method
     *****************************************************************/
    @Test
    public void getPositionTest() {
        assertEquals(0, player.getPosition());
    }

    /*****************************************************************
     * Test the setPosition method
     *****************************************************************/
    @Test
    public void setPositionTest() {
        player.setPosition(39);
        assertEquals(39, player.getPosition());
    }

    /*****************************************************************
     * Test the getInJail method
     *****************************************************************/
    @Test
    public void getInJailTest() {
        assertEquals(-1, player.getInJail());
    }

    /*****************************************************************
     * Test the setInJail method
     *****************************************************************/
    @Test
    public void setInJailTest() {
        player.setInJail(2);
        assertEquals(2, player.getInJail());
    }

    /*****************************************************************
     * Test the getGroupsOwned method
     *****************************************************************/
    @Test
    public void getGroupsOwnedTest() {
        ArrayList<Integer> tmp = new ArrayList<>();
        assertEquals(tmp, player.getGroupsOwned());
    }

    /*****************************************************************
     * Test the setGroupsOwned method
     *****************************************************************/
    @Test
    public void setGroupsOwnedTest() {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        player.setGroupsOwned(tmp);
        assertEquals(1, player.getGroupsOwned().size());
    }

    /*****************************************************************
     * Test the pay method
     *****************************************************************/
    @Test
    public void payTest() {
        // Test when player does not have enough money. Should pay all they have
        player.setWallet(10);
        assertEquals("Test when player does not have enough money.",10, player.pay(50));
        assertEquals("Player did not have enough. Payed all they had", 0, player.getWallet());

        // Test when the player has just enough.
        player.setWallet(50);
        assertEquals("Test when the player has just enough.", 50, player.pay(50));
        assertEquals("Player had just enough. Wallet should be 0", 0, player.getWallet());

        // Test when the player has more than enough
        player.setWallet(1000);
        assertEquals("Test when the player has more than enough",50, player.pay(50));
        assertEquals("Player had enough. Wallet still have money", 950, player.getWallet());

    }

    /*****************************************************************
     * Test the giveProperty method
     *****************************************************************/
    @Test
    public void givePropertyTest() {
        PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
                100, 200, 300, 400, 500,
                600, 2, 100, 500, 2);        ArrayList<OwnableSquare> tmp = new ArrayList<>();
        tmp.add(propertySquare);
        player.setPropertiesOwned(tmp);
        player.giveProperty(propertySquare);
        assertTrue("PropertiesOwned should be empty", player.getPropertiesOwned().size() == 0);
    }

    /*****************************************************************
     * Test the receiveProperty method
     *****************************************************************/
    @Test
    public void recievePropertyTest() {
        PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
                100, 200, 300, 400, 500,
                600, 2, 100, 500, 2);        player.setPropertiesOwned(new ArrayList<>());
        player.recieveProperty(propertySquare);
        assertTrue("PropertiesOwned should have one property", player.getPropertiesOwned().size() == 1);

    }

    /*****************************************************************
     * Test the buildHouse method
     *****************************************************************/
    @Test
    public void buildHouseTest() {
        PropertySquare square = new PropertySquare("GVSU", 20, 5000, 100, 100,
                200, 300, 400, 500, 1000, 0,
                100, 200, 3);
        assertEquals("square should have no houses", 0, square.getNumHouses());
        player.setWallet(100);
        player.buildHouse(square);
        assertEquals("square should have 1 house", 1, square.getNumHouses());
        assertEquals("player should have no money", 0, player.getWallet());

    }

    /*****************************************************************
     * Test the buildHotel method
     *****************************************************************/
    @Test
    public void buildHotelTest() {
        PropertySquare square = new PropertySquare("GVSU", 20, 5000, 100, 100,
                200, 300, 400, 500, 1000, 0,
                100, 200, 3);
        assertFalse("square should have no hotel", square.isHasHotel());
        player.setWallet(200);
        player.buildHotel(square);
        assertTrue("square should have a hotel", square.isHasHotel());
        assertEquals("player should have no money", 0, player.getWallet());
    }

    /*****************************************************************
     * Test the sellHouse method
     *****************************************************************/
    @Test
    public void sellHouseTest() {
        PropertySquare square = new PropertySquare("GVSU", 20, 5000, 100, 100,
                200, 300, 400, 500, 1000, 1,
                100, 200, 3);
        assertEquals("square should have 1 houses", 1, square.getNumHouses());
        player.setWallet(0);
        player.sellHouse(square);
        assertEquals("square should have 0 house", 0, square.getNumHouses());
        assertEquals("player should have 50 money", 50, player.getWallet());
    }

    /*****************************************************************
     * Test the sellHotel method
     *****************************************************************/
    @Test
    public void sellHotelTest() {
        PropertySquare square = new PropertySquare("GVSU", 20, 5000, 100, 100,
                200, 300, 400, 500, 1000, 0,
                100, 200, 3);
        square.setHasHotel(true);
        assertTrue("square should have hotel", square.isHasHotel());
        player.setWallet(0);
        player.sellHotel(square);
        assertFalse("square should have no hotel", square.isHasHotel());
        assertEquals("player should have 100 money", 100, player.getWallet());
    }

    /*****************************************************************
     * Test the addGroupOwned method
     *****************************************************************/
    @Test
    public void addGroupOwnedTest() {
        player.addGroupOwned(1);
        assertTrue(player.getGroupsOwned().contains(1));
    }

    /*****************************************************************
     * Test the removeGroupOwned method
     *****************************************************************/
    @Test
    public void removeGroupOwnedTest() {
        player.addGroupOwned(1);
        player.removeGroupOwned(1);
        assertFalse(player.getGroupsOwned().contains(1));
    }

    /*****************************************************************
     * Test the receiveCard method
     *****************************************************************/
    @Test
    public void recieveCardTest() {
        //TODO: This method is not written in Player class
    }

    /*****************************************************************
     * Test the receiveMoney method
     *****************************************************************/
    @Test
    public void receiveMoneyTest() {
        //TODO: This method is not written in Player class

    }
}