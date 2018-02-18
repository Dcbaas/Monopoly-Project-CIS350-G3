package Model.BoardPackage;

/**********************************************************************
 * The UtilitiesSquare class is a BoardSquare class that defines the
 * BoardSquares that are properties. It tracks variables related to
 * properties such as the PRICE of purchase and the rent cost of
 * landing on this BoardSquare.
 *
 * @author Dustin Ecker David Baas
 * @since 2/16/2017
 * @version 2/17/2018
 *********************************************************************/
public class UtilitiesSquare extends OwnableSquare {

	/******************************************************************
	 * The constructor creates a UtilitiesSquare and sets all the 
	 * attributes related to it.
	 * @param name The name of the UtilitiesSquare.
	 * @param price The price to purchase this property.
	 * @param mortgageValue The value returned from mortgaging this
	 * property.
	 *****************************************************************/
	public UtilitiesSquare(String name, int postion, int price, int mortgageValue, int groupNumber) {
		super(name, postion, price, mortgageValue, groupNumber);
		type = 3;

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
