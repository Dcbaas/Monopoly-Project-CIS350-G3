package Model.GamePackage;

import Model.BoardPackage.Board;
import Model.BoardPackage.BoardSquare;
import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import Model.BoardPackage.RailRoadSquare;
import Model.BoardPackage.UtilitiesSquare;
import Model.CardPackage.Card;
import Model.CardPackage.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**********************************************************************
 * The Game class is responsible for almost all of the game logic.
 * Any action that goes on in the game is handled here.
 *
 * @author Dylan Kernohan
 * @author Santiago Quiroga
 * @version 2/19/2018
 *********************************************************************/
public class Game {

  /**
   * The bank object used by the game
   */
  private Bank bank;

  /**
   * The list of players playing the game
   */
  private ArrayList<Player> players;

  /**
   * Dice 1 used in the game
   */
  private Die dieOne;

  /**
   * Dice 2 used in the game
   */
  private Die dieTwo;

  /**
   * The game board
   */
  private Board board;

  /**
   * The deck object that holds community chest cards
   */
  private Deck comunityChestDeck;

  /**
   * The deck object that holds chance cards
   */
  private Deck chanceDeck;

  /**
   * A variable to keep track of which players turn it is
   */
  private Player currentPlayer;

  /**********************************************************************
   * The constructor creates a game by creating the die, board,
   * decks/cards, players, and bank objects.
   *
   * @param boardFile The file that is used to generates the board.
   * @param communityChestDeckFile The file that is used to generate the
   *                               community chest deck.
   * @param chanceDeckFile The file that is used to generate the chance
   *                       deck.
   *********************************************************************/
  public Game(String boardFile, String communityChestDeckFile, String chanceDeckFile) {
    dieOne = new Die();
    dieTwo = new Die();
    board = new Board(boardFile);
    comunityChestDeck = new Deck(communityChestDeckFile, false);
    chanceDeck = new Deck(chanceDeckFile, true);
    players = new ArrayList<>();
    bank = new Bank(board.getOwnableSquares());
  }

  // Getters and Setters for Game variables. Mainly used so the Controller can get info from game elements ===========

  /**********************************************************************
   * This method gets the Bank object
   * @return Bank
   *********************************************************************/
  public Bank getBank() {
    return bank;
  }

  /**********************************************************************
   * This method sets the Bank object.
   * @param bank the bank inside the game class
   *********************************************************************/
  public void setBank(Bank bank) {
    this.bank = bank;
  }

  /**********************************************************************
   * This method gets the arraylist of players playing the game
   * @return the ArrayList of players
   *********************************************************************/
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**********************************************************************
   * This method sets the arraylist of players playing the game
   * @param players the new ArrayList of player in their respective order
   *********************************************************************/
  public void setPlayers(ArrayList<Player> players) {
    this.players = players;
  }

  /**********************************************************************
   * This method gets Die 1 for the Game
   * @return the dieOne Object
   *********************************************************************/
  public Die getDieOne() {
    return dieOne;
  }

  /**********************************************************************
   * This method sets Die 1 for the Game
   * @param dieOne the new dieOne Object
   *********************************************************************/
  public void setDieOne(Die dieOne) {
    this.dieOne = dieOne;
  }

  /**********************************************************************
   * This method gets Die 2 for the Game
   * @return The dieTwo Object
   *********************************************************************/
  public Die getDieTwo() {
    return dieTwo;
  }

  /**********************************************************************
   * This method sets Die 2 for the Game
   * @param dieTwo the new dieTwo Object
   *********************************************************************/
  public void setDieTwo(Die dieTwo) {
    this.dieTwo = dieTwo;
  }

  /**********************************************************************
   * This method gets the Game board
   * @return the Bard Object
   *********************************************************************/
  public Board getBoard() {
    return board;
  }

  /**********************************************************************
   * This method sets the Game board
   * @param board the new Board Object
   *********************************************************************/
  public void setBoard(Board board) {
    this.board = board;
  }

