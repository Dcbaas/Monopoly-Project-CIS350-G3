package Model.GamePackage;

import Model.BoardPackage.OwnableSquare;
import Model.CardPackage.Card;
import Model.CardPackage.Deck;
import Model.BoardPackage.PropertySquare;
import Model.BoardPackage.Board;

import java.util.ArrayList;

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
     *
     *********************************************************************/
    public void setPlayerOrder(){
        /*
        TODO:
            Not sure if this method should handle each player rolling and
            reordering. Or if Controller/View should handle the rolls and
            this method just redefine players list?
         */
    }

    /**********************************************************************
     * When a player wants to buy a house. Returns true when the player
     * has enough money. False if they do not.
     * @param ownableSquare The ownable square the player wants to buy
     * @param buyer The player trying to buy
     * @return
     *********************************************************************/
    public boolean buyOwnableSquare(OwnableSquare ownableSquare, Player buyer) {
        // Check is buyer has enough money
        if(buyer.getWallet() >= ownableSquare.getPRICE()) {
            //TODO: Should this method just be for buying a property that the bank owns? Use a separate method for trading (player to player buying/selling)
            // Another player owns the property
            if (ownableSquare.getOwner() != null) {
                buyer.recieveProperty(ownableSquare.getOwner().giveProperty(ownableSquare));
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
            // TODO: Handle what happens when the player passes go here
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

    public void mortgageProperty(PropertySquare property) {
        //TODO: finish body
        //Display the list of properties the player has.
    }

    public void payMortgage(){
        //TODO: finish body
    }

    public void auctionProperty() {
        //TODO: finish body
        // check for highest bidder
        System.out.println("auction has commenced ");
        //give property to highest bidder
    }

    public void drawCard(Boolean deckType) {
            //Checks for the deckType and gives the player a card from hte specific type of deck
            currentPlayer.recieveCard((deckType)?chanceDeck.drawCard():comunityChestDeck.drawCard());
    }

    public void useCard(Card cardToUse) {
        //Retrieve the instructions from the card
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
            cardSetOwner(currentPlayer);
        }
        if (actions[4] != -1){
            cardMoveBack(actions[4]);
        }
        if (actions[5] != -1){
            cardTax(currentPlayer);
        }
        if (actions[6] != -1){
            cardPayBank(actions[6]);
        }
        if (actions[7] != -1){
            cardPayAllPlayers(actions[7]);
        }
        if (actions[8] != -1){
            cardCollectFromPlayers(actions[8]);
        }
    }

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

    public void cardCollect(int amount){
        currentPlayer.receiveMoney(amount);
    }

    public void cardMovePosition(int position){
        board.setPlayerPosition(currentPlayer,position);
    }

    public void cardMoveNearest(int typeID){
        board.getLocationbyType(typeID).forEach(square -> {
            int closestPosition = 40;
            if(Math.abs(currentPlayer.getPosition() - square.getPOSITION()) < closestPosition){
                closestPosition = square.getPOSITION();
            }
        });
    }

    public void cardSetOwner(Player player){
        //TODO: finish body
    }

    public void cardMoveBack(int numSquares){
        board.setPlayerPosition(currentPlayer, currentPlayer.getPosition() - numSquares);
    }

    public void cardTax(Player player){
        //TODO: I don't seee any card using this method, so I have to review this method
    }

    public void cardPayBank(int amount){
        currentPlayer.pay(amount);
    }

    public void cardPayAllPlayers(int amount){
        //Checks if the player can pay all player
        if (currentPlayer.getWallet()  < amount * (players.size() - 1)){
            players.stream().filter(player -> player != currentPlayer).forEach(player -> {
                player.receiveMoney(currentPlayer.pay(amount));
            });
        }else {
            //TODO:  NOTE: talk about this logic (when player can possibly cgo bankrupt
            /*
             * Maybe we could send a bankrupted method in here whe we
             * check if the player is truly bankrupt or can still save
             * themselves
             */
        }
    }

    public void cardCollectFromPlayers(int amount){
        players.stream().filter(player -> currentPlayer != player).forEach(player -> {
            if(player.getWallet() < amount){
                currentPlayer.receiveMoney(player.pay(amount));
            }else{//TODO: NOTE talk about this logic (when player can possibly cgo bankrupt
            /*
             * Maybe we could send a bankrupted method in here whe we
             * check if the player is truly bankrupt or can still save
             * themselves
             */
            }
        });
    }


}
