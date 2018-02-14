package BoardPackage;

import GamePackage.Player;

/********************************************************************
 * The PropertySquare class is a BoardSquare class that defines the
 * BoardSquares that are properties. It tracks variables related to
 * properties such as the PRICE of purchase and the rent cost of
 * landing on this BoardSquare.
 *
 * @author David Baas
 * @version 2/12/2018
 ********************************************************************/
public class PropertySquare extends BoardSquare{

    /**An int to hold the PRICE of the property*/
    private final int PRICE;

    /**An int to hold the property's mortgage value*/
    private final int MORTGAGE_VAL;

    /**An int to hold the cost of rent w/ no houses.*/
    private final int BASE_RENT;

    /**An int to hold the cost of rent w/ one house*/
    private final int ONE_HOUSE_RENT;

    /**An int to hold the cost of rent w/ two houses*/
    private final int TWO_HOUSE_RENT;

    /**An int to hold the cost of rent w/ three houses*/
    private final int THREE_HOUSE_RENT;

    /**An int to hold the cost of rent w/ four houses*/
    private final int FOUR_HOUSE_RENT;

    /**An int to hold the cost of rent w/ a hotel*/
    private final int HOTEL_RENT;

    /**An int to track how many houses are on the property*/
    private int numHouses;

    /**A boolean to track weather the property has a hotel.*/
    private boolean hasHotel;

    /**A boolean flag to track if the property is mortgaged*/
    private boolean isMortgaged;

    /**A Player variable to track what player owns this property*/
    private Player owner;

    /**An int to track the cost of purchasing one house for this property*/
    private final int HOUSE_COST;

    /**An int to track the cost of purchasing one hotel for this property*/
    private final int HOTEL_COST;

    /******************************************************************
     * The constructor creates a Property and sets all the attributes
     * related to the property.
     * @param name the name of the PropertySquare
     * @param price the PRICE of the property
     * @param mortgageValue The mortgage value of the property.
     * @param baseRent The rent cost of the property.
     * @param oneHouseRent The rent cost of the property w/ one house.
     * @param twoHouseRent The rent cost of the property w/ two houses.
     * @param threeHouseRent The rent cost of the property w/ three houses.
     * @param fourHouseRent The rent cost of the property w/ four houses.
     * @param hotelRent The rent cost of the property w/ a hotel.
     * @param numHouses The number of houses on the property.
     * @param houseCost The cost of adding a house to the property.
     * @param hotelCost The cost of adding a hotel to the property.
     *****************************************************************/
    public PropertySquare(String name, int price, int mortgageValue,
                          int baseRent, int oneHouseRent,
                          int twoHouseRent, int threeHouseRent,
                          int fourHouseRent, int hotelRent,
                          int numHouses, int houseCost,
                          int hotelCost) {
        super(name);

        PRICE = price;
        MORTGAGE_VAL = mortgageValue;

        BASE_RENT = baseRent;
        ONE_HOUSE_RENT = oneHouseRent;
        TWO_HOUSE_RENT = twoHouseRent;
        THREE_HOUSE_RENT = threeHouseRent;
        FOUR_HOUSE_RENT = fourHouseRent;
        HOTEL_RENT = hotelRent;

        this.numHouses = numHouses;
        hasHotel = false;
        isMortgaged = false;
        owner = null;


        HOUSE_COST = houseCost;
        HOTEL_COST = hotelCost;
    }

    /******************************************************************
     * The getPrice method returns the PRICE of this property.
     *
     * @return PRICE the price of the property.
     *****************************************************************/
    public int getPrice() {
        return PRICE;
    }

    /******************************************************************
     * The getMortgageValue method returns the value of mortgaging
     * this property.
     *
     * @return MORTGAGE_VAL the mortgage value of the property.
     *****************************************************************/
    public int getMortgageValue() {
        return MORTGAGE_VAL;
    }

    /******************************************************************
     * The getBaseRent method returns the cost of rent for this property
     *
     * @return BASE_RENT the base rent of the property.
     *****************************************************************/
    public int getBaseRent() {
        return BASE_RENT;
    }

    /******************************************************************
     * The getOneHouseRent method returns the cost of rent with one
     * house on the property.
     *
     * @return ONE_HOUSE_RENT the cost of rent w/ one house on the
     * property.
     *****************************************************************/
    public int getOneHouseRent() {
        return ONE_HOUSE_RENT;
    }