  /**********************************************************************
   * This method gets Game's the community chest deck
   * @return the comunity chest Deck Object
   *********************************************************************/
  public Deck getComunityChestDeck() {
    return comunityChestDeck;
  }

  /**********************************************************************
   * This method sets the Game's community chest deck
   * @param comunityChestDeck the new community chest Deck Object.
   *********************************************************************/
  public void setComunityChestDeck(Deck comunityChestDeck) {
    this.comunityChestDeck = comunityChestDeck;
  }

  /**********************************************************************
   * This method gets the Game's chance deck
   * @return the chance Deck Object
   *********************************************************************/
  public Deck getChanceDeck() {
    return chanceDeck;
  }

  /**********************************************************************
   * This method sets the Game's chance deck
   * @param chanceDeck the new cheance Deck Object
   *********************************************************************/
  public void setChanceDeck(Deck chanceDeck) {
    this.chanceDeck = chanceDeck;
  }

  /**********************************************************************
   * This method gets the Game's current player
   * (The player whos turn it is)
   * @return The current Player
   *********************************************************************/
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**********************************************************************
   * This method sets the Game's current player
   * (The player whos turn it is)
   * @param currentPlayer the new current Player
   *********************************************************************/
  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  // Game logic methods ==============================================================================================

  /**********************************************************************
   * Rolls both die
   *********************************************************************/
  public void rollDice() {
    dieOne.roll();
    dieTwo.roll();
  }

  /**********************************************************************
   * Creates a player object with the provided name and token
   * and adds to the arraylist of players in the game
   * @param name The display name the player chooses
   * @param token The token the player chooses
   *********************************************************************/
  public void addPlayer(String name, String token) {
    players.add(new Player(name, token, 1500));
  }

  /**********************************************************************
   * Remove a player from the list when they lose
   * @param player The player that needs to be removed
   **********************************************************************/
  public void removePlayer(Player player) {
    players.remove(player);

  }

  /**********************************************************************
   * Set the current player to the one whos turn is next
   *********************************************************************/
  public void nextTurn() {
    // Determine if current player is last one in list. If so, set current back to first player
    if (players.indexOf(currentPlayer) + 1 != players.size()) {
      currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
    } else {
      currentPlayer = players.get(0);
    }
  }

  /**********************************************************************
   *  Shuffles the players =to emulate dies rolling fro turns
   *********************************************************************/
  public void setPlayerOrder() {
    Collections.shuffle(players);
  }

  /**********************************************************************
   * When a player wants to buy a house. Returns true when the player
   * has enough money. False if they do not.
   * @param ownableSquare The ownable square the player wants to buy
   * @param buyer The player trying to buy
   * @return wetherthe action was able to be completed succesfully or not
   *********************************************************************/
  public boolean buyOwnableSquare(OwnableSquare ownableSquare, Player buyer, int price) {
    // Check is buyer has enough money
    if (buyer.getWallet() >= ownableSquare.getPRICE() && ownableSquare.getOwner() == null) {
      buyer.recieveProperty(bank.giveProperty(ownableSquare));
      buyer.pay(ownableSquare.getPRICE());
      ownableSquare.setOwner(buyer);

      int groupCounter = 0;
      // Check if player now owns group and set it
      for (OwnableSquare square : currentPlayer.getPropertiesOwned()) {
        if (square.getGROUP_NUMBER() == ownableSquare.getGROUP_NUMBER()) {
          groupCounter++;
        }
      }

      if (ownableSquare.getGROUP_NUMBER() == 1 || ownableSquare.getGROUP_NUMBER() == 8) {
        if (groupCounter == 2) {
          currentPlayer.addGroupOwned(ownableSquare.getGROUP_NUMBER());
        }
      } else {
        if (groupCounter == 3) {
          currentPlayer.addGroupOwned(ownableSquare.getGROUP_NUMBER());
        }
      }
      return true;
    }
    return false;
  }

