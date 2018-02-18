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
        buyer.pay(property.getPrice());
    }
    public void movePlayer(Player player, int position) {
        //move player to the given position
        board.setPlayerPosition(player, position);
        player.setPosition(position);
        //check for BoardSquare
        /**
         * PropertySquare = 0
         * RailRoadSquare = 1
         * DrawCard = 2
         * UtilitiesSquare = 3
         * FreeParking = 4
         * GoSquare = 5
         * JailSquare = 6
         * GoToJailSquare = 7
         */
        switch (board.getLocationType(position)) {
            case 0:
                porpertySquareLogic(board.getProperty(position));
                break;
            case 1:
                System.out.print("At RailroadSquare");
                break;
            case 2:
                System.out.print("At Draw Card");
                break;
            case 3:
                System.out.print("At UtilitiesSquare");
                break;
            case 4:
                System.out.print("At FreeParking Square");
                break;
            case 5:
                System.out.print("At GoSquare");
                break;
            case 6:
                System.out.print("At JailSquare");
                break;
            case 7:
                System.out.print("At GoToJailSquare");
                break;
            default:
                System.out.println("default scenario");
                break;
        }
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
