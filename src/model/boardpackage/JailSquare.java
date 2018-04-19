package model.boardpackage;

import java.util.ArrayList;

import model.gamepackage.Player;

/**
 * The JailSquare Class tracks what players are in jail.
 *
 * @author David Baas
 * @version 2/14/2018
 */
public class JailSquare extends BoardSquare {

  /**
   * An ArrayList to store what players are in jail.
   */
  private ArrayList<Player> inJail;

  /**
   * The default constructor creates a JailSquare setting its name
   * variable and initializes the inJail ArrayList.
   *
   * @param name sets the name of the board square.
   */
  public JailSquare(String name) {

    super(name, 10);

    inJail = new ArrayList<Player>();
    type = 6;
  }

  /**
   * The addToJail method adds a single player into the jail list
   * There is a check when this method is invoked that the same
   * player is not added to jail twice.
   *
   * @param player the Player who is being sent to jail.
   */
  public void addToJail(Player player) {
    inJail.add(player);
  }

  /**
   * The removeFrom Jail method removes a player from being in jail.
   * There is a distinction from being in jail and visiting jail.
   * There is a check to see if the player being removed from jail
   * is within the inJail list.
   *
   * @param player the Player being removed from jail.
   * @return true if the Player specified exist in the inJail list
   *         and is removed, false otherwise.
   */
  public boolean removeFromJail(Player player) {
    return inJail.remove(player);
  }

  /**
   * The getInJail method returns the list of player currently in
   * jail.
   *
   * @return inJail the ArrayList of Players currently in jail.
   */
  public ArrayList<Player> getInJail() {
    return inJail;
  }

  /**
   * The setInJail method sets the inJail ArrayList to a list of
   * Players from the input parameters.
   * @param inJail the ArrayList of players that will be stored for
   *               this instance of inJail.
   */
  public void setInJail(ArrayList<Player> inJail) {
    this.inJail = inJail;
  }
}
