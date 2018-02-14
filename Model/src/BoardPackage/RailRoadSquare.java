package BoardPackage;

import GamePackage.Player;

/*****************************************************************
 The RailRoadSquare class is a BoardSquare that tracks
 information specific to RailRoadSquares.

 @author Dylan Kernohan
 @version 2/12/2018
 *****************************************************************/
public class RailRoadSquare extends BoardSquare{

    /**The price of the RailRoad square*/
    private final int PRICE;

    /**The initial rent of the RailRoad square*/
    private final int BASE_RENT;

    /**The rent if a player owns two RailRoads*/
    private final int TWO_RENT;

    /**The rent if a player owns three RailRoads*/
    private final int THREE_RENT;

    /**The rent if a player owns four RailRoads*/
    private final int FOUR_RENT;

    /**The value a player receives if they mortgage this RailRoad*/
    private final int MORTGAGE_VALUE;

    /**A flag to tell if this RailRoad is currently mortgaged*/
    private boolean isMortgaged;

    /**The player that owns this RailRoad*/
    private Player owner;


    /*****************************************************************
     Constructor instantiates the variables and sets the name of the
     BoardSquare to what is input as a parameter.

     @param name String that will be the name of the BoardSquare.
     @param price The price of the RailRoad square
     @param baseRent The initial rent of the RailRoad square
     @param twoRent rent if a player owns two RailRoads
     @param threeRent rent if a player owns three RailRoads
     @param fourRent The rent if a player owns four RailRoads
     @param mortgageValue The value a player receives if they
     mortgage this RailRoad
     ****************************************************************/
    public RailRoadSquare(String name, int price, int baseRent,
                          int twoRent, int threeRent, int fourRent,
                          int mortgageValue) {

        super(name);
        this.PRICE = price;
        this.BASE_RENT = baseRent;
        this.TWO_RENT = twoRent;
        this.THREE_RENT = threeRent;
        this.FOUR_RENT = fourRent;
        this.MORTGAGE_VALUE = mortgageValue;
        this.isMortgaged = false;
        // TODO: Do we want to keep this null or make Bank own all squares to start out with? If so we would add Bank as param
        this.owner = null;
    }


    /*****************************************************************
     Get the price of the RailRoad square.

     @return PRICE The price of the RailRoad square.
     ****************************************************************/
    public int getPRICE() {
        return PRICE;
    }

    /*****************************************************************
     Get the initial rent of the RailRoad square.

     @return BASE_RENT The initial rent of the RailRoad square.
     ****************************************************************/
    public int getBASE_RENT() {
        return BASE_RENT;
    }

    /*****************************************************************
     Get the rent of the RailRoad when the player owns two RailRoad
     squares.

     @return TWO_RENT The rent if the player owns two RailRoads.
     ****************************************************************/
    public int getTWO_RENT() {
        return TWO_RENT;
    }

    /*****************************************************************
     Get the rent of the RailRoad when the player owns three RailRoad
     squares.

     @return THREE_RENT The rent if the player owns three RailRoads.
     ****************************************************************/
    public int getTHREE_RENT() {
        return THREE_RENT;
    }

    /*****************************************************************
     Get the rent of the RailRoad when the player owns four RailRoad
     squares.

     @return FOUR_RENT The rent if the player owns four RailRoads.
     ****************************************************************/
    public int getFOUR_RENT() {
        return FOUR_RENT;
    }

    /*****************************************************************
     Get the mortgage value of the RailRoad square.

     @return MORTGAGE_VALUE The mortgage value of the RailRoad.
     ****************************************************************/
    public int getMORTGAGE_VALUE() {
        return MORTGAGE_VALUE;
    }

    /*****************************************************************
     This method tells if the RailRoad is mortgaged or not.

     @return isMortgaged True if the RailRoad is mortgaged, False if
     it is not.
     ****************************************************************/
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /*****************************************************************
     This method sets the boolean flag isMortgaged.

     @param mortgaged The boolean value this flag is being set to.
     ****************************************************************/
    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    /*****************************************************************
     This method gets the player that owns this RailRoad.

     @return owner The player that owns this Railroad.
     ****************************************************************/
    public Player getOwner() {
        return owner;
    }

    /*****************************************************************
     This method sets the player that owns this RailRoad.

     @param owner The player that owns the RailRoad.
     ****************************************************************/
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