  /**********************************************************************
   * This method takes the die params and moves the player that many
   * squares.
   * @param player The player that is moving
   * @param dieVal1 The value of die 1 after it is rolled
   * @param dieVal2 The value of die 2 after it is rolled
   *********************************************************************/
  public void movePlayer(Player player, int dieVal1, int dieVal2) {
        /*
        NOTE: The assumption here is that the die will be rolled by the
                view/controller with an animation and then the
                view/controller will call this passing the die values
              The other option is we call roll for both die here and the
                view/controller simply call this after a "move" button is
                clicked. Not a "roll" and die animation.
         */
    // Check if player will be passing GO square
    if (player.getPosition() + (dieVal1 + dieVal2) > 39) {
      // New position = old position + dieVals - numberOfSquares
      board.setPlayerPosition(player, (player.getPosition() + (dieVal1 + dieVal2)) - 40);
      player.setPosition((player.getPosition() + (dieVal1 + dieVal2)) - 40);
      player.receiveMoney(200);
    } else {
      //Sets the player position
      board.setPlayerPosition(player, player.getPosition() + (dieVal1 + dieVal2));
      player.setPosition(player.getPosition() + (dieVal1 + dieVal2));
    }
  }

  /**********************************************************************
   * One player sells one of their ownableSquares to another player
   * @param ownableSquare The ownable square being sold
   * @param buyer The player buying the ownable square
   * @return whether the action was able to be completed )
   *********************************************************************/
  public boolean sellOwnableSquare(OwnableSquare ownableSquare, Player buyer) {
    // Check if buyer paid full amount
    if (buyer.pay(ownableSquare.getPRICE()) == ownableSquare.getPRICE()) {
      // Buyer receives ownableSquare, Owner gives it.
      buyer.recieveProperty(ownableSquare.getOwner().giveProperty(ownableSquare));
    } else {
      return false;
    }
    return true;
  }

  /**********************************************************************
   * Sets the given property to mortgaged and pays the owner the respective
   * amount.
   *
   * @param property the property that is about to be mortgaged
   *********************************************************************/
  public void mortgageProperty(OwnableSquare property) {
    property.setMortgaged(true);
    property.getOwner().receiveMoney(property.getMORTGAGE_VAL());
  }

  /**********************************************************************
   * This methods allows a player to the property mortgage and set the
   * mortgage property to false and discount the money from the player's
   * wallet.
   *
   * @param propertySquare the property that the mortgage is being lifted.
   *********************************************************************/
  public void payMortgage(OwnableSquare propertySquare) {
    propertySquare.setMortgaged(false);
    propertySquare.getOwner().pay((int) Math.ceil(propertySquare.getMORTGAGE_VAL() * 1.1));
  }


  /**********************************************************************
   * This method will give the current player a card from the given deck
   * true = chance
   * false = community
   * @param deckType true or false acording to the decktype
   * @return The card that was drawn
   *********************************************************************/
  public Card drawCard(Boolean deckType) {
    //Checks for the deckType
      Card card;
      if(deckType == true){
          card = chanceDeck.drawCard();
      }
      else{
          card = comunityChestDeck.drawCard();
      }

      // Gives player "get out of jail free" card, uses all others right away.
    if(card.getCardDescription().equals("Get out of Jail Free Card")){
          currentPlayer.recieveCard(card);
    }
    else{
          useCard(card);

    }

    return card;
  }


