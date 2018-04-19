package model.gamepackage;

import java.util.ArrayList;

import model.boardpackage.OwnableSquare;

/**
 * The bank class is responsible of keeping track of the game's houses,
 * player montages, and also perform certain transactions.
 *
 * @author Santiago Quiroga
 * @since 2/13/2018
 * @version 2/21/2018
 */
public class Bank {

  /**
   * Number of houses.
   */
  private int numHouses;

  /**
   * Number of Hotels.
   */
  private int numHotels;

  /**
   * List of properties owned.
   */
  private ArrayList<OwnableSquare> propertiesOwned;

  /**
   * List of properties mortgaged.
   */
  private ArrayList<OwnableSquare> propertiesMortgaged;

  /**
   *  This constructor initializes the bank with a specific set
   *  of properties, and uses the size of properties to compute the
   *  size of the properties that can be mortgaged.
   *
   * @param listOfProperties The ArrayList of OwnableSquares that
   *                         can be purchased.
   */
  public Bank(ArrayList<OwnableSquare> listOfProperties) {
    // initialises all variables
    propertiesOwned = listOfProperties;
    propertiesMortgaged = new ArrayList<>(listOfProperties.size());
    numHotels = 32;
    numHouses = 12;
  }

  /**
   * This method will return a specific property, based of its ID.
   *
   * @param property The property being given away by the Bank.
   * @return property The property being given away by the Bank.
   */
  public OwnableSquare giveProperty(OwnableSquare property) {
    //removes and returns the specific property.
    return propertiesOwned.remove(propertiesOwned.indexOf(property));
  }

  /**
   * This method will store the given property into the list of
   * owned properties.
   *
   * @param property The property being received by the Bank.
   */
  public void receiveProperty(OwnableSquare property) {
    //Sets the owner of the property to null since to show.
    property.setOwner(null);

    //Adds the property to its list.
    propertiesOwned.add(property);
  }

  /**
   * Decrements the number of houses by one.
   */
  public void giveHouse() {
    numHouses--;
  }

  /**
   * Decrements the number of hotels by one.
   */
  public void giveHote() {
    numHotels--;
  }

  /**
   * Increments the number of houses by one.
   */
  public void receiveHouse() {
    numHouses++;
  }

  /**
   * Increments the number of hotels by one.
   */
  public void receiveHotel() {
    numHotels++;
  }

  /**
   * Returns the number of houses the Bank currently has in stock.
   *
   * @return numHouses The number of houses the Bank currently has
   *          in stock.
   **/
  public int getNumHouses() {
    return numHouses;
  }

  /**
   * Sets the number of houses the Bank currently has in stock.
   *
   * @param numHouses The number of houses the Bank will have in
   *                  stock.
   */
  public void setNumHouses(int numHouses) {
    this.numHouses = numHouses;
  }

  /**
   * Returns the number of hotels the Bank currently has in stock.
   *
   * @return numHotels The number of hotels the Bank currently has
   *        in stock.
   */
  public int getNumHotels() {
    return numHotels;
  }

  /**
   * Sets the number of hotels the Bank currently has in stock.
   *
   * @param numHotels The number of hotels the Bank will have in
   *                  stock.
   */
  public void setNumHotels(int numHotels) {
    this.numHotels = numHotels;
  }

  /**
   * Returns the list of properties owned.
   *
   * @return propertiesOwned The list of properties owned.
   **/
  public ArrayList<OwnableSquare> getPropertiesOwned() {
    return propertiesOwned;
  }

  /**
   * Sets the current properties owned.
   *
   * @param propertiesOwned Sets the number of properties owned.
   */
  public void setPropertiesOwned(ArrayList<OwnableSquare> propertiesOwned) {
    this.propertiesOwned = propertiesOwned;
  }

  /**
   * Returns the list of mortgaged properties.
   *
   * @return propertiesMortgaged The list of mortgaged properties.
   */
  public ArrayList<OwnableSquare> getPropertiesMortgaged() {
    return propertiesMortgaged;
  }

  /**
   * Set the list of properties mortgaged.
   *
   * @param propertiesMortgaged The list of mortgaged properties.
   */
  public void setPropertiesMortgaged(ArrayList<OwnableSquare> propertiesMortgaged) {
    this.propertiesMortgaged = propertiesMortgaged;
  }
}
