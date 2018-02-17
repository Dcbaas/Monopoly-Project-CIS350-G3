package Model.BoardPackage;

import GamePackage.Player;


/**********************************************************************
 * The UtilitiesSquare class is a BoardSquare class that defines the
 * BoardSquares that are properties. It tracks variables related to
 * properties such as the PRICE of purchase and the rent cost of
 * landing on this BoardSquare.
 *
 * @author Dustin Ecker
 * @version 2/16/2018
 *********************************************************************/
public class UtilitiesSquare extends BoardSquare {
	
	/** Holds the price to purchase property */
	private final int PRICE;
	
	/** Holds the value of mortgaging property */
	private final int MORTGAGE_VAL;
	
	/** Holds the current owner of property */
	private Player owner;
	
	/** Holds whether property is mortgaged or not */
	private boolean isMortgaged;
	
	/******************************************************************
	 * The constructor creates a UtilitiesSquare and sets all the 
	 * attributes related to it.
	 * @param name The name of the UtilitiesSquare.
	 * @param price The price to purchase this property.
	 * @param mortgageValue The value returned from mortgaging this
	 * property.
	 *****************************************************************/
	public UtilitiesSquare(String name, int price, int mortgageValue) {
		super(name);
		
		PRICE = price;
		MORTGAGE_VAL = mortgageValue;
		
		isMortgaged = false;
		owner = null;
	}

	/******************************************************************
	 * Getter method for owner variable.
	 * 
	 * @return owner The owner of this property.
	 *****************************************************************/
	public Player getOwner() {
		return owner;
	}

	/******************************************************************
	 * Setter method for owner variable.
	 * 
	 * @param owner The owner of this property.
	 *****************************************************************/
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/******************************************************************
	 * Getter method for isMortgaged variable.
	 * 
	 * @return isMortgaged Whether the property is mortgaged or not.
	 *****************************************************************/
	public boolean isMortgaged() {
		return isMortgaged;
	}

	/******************************************************************
	 * Setter method for isMortgaged variable.
	 * 
	 * @param isMortgaged Sets if property is mortgaged or not.
	 *****************************************************************/
	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	/******************************************************************
	 * Getter method for PRICE variable.
	 * 
	 * @return PRICE The price of this property.
	 *****************************************************************/
	public int getPRICE() {
		return PRICE;
	}

	/******************************************************************
	 * Getter method for MORTGAGE_VAL variable.
	 * 
	 * @return MORTGAGE_VAL The value of mortgaging this property.
	 *****************************************************************/
	public int getMORTGAGE_VAL() {
		return MORTGAGE_VAL;
	}
	
	/******************************************************************
	 * Method returns the rent cost of owning one UtilitiesSquare
	 * property.
	 * 
	 * @return rent Returns the diceRoll value multiplied by 4;
	 *****************************************************************/
	public int getRentOne(int diceRoll) {
		return diceRoll * 4;
	}
	
	/******************************************************************
	 * Method returns the rent cost of owning two UtilitiesSquare
	 * properties.
	 * 
	 * @return rent Returns the diceRoll value multiplied by 10;
	 *****************************************************************/
	public int getRentTwo(int diceRoll) {
		return diceRoll * 10;
	}
}
