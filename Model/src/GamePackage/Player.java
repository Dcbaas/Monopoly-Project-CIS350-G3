package GamePackage;

import java.util.ArrayList;

import BoardPackage.BoardSquare;
import BoardPackage.PropertySquare;
import CardPackage.Card;

/**********************************************************************
 * The Player class will keep track of each player's money, properties,
 * and other stats.
 * 
 * @author Dustin Ecker
 * @version 2/14/2018
 *********************************************************************/
public class Player {
	
	/** Player's name */
	private final String DISPLAY_NAME;
	
	/** Player's token */
	private final String TOKEN;
	
	/** Tells if player can move */
	private boolean canMove;
	
	/** Tells if player is bankrupt */
	private boolean isBankrupt;
	
	/** Holds player's current wallet balance */
	private int wallet;
	
	/** ArrayList that holds player's owned properties */
	private ArrayList<BoardSquare> propertiesOwned;
	
	/** ArrayList that holds player's cards held */
	private ArrayList<Card> cardsHeld;
	
	/******************************************************************
	 * Constructor method for Player class.
	 * 
	 * @param displayName Sets the player's name.
	 * @param token Sets the player's chosen token.
	 * @param wallet Sets the player's starting wallet value.
	 *****************************************************************/
	public Player(String displayName, String token, int wallet) {
		DISPLAY_NAME = displayName;
		TOKEN = token;
		this.wallet = wallet;
		
		canMove = true;
		isBankrupt = false;
		
		propertiesOwned = new ArrayList<BoardSquare>();
		cardsHeld = new ArrayList<Card>();
	}

	/******************************************************************
	 * Getter method for displayName variable.
	 * 
	 * @return displayName Returns the String displayName.
	 *****************************************************************/
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	/******************************************************************
	 * Getter method for token variable.
	 * 
	 * @return token Returns the String token.
	 *****************************************************************/
	public String getToken() {
		return TOKEN;
	}

	/******************************************************************
	 * Getter method for canMove variable.
	 * 
	 * @return canMove Returns the boolean canMove.
	 *****************************************************************/
	public boolean canMove() {
		return canMove;
	}

	/******************************************************************
	 * Setter method for canMove variable.
	 * 
	 * @param canMove Sets the boolean canMove.
	 *****************************************************************/
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	/******************************************************************
	 * Getter method for isBankrupt variable.
	 * 
	 * @return isBankrupt Returns the boolean isBankrupt.
	 *****************************************************************/
	public boolean isBankrupt() {
		return isBankrupt;
	}

	/******************************************************************
	 * Setter method for isBankrupt variable.
	 * 
	 * @param isBankrupt Sets the boolean isBankrupt.
	 *****************************************************************/
	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	/******************************************************************
	 * Getter method for wallet variable.
	 * 
	 * @return wallet Returns the int wallet.
	 *****************************************************************/
	public int getWallet() {
		return wallet;
	}

	/******************************************************************
	 * Setter method for wallet variable.
	 * 
	 * @param wallet Sets the int wallet.
	 *****************************************************************/
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	/******************************************************************
	 * Getter method for propertiesOwned ArrayList.
	 * 
	 * @return propertiesOwned Returns the ArrayList propertiesOwned.
	 *****************************************************************/
	public ArrayList<BoardSquare> getPropertiesOwned() {
		return propertiesOwned;
	}

	/******************************************************************
	 * Setter method for propertiesOwned ArrayList.
	 * 
	 * @param propertiesOwned Sets the ArrayList propertiesOwned.
	 *****************************************************************/
	public void setPropertiesOwned(ArrayList<BoardSquare> 
	propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}

	/******************************************************************
	 * Getter method for cardsHeld ArrayList.
	 * 
	 * @return cardsHeld Returns the ArrayList cardsHeld.
	 *****************************************************************/
	public ArrayList<Card> getCardsHeld() {
		return cardsHeld;
	}

	/******************************************************************
	 * Setter method for cardsHeld ArrayList.
	 * 
	 * @param cardsHeld Sets the ArrayList cardsHeld.
	 *****************************************************************/
	public void setCardsHeld(ArrayList<Card> cardsHeld) {
		this.cardsHeld = cardsHeld;
	}
	
	/******************************************************************
	 * Checks to make sure player has sufficient funds before
	 * subtracting amount from wallet. If the player has sufficient
	 * funds the method subtracts amount from wallet and returns the
	 * amount paid. If player doesn't have sufficient funds he method
	 * subtracts what is able to be paid from wallet and returns the
	 * amount that has been paid.
	 * 
	 * @param amount The amount of money requested from player.
	 * @return amountPaid The amount of money that was paid by player.
	 *****************************************************************/
	public int pay(int amount) {
		int amountPaid = 0;
		
		if(wallet >= amount) {
			wallet -= amount;
			amountPaid = amount;
		}
		else {
			amountPaid = wallet;
			wallet -= amountPaid;
		}
		
		return amountPaid;
	}
	
	/******************************************************************
	 * Removes the property from the player's ArrayList of properties
	 * owned.
	 * 
	 * @param property The property to be removed from owned properties
	 * list.
	 *****************************************************************/
	public void sell(BoardSquare property) {
		propertiesOwned.remove(property);
	}
	
	/******************************************************************
	 * Mortgages the player's property and adds the mortgage value to
	 * their wallet.
	 * 
	 * @param property The property to be mortgaged.
	 *****************************************************************/
	public void mortgage(BoardSquare property) {
		wallet += property.getMortgageValue();
		property.setIsMortgaged(true);
	}
	
	/******************************************************************
	 * Removes the property from the player's ArrayList of properties
	 * owned.
	 * 
	 * @param property The property to be removed from owned properties
	 * list.
	 *****************************************************************/
	public void giveProperty(BoardSquare property) {
		propertiesOwned.remove(property);
	}
	
	/******************************************************************
	 * Adds the property to the player's ArrayList of properties
	 * owned.
	 * 
	 * @param property The property to be added to owned properties
	 * list.
	 *****************************************************************/
	public void recieveProperty(BoardSquare property) {
		propertiesOwned.add(property);
	}
	
	/******************************************************************
	 * Builds a house on the property from the players ArrayList of 
	 * properties owned.
	 * 
	 * @param property The property that the house will be built on.
	 *****************************************************************/
	public void buildHouse(PropertySquare property) {
		property.setNumHouses(property.getNumHouses() + 1);
	}
	
	/******************************************************************
	 * Builds a hotel on the property from the players ArrayList of 
	 * properties owned.
	 * 
	 * @param property The property that the hotel will be built on.
	 *****************************************************************/
	public void buildHotel(PropertySquare property) {
		property.setNumHotels(1);
	}
	
}