  /**********************************************************************
   * This method checks for the list of actions that the card has and
   * performs the necessary actions.
   *
   * @param cardToUse the card that is going to be used
   * @return true if action was success, false if it was not.
   *********************************************************************/
  public boolean useCard(Card cardToUse) {
    boolean actionSuccess = true;

    // Retrieve the instructions from the card
    int[] actions = cardToUse.getActions();

    //Checks which instructions need to be performed
    if (actions[0] != -1) {
      cardCollect(actions[0]);
    }
    if (actions[1] != -1) {
      cardMovePosition(actions[1]);
    }
    if (actions[2] != -1) {
      cardMoveNearest(actions[2]);
    }
    if (actions[3] != -1) {
      cardEscapeFromJail(currentPlayer);
    }
    if (actions[4] != -1) {
      cardMoveBack(actions[4]);
    }
    if (actions[5] != -1) {
      actionSuccess = !(actionSuccess && !cardTax(currentPlayer)) && actionSuccess;
    }
    if (actions[6] != -1) {
      actionSuccess = !(actionSuccess && !cardPayBank(actions[6])) && actionSuccess;
    }
    if (actions[7] != -1) {
      actionSuccess = !(actionSuccess && !cardPayAllPlayers(actions[7])) && actionSuccess;
    }
    if (actions[8] != -1) {
      actionSuccess = !(actionSuccess && !cardCollectFromPlayers(actions[8])) && actionSuccess;
    }
    return actionSuccess;
  }

  /**********************************************************************
   * This method collects a specific amount of money from a specific player
   * and pays another player.
   *
   * @param unfortunateSoul the player that has to pay the fee
   * @param fortunateSoul the player that is collecting the fee
   * @param fee the amount to be paid
   * @return boolean whether the action was fully proceed
   *********************************************************************/
  public boolean collectFee(Player unfortunateSoul, Player fortunateSoul, int fee) {
    //Check if player can pay fee
    if (unfortunateSoul.getWallet() - fee >= 0 && fortunateSoul != null) {
      //collect the given fee from player / bank
      unfortunateSoul.pay(fee);
      //pay the collected fee to player/ bank
      fortunateSoul.setWallet(fortunateSoul.getWallet() + fee);
    } else if (unfortunateSoul.getWallet() - fee >= 0 && fortunateSoul == null) {
      unfortunateSoul.pay(fee);
    } else {
      return false;
    }

    return true;
  }

  // Card Actions ====================================================================================================


  /**********************************************************************
   * Pays a player a specific amount of money.
   *
   * @param amount the amount of money the player will collect
   *********************************************************************/
  private void cardCollect(int amount) {
    currentPlayer.receiveMoney(amount);
  }


  /**********************************************************************
   * Moves a player to the given position
   *
   * @param position the position the player will be moved to
   *********************************************************************/
  private void cardMovePosition(int position) {
    board.setPlayerPosition(currentPlayer, position);
    currentPlayer.setPosition(position);
  }

  /**********************************************************************
   * Moves a player to the nearest position of the specific BoardSquare
   * type.
   *
   * @param typeID the type of the BoardSquare that the player has to be
   *               moved to
   *********************************************************************/
  private void cardMoveNearest(int typeID) {
    //Splits the board into two halves.
    int midPoint = board.getSquaresList().size() / 2;

    //Retrieves all the squares from the board.
    List<BoardSquare> boardSquares = board.getSquaresList();

    //Create two counters to count the distance from both paths.
    int negativeMoves = 1, positiveMoves = 1;

    //Creates two pointers that start right where the player is at.
    int negativePointer = currentPlayer.getPosition(), positivePointer = currentPlayer
        .getPosition();

    //The closest board square.
    BoardSquare closestSquare = boardSquares.get(currentPlayer.getPosition());

    //Checks lef and right side until finding the closest square.
    while (negativeMoves <= midPoint && positiveMoves <= midPoint) {

      //Check if either pointer will go over the limits of the board
      negativePointer =
          (negativePointer - 1 == 0) ? board.getSquaresList().size() - 1 : negativePointer;
      positivePointer =
          (positivePointer + 1 == board.getSquaresList().size() - 1) ? 0 : positivePointer;

      //Check if the negative pointer to see if the current location matches the desired square
      if (boardSquares.get(negativePointer).getType() == typeID) {
        closestSquare = boardSquares.get(negativePointer);
        break;
      }

      //Check if the positive pointer to see if the current location matches the desired square
      if (boardSquares.get(positivePointer).getType() == typeID) {
        closestSquare = boardSquares.get(positivePointer);
        break;
      }

      //Increases the pointers
      positivePointer++;
      negativePointer--;
    }

    //Moves the player to the nearest specific location.
    board.setPlayerPosition(currentPlayer, closestSquare.getPOSITION());
    currentPlayer.setPosition(closestSquare.getPOSITION());
  }

