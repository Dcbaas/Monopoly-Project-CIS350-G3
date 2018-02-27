package Model.GamePackage;

import Model.BoardPackage.*;
import Model.CardPackage.Card;
import Model.CardPackage.Deck;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**********************************************************************
 * The Game class is responsible for almost all of the game logic.
 * Any action that goes on in the game is handled here.
 *
 * @author Dylan Kernohan
 * @version 2/19/2018
 *********************************************************************/
public class Game {

    /**The bank object used by the game*/
    private Bank bank;

    /**The list of players playing the game*/
    private ArrayList<Player> players;

    /**Dice 1 used in the game*/
    private Die dieOne;

    /**Dice 2 used in the game*/
    private Die dieTwo;

    /**The game board*/
    private Board board;

    /**The deck object that holds community chest cards*/
    private Deck comunityChestDeck;

    /**The deck object that holds chance cards*/
    private Deck chanceDeck;

    /**A variable to keep track of which players turn it is*/
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
     * @return
     *********************************************************************/
    public Bank getBank() {
        return bank;
    }

    /**********************************************************************
     * This method sets the Bank object.
     * @param bank
     *********************************************************************/
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**********************************************************************
     * This method gets the arraylist of players playing the game
     * @return
     *********************************************************************/
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**********************************************************************
     * This method sets the arraylist of players playing the game
     * @param players
     *********************************************************************/
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**********************************************************************
     * This method gets Die 1 for the Game
     * @return
     *********************************************************************/
    public Die getDieOne() {
        return dieOne;
    }

    /**********************************************************************
     * This method sets Die 1 for the Game
     * @param dieOne
     *********************************************************************/
    public void setDieOne(Die dieOne) {
        this.dieOne = dieOne;
    }

    /**********************************************************************
     * This method gets Die 2 for the Game
     * @return
     *********************************************************************/
    public Die getDieTwo() {
        return dieTwo;
    }

    /**********************************************************************
     * This method sets Die 2 for the Game
     * @param dieTwo
     *********************************************************************/
    public void setDieTwo(Die dieTwo) {
        this.dieTwo = dieTwo;
    }

    /**********************************************************************
     * This method gets the Game board
     * @return
     *********************************************************************/
    public Board getBoard() {
        return board;
    }

    /**********************************************************************
     * This method sets the Game board
     * @param board
     *********************************************************************/
    public void setBoard(Board board) {
        this.board = board;
    }

    /**********************************************************************
     * This method gets Game's the community chest deck
     * @return
     *********************************************************************/
    public Deck getComunityChestDeck() {
        return comunityChestDeck;
    }

    /**********************************************************************
     * This method sets the Game's community chest deck
     * @param comunityChestDeck
     *********************************************************************/
    public void setComunityChestDeck(Deck comunityChestDeck) {
        this.comunityChestDeck = comunityChestDeck;
    }

    /**********************************************************************
     * This method gets the Game's chance deck
     * @return
     *********************************************************************/
    public Deck getChanceDeck() {
        return chanceDeck;
    }

    /**********************************************************************
     * This method sets the Game's chance deck
     * @param chanceDeck
     *********************************************************************/
    public void setChanceDeck(Deck chanceDeck) {
        this.chanceDeck = chanceDeck;
    }

    /**********************************************************************
     * This method gets the Game's current player
     * (The player whos turn it is)
     * @return
     *********************************************************************/
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**********************************************************************
     * This method sets the Game's current player
     * (The player whos turn it is)
     * @param currentPlayer
     *********************************************************************/
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    // Game logic methods ==============================================================================================

