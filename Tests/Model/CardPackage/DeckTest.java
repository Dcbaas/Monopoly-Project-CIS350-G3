package Model.CardPackage;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;


/************************************************************************************
 * This class test the Deck class
 *
 * @author Santiago Quiroga
 * @version 2/28/2018
 ***********************************************************************************/
public class DeckTest {
    Deck chanceDeck, communityDeck;

    /**********************************************************************************
     * This restarts the decks before every test
     ***********************************************************************************/
    @Before
    void setUp(){
        chanceDeck = new Deck(System.getenv("CHANCE_FILE"),true);
        communityDeck = new Deck(System.getenv("COMMUNITY_FILE"),false);
    }

    /**********************************************************************************
     * Test the functionality of the Draw card method
     ***********************************************************************************/
    @Test
    void drawCard() {
        Card drawnCard;
        int beforeSize = chanceDeck.getDeck().size();

        drawnCard = chanceDeck.drawCard();

        //Checks if the size of the deck decreases when a card is drawn.
        assertNotEquals(chanceDeck.getDeck().size(),beforeSize, 0);

        //Checks if the card drawn is no longer in the deck.
        assertFalse("the dranw card was not removed from the Deck",chanceDeck.getDeck().stream().anyMatch(card -> card == drawnCard));
    }


    /**********************************************************************************
     * Tests the fucntionality of the returnCard method
     ***********************************************************************************/
    @Test
    void returnCard() {
        Card drawnCard = communityDeck.drawCard();
        int beforeSize = communityDeck.getDeck().size();

        chanceDeck.returnCard(drawnCard);

        //Checks if the size of the deck decreases when a card is drawn.
        assertNotEquals(chanceDeck.getDeck().size(),beforeSize, 0);

        //Checks that the drawn card was added back to the deck.
        assertTrue("the drawn card was not removed from the Deck",chanceDeck.getDeck().stream().anyMatch(card -> card == drawnCard));
    }


    /**********************************************************************************
     * Test the functionality of the sheufle deck method
     ***********************************************************************************/
    @Test
    void shufleDeck() {
        Random random = new Random();
        int randomCardIndex = random.nextInt(chanceDeck.getDeck().size());
        Card randomCard;

        //Checks if the Deck is shuffled
        randomCard = chanceDeck.getDeck().get(randomCardIndex);
        chanceDeck.shufleDeck();
        assertFalse( "The deck did not get shuffled",chanceDeck.getDeck().get(randomCardIndex).equals(randomCard));
    }

    /**********************************************************************************
     * Test the functionality of the getDeck method
     ***********************************************************************************/
    @Test
    void getDeck() {
        assertNotNull(communityDeck.getDeck());
        assertNotNull(chanceDeck.getDeck());
    }

    /**********************************************************************************
     * test the fucntionality of the set Deck method
     ***********************************************************************************/
    @Test
    void setDeck() {
        ArrayList<Card> testDeck = new ArrayList<>();

        for (int index = 0; index < 10 ; index ++ ){
            testDeck.add(new Card("test" + index, true,new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1}));
        }

        chanceDeck.setDeck(testDeck);

        assertEquals(chanceDeck.getDeck(),testDeck);
    }
}