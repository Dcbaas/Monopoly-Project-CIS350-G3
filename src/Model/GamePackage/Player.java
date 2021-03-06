package Model.GamePackage;

import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import Model.CardPackage.Card;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**********************************************************************
 * The Player class will keep track of each player's money, properties,
 * and other stats.
 *
 * @author Dustin Ecker Dylan Kernohan David Baas
 * @since 2/16/2018
 * @version 4/10/2018
 *********************************************************************/
public class Player {

  /**
   * Player's name
   */
  private final String DISPLAY_NAME;

  /**
   * Player's token
   */
  private Image TOKEN;

  /**
   * Player's token in string form
   */
  @Deprecated
  private final String STR_TOKEN;

  /**
   * Tells if player is bankrupt
   */
  private boolean isBankrupt;

  /**
   * Holds player's current wallet balance
   */
  private int wallet;

  /**
   * ArrayList that holds player's owned squares
   */
  private ArrayList<OwnableSquare> propertiesOwned;

  /**
   * ArrayList that holds player's cards held
   */
  private ArrayList<Card> cardsHeld;

  /**
   * An integer to represent the players positon on the board.
   */
  private int position;

  /**
   * An int that tells if they player is in Jail, and for how many turns.
   * -1 = not in Jail
   * 0 - 3 = Number of turns in Jail
   */
  private int inJail;

  /**
   * An array list that keeps tracks of which groups of ownableSqaures
   * the player owns.
   */
  private ArrayList<Integer> groupsOwned;


  /******************************************************************
   * Constructor method for Player class.
   *
   * @param displayName Sets the player's name.
   * @param token Sets the player's chosen token.
   * @param wallet Sets the player's starting wallet value.
   *****************************************************************/
  @Deprecated
  public Player(String displayName, String token, int wallet) {
    DISPLAY_NAME = displayName;
    STR_TOKEN = token;
    this.wallet = wallet;

    position = 0;

    isBankrupt = false;

    propertiesOwned = new ArrayList<>();
    cardsHeld = new ArrayList<>();

    inJail = -1;
    groupsOwned = new ArrayList<>();
  }


  /******************************************************************
   * Constructor method for Player class.
   *
   * @param displayName Sets the player's name.
   * @param tokenImage The file that contains the image for this players
   * token.
   * @param wallet Sets the player's starting wallet value.
   *****************************************************************/
  public Player(String displayName, File tokenImage, int wallet)
      throws IOException {
    DISPLAY_NAME = displayName;
    TOKEN = ImageIO.read(tokenImage);
    this.wallet = wallet;

    position = 0;

    isBankrupt = false;

    propertiesOwned = new ArrayList<>();
    cardsHeld = new ArrayList<>();

    inJail = -1;
    groupsOwned = new ArrayList<>();
    STR_TOKEN = null;
  }

  /******************************************************************
   * Getter method for displayName variable.
   *
   * @return displayName Returns the String displayName.
   *****************************************************************/
  public String getDisplayName() {
    return DISPLAY_NAME;
  }

  /******************************************************************
   * Get the image for this players token.
   * @return The image for this players token.
   *******************************************************************/
  public Image getToken() {
    return TOKEN;
  }


  /******************************************************************
   * Getter method for isBankrupt variable.
   *
   * @return isBankrupt Returns the boolean isBankrupt.
   *****************************************************************/
  public boolean isBankrupt() {
    return isBankrupt;
  }

  /******************************************************************
   * Setter method for isBankrupt variable.
   *
   * @param isBankrupt Sets the boolean isBankrupt.
   *****************************************************************/
  public void setBankrupt(boolean isBankrupt) {
    this.isBankrupt = isBankrupt;
  }

  /******************************************************************
   * Getter method for wallet variable.
   *
   * @return wallet Returns the int wallet.
   *****************************************************************/
  public int getWallet() {
    return wallet;
  }

  /******************************************************************
   * Setter method for wallet variable.
   *
   * @param wallet Sets the int wallet.
   *****************************************************************/
  void setWallet(int wallet) {
    this.wallet = wallet;
  }

  /******************************************************************
   * Getter method for propertiesOwned ArrayList.
   *
   * @return propertiesOwned Returns the ArrayList propertiesOwned.
   *****************************************************************/
  public ArrayList<OwnableSquare> getPropertiesOwned() {
    return propertiesOwned;
  }

  /******************************************************************
   * Setter method for propertiesOwned ArrayList.
   *
   * @param propertiesOwned Sets the ArrayList propertiesOwned.
   *****************************************************************/
  public void setPropertiesOwned(ArrayList<OwnableSquare>
      propertiesOwned) {
    this.propertiesOwned = propertiesOwned;
  }

  /******************************************************************
   * Getter method for cardsHeld ArrayList.
   *
   * @return cardsHeld Returns the ArrayList cardsHeld.
   *****************************************************************/
  public ArrayList<Card> getCardsHeld() {
    return cardsHeld;
  }

  /******************************************************************
   * Setter method for cardsHeld ArrayList.
   *
   * @param cardsHeld Sets the ArrayList cardsHeld.
   *****************************************************************/
  public void setCardsHeld(ArrayList<Card> cardsHeld) {
    this.cardsHeld = cardsHeld;
  }

  /******************************************************************
   * The getPosition method returns the current position of the player.
   * @return position the current position of the player.
   *****************************************************************/
  public int getPosition() {
    return position;
  }

  /******************************************************************
   * The setPosition method sets the postion of the player on the
   * board
   * @param position the positon the boaad the player is being sent
   *                 to.
   *****************************************************************/
  public void setPosition(int position) {
    this.position = position;
  }