    /******************************************************************
     * The getTwoHouseRent method returns the cost of rent with two
     * houses on the property.
     *
     * @return TWO_HOUSE_RENT the cost of rent w/ two houses on the
     * property.
     *****************************************************************/
    public int getTwoHouseRent() {
        return TWO_HOUSE_RENT;
    }

    /******************************************************************
     * The getThreeHouseRent method returns the cost of rent with three
     * houses on the property.
     *
     * @return THREE_HOUSE_RENT the cost of rent w/ three houses on the
     * property.
     *****************************************************************/
    public int getThreeHouseRent() {
        return THREE_HOUSE_RENT;
    }

    /******************************************************************
     * The getFOUR_HOUSE_RENT method returns the cost of rent with four
     * houses on the property.
     *
     * @return FOUR_HOUSE_RENT the cost of rent w/ three houses on the
     * property.
     *****************************************************************/
    public int getFOUR_HOUSE_RENT() {
        return FOUR_HOUSE_RENT;
    }

    /******************************************************************
     * The getHotelRent method returns the cost of rent with a hotel
     * on the property.
     *
     * @return HOTEL_RENT the cost of rent w/ three houses on the
     * property.
     *****************************************************************/
    public int getHotelRent() {
        return HOTEL_RENT;
    }

    /******************************************************************
     * The getCurrentRent method returns the current rent price of the
     * property based off of if the property has a hotel or how many
     * houses the property has.
     * @return BASE_RENT, ONE_HOUSE_RENT, TWO_HOUSE_RENT,
     * THREE_HOUSE_RENT, FOUR_HOUSE_RENT, or HOTEL_RENT depending on
     * if there is a hotel on the property or how many houses are on
     * the property.
     *****************************************************************/
    //Todo: is this comment block right with regards to the return statements
    public int getCurrentRent(){
        if(hasHotel)
            return HOTEL_RENT;

        switch (numHouses){
            case 1: return ONE_HOUSE_RENT;

            case 2: return TWO_HOUSE_RENT;

            case 3: return THREE_HOUSE_RENT;

            case 4: return FOUR_HOUSE_RENT;

            default: return BASE_RENT;
        }
    }

    /******************************************************************
     * The getNumHouses method returns how many houses are on this
     * property.
     *
     * @return numHouses the number of houses on the property.
     *****************************************************************/
    public int getNumHouses() {
        return numHouses;
    }

    /******************************************************************
     * The setNumHouses method sets the number of houses on the
     * property.
     *
     * @param numHouses the number of houses for this property.
     *****************************************************************/
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    /******************************************************************
     * The isHasHotel method returns weather the property has a hotel
     * or not.
     *
     * @return hasHotel true if the property has a hotel, false
     * otherwise.
     *****************************************************************/
    public boolean isHasHotel() {
        return hasHotel;
    }

    /******************************************************************
     * The setHasHotel method set the hotel flag to determine if this
     * property has a hotel.
     *
     * @param hasHotel set weather this property has a hotel or not.
     *****************************************************************/
    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    /******************************************************************
     * The isMortgaged method returns weather the property is
     * mortgaged or not.
     *
     * @return isMortgaged true if the property is mortgaged, false
     * otherwise.
     *****************************************************************/
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /******************************************************************
     * The setMortgaged method sets the mortgage flag.
     *
     * @param mortgaged the mortgage status this property is being set
     *                  to.
     *****************************************************************/
    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    /******************************************************************
     * The getOwner method returns the Player who owns this property or
     * null if the bank owns the property.
     *
     * @return owner the Player who owns the property or null if the
     * bank owns the property.
     *****************************************************************/
    public Player getOwner() {
        return owner;
    }

    /******************************************************************
     * The setOwner method sets the Player who owns this property.
     * set the input to null if the bank should own the property.
     *
     * @param owner the Player who will own the property.
     *****************************************************************/
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /******************************************************************
     * The getHouseCost method returns the cost of purchasing a house
     * on this property.
     *
     * @return HOUSE_COST the cost of purchasing a house on this property.
     *****************************************************************/
    public int getHouseCost() {
        return HOUSE_COST;
    }

    /******************************************************************
     * The getHotelCost method returns the cost of purchasing a Hotel
     * on this property.
     *
     * @return HOTEL_COST the cost of purchasing a Hotel on this property.
     *****************************************************************/
    public int getHotelCost() {
        return HOTEL_COST;
    }
}