    /**********************************************************************
     * Rolls both die
     *********************************************************************/
    public void rollDie() {
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
        }
        else {
            currentPlayer = players.get(0);
        }
    }

    /**********************************************************************
     *  Shuffles the players =to emulate dies rolling fro turns
     *********************************************************************/
    public void setPlayerOrder(){
        Collections.shuffle(players);
    }

    /**********************************************************************
     * When a player wants to buy a house. Returns true when the player
     * has enough money. False if they do not.
     * @param ownableSquare The ownable square the player wants to buy
     * @param buyer The player trying to buy
     * @return
     *********************************************************************/
    public boolean buyOwnableSquare(OwnableSquare ownableSquare, Player buyer, int  price) {
        // Check is buyer has enough money
        if(buyer.getWallet() >= ownableSquare.getPRICE()) {
            // Another player owns the property
            if (ownableSquare.getOwner() != null) {
                buyer.recieveProperty(ownableSquare.getOwner().giveProperty(ownableSquare));
                buyer.pay(price);
            }
            // The bank owns the property
            else {
                buyer.recieveProperty(bank.giveProperty(ownableSquare));
            }
            buyer.pay(ownableSquare.getPRICE());
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
        if(player.getPosition() + (dieVal1 + dieVal2) > 39){
            // New position = old position + dieVals - numberOfSquares
            board.setPlayerPosition(player, (player.getPosition() + (dieVal1 + dieVal1)) - 40);
            player.setPosition((player.getPosition() + (dieVal1 + dieVal1)) - 40);
            player.receiveMoney(200);
        }
        board.setPlayerPosition(player, player.getPosition() + (dieVal1 + dieVal1));
        player.setPosition(player.getPosition() + (dieVal1 + dieVal1));

    }

    /**********************************************************************
     * One player sells one of their ownableSquares to another player
     * @param ownableSquare The ownable square being sold
     * @param buyer The player buying the ownable square
     *********************************************************************/
    public void sellOwnableSquare(OwnableSquare ownableSquare, Player buyer) {
        // Check if buyer paid full amount
        if( buyer.pay(ownableSquare.getPRICE()) == ownableSquare.getPRICE()){
            // Buyer receives ownableSquare, Owner gives it.
            buyer.recieveProperty(ownableSquare.getOwner().giveProperty(ownableSquare));
        }
        else{
            // TODO: Call a function that handles when a buyer only has partial (if any) of the money during a trade
        }
    }

    /**********************************************************************
     * Sets the given property to mortgaged and pays the owner the respective
     * amount.
     *
     * @param property
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
     * @param propertySquare
     *********************************************************************/
    public void payMortgage(PropertySquare propertySquare){
       propertySquare.setMortgaged(false);
       propertySquare.getOwner().pay((int)Math.ceil(propertySquare.getMORTGAGE_VAL() * 1.1));
     }


    /**********************************************************************
     * This method will give the current player a card from the given deck
     *
     * @param deckType
     *********************************************************************/
    public void drawCard(Boolean deckType) {
            //Checks for the deckType and gives the player a card from hte specific type of deck
            currentPlayer.recieveCard((deckType)?
                    chanceDeck.drawCard():comunityChestDeck.drawCard());
    }


    /**********************************************************************
     * This method checks for the list of actions that the card has and
     * performs the necessary actions.
     *
     * @param cardToUse
     *********************************************************************/
    public boolean useCard(Card cardToUse) {

        // Retrieve the instructions from the card
        int[] actions = cardToUse.getActions();

        //Checks which instructions need to be performed
        if (actions[0] != -1){
            cardCollect(actions[0]);
        }
        if (actions[1] != -1){
            cardMovePosition(actions[1]);
        }
        if (actions[2] != -1){
            cardMoveNearest(actions[2]);
        }
        if (actions[3] != -1){
            cardEscapeFromJail(currentPlayer);
        }
        if (actions[4] != -1){
            cardMoveBack(actions[4]);
        }
        if (actions[5] != -1){
            if (!cardTax(currentPlayer)){
                return false;
            }
        }
        if (actions[6] != -1){
            cardPayBank(actions[6]);
        }
        if (actions[7] != -1){
            if (!cardPayAllPlayers(actions[7])){
                return false;
            }
        }
        if (actions[8] != -1){
            if (cardCollectFromPlayers(actions[8])){
                return false;
            }
        }
        return true;
    }

    /**********************************************************************
     * This method collects a specific amount of money from a specific player
     * and pays another player.
     *
     * @param unfortunateSoul
     * @param fortunateSoul
     * @param fee
     *********************************************************************/
    public void collectFee(Player unfortunateSoul, Player fortunateSoul,int fee) {
        //Check if player can pay fee
        if (unfortunateSoul.getWallet() - fee >= 0 && fortunateSoul != null) {
            //collect the given fee from player / bank
            unfortunateSoul.pay(fee);
            //pay the collected fee to player/ bank
            fortunateSoul.setWallet(fortunateSoul.getWallet() + fee);
        }else if (unfortunateSoul.getWallet() - fee >= 0 &&fortunateSoul == null){
            unfortunateSoul.pay(fee);
        }
        else{
            System.out.println("should allow players to make deals or go bankrupt");// TODO: implement this logic
        }
    }

    // Card Actions ====================================================================================================


    /**********************************************************************
     * Pays a player a specific amount of money.
     *
     * @param amount
     *********************************************************************/
    public void cardCollect(int amount){
        currentPlayer.receiveMoney(amount);
    }


    /**********************************************************************
     * Moves a player to the given position
     *
     * @param position
     *********************************************************************/
    public void cardMovePosition(int position){
        board.setPlayerPosition(currentPlayer,position);
        currentPlayer.setPosition(position);
    }

    /**********************************************************************
     * Moves a player to the nearest position of the specific BoardSquare
     * type.
     *
     * @param typeID
     *********************************************************************/
    public void cardMoveNearest(int typeID){
        //Splits the board into two halves.
        int midPoint = board.getSquaresList().size() / 2;

        //Retrieves all the squares from the board.
        List<BoardSquare> boardSquares = board.getSquaresList();

        //Create two counters to count the distance from both paths.
        int negativeMoves = 1, positiveMoves = 1;


        //Creates two pointers that start right where the player is at.
        int negativePointer = currentPlayer.getPosition() , positivePointer = currentPlayer.getPosition();

        //The closest board square.
        BoardSquare closestSquare = boardSquares.get(currentPlayer.getPosition());

        //Checks lef and right side until finding the closest square.
        while (negativeMoves <= midPoint && positiveMoves <= midPoint){

            //Check if either pointer will go over the limits of the board
            negativePointer = (negativePointer - 1 == 0)? board.getSquaresList().size() - 1: negativePointer;
            positivePointer = (positivePointer + 1 == board.getSquaresList().size() - 1)? 0: positivePointer;


            //Check if the negative pointer to see if the current location matches the desired square
            if(boardSquares.get(negativePointer).getType() == typeID){
                closestSquare = boardSquares.get(negativePointer);
                break;
            }

            //Check if the positive pointer to see if the current location matches the desired square
            if(boardSquares.get(positivePointer).getType() == typeID){
                closestSquare = boardSquares.get(positivePointer);
                break;
            }

            //Increases the pointers
            positivePointer ++;
            negativePointer --;
        }

        //Moves the player to the nearest specific location.
        board.setPlayerPosition(currentPlayer, closestSquare.getPOSITION());
        currentPlayer.setPosition(closestSquare.getPOSITION());

        if ((board.getLocationType(currentPlayer.getPosition()) == 1 ||  0 || 3)){
            if (board.getOwnableSquare(currentPlayer.getPosition()).getOwner() != currentPlayer)
                collectFee(currentPlayer, ); //TODO: finish this logic
        }
    }

    /**********************************************************************
     * Sets a player inJail status to -1, so the player can scape jail
     *
     * @param player
     *********************************************************************/
    public void cardEscapeFromJail(Player player){
        player.setInJail(-1);
        //TODO:  NOTE: Do we need to allow the player to roll right away ? YES
    }

    /**********************************************************************
     * Moves a player back numSquares
     *
     * @param numSquares
     *********************************************************************/
    public void cardMoveBack(int numSquares){
        //Checks if the number of steps that the player has to move back are beyond th Go square
        int newPosition = (currentPlayer.getPosition() - numSquares > 0)?
                currentPlayer.getPosition() - numSquares:
                currentPlayer.getPosition() - numSquares + board.getSquaresList().size() -1;

        //Set the player's new position
        board.setPlayerPosition(currentPlayer,newPosition);
        currentPlayer.setPosition(newPosition);
    }

    /**********************************************************************
     * It collects a tax from a the given player. For every house the player
     * owns, the player will pay 40$, and for every hotel the player owns,
     * the player will pa 115$
     *
     * @param player
     *********************************************************************/
    public boolean cardTax(Player player){
        int amountDue = 0;
        for (PropertySquare propertySquare : player.getOwnableProperties().stream()
                .filter(property -> property.getType() == 0).map(property -> (PropertySquare) property)
                .filter(propertySquare -> propertySquare.isHasHotel() || propertySquare.getNumHouses() > 0)
                .collect(Collectors.toCollection(ArrayList<PropertySquare>::new))) {
            amountDue += (propertySquare.isHasHotel())? 115: 40;
        }
        if (player.getWallet() >= amountDue) {
            player.pay(amountDue);
        }else{
            return false;
        }
        return true;
    }

    public void cardPayBank(int amount){
        currentPlayer.pay(amount);
    }

    public boolean cardPayAllPlayers(int amount){
        //Checks if the player can pay all player
        if (currentPlayer.getWallet()  < amount * (players.size() - 1)){
            players.stream().filter(player -> player != currentPlayer).forEach(player -> {
                player.receiveMoney(currentPlayer.pay(amount));
            });
            return true;
        }else {
            return false;
        }
    }

    public boolean cardCollectFromPlayers(int amount){
        for (Player player : players) {
            if (currentPlayer != player) {
                if (player.getWallet() < amount) {
                    currentPlayer.receiveMoney(player.pay(amount));
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }


}
