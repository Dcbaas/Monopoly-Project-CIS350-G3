package BoardPackage;

import GamePackage.Player;

/********************************************************************
 * The PropertySquare class is a BoardSquare class that defines the
 * BoardSquares that are properties. It tracks variables related to
 * properties such as the price of purchase and the rent cost of
 * landing on this BoardSquare.
 *
 * @author David Baas
 * 
 ********************************************************************/
public class PropertySquare extends BoardSquare{

    final int price;

    final int mortgageValue;

    int baseRent;

    int oneHouseRent;

    int twoHouseRent;

    int threeHouseRent;

    int fourHouseRent;

    int hotelRent;

    int numHouses;

    boolean isMortgaged;

    Player owner;

    int houseCost;

    int hotelCost;

    public PropertySquare(String name, int price, int mortgageValue,
                          int baseRent, int oneHouseRent,
                          int twoHouseRent, int threeHouseRent,
                          int fourHouseRent, int hotelRent,
                          int numHouses, boolean isMortgaged,
                          Player owner, int houseCost, int hotelCost) {
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
        this.isMortgaged = isMortgaged;
        this.owner = owner;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
    }
}
