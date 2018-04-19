package model.boardpackage;

import model.gamepackage.Player;

/**
 * The OwnableSquare Class provides the framework for boardsqures that
 * are ownable. Allows for a universal list of ownable board squares.
 *
 * @author David Baas Dustin Ecker Dylan Kernohan
 * @since 2/18/2018
 * @version 3/17/2018
 *
 */
public abstract class OwnableSquare extends BoardSquare {

  /**
   * An int to hold the PRICE of the property.
   */
  protected final int PRICE;

  /**
   * An int to hold the property's mortgage value.
   */
  protected final int MORTGAGE_VAL;
  /**
   * An int to keep track of which group this ownableSquare is in.
   */
  protected final int GROUP_NUMBER;
  /**
   * A boolean flag to track if the property is mortgaged.
   */
  protected boolean isMortgaged;
  /**
   * A Player variable to track what player owns this property.
   */
  protected Player owner;


  /**
   * The default constructor initializes all of the variables.
   *
   * @param name the name of the PropertySquare
   * @param position the position of this OwnableSquare on the board.
   * @param PRICE the PRICE of the property
   * @param MORTGAGE_VAL The mortgage value of the property.
   * @param GROUP_NUMBER The Ownable group this OwnableSquare
   * belongs to.
  */
  public OwnableSquare(String name, int position, int PRICE, int MORTGAGE_VAL, int GROUP_NUMBER) {
    super(name, position);
    this.PRICE = PRICE;
    this.MORTGAGE_VAL = MORTGAGE_VAL;
    this.GROUP_NUMBER = GROUP_NUMBER;
    isMortgaged = false;
    owner = null;
  }

  /**
   * The getPrice method returns the PRICE of this OwnableSquare.
   *
   * @return PRICE the price of the property.
   */
  public int getPRICE() {
    return PRICE;
  }

  /**
   * The getMortgageValue method returns the value of mortgaging
   * this OwnableSquare.
   *
   * @return MORTGAGE_VAL the mortgage value of the property.
   */
  public int getMORTGAGE_VAL() {
    return MORTGAGE_VAL;
  }

  /**
   * The isMortgaged method returns weather the OwnableSquare is
   * mortgaged or not.
   *
   * @return isMortgaged true if the OwnableSquare is mortgaged, false
   *         otherwise.
   */
  public boolean isMortgaged() {
    return isMortgaged;
  }

  /**
   * The setMortgaged method sets the mortgage flag.
   *
   * @param mortgaged the mortgage status this OwnableSquare is being
   *                 set to.
   */
  public void setMortgaged(boolean mortgaged) {
    isMortgaged = mortgaged;
  }

  /**
   * The getOwner method returns the Player who owns this OwnableSquare
   * or null if the bank owns the property.
   *
   * @return owner the Player who owns the OwnableSquare or null if the
   *         bank owns the property.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * The setOwner method sets the Player who owns this OwnableSquare.
   * set the input to null if the bank should own the OwnableSquare.
   *
   * @param owner the Player who will own the property.
   */
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * This method gets the group number of this ownableSquare.
   *
   * @return GROUP_NUMBER The group number for this ownableSquare
   */
  public int getGROUP_NUMBER() {
    return GROUP_NUMBER;
  }

}
