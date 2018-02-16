package src.BoardPackage;

import src.GamePackage.Player;

import java.util.ArrayList;

 /********************************************************************
  * The BoardSquare Interface is the base class for all board squares
  * which will make up the board. It tracks variables universal to
  * all board squares and has methods for getting and setting those
  * variables.
  *
  * @author David Baas
  * @version 2/11/2018
 *********************************************************************/
public abstract class BoardSquare {

    /**A constant for the maximum number of player that can be on a
     * square*/
    public static final int MAX_PLAYERS = 6;

    /**A String to track the name of the BoardSquare*/
    protected String name;

    /**An ArrayList of Players that tracks what players are on this
     * square.
     */
    protected ArrayList<Player> occupiedBy;

    /*****************************************************************
     * Constructor instantiates the variables and sets the name of the
     * BoardSquare to what is input as a parameter.
     *
     * @param name String that will be the name of the BoardSquare.
     ****************************************************************/
    public BoardSquare(String name) {
        this.name = name;
        occupiedBy = new ArrayList<Player>(MAX_PLAYERS);
    }

    /*****************************************************************
     *The getName method returns the name of the BoardSquare
     *
     * @return name The string that is the name of the BoardSquare.
     ****************************************************************/
    public String getName() {
        return name;
    }

     /****************************************************************
      * The setName method sets the name of the BoardSquare.
      *
      * @param name The name that this BoardSquare is set to.
      ***************************************************************/
    public void setName(String name) {
        this.name = name;
    }

     /****************************************************************
      * The getOccupiedBy method returns the Arraylist that is the
      * current players on this BoardSquare
      *
      * @return occupiedBy the list of players on this BoardSquare.
      ***************************************************************/
    public ArrayList<Player> getOccupiedBy() {
        return occupiedBy;
    }

     /****************************************************************
      * The setOccupied by sets what players are on this BoardSquare.
      *
      * @param occupiedBy the list of players that will be who is
      *                   occupying this BoardSquare.
      ***************************************************************/
    public void setOccupiedBy(ArrayList<Player> occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

     /****************************************************************
      * The addPlayer method adds new player that lands on this
      * BoardSquare.
      *
      * @param p the Player landing on this BoardSquare.
      ***************************************************************/
    public  void addPlayer(Player p){
        occupiedBy.add(p);
    }

     /****************************************************************
      * The removePlayer method removes a player who is leaving
      * this BoardSquare.
      *
      * @param p the Player leaving this BoardSquare.
      ***************************************************************/
    public void removePlayer(Player p){
        occupiedBy.remove(p);
    }
}