  /**********************************************************************
   * Sets a player inJail status to -1, so the player can escape jail
   *
   * @param player the player that is escaping from jail
   *********************************************************************/
  private void cardEscapeFromJail(Player player) {
    player.setInJail(-1);
  }

  /**********************************************************************
   * Moves a player back numSquares
   *
   * @param numSquares the amount of squares the player is moving back
   *********************************************************************/
  private void cardMoveBack(int numSquares) {
    //Checks if the number of steps that the player has to move back are beyond th Go square
    int newPosition = (currentPlayer.getPosition() - numSquares > 0) ?
        currentPlayer.getPosition() - numSquares :
        currentPlayer.getPosition() - numSquares + board.getSquaresList().size() - 1;

    //Set the player's new position
    board.setPlayerPosition(currentPlayer, newPosition);
    currentPlayer.setPosition(newPosition);
  }

  /**********************************************************************
   * It collects a tax from a the given player. For every house the player
   * owns, the player will pay 40$, and for every hotel the player owns,
   * the player will pa 115$
   *
   * @param player the player that is being taxed.
   *********************************************************************/
  private boolean cardTax(Player player) {
    int amountDue = 0;

    //goes through all properties that have a house or a hotel and calculates the amount due
    for (PropertySquare propertySquare : player.getOwnableProperties().stream()
        .filter(property -> property.getType() == 0).map(property -> (PropertySquare) property)

        .filter(lambdaPropertySquare -> lambdaPropertySquare.isHasHotel()
            || lambdaPropertySquare.getNumHouses() > 0)

        .collect(Collectors.toCollection(ArrayList<PropertySquare>::new))) {
      amountDue += (propertySquare.isHasHotel()) ? 115 : 40;
    }

    //Checks if the player will be able to pay the fee
    if (player.getWallet() >= amountDue) {
      player.pay(amountDue);
    } else {
      return false;
    }

    return true;
  }

  /**********************************************************************
   * This actions collect a specific amount of money fro the player
   * if the player cannot pay the full amount then the method will return
   * false
   * @param amount The amount of money tha player will have to pay.
   * @return whether the player was able to pay the bank or not.
   *********************************************************************/
  private boolean cardPayBank(int amount) {
    //Checks if the player can pay the amount due.
    if (currentPlayer.getWallet() >= amount) {
      currentPlayer.pay(amount);
      return true;
    }

    return false;
  }

  /**********************************************************************
   * This actions enforces that a player pays every palyer an specific
   * amount of money. If the player doesn't have the money to pay every player,
   * then the method will return false.
   * @param amount the amount the palyer has to pay each player
   * @return whether the action was able to be completed or not
   *********************************************************************/
  private boolean cardPayAllPlayers(int amount) {
    //Checks if the player can pay all player
    if (currentPlayer.getWallet() < amount * (players.size() - 1)) {
      players.stream().filter(player -> player != currentPlayer).forEach(player ->
          player.receiveMoney(currentPlayer.pay(amount)));
      return true;
    } else {
      return false;
    }
  }

  /**********************************************************************
   * This actions will collect the given amount from each player, if a player
   * cannot pay the specific amount the method will return false.
   * @param amount the amount each player has to pay the current player
   * @return whether the actions was able to be completed
   *********************************************************************/
  private boolean cardCollectFromPlayers(int amount) {
    //Goes through the list of other players
    for (Player player : players.stream().filter(lambdaPlayer -> lambdaPlayer != currentPlayer)

        .collect(Collectors.toCollection(ArrayList<Player>::new))) {
      //checks if the given player will be able to pay the given amount.
      if (player.getWallet() >= amount) {
        currentPlayer.receiveMoney(player.pay(amount));
      } else {
        return false;
      }
    }

    return true;
  }

