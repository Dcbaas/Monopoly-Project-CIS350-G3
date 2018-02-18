package Model.BoardPackage;

import Model.GamePackage.Player;

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
    public static final int MAX_PLAYERS = 8;

    /**A String to track the name of the BoardSquare*/
    protected String name;

    /**An ArrayList of Players that tracks what players are on this
     * square.
     */
    protected ArrayList<Player> occupiedBy;

    /**An integer to represent the type of boardSquare*/
    protected int type;

    /**A integer that represents the square's POSITION on the board.*/
    protected final int POSITION;

    /*****************************************************************
     * Constructor instantiates the variables and sets the name of the
     * BoardSquare to what is input as a parameter.
     *
     * @param name String that will be the name of the BoardSquare.
     ****************************************************************/
    public BoardSquare(String name,int position) {
        this.name = name;
        this.POSITION = position;
        occupiedBy = new ArrayList<Player>(MAX_PLAYERS);
        type = 2;
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

     /****************************************************************
      * The getPOSITION method returns what position this board square
      * is on the board.
      *
      * @return POSITION The position of this boardsquare on the board
      ***************************************************************/
    public int getPOSITION() {
         return POSITION;
    }

     /****************************************************************
      * The getType method returns what type of BoardSquare this
      * board square is.
      *
      * @return type the type of BoardSquare this board square is.
      */
    public int getType() {
         return type;
     }

     /****************************************************************
      * the setType method sets what type of board square this
      * BoardSquare is.
      *
      * @param type the type of BoardSquare this BoardSquare will be
      *             set to.
      */
    public void setType(int type) {
         this.type = type;
    }
 }
