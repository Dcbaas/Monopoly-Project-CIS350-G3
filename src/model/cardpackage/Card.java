package model.cardpackage;

/**
 * The Card class is a template for creating each Chance and Community
 * Chest card.
 *
 * @author Dustin Ecker
 * @version 2/17/2018
 */
public class Card {

  /**
   * String to hold card description.
   */
  private final String CARD_DESCRIPTION;

  /**
   * Boolean for card type. True = chance False = community chest.
   */
  private final boolean CARD_TYPE;

  /**
   * Array of integers to hold card actions.
   */
  private final int ACTIONS [];

  /**
   * Constructor method set variable properties and creates card
   * actions.
   *
   * @param cardDescription Sets card text description.
   * @param cardType Sets the type of card as true or false (chance
   *        or community chest).
   * @param actions Sets the array of actions the card will perform.
   */
  public Card(String cardDescription, boolean cardType,
      int actions []) {
    CARD_DESCRIPTION = cardDescription;
    CARD_TYPE = cardType;

    ACTIONS = getNewPointers(actions);
  }

  /**
   * This method helps to return a user with different internal
   * pointers to prevent fiving the user the internal representation
   * of the object through an mutable object.
   * access to the in.
   * @param list the mian list.
   * @return the int array for the actions
   */
  private int [] getNewPointers(int[] list) {
    int [] tempList = new int [list.length];
    for (int index = 0; index < list.length; ++index) {
      tempList[index] = Integer.valueOf(list [index]);
    }

    return tempList;
  }

  /**
   * Getter method for CARD_DESCRIPTION variable.
   *
   * @return CARD_DESCRIPTION Returns the card text description.
   */
  public String getCardDescription() {
    return CARD_DESCRIPTION;
  }

  /**
   * Getter method for CARD_TYPE variable.
   *
   * @return CARD_TYPE Returns the card type.
   */
  public boolean getCardType() {
    return CARD_TYPE;
  }

  /**
   * Getter method for ACTIONS variable.
   *
   * @return ACTIONS Returns the actions the card will perform.
   */
  public int[] getActions() {
    return getNewPointers(ACTIONS);
  }
}
