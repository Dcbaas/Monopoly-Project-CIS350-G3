package Model.BoardPackage;

import Model.GamePackage.Player;

/*********************************************************************
 * The OwnableSquare Class provides the framework for boardsqures that
 * are ownable. Allows for a universal list of ownable board squares.
 *
 * @author David Baas Dustin Ecker
 * @version 2/17/2018
 *
 ********************************************************************/
public class OwnableSquare extends BoardSquare{

    /**An int to hold the PRICE of the property*/
    private final int PRICE;

    /**An int to hold the property's mortgage value*/
    private final int MORTGAGE_VAL;

    /**A boolean flag to track if the property is mortgaged*/
    private boolean isMortgaged;

    /**A Player variable to track what player owns this property*/
    private Player owner;

    /*****************************************************************
     * The default constructor initializes all of the variables.
     *
     * @param name the name of the PropertySquare
     * @param PRICE the PRICE of the property
     * @param MORTGAGE_VAL The mortgage value of the property.
     ****************************************************************/
    public OwnableSquare(String name, int PRICE, int MORTGAGE_VAL) {
        super(name);
        this.PRICE = PRICE;
        this.MORTGAGE_VAL = MORTGAGE_VAL;
        isMortgaged = false;
        owner = null;
    }

    /******************************************************************
     * The getPrice method returns the PRICE of this OwnableSquare.
     *
     * @return PRICE the price of the property.
     *****************************************************************/
    public int getPRICE() {
        return PRICE;
    }

    /******************************************************************
     * The getMortgageValue method returns the value of mortgaging
     * this OwnableSquare.
     *
     * @return MORTGAGE_VAL the mortgage value of the property.
     *****************************************************************/
    public int getMORTGAGE_VAL() {
        return MORTGAGE_VAL;
    }

    /******************************************************************
     * The isMortgaged method returns weather the OwnableSquare is
     * mortgaged or not.
     *
     * @return isMortgaged true if the OwnableSquare is mortgaged, false
     * otherwise.
     *****************************************************************/
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /******************************************************************
     * The setMortgaged method sets the mortgage flag.
     *
     * @param mortgaged the mortgage status this OwnableSquare is being
     *                 set to.
     *****************************************************************/
    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    /******************************************************************
     * The getOwner method returns the Player who owns this OwnableSquare
     * or null if the bank owns the property.
     *
     * @return owner the Player who owns the OwnableSquare or null if the
     * bank owns the property.
     *****************************************************************/
    public Player getOwner() {
        return owner;
    }

    /******************************************************************
     * The setOwner method sets the Player who owns this OwnableSquare.
     * set the input to null if the bank should own the OwnableSquare.
     *
     * @param owner the Player who will own the property.
     *****************************************************************/
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