  /**********************************************************************
   * Returns true if the current value on dieOne equals the current
   * value of dieTwo.
   *
   * @return Whether both dies match or not.
   *********************************************************************/
  public boolean rolledPair() {
    return dieTwo.value == dieOne.value;
  }

  /**********************************************************************
   * Sends the current player to jail/
   *********************************************************************/
  public void sendPlayerToJail() {
    board.sendToJail(currentPlayer);
    currentPlayer.setPosition(10);
  }

  /*********************************************************************
   * This method checks if the sqaure passed in is ownable
   *
   * @param boardSquare The square being checked
   * @return The ownableSqure if it is ownable. Null if it is not
   *********************************************************************/
  public OwnableSquare checkIfOwnable(BoardSquare boardSquare) {
    if (board.getOwnableSquares().contains(boardSquare)) {
      return (OwnableSquare) boardSquare;
    } else {
      return null;
    }
  }

  /**********************************************************************
   * This method gets the current players location
   *
   * @return The current players location
   *********************************************************************/
  public BoardSquare getCurrentPlayerLocation() {
    return board.getSquaresList().get(currentPlayer.getPosition());
  }

  /**********************************************************************
   * this method collect the correct amount form the currentPlayer
   * @return the amount the player was taxed
   *********************************************************************/
  public int collectFee() {
    Random random = new Random();

    int rent = 0;

    switch (board.getOwnableSquare(currentPlayer.getPosition()).getType()) {

      case 0:
        PropertySquare ownableSquare = (PropertySquare) board
            .getOwnableSquare(currentPlayer.getPosition());
        rent = ownableSquare.getCurrentRent();
        collectFee(currentPlayer, ownableSquare.getOwner(), rent);
        break;
      case 1:

        RailRoadSquare railRoadSquare = (RailRoadSquare) board
            .getOwnableSquare(currentPlayer.getPosition());

        switch (currentPlayer.getNumPropertiesOwnedByType(1)) {
          case 1:
            rent = railRoadSquare.getBASE_RENT();
            break;
          case 2:
            rent = railRoadSquare.getTWO_RENT();
            break;
          case 3:
            rent = railRoadSquare.getTHREE_RENT();
            break;
          case 4:
            rent = railRoadSquare.getFOUR_RENT();
        }
        collectFee(currentPlayer, railRoadSquare.getOwner(), rent);
      case 3:
        UtilitiesSquare utilitiesSquare = (UtilitiesSquare) board
            .getOwnableSquare(currentPlayer.getPosition());
        rent = (currentPlayer.getNumPropertiesOwnedByType(3) > 1) ? utilitiesSquare
            .getRentOne(random.nextInt(6) + 1) : utilitiesSquare.getRentTwo(random.nextInt(6) + 1);
        collectFee(currentPlayer, utilitiesSquare.getOwner(), rent);


    }
    return rent;
  }

  /**********************************************************************
   * This method checks whether the current player should be taxed by
   * checking its current position and its owner. If the current position
   * is owned by the bank (null), then the player should not be taxex.
   * @return Whether the player should be taxed
   *********************************************************************/
  public boolean shouldPlayerBeTaxed() {
    //Checks if the player is at an OwnableSquare
    if (board.isSquareOwnable(currentPlayer.getPosition())) {
      //Checks if the bank owns the square
      if (board.getOwnableSquare(currentPlayer.getPosition()).getOwner() != null)
      //Checks if the current player is the owner of the square he is standing at.
      {
        return !board.getOwnableSquare(currentPlayer.getPosition()).getOwner()
            .equals(currentPlayer);
      }
    }

    return false;
  }
}
