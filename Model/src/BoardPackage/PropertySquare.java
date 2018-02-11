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

    private final int price;

    private final int mortgageValue;

    private final int baseRent;

    private final int oneHouseRent;

    private final int twoHouseRent;

    private final int threeHouseRent;

    private final int fourHouseRent;

    private final int hotelRent;

    private int numHouses;

    private boolean isMortgaged;

    private Player owner;

    private final int houseCost;

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
