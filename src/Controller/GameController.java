package Controller;

import Model.BoardPackage.BoardSquare;
import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import Model.CardPackage.Card;
import Model.GamePackage.Game;
import Model.GamePackage.Player;
import view.GameView;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private Game game;
    private GameView view;
    private int numPairs;
    private boolean canRoll;
    private boolean canBuy;
    private boolean canMortgage;
    private boolean canDraw;
    private List<String> holder = null;

    /**********************************************************************git
     * The constructor that builds a game controller with a Game and view
     *
     * @param game The Game object
     * @param view The view object
     *********************************************************************/
    public GameController(Game game, GameView view, boolean canRoll, boolean canBuy, boolean canMortgage, boolean canDraw) {
        this.game = game;
        this.view = view;
        this.canRoll = canRoll;
        this.canBuy = canBuy;
        this.canMortgage = canMortgage;
        this.canDraw = canDraw;

    }

    /**********************************************************************
     * This method takes info from the view calls the games add players method.
     *
     * @param num The number of players passed from the view
     *********************************************************************/
    public void addPlayers(int num) {
        for (int i = 0; i < num; i++) {
            game.addPlayer("Player" + i, Integer.toString(i));
        }
    }

    /**********************************************************************
     * This method takes info from the view calls the games add players method.
     *
     * @param players The ArrayList of players
     *********************************************************************/
    public void addPlayers(ArrayList<Player> players) {
        game.setPlayers(players);
        game.setPlayerOrder();

        for(Player player: game.getPlayers())
            view.getGamePanel().addPlayer(player);
    }



    /**********************************************************************
     * This method calls Game function based on command passed in.
     *
     * @param command The command the user entered.
     *********************************************************************/
    public void commands(String command) throws InterruptedException {

        // Update player detail panel
        view.getPlayerDetailPanel().setDisplay(game.getCurrentPlayer());

        //Checks if the player has rolled pairs three times in a row
        if (numPairs == 3) {
            canRoll = false;
            canBuy = false;
            game.sendPlayerToJail();
            view.getTextPanel().printToTextArea("Die 1: " + game.getDieOne().getValue() + "\n" + "Die 2: " + game.getDieTwo().getValue());
            nextPlayer();
            return;
        }

        //Checks which command the player used
        switch (command.toLowerCase()) {
            case "roll":
                // Do not need to check if canRoll b/c button wont be enabled if its false
                roll();
                break;
            case "buy":
                // Do not need to check if canBuy b/c button wont be enabled if its false
                buy();
                break;
            case "house":
                buildHouse();
                break;
            case "hotel":
                buildHotel();
                break;
            case "done":
                if (!canRoll) {
                    nextPlayer();
                } else {
                    // This does not apply, button should be disabled
                }
                break;
            case "mortgage":
                // Do not need to check if canMortgage b/c button wont be enabled if its false
                mortgage();
                break;
            case "draw":
                // Do not need to check if canDraw b/c button wont be enabled if its false
                draw();
                break;
            default:
                // Do not need a print here like text interface. Only opetions has is buttons
        }

        // Update player detail panel
        view.getPlayerDetailPanel().setDisplay(game.getCurrentPlayer());


        //Checks if the player has any actions left
        if (!canRoll && !canBuy) {
            nextPlayer();
        }
    }

    /**********************************************************************
     * This method returns the current player for the GUI to use the data
     *********************************************************************/
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    // ======================

    /**********************************************************************
     * This method returns the number of pairs
     * @return The number of pairs.
     *********************************************************************/
    public int getNumPairs() {
        return numPairs;
    }

    /**********************************************************************
     * This method sets the number of pairs
     * @param numPairs The number of pairs.
     *********************************************************************/
    public void setNumPairs(int numPairs) {
        this.numPairs = numPairs;
    }

    /**********************************************************************
     * This method gets the canRoll boolean
     * @return The canRoll boolean
     *********************************************************************/
    public boolean isCanRoll() {
        return canRoll;
    }

    /**********************************************************************
     * This method sets the canRoll method
     * @param canRoll True if the player can roll, False if they can't.
     *********************************************************************/
    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
    }

    /**********************************************************************
     * This method returns if the player can buy or not
     * @return True if player can buy, False if they can't.
     *********************************************************************/
    public boolean isCanBuy() {
        return canBuy;
    }

    /**********************************************************************
     * This method sets the canBuy boolean.
     * @param canBuy True if the player can buy, False if they can't.
     *********************************************************************/
    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    /**********************************************************************
     * This method returns the canMortgage boolean
     * @return True if the player can mortgage, False if the player can't.
     *********************************************************************/
    public boolean isCanMortgage() {
        return canMortgage;
    }

    /**********************************************************************
     * This method sets the canMortgage boolean
     * @param canMortgage True if the player can mortgage, False if they can't
     *********************************************************************/
    public void setCanMortgage(boolean canMortgage) {
        this.canMortgage = canMortgage;
    }

    /**********************************************************************
     * This method returns the canDraw boolean
     * @return True if the player can draw, false if they can't.
     *********************************************************************/
    public boolean isCanDraw() {
        return canDraw;
    }

    /**********************************************************************
     * This method sets the canDraw boolean
     * @param canDraw True if the player can draw, False if they can't.
     *********************************************************************/
    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }

    // =============================== Private Helpers ==============================================

    /**********************************************************************
     * This method allows the player to mortgage properties.
     * If the player owns a group where some of the properties
     * have houses or hotels, then the player will be asked to
     * sell all building before being able to mortgage the desired
     * property.
     *********************************************************************/
    private void mortgage() throws InterruptedException {
        //The variable that will hold the property in question
        OwnableSquare property = null;

        //The variable with the property id
        int propertyId = 0;

        //This variable will make sure the player has a chance to mortgage as many properties as desired
        Boolean run = true;

        //This property will hold the player command
        String command;

        //Starts the mortgage loop
        while (run) {
            //Propts the user and intakes a command
            command = JOptionPane.showInputDialog("Type The name of the property you would like to mortgage");

            //Checks if the user wants to exit the mortgage loop
            if (command.equalsIgnoreCase("done")) {
                run = false;
                break;
            }

            //If the player doesn't enter a valid number he well re start the loop
            try {
                propertyId = Integer.parseUnsignedInt(command);
            } catch (NumberFormatException e) {
                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " cannot perform that action right now.");
                continue;
            }


            //find the property the player Owns from the board
            for (OwnableSquare propertyOwned : game.getCurrentPlayer().getPropertiesOwned()) {
                if (propertyOwned.getName().equalsIgnoreCase(command)) {
                    property = propertyOwned;
                }
            }


            //Checks if the player owns the group of properties
            if (game.getCurrentPlayer().getPropertiesOwned().contains(property.getGROUP_NUMBER())) {
                //The array of the specific group of properties the player owns
                ArrayList<PropertySquare> group = (ArrayList<PropertySquare>) game.getBoard()
                        .getGroup(property.getGROUP_NUMBER())
                        .stream().map(ownableSquare -> (PropertySquare) ownableSquare);

                //Filters the propertiues in the group that  have buildings
                group = group.stream()
                        .filter(groupProperty -> groupProperty.getNumHouses() > 0 || groupProperty.isHasHotel())
                        .collect(
                                Collectors.toCollection(ArrayList<PropertySquare>::new));

                //Checks if the player did have building in any property of the group
                if (!group.isEmpty()) {

                    //starts the sell buildings loop
                    boolean answered = false;

                    //Sell buildings loop
                    while (!answered) {
                        //Prompts the user and reads a command
                        command = JOptionPane.showInputDialog("Would you like to sell all building for the group matching the \n property that is going to be mortgaged ? (YES/NO) \n or type  done to stop action");



                        //Checks if the user would like to exit out of the mortgage loop
                        if (command.equalsIgnoreCase("done")) {
                            run = false;
                            break;
                        }

                        //Checks for a response command
                        if (command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("no")) {
                            //stops the sell buildings loop
                            answered = true;

                            //Checks if the user does want to sell all buildings
                            if (command.equalsIgnoreCase("yes")) {
                                //Sells all buildings and removes the group from the player's owned list
                                sellBuildings(group);
                                game.getCurrentPlayer().removeGroupOwned(group.get(0).getGROUP_NUMBER());
                                group = null;
                            }
                        } else {
                            view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " cannot perform that action right now.");

                        }
                    }

                    //Checks whether player completed all requirements for the property to be mortgaged
                    if (group == null) {
                        //mortgages decired property and pays player the corresponding value
                        property.setMortgaged(true);
                        game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());

                        view.getTextPanel().printToTextArea(property.getName() + " was mortgaged for " + property.getMORTGAGE_VAL());
                    } else {
                        view.getTextPanel().printToTextArea("In order to mortgage the property, " + game.getCurrentPlayer().getDisplayName() + " must sell all the buildings \n for all properties in that group.");
                    }
                }
            } else {

                property.setMortgaged(true);
                game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
                view.getTextPanel().printToTextArea(property.getName() + " was mortgaged for " + property.getMORTGAGE_VAL());
            }

        }

        //Change the buttons based on the state of the game.
        view.getButtonPanel().toggleButtons();
    }

    /**********************************************************************
     * Sells all the buildings of a given group and pays the player
     * the corresponding amount.
     *
     * @param group the list of PropertySquares the player has houses at
     *********************************************************************/
    private void sellBuildings(ArrayList<PropertySquare> group) {
        //Keeps track of the total amount the player will earn after selling
        //all buildings
        int payout = 0;

        //Iterates trhough each property in the group
        for (PropertySquare property : group) {
            //Adds the corresponding valuebased on the building cost and amount.
            payout += (property.isHasHotel()) ? (property.getHotelCost() / 2)
                    : property.getNumHouses() * (property.getHouseCost() / 2);

            //sets all values to zero/false
            property.setNumHouses(0);
            property.setHasHotel(false);
        }

        //Pays the player the corresponding amount.
        game.getCurrentPlayer().receiveMoney(payout);
    }

    /**********************************************************************
     * This method performs all the logic for the roll command.
     *********************************************************************/
    private void roll() {
        game.rollDice();
        view.getTextPanel().printToTextArea("Die 1: " + game.getDieOne().getValue() + "\n" + "Die 2: " + game.getDieTwo().getValue());
        if (game.rolledPair()) {
            numPairs++;
        } else {
          canRoll = false;
        }

      int diceSum = game.getDieOne().getValue() + game.getDieTwo().getValue();

      // Move player in GUI
      view.getGamePanel().movePlayer(game.getCurrentPlayer().getPosition(),
          diceSum, game.getCurrentPlayer());

      // Move Player
      game.movePlayer(game.getCurrentPlayer(), game.getDieOne().getValue(),
          game.getDieTwo().getValue());


      // Check if player can draw
      checkDraw();
      String locationName = game.getBoard().getSquaresList()
          .get(game.getCurrentPlayer().getPosition()).getName();
      String locationOwner = "N/A";
      //Figure out if current location is ownable square. If so, find owner name
      if (game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 0
          ||
          game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 1
                ||
                game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 3
                ) {
            // Check if the owner is null (Bank)
            if (((OwnableSquare) game.getBoard().getSquaresList()
                    .get(game.getCurrentPlayer().getPosition())).getOwner() != null) {
                locationOwner = ((OwnableSquare) game.getBoard().getSquaresList()
                        .get(game.getCurrentPlayer().getPosition())).getOwner().getDisplayName();
            } else {
                locationOwner = "Bank";
            }

            if (game.shouldPlayerBeTaxed() && !canRoll) {
                tax();
            }
        }
        view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " landed on '" + locationName + "'. \nThe owner of this location is: " + locationOwner);

      //Change the buttons based on the state of the game.
      view.getButtonPanel().toggleButtons();
    }

    /**********************************************************************
     * This method performs all the logic for the buy command.
     *********************************************************************/
    private void buy() {
        // Check if current location is ownable and can be bought.
        if (game.checkIfOwnable(game.getCurrentPlayerLocation()) != null && !canRoll) {
            canBuy = false;
            // Call buyOwnable, check if it was successful or not
            if (game.buyOwnableSquare((OwnableSquare) game.getCurrentPlayerLocation(),
                    game.getCurrentPlayer(),
                    ((OwnableSquare) game.getCurrentPlayerLocation()).getPRICE())) {
                // Player had enough money
                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " bought " + game.getCurrentPlayerLocation().getName());
            } else {
                // Player did not have enough money
                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " do not have enough money to buy this");
            }
        } else {
            // This action could not be performed.
            view.getTextPanel().printToTextArea("This property cannot be bought at the moment.");
        }

        //Change the buttons based on the state of the game.
        view.getButtonPanel().toggleButtons();
    }

    /**********************************************************************
     * This method performs all the logic for the draw command
     *********************************************************************/
    private void draw() {
        Card card = null;
        // Determine which deck to draw from
        String deckType = game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName();

        int position = game.getCurrentPlayer().getPosition();

        if (deckType.equals("COMMUNITY CHEST")) {
            card = game.drawCard(false);
        } else if (deckType.equals("CHANCE")) {
            card = game.drawCard(true);
        }

        view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " drew:\n\t" + card.getCardDescription());
        canDraw = false;
        if(game.getCurrentPlayer().getPosition() != position)
            view.getGamePanel().movePlayer(position,
                game.getCurrentPlayer().getPosition() - position,
                game.getCurrentPlayer());
        view.getButtonPanel().toggleButtons();
    }

    private void tax() {
        view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " paid " + game.collectFee() + " in fees/rent");
    }

    /**********************************************************************
     * this method changes player's and resets all available commands.
     *********************************************************************/
    private void nextPlayer() {
        game.nextTurn();
        canMortgage = !game.getCurrentPlayer().getPropertiesOwned().isEmpty();
        numPairs = 0;
        canRoll = true;
        canBuy = true;
        view.getPlayerDetailPanel().setDisplay(game.getCurrentPlayer());
        view.getTextPanel().printToTextArea("\n---------------------------------------------------- " + game.getCurrentPlayer().getDisplayName() + "'s Turn ----------------------------------------------------\n");

        //Change the buttons based on the state of the game.
        view.getButtonPanel().toggleButtons();
    }

    /**********************************************************************
     * This method builds a house on a property
     *********************************************************************/
    private void buildHouse() throws InterruptedException {
        // Show the user all owned properties and prompt for which prop to build on

        String propertyName = JOptionPane.showInputDialog("Enter which property you want to build on below.");


        //holder.add("trash");
        // Get the actual propertySqaure
        PropertySquare property = null;
        for (BoardSquare square : game.getBoard().getSquaresList()) {
            if (square.getName().equalsIgnoreCase(propertyName)) {
                property = (PropertySquare) square;
            }
        }

        // Check if the bank has at least one house
        if (game.getBank().getNumHouses() >= 1) {
            // Check that the player owns the group for desired property
            if (game.getCurrentPlayer().getGroupsOwned().size() > 0 && game.getCurrentPlayer()
                    .getGroupsOwned().contains(property.getGROUP_NUMBER())) {
                // Check if the property has a hotel
                if (property.isHasHotel() == false) {
                    // Check if property has 4 houses
                    if (property.getNumHouses() < 4) {
                        // Check that all other properties in group are not less than 1 house different
                        // If you want to build the second house on a property, all other props in that group need to have at least 1 house
                        if (checkHouseCount(property) == true) {
                            // Check that player has enough money
                            if (game.getCurrentPlayer().getWallet() >= property.getHouseCost()) {
                                //add one house to the desired property
                                property.setNumHouses(property.getNumHouses() + 1);
                                //Update the view.
                                view.getGamePanel().setHouses(property.getPOSITION(),property.getNumHouses());

                                //remove one house from bank
                                game.getBank().setNumHouses(game.getBank().getNumHouses() - 1);

                                // Print build success
                                view.getTextPanel().printToTextArea("A house was successfully built on " + property.getName() + ".");
                            } else {
                                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " does not have enough money to buy this");
                            }
                        } else {
                            view.getTextPanel().printToTextArea("House counts across this group are not even. Try building on a different property in this group first.");
                        }
                    } else {
                        view.getTextPanel().printToTextArea("Property already has 4 houses. Build a hotel here.");
                    }
                } else {
                    view.getTextPanel().printToTextArea("This property has a hotel.");
                }

            } else {
                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " does not own this monopoly.");
            }
        } else {
            view.getTextPanel().printToTextArea("The bank is out of houses.");
        }

    }

    /**********************************************************************
     * This method builds a hotel on a property
     *********************************************************************/
    private void buildHotel() throws InterruptedException {
        // Show the user all owned properties and prompt for which prop to build on
        String propertyName = JOptionPane.showInputDialog("Enter which property you want to build on below.");
        // Get the actual propertySqaure
        PropertySquare property = null;
        for (BoardSquare square : game.getBoard().getSquaresList()) {
            if (square.getName().equalsIgnoreCase(propertyName)) {
                property = (PropertySquare) square;
            }
        }

        // Check if bank has at least one hotel
        if (game.getBank().getNumHotels() >= 1) {
            // Check that the player owns the group for desired property
            if (game.getCurrentPlayer().getGroupsOwned().size() > 0 && game.getCurrentPlayer()
                    .getGroupsOwned().contains(property.getGROUP_NUMBER())) {
                // Check that the property does not have a hotel already
                if (property.isHasHotel() == false) {
                    // Check that the property has 4 houses
                    if (property.getNumHouses() == 4) {
                        // Check that all other properties in group are not less than 1 house different
                        // If you want to build the second house on a property, all other props in that group need to have at least 1 house
                        boolean isHouseCountEven = true;
                        for (BoardSquare square : game.getBoard().getSquaresList()) {
                            // Only keep checking if isHouseCunt is true. If false, no point to keep going
                            if (isHouseCountEven == true) {
                                // Make sure its a propertySquare
                                if (square.getType() == 0) {
                                    PropertySquare propertySquare = (PropertySquare) square;
                                    // Check if property is in same group
                                    if (propertySquare.getGROUP_NUMBER() == property.getGROUP_NUMBER()) {
                                        // Make sure num houses is not less desired prop num houses - 1
                                        if (propertySquare.getNumHouses() < 4 && propertySquare.isHasHotel() == false) {
                                            isHouseCountEven = false;
                                        }
                                    }
                                }
                            }
                        }
                        if (isHouseCountEven == true) {
                            // Check that player has enough money
                            if (game.getCurrentPlayer().getWallet() >= property.getHotelCost()) {
                                // Add one hotel to the desired property (set hasHotel to true)
                                property.setHasHotel(true);

                                //Update the view.
                                view.getGamePanel().setHouses(property.getPOSITION(),5);

                                // remove 4 houses from the desired property
                                property.setNumHouses(property.getNumHouses() - 4);
                                // remove one hotel from the bank
                                game.getBank().setNumHotels(game.getBank().getNumHotels() - 1);
                                // add 4 houses to the bank
                                game.getBank().setNumHouses(game.getBank().getNumHouses() + 4);

                                // Print build success
                                view.getTextPanel().printToTextArea("A hotel was successfully built on " + property.getName() + ".");
                            } else {
                                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " does not have enough money to buy this");
                            }
                        } else {
                            view.getTextPanel().printToTextArea("House counts across this group are not even. Try building on a different property in this group first.");
                        }

                    } else {
                        view.getTextPanel().printToTextArea("Cannot build a Hotel. This property does not have 4 houses yet.");
                    }
                } else {
                    view.getTextPanel().printToTextArea("This property has a hotel.");
                }
            } else {
                view.getTextPanel().printToTextArea(game.getCurrentPlayer().getDisplayName() + " does not own this monopoly.");
            }
        } else {
            view.getTextPanel().printToTextArea("The bank is out of hotels.");
        }
    }

    /**********************************************************************
     * This method is used in buildHouse and buildHotel to check if the
     * property has even house counts across the group.
     *********************************************************************/
    private boolean checkHouseCount(PropertySquare property) {
        boolean isHouseCountEven = true;
        for (BoardSquare square : game.getBoard().getSquaresList()) {
            // Only keep checking if isHouseCunt is true. If false, no point to keep going
            if (isHouseCountEven == true) {
                // Make sure its a propertySquare
                if (square.getType() == 0) {
                    PropertySquare propertySquare = (PropertySquare) square;
                    // Check if property is in same group
                    if (propertySquare.getGROUP_NUMBER() == property.getGROUP_NUMBER()) {
                        // Make sure num houses is not less desired prop num houses - 1
                        if (propertySquare.getNumHouses() <= property.getNumHouses() - 1) {
                            isHouseCountEven = false;
                        }
                    }
                }
            }
        }
        return isHouseCountEven;
    }

    /**********************************************************************
     * This method is used to check if the player can draw a card
     *********************************************************************/
    private void checkDraw() {
        // If currentLocation type is 2 and ites CHANCE or COMMUNITY CHEST sqaure, canDraw = true
        if (game.getBoard().getLocationType(game.getCurrentPlayer().getPosition()) == 2 &&
                (game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("CHANCE")
                        || game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("COMMUNITY CHEST"))) {
            canDraw = true;
        } else {
            canDraw = false;
        }
    }


    /**
     * Set the controller view.
     * @param view the controller view.
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     *  Sets the controller holder.
     * @param holder the controller holder.
     */
    public void setHolder(List<String> holder) {
        this.holder = holder;
    }

    /**
     * Restart the game in order to play a new game.
     */
    public void newGame() {
        game.newGame("res/board.txt","res/community.txt",
            "res/chance.txt");

        view.getButtonPanel().toggleButtons();
        view.getGamePanel().clearBoard();
    }

    /**
     * Set the inital player for game
     */
    public void setInitialPlayer(){
        game.setCurrentPlayer(game.getPlayers().get(0));
        view.getPlayerDetailPanel().setDisplay(game.getCurrentPlayer());
        view.getTextPanel().printToTextArea("\n---------------------------------------------------- " + game.getCurrentPlayer().getDisplayName() + "'s Turn ----------------------------------------------------\n");
    }
}
