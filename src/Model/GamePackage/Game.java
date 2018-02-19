package Model.GamePackage;

import Model.BoardPackage.OwnableSquare;
import Model.CardPackage.Deck;
import Model.BoardPackage.PropertySquare;
import Model.BoardPackage.Board;

import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Bank bank;
    private ArrayList<Player> players;
    private Die dieOne;
    private Die dieTwo;
    private Board board;
    private Deck comunityChestDeck, chanceDeck;
    private int numPairs;
    private Player currentPlayer;
    Scanner scanner;
    public Game() {
        dieOne = new Die();
        dieTwo = new Die();
        board = new Board();
        comunityChestDeck = new Deck("Model/res/community.txt");
        chanceDeck = new Deck("Model/res/chance.txt");
        numPairs = 0;
        players = new ArrayList<>();
        bank = new Bank(board.getOwnableSquares());
        scanner = new Scanner(System.in);
    }
    public void roll() {
        dieOne.roll();
        dieTwo.roll();
    }
    public void addPlayer() {
        String name, token;
        //ask for player name and token
        System.out.println("Enter Player Name: ");
        name = scanner.next();
        System.out.println("Select a token: ");
        token = scanner.next();
        //set wallet to initial 1500
        //addPlayer to player list
        players.add(new Player(name, token, 1500));
    }
    public void removePlayer() {
        //remove player from player list
    }
    public void nextTurn() {
        //Move to the next player
        if (players.indexOf(currentPlayer) + 2 != players.size()) {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
        else {
            currentPlayer = players.get(0);
        }
    }
    public void buyProperty(OwnableSquare ownableSquare, Player owner, Player buyer) {
        //get property from bank,
        // give property from bank
        if (owner != null) {
            buyer.recieveProperty(owner.giveProperty(ownableSquare));
        }else{
            buyer.recieveProperty(bank.giveProperty(ownableSquare));
        }
        buyer.pay(ownableSquare.getPRICE());
    }
    public void movePlayer(Player player, int position) {
        //move player to the given position
        board.setPlayerPosition(player, position);
        player.setPosition(position);

    }
    public void sellPorperty(PropertySquare property, Player buyer) {
        //TODO: finish body
        //allow the player to sell a property from their list
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
    public void drawCard() {
        //TODO: finish body
        //draw a card from the specific chest
        System.out.println("Player draw card");
        //give card to player
    }
    public void useCard() {
        //TODO: finish body
        //display list of cards
        System.out.println("Card Used");
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
            System.out.println("should allow players to make deals or go bankrupt");
        }
    }
    public void start() {
        //TODO: Finish the function logic for a whole text game run
        boolean gameIsRunning = true;
        int choice = 0;
        String value = "";
        while (gameIsRunning) {
        }
    }

    // Card Actions -------------------------------------

    public void cardCollect(int amount){

    }

    public void cardMovePosition(int position){

    }

    public void cardMoveNearest(int typeID){

    }

    public void cardSetOwner(Player player){

    }

    public void cardMoveBack(int numSquares){

    }

    public void cardTax(Player player){

    }

    public void cardPayBank(int amount){

    }

    public void cardPayAllPlayers(int amount){

    }

    public void cardCollectFromPplayers(int amount){

    }

    
    //helper
    private void porpertySquareLogic(PropertySquare property) {
        if (property.getOwner() == null && property.getPrice() <= currentPlayer.getWallet()) {
            //offer to sell property
            System.out.println("would you like to buy: " + property.getName() + "? ([1](yes) [2](no)");
            if (scanner.nextInt() == 1) {
                buyProperty(property, null,currentPlayer);
            }
            else {
                auctionProperty();
            }
        }
        else {
            //check owner and fees
        }
    }
    public static void TestRun(String[] args) {
        Game game = new Game();
        game.addPlayer();
        game.addPlayer();
        game.start();
    }
}