  /******************************************************************
   * This method gets an int that says if the player is in Jail or not.
   * -1 = not in jail
   * 0 - 3 = Number of turns in Jail
   * @return inJail An int that says if the player is in jail.
   * -1 = not in jail
   * 0 - 3 = Number of turns in Jail
   *****************************************************************/
  public int getInJail() {
    return inJail;
  }

  /******************************************************************
   * This method sets the int value saying if the player is in Jail.
   * -1 = not in jail
   * 0 - 3 = Number of turns in Jail
   * @param inJail An int that says if the player is in Jail
   *               -1 = not in jail
   *                0 - 3 = Number of turns in Jail
   ******************************************************************/
  public void setInJail(int inJail) {
    this.inJail = inJail;
  }

  /*****************************************************************
   * This method gets a list of all the groups of ownable squares
   * this player owns.
   * @return groupsOwned An array list of all the groups this
   * player owns.
   *****************************************************************/
  public ArrayList<Integer> getGroupsOwned() {
    return groupsOwned;
  }

  /******************************************************************
   * This method sets the list of groups the player owns.
   * @param groupsOwned An array list of groups the player owns.
   ******************************************************************/
  public void setGroupsOwned(ArrayList<Integer> groupsOwned) {
    this.groupsOwned = groupsOwned;
  }

  /******************************************************************
   * Checks to make sure player has sufficient funds before
   * subtracting amount from wallet. If the player has sufficient
   * funds the method subtracts amount from wallet and returns the
   * amount paid. If player doesn't have sufficient funds he method
   * subtracts what is able to be paid from wallet and returns the
   * amount that has been paid.
   *
   * @param amount The amount of money requested from player.
   * @return amountPaid The amount of money that was paid by player.
   *****************************************************************/
  public int pay(int amount) {
    int amountPaid;

    if (wallet >= amount) {
      wallet -= amount;
      amountPaid = amount;
    } else {
      amountPaid = wallet;
      wallet -= 0;
    }

    return amountPaid;
  }


  /******************************************************************
   * Removes the property from the player's ArrayList of properties
   * owned.
   *
   * @param property The property to be removed from owned properties
   * list.
   * @return the property the player is givig away.
   *****************************************************************/
  public OwnableSquare giveProperty(OwnableSquare property) {
    return propertiesOwned.remove(propertiesOwned.indexOf(property));
  }

  /******************************************************************
   * Adds the property to the player's ArrayList of properties
   * owned.
   *
   * @param property The property to be added to owned properties
   * list.
   *****************************************************************/
  public void recieveProperty(OwnableSquare property) {
    propertiesOwned.add(property);
  }

  /******************************************************************
   * Builds a house on the property from the players ArrayList of
   * properties owned.
   *
   * @param property The property that the house will be built on.
   *****************************************************************/
  public void buildHouse(PropertySquare property) {
    property.setNumHouses(property.getNumHouses() + 1);
    wallet -= property.getHouseCost();
  }

  /******************************************************************
   * Builds a hotel on the property from the players ArrayList of
   * properties owned.
   *
   * @param property The property that the hotel will be built on.
   *****************************************************************/
  public void buildHotel(PropertySquare property) {
    property.setHasHotel(true);
    wallet -= property.getHotelCost();
  }

  /******************************************************************
   * Sells a house on the property from the players ArrayList of
   * properties owned.
   *
   * @param property The property that the house will be sold from.
   *****************************************************************/
  public void sellHouse(PropertySquare property) {
    property.setNumHouses(property.getNumHouses() - 1);
    wallet += property.getHouseCost() / 2;
  }

  /******************************************************************
   * Sells a hotel on the property from the players ArrayList of
   * properties owned.
   *
   * @param property The property that the hotel will be sold from.
   *****************************************************************/
  public void sellHotel(PropertySquare property) {
    property.setHasHotel(false);
    wallet += property.getHotelCost() / 2;
  }

  /******************************************************************
   * This method adds a group number to the ArrayList.
   *
   * @param groupNumber The group number being added
   * @return true if the groupsOwned list is updated; false otherwise.
   *****************************************************************/
  public boolean addGroupOwned(int groupNumber) {
    return groupsOwned.add(groupNumber);
  }

  /******************************************************************
   * This method removes a group number from the ArrayList.
   *
   * @param groupNumber The group number being removed.
   * @return weather the action was succesfull or not.
   *****************************************************************/
  public boolean removeGroupOwned(int groupNumber) {
    return groupsOwned.remove((Integer) groupNumber);
  }


  /******************************************************************
   * Allows a player to receive a card from an external source, and
   * adds the card to the Deck of cards held
   *
   * @param card the card given to the player
   *****************************************************************/
  public void recieveCard(Card card) {
    cardsHeld.add(card);
  }

  /******************************************************************
   * Allows the player to receive money, and adds it to the wallet.
   * @param amount the amount of money the player receives
   *****************************************************************/
  public void receiveMoney(int amount) {
    wallet += amount;
  }

  /******************************************************************
   * returns all Ownable squares from the player.
   * @return the list of Ownable squares.
   *****************************************************************/
  public ArrayList<OwnableSquare> getOwnableProperties() {
    return propertiesOwned;
  }

  public int getNumPropertiesOwnedByType(int typeId) {
    int count = 0;

    for (OwnableSquare onableSquare : propertiesOwned) {
      if (typeId == onableSquare.getType()) {
        count++;
      }
    }

    return count;
  }
}
