package model.cardpackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * The Deck class is a data structure class which contains a deck of
 * cards.
 *
 * @author Santiago Quirgoa
 * @since 2/18/2018
 * @version 3/17/2018
 */
public class Deck {

  /**
   * An ArrayList list with all the cards.
   */
  private ArrayList<Card> deck;

  /**
   *  This constructor reads a text file and populates the deck with the
   *  specific set of cards.
   *
   * @param fileName the name of the File being used to create the
   *        deck.
   * @param cardType True for Chance, False for Community Chest.
   */
  public Deck(String fileName, boolean cardType) {
    //intializes the deck
    deck = new ArrayList<>();

    try (Stream<String> text = Files.lines(Paths.get(fileName))) {

      //Iterates thought every line in the stream
      text.forEach(line -> {
        //Defines and instantiates a Scanner to read through the each line
        Scanner scanner = new Scanner(line).useDelimiter("[,\r\n]+");

        deck.add(new Card(scanner.next(), cardType,
            new int[]{scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt()}));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the card on top of the deck.
   *
   * @return the Card drawn from the top of the deck.
   */
  public Card drawCard() {
    return deck.remove(0);
  }

  /**
   *  Adds the given card to back to the deck.
   *
   * @param card The card being returned to the bottom of the deck.
   * @return True if the card was returned; False otherwise.
   */
  public boolean returnCard(Card card) {
    return deck.add(card);
  }

  /**
   * Shuffles the ArrayList to a pseudo random state.
   */
  public void shuffleDeck() {
    Collections.shuffle(deck);
  }

  /**
   * Get the entire ArrayList that is the deck of cards.
   * @return deck The entire deck of cards.
   */
  public ArrayList<Card> getDeck() {
    return deck;
  }

  /**
   * Set the deck of cards to the input parameter.
   * @param deck The deck that this deck is being set to.
   */
  public void setDeck(ArrayList<Card> deck) {
    this.deck = deck;
  }
}
