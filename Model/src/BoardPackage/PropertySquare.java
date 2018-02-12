package BoardPackage;

import GamePackage.Player;

/********************************************************************
 * The PropertySquare class is a BoardSquare class that defines the
 * BoardSquares that are properties. It tracks variables related to
 * properties such as the price of purchase and the rent cost of
 * landing on this BoardSquare.
 *
 * @author David Baas
 * @version 2/11/2018
 ********************************************************************/
public class PropertySquare extends BoardSquare{

    /**An int to hold the price of the property*/
    private final int price;

    /**An int to hold the property's mortgage value*/
    private final int mortgageValue;

    /**An int to hold the cost of rent w/ no houses.*/
    private final int baseRent;

    /**An int to hold the cost of rent w/ one house*/
    private final int oneHouseRent;

    /**An int to hold the cost of rent w/ two houses*/
    private final int twoHouseRent;

    /**An int to hold the cost of rent w/ three houses*/
    private final int threeHouseRent;

    /**An int to hold the cost of rent w/ four houses*/
    private final int fourHouseRent;

    /**An int to hold the cost of rent w/ a hotel*/
    private final int hotelRent;

    /**An int to track how many houses are on the property*/
    private int numHouses;

    /**A boolean flag to track if the property is mortgaged*/
    private boolean isMortgaged;

    /**A Player variable to track what player owns this property*/
    private Player owner;

    /**An int to track the cost of purchasing one house for this property*/
    private final int houseCost;

    /**An int to track the cost of purchasing one hotel for this property*/
    private final int hotelCost;

    public PropertySquare(String name, int price, int mortgageValue,
                          int baseRent, int oneHouseRent,
                          int twoHouseRent, int threeHouseRent,
                          int fourHouseRent, int hotelRent,
                          int numHouses, int houseCost,
                          int hotelCost) {
        super(name);

        this.price = price;
        this.mortgageValue = mortgageValue;

        this.baseRent = baseRent;
        this.oneHouseRent = oneHouseRent;
        this.twoHouseRent = twoHouseRent;
        this.threeHouseRent = threeHouseRent;
        this.fourHouseRent = fourHouseRent;
        this.hotelRent = hotelRent;

        this.numHouses = numHouses;
        isMortgaged = false;
        owner = null;


        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public int getBaseRent() {
        return baseRent;
    }

    public int getOneHouseRent() {
        return oneHouseRent;
    }

    public int getTwoHouseRent() {
        return twoHouseRent;
    }

    public int getThreeHouseRent() {
        return threeHouseRent;
    }

    public int getFourHouseRent() {
        return fourHouseRent;
    }

    public int getHotelRent() {
        return hotelRent;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getHotelCost() {
        return hotelCost;
    }
}
