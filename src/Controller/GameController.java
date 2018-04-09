//package Controller;
//
//import Model.BoardPackage.BoardSquare;
//import Model.BoardPackage.OwnableSquare;
//import Model.BoardPackage.PropertySquare;
//import Model.CardPackage.Card;
//import Model.GamePackage.Game;
//import Model.GamePackage.Player;
//import View.GameView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class GameController {
//
//    private Game game;
//    private GameView view;
//    private int numPairs;
//    private boolean canRoll;
//    private boolean canBuy;
//    private boolean canMortgage;
//    private boolean canDraw;
//    private final List<String> holder = view.getTextPanel().getHolder();
//
//    /**********************************************************************git
//     * The constructor that builds a game controller with a Game and View
//     *
//     * @param game The Game object
//     * @param view The view object
//     *********************************************************************/
//    public GameController(Game game, GameView view, boolean canRoll, boolean canBuy, boolean canMortgage, boolean canDraw) {
//        this.game = game;
//        this.view = view;
//        this.canRoll = canRoll;
//        this.canBuy = canBuy;
//        this.canMortgage = canMortgage;
//        this.canDraw = canDraw;
//    }
//
//    /**********************************************************************
//     * This method takes info from the view calls the games add players method.
//     *
//     * @param num The number of players passed from the view
//     *********************************************************************/
//    public void addPlayers(int num) {
//        for (int i = 0; i < num; i++) {
//            //TODO: GUI Prompt user
//            game.addPlayer(view.getPlayerName(i + 1), view.getPlayerToken(i + 1));
//        }
//    }
//
//    /**********************************************************************
//     * This method calls Game function based on command passed in.
//     *
//     * @param command The command the user entered.
//     *********************************************************************/
//    public void commands(String command) throws InterruptedException {
//
//        //Checks if the player has rolled pairs three times in a row
//        if (numPairs == 3) {
//            canRoll = false;
//            canBuy = false;
//            game.sendPlayerToJail();
//            //TODO: print to GUI
//            view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
//            nextPlayer();
//            return;
//        }
//
//        //Checks which command the player used
//        switch (command.toLowerCase()) {
//            case "roll":
//                // Do not need to check if canRoll b/c button wont be enabled if its false
//                roll();
//                break;
//            case "buy":
//                // Do not need to check if canBuy b/c button wont be enabled if its false
//                buy();
//                break;
//            case "house":
//                buildHouse();
//                break;
//            case "hotel":
//                buildHotel();
//                break;
//            case "done":
//                if (!canRoll) {
//                    nextPlayer();
//                } else {
//                    // This does not apply, button should be disabled
//                }
//                break;
//            case "mortgage":
//                // Do not need to check if canMortgage b/c button wont be enabled if its false
//                mortgage();
//                break;
//            case "draw":
//                // Do not need to check if canDraw b/c button wont be enabled if its false
//                draw();
//                break;
//            default:
//                // Do not need a print here like text interface. Only opetions has is buttons
//        }
//
//        //Checks if the player has any actions left
//        if (!canRoll && !canBuy) {
//            nextPlayer();
//        }
//    }
//
//    /**********************************************************************
//     * This method returns the current player for the GUI to use the data
//     *********************************************************************/
//    public Player getCurrentPlayer() {
//        return game.getCurrentPlayer();
//    }
//
//    // ======================
//
//    /**********************************************************************
//     * This method returns the number of pairs
//     * @return The number of pairs.
//     *********************************************************************/
//    public int getNumPairs() {
//        return numPairs;
//    }
//
//    /**********************************************************************
//     * This method sets the number of pairs
//     * @param numPairs The number of pairs.
//     *********************************************************************/
//    public void setNumPairs(int numPairs) {
//        this.numPairs = numPairs;
//    }
//
//    /**********************************************************************
//     * This method gets the canRoll boolean
//     * @return The canRoll boolean
//     *********************************************************************/
//    public boolean isCanRoll() {
//        return canRoll;
//    }
//
//    /**********************************************************************
//     * This method sets the canRoll method
//     * @param canRoll True if the player can roll, False if they can't.
//     *********************************************************************/
//    public void setCanRoll(boolean canRoll) {
//        this.canRoll = canRoll;
//    }
//
//    /**********************************************************************
//     * This method returns if the player can buy or not
//     * @return True if player can buy, False if they can't.
//     *********************************************************************/
//    public boolean isCanBuy() {
//        return canBuy;
//    }
//
//    /**********************************************************************
//     * This method sets the canBuy boolean.
//     * @param canBuy True if the player can buy, False if they can't.
//     *********************************************************************/
//    public void setCanBuy(boolean canBuy) {
//        this.canBuy = canBuy;
//    }
//
//    /**********************************************************************
//     * This method returns the canMortgage boolean
//     * @return True if the player can mortgage, False if the player can't.
//     *********************************************************************/
//    public boolean isCanMortgage() {
//        return canMortgage;
//    }
//
//    /**********************************************************************
//     * This method sets the canMortgage boolean
//     * @param canMortgage True if the player can mortgage, False if they can't
//     *********************************************************************/
//    public void setCanMortgage(boolean canMortgage) {
//        this.canMortgage = canMortgage;
//    }
//
//    /**********************************************************************
//     * This method returns the canDraw boolean
//     * @return True if the player can draw, false if they can't.
//     *********************************************************************/
//    public boolean isCanDraw() {
//        return canDraw;
//    }
//
//    /**********************************************************************
//     * This method sets the canDraw boolean
//     * @param canDraw True if the player can draw, False if they can't.
//     *********************************************************************/
//    public void setCanDraw(boolean canDraw) {
//        this.canDraw = canDraw;
//    }
//
//    // =============================== Private Helpers ==============================================
//
//    /**********************************************************************
//     * This method allows the player to mortgage properties.
//     * If the player owns a group where some of the properties
//     * have houses or hotels, then the player will be asked to
//     * sell all building before being able to mortgage the desired
//     * property.
//     *********************************************************************/
//    private void mortgage() throws InterruptedException {
//        //The variable that will hold the property in question
//        OwnableSquare property;
//
//        //The variable with the property id
//        int propertyId = 0;
//
//        //This variable will make sure the player has a chance to mortgage as many properties as desired
//        Boolean run = true;
//
//        //This property will hold the player command
//        String command;
//
//        //Starts the mortgage loop
//        while (run) {
//            //Propts the user and intakes a command
//            view.getTextPanel().printToTextArea("Type The name of the property you would like to mortgage");
//
//            }
//
//            // Call promptUser() which is synchronized call
//            command = promptUser();
//
//            //Checks if the user wants to exite the mortgage loop
//            if (command.equalsIgnoreCase("done")) {
//                run = false;
//                break;
//            }
//
//            //If the player doesn't enter a valid number he well re start the loop
//            try {
//                propertyId = Integer.parseUnsignedInt(command);
//            } catch (NumberFormatException e) {
//                //TODO: print to GUI
//                view.printActionError();
//                continue;
//            }
//
//            //find the property the player Owns from the board
//      for (OwnableSquare propertyOwned: game.getCurrentPlayer().getPropertiesOwned()) {
//        if (propertyOwned.getName().equalsIgnoreCase(command)) {
//          property = propertyOwned;
//        }
//      }
//
//
//            //Checks if the player owns the group of properties
//            if (game.getCurrentPlayer().getPropertiesOwned().contains(property.getGROUP_NUMBER())) {
//                //The array of the specific group of properties the player owns
//                ArrayList<PropertySquare> group = (ArrayList<PropertySquare>) game.getBoard()
//                        .getGroup(property.getGROUP_NUMBER())
//                        .stream().map(ownableSquare -> (PropertySquare) ownableSquare);
//
//                //Filters the propertiues in the group that  have buildings
//                group = group.stream()
//                        .filter(groupProperty -> groupProperty.getNumHouses() > 0 || groupProperty.isHasHotel())
//                        .collect(
//                                Collectors.toCollection(ArrayList<PropertySquare>::new));
//
//                //Checks if the player did have building in any property of the group
//                if (!group.isEmpty()) {
//
//                    //starts the sell buildings loop
//                    boolean answered = false;
//
//                    //Sell buildings loop
//                    while (!answered) {
//                        //Prompts the user and reads a command
//                        //TODO: print to GUI
//                        view.printSellBuilding();
//                        //TODO: prompt user through GUI
//                        command = view.getCommand();
//
//                        //Checks if the user would like to exit out of the mortgage loop
//                        if (command.equalsIgnoreCase("done")) {
//                            run = false;
//                            break;
//                        }
//
//                        //Checks for a response command
//                        if (command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("no")) {
//                            //stops the sell buildings loop
//                            answered = true;
//
//                            //Checks if the user does want to sell all buildings
//                            if (command.equalsIgnoreCase("yes")) {
//                                //Sells all buildings and removes the group from the player's owned list
//                                sellBuildings(group);
//                                game.getCurrentPlayer().removeGroupOwned(group.get(0).getGROUP_NUMBER());
//                                group = null;
//                            }
//                        } else {
//                            //TODO: print to GUI
//                            view.printActionError();
//                        }
//                    }
//
//                    //Checks whether player completed all requirements for the property to be mortgaged
//                    if (group == null) {
//                        //mortgages decired property and pays player the corresponding value
//                        property.setMortgaged(true);
//                        game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
//
//                        //prompts the player
//                        //TODO: print to GUI
//                        view.printMortgagedProperty(property);
//                    } else {
//                        //TODO: print to GUI
//                        view.printMustSellBuildings();
//                    }
//                }
//            } else {
//
//                property.setMortgaged(true);
//                game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
//                //TODO: print to GUI
//                view.printMortgagedProperty(property);
//            }
//
//        }
//    }
//
//    /**********************************************************************
//     * Sells all the buildings of a given group and pays the player
//     * the corresponding amount.
//     *
//     * @param group the list of PropertySquares the player has houses at
//     *********************************************************************/
//    private void sellBuildings(ArrayList<PropertySquare> group) {
//        //Keeps track of the total amount the player will earn after selling
//        //all buildings
//        int payout = 0;
//
//        //Iterates trhough each property in the group
//        for (PropertySquare property : group) {
//            //Adds the corresponding valuebased on the building cost and amount.
//            payout += (property.isHasHotel()) ? (property.getHotelCost() / 2)
//                    : property.getNumHouses() * (property.getHouseCost() / 2);
//
//            //sets all values to zero/false
//            property.setNumHouses(0);
//            property.setHasHotel(false);
//        }
//
//        //Pays the player the corresponding amount.
//        game.getCurrentPlayer().receiveMoney(payout);
//    }
//
//    /**********************************************************************
//     * This method performs all the logic for the roll command.
//     *********************************************************************/
//    private void roll() {
//        game.rollDice();
//        //TODO: print to GUI
//        view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
//        if (game.rolledPair()) {
//            numPairs++;
//        } else {
//            canRoll = false;
//        }
//
//        int diceSum = game.getDieOne().getValue() + game.getDieTwo().getValue();
//        // Move Player
//        game.movePlayer(game.getCurrentPlayer(), game.getDieOne().getValue(),
//                game.getDieTwo().getValue());
//        // Check if player can draw
//        checkDraw();
//        String locationName = game.getBoard().getSquaresList()
//                .get(game.getCurrentPlayer().getPosition()).getName();
//        String locationOwner = "N/A";
//        //Figure out if current location is ownable square. If so, find owner name
//        if (game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 0
//                ||
//                game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 1
//                ||
//                game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getType() == 3
//                ) {
//            // Check if the owner is null (Bank)
//            if (((OwnableSquare) game.getBoard().getSquaresList()
//                    .get(game.getCurrentPlayer().getPosition())).getOwner() != null) {
//                locationOwner = ((OwnableSquare) game.getBoard().getSquaresList()
//                        .get(game.getCurrentPlayer().getPosition())).getOwner().getDisplayName();
//            } else {
//                locationOwner = "Bank";
//            }
//
//            if (game.shouldPlayerBeTaxed() && !canRoll) {
//                tax();
//            }
//        }
//        //TODO: print to GUI
//        view.printLocation(diceSum, locationName, locationOwner);
//    }
//
//    /**********************************************************************
//     * This method performs all the logic for the buy command.
//     *********************************************************************/
//    private void buy() {
//        //TODO:if a player lands in a square that is owned by another player, it should have a
//        // different message when trying to buy it
//
//        // Check if current location is ownable and can be bought.
//        if (game.checkIfOwnable(game.getCurrentPlayerLocation()) != null && !canRoll) {
//            canBuy = false;
//            // Call buyOwnable, check if it was successful or not
//            if (game.buyOwnableSquare((OwnableSquare) game.getCurrentPlayerLocation(),
//                    game.getCurrentPlayer(),
//                    ((OwnableSquare) game.getCurrentPlayerLocation()).getPRICE())) {
//                // Player had enough money
//                //TODO: print to GUI
//                view.printBuySuccessful(game.getCurrentPlayerLocation().getName());
//            } else {
//                // Player did not have enough money
//                //TODO: print to GUI
//                view.printBuyFail();
//            }
//        } else {
//            // This action could not be performed.
//            //TODO: print to GUI
//            view.printCannotBuy();
//        }
//    }
//
//    /**********************************************************************
//     * This method performs all the logic for the draw command
//     *********************************************************************/
//    private void draw() {
//        Card card = null;
//        // Determine which deck to draw from
//        String deckType = game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName();
//
//        if (deckType.equals("COMMUNITY CHEST")) {
//            card = game.drawCard(false);
//        } else if (deckType.equals("CHANCE")) {
//            card = game.drawCard(true);
//        }
//
//        //TODO: print to GUI
//        view.printCardUse(card.getCardDescription());
//        canDraw = false;
//    }
//
//    private void tax() {
//        //TODO: print to GUI
//        view.printTaxCollected(game.collectFee());
//    }
//
//    /**********************************************************************
//     * this method changes player's and resets all available commands.
//     *********************************************************************/
//    private void nextPlayer() {
//        game.nextTurn();
//        canMortgage = !game.getCurrentPlayer().getPropertiesOwned().isEmpty();
//        numPairs = 0;
//        canRoll = true;
//        canBuy = true;
//    }
//
//    /**********************************************************************
//     * This method builds a house on a property
//     *********************************************************************/
//    private void buildHouse() {
//        // Show the user all owned properties and prompt for which prop to build on
//        // TODO: Prompt user through GUI
//        String propertyName = view.getPropertyToBuildOn();
//
//        // Get the actual propertySqaure
//        PropertySquare property = null;
//        for (BoardSquare square : game.getBoard().getSquaresList()) {
//            if (square.getName().equalsIgnoreCase(propertyName)) {
//                property = (PropertySquare) square;
//            }
//        }
//
//        // Check if the bank has at least one house
//        if (game.getBank().getNumHouses() >= 1) {
//            // Check that the player owns the group for desired property
//            if (game.getCurrentPlayer().getGroupsOwned().size() > 0 && game.getCurrentPlayer()
//                    .getGroupsOwned().contains(property.getGROUP_NUMBER())) {
//                // Check if the property has a hotel
//                if (property.isHasHotel() == false) {
//                    // Check if property has 4 houses
//                    if (property.getNumHouses() < 4) {
//                        // Check that all other properties in group are not less than 1 house different
//                        // If you want to build the second house on a property, all other props in that group need to have at least 1 house
//                        if (checkHouseCount(property) == true) {
//                            // Check that player has enough money
//                            if (game.getCurrentPlayer().getWallet() >= property.getHouseCost()) {
//                                //add one house to the desired property
//                                property.setNumHouses(property.getNumHouses() + 1);
//
//                                //remove one house from bank
//                                game.getBank().setNumHouses(game.getBank().getNumHouses() - 1);
//
//                                // Print build success
//                                // TODO: Print to GUI
//                                view.printBuildSuccessful("House", property.getName());
//                            } else {
//                                // TODO: Print to GUI
//                                view.printBuyFail();
//                            }
//                        } else {
//                            // TODO: Print to GUI
//                            view.printHouseCountsError();
//                        }
//                    } else {
//                        // TODO: Print to GUI
//                        view.printPropertyMaxHouse();
//                    }
//                } else {
//                    // TODO: Print to GUI
//                    view.printPropertyHasHotel();
//                }
//
//            } else {
//                // TODO: Print to GUI
//                view.printDoesNotOwnMonopoly();
//            }
//        } else {
//            // TODO: Print to GUI
//            view.printBankOutOfHouses();
//        }
//
//    }
//
//    /**********************************************************************
//     * This method builds a hotel on a property
//     *********************************************************************/
//    private void buildHotel() {
//        // Show the user all owned properties and prompt for which prop to build on
//        // TODO: Prompt user through GUI
//        String propertyName = view.getPropertyToBuildOn();
//
//        // Get the actual propertySqaure
//        PropertySquare property = null;
//        for (BoardSquare square : game.getBoard().getSquaresList()) {
//            if (square.getName().equalsIgnoreCase(propertyName)) {
//                property = (PropertySquare) square;
//            }
//        }
//
//        // Check if bank has at least one hotel
//        if (game.getBank().getNumHotels() >= 1) {
//            // Check that the player owns the group for desired property
//            if (game.getCurrentPlayer().getGroupsOwned().size() > 0 && game.getCurrentPlayer()
//                    .getGroupsOwned().contains(property.getGROUP_NUMBER())) {
//                // Check that the property does not have a hotel already
//                if (property.isHasHotel() == false) {
//                    // Check that the property has 4 houses
//                    if (property.getNumHouses() == 4) {
//                        // Check that all other properties in group are not less than 1 house different
//                        // If you want to build the second house on a property, all other props in that group need to have at least 1 house
//                        boolean isHouseCountEven = true;
//                        for (BoardSquare square : game.getBoard().getSquaresList()) {
//                            // Only keep checking if isHouseCunt is true. If false, no point to keep going
//                            if (isHouseCountEven == true) {
//                                // Make sure its a propertySquare
//                                if (square.getType() == 0) {
//                                    PropertySquare propertySquare = (PropertySquare) square;
//                                    // Check if property is in same group
//                                    if (propertySquare.getGROUP_NUMBER() == property.getGROUP_NUMBER()) {
//                                        // Make sure num houses is not less desired prop num houses - 1
//                                        if (propertySquare.getNumHouses() < 3 && propertySquare.isHasHotel() == false) {
//                                            isHouseCountEven = false;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (isHouseCountEven == true) {
//                            // Check that player has enough money
//                            if (game.getCurrentPlayer().getWallet() >= property.getHotelCost()) {
//                                // Add one hotel to the desired property (set hasHotel to true)
//                                property.setHasHotel(true);
//                                // remove 4 houses from the desired property
//                                property.setNumHouses(property.getNumHouses() - 4);
//                                // remove one hotel from the bank
//                                game.getBank().setNumHotels(game.getBank().getNumHotels() - 1);
//                                // add 4 houses to the bank
//                                game.getBank().setNumHouses(game.getBank().getNumHouses() + 4);
//
//                                // Print build success
//                                // TODO: Print to GUI
//                                view.printBuildSuccessful("Hotel", property.getName());
//                            } else {
//                                // TODO: Print to GUI
//                                view.printBuyFail();
//                            }
//                        } else {
//                            // TODO: Print to GUI
//                            view.printHouseCountsError();
//                        }
//
//                    } else {
//                        // TODO: Print to GUI
//                        view.printNumHouseError();
//                    }
//                } else {
//                    // TODO: Print to GUI
//                    view.printPropertyHasHotel();
//                }
//            } else {
//                // TODO: Print to GUI
//                view.printDoesNotOwnMonopoly();
//            }
//        } else {
//            // TODO: Print to GUI
//            view.printBankOutOfHotels();
//        }
//    }
//
//    /**********************************************************************
//     * This method is used in buildHouse and buildHotel to check if the
//     * property has even house counts across the group.
//     *********************************************************************/
//    private boolean checkHouseCount(PropertySquare property) {
//        boolean isHouseCountEven = true;
//        for (BoardSquare square : game.getBoard().getSquaresList()) {
//            // Only keep checking if isHouseCunt is true. If false, no point to keep going
//            if (isHouseCountEven == true) {
//                // Make sure its a propertySquare
//                if (square.getType() == 0) {
//                    PropertySquare propertySquare = (PropertySquare) square;
//                    // Check if property is in same group
//                    if (propertySquare.getGROUP_NUMBER() == property.getGROUP_NUMBER()) {
//                        // Make sure num houses is not less desired prop num houses - 1
//                        if (propertySquare.getNumHouses() <= property.getNumHouses() - 1) {
//                            isHouseCountEven = false;
//                        }
//                    }
//                }
//            }
//        }
//        return isHouseCountEven;
//    }
//
//    /**********************************************************************
//     * This method is used to check if the player can draw a card
//     *********************************************************************/
//    private void checkDraw() {
//        // If currentLocation type is 2 and ites CHANCE or COMMUNITY CHEST sqaure, canDraw = true
//        if (game.getBoard().getLocationType(game.getCurrentPlayer().getPosition()) == 2 &&
//                (game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("CHANCE")
//                        || game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("COMMUNITY CHEST"))) {
//            canDraw = true;
//        } else {
//            canDraw = false;
//        }
//    }
//
//    /**********************************************************************
//     * This method uses a synchronized call and waits for the
//     *   user to enter in a command.
//     *********************************************************************/
//    private String promptUser() throws InterruptedException {
//
//        String command;
//        // Make textField Editable
//        view.getTextPanel().getTextField().setEditable(true);
//        view.getTextPanel().setDeafaultText();
//
//        // Blocking synchronized code. Makes program wait for textField Input
//        synchronized (holder) {
//
//            // wait for input from field
//            while (holder.isEmpty()) {
//                holder.wait();
//            }
//            command = holder.remove(0);
//        }
//
//        // Make textField not Editable
//        view.getTextPanel().getTextField().setEditable(false);
//        view.getTextPanel().getTextField().setText("");
//
//        return command;
//    }
//
//}
