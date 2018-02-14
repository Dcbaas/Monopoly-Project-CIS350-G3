package GamePackage;

import BoardPackage.BoardSquare;
import BoardPackage.PropertySquare;

import java.util.ArrayList;

/****************************************************************************************************************
 * The bank class is responsible of keeping track of the game's houses, player montages, and also perform
 * certain transactions.
 *
 * @author Santiago Quiroga
 * @version 2/13/2018
 ***************************************************************************************************************/
public class Bank {
    /* Number of houses and Hotels */
    private int numHouses, numHotels;

    /* List of properties owned and properties mortgaged */

    private ArrayList<PropertySquare> propertiesOwned, propertiesMortgaged;

    /**
     *  This constructor initializes the bank with a specific set of properties,
     *  and uses the size of properties tocompute the size of the properties that can be mortgaged.
     * @param listOfProperties
     */
    public Bank(ArrayList<PropertySquare> listOfProperties) {
        /** initialises all variables */
        propertiesOwned = listOfProperties;
        propertiesMortgaged = new ArrayList<>(listOfProperties.size());
        numHotels = 16;
        numHouses = 16;
    }

    /****************************************************************************************************************
     * This method will return a specific property, based of its ID.
     *
     * @param property
     ***************************************************************************************************************/
    public PropertySquare giveProperty(PropertySquare property) {
        //removes and returns the specific property.
        return propertiesOwned.remove(property.getId());
    }

    /****************************************************************************************************************
     * This method will store the given property into the list of owned properties.
     *
     * @param property
     ***************************************************************************************************************/
    public void receiveProperty(PropertySquare property) {
        //Sets the owner of the property to null since to show.
        property.setOwner(null);

        //Adds the property to its list.
        propertiesOwned.add(property);
    }

    /****************************************************************************************************************
     * Subtracts the 1 from the numhouses
     ***************************************************************************************************************/
    public void giveHouse() {
        numHouses--;
    }

    /****************************************************************************************************************
     * Subtracts one from the numHotels
     ***************************************************************************************************************/
    public void giveHote() {
        numHotels--;
    }

    /****************************************************************************************************************
     * Adds to the numHouses
     ***************************************************************************************************************/
    public void receiveHouse() {
        numHouses++;
    }

    /****************************************************************************************************************
     * Adds to the numHotels
     ***************************************************************************************************************/
    public void receiveHotel() {
        numHotels++;
    }

    /****************************************************************************************************************
     * Returns the numHouses
     *
     * @return
     ***************************************************************************************************************/
    public int getNumHouses() {
        return numHouses;
    }

    /****************************************************************************************************************
     * Sets the numHouses
     *
     * @param numHouses
     ***************************************************************************************************************/
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    /****************************************************************************************************************
     * @return
     ***************************************************************************************************************/
    public int getNumHotels() {
        return numHotels;
    }

    /****************************************************************************************************************
     * @param numHotels
     ***************************************************************************************************************/
    public void setNumHotels(int numHotels) {
        this.numHotels = numHotels;
    }

    /****************************************************************************************************************
     * @return
     ***************************************************************************************************************/
    public ArrayList<PropertySquare> getPropertiesOwned() {
        return propertiesOwned;
    }

    /****************************************************************************************************************
     * @param propertiesOwned
     ***************************************************************************************************************/
    public void setPropertiesOwned(ArrayList<PropertySquare> propertiesOwned) {
        this.propertiesOwned = propertiesOwned;
    }

    /****************************************************************************************************************
     * @return
     ***************************************************************************************************************/
    public ArrayList<PropertySquare> getPropertiesMortgaged() {
        return propertiesMortgaged;
    }

    /****************************************************************************************************************
     * @param propertiesMortgaged
     ***************************************************************************************************************/
    public void setPropertiesMortgaged(ArrayList<PropertySquare> propertiesMortgaged) {
        this.propertiesMortgaged = propertiesMortgaged;
    }
}
