package Controller;

import Model.BoardPackage.BoardSquare;
import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import Model.CardPackage.Card;
import Model.GamePackage.Game;
import View.GameTextView;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**********************************************************************
 * The text based controller for monopoly
 *
 * @author Santiago Quiroga
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class GameTextController {

  private Game game;
  private GameTextView view;
  private int numPairs;
  private boolean canRoll;
  private boolean canBuy;
  private boolean canMortgage;
  private boolean canDraw;

  /**********************************************************************git
   * The constructor that builds a game controller with a Game and View
   *
   * @param game The Game object
   * @param view The view object
   *********************************************************************/
  public GameTextController(Game game, GameTextView view, boolean canRoll, boolean canBuy, boolean canMortgage, boolean canDraw) {
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
      game.addPlayer(view.getPlayerName(i + 1), view.getPlayerToken(i + 1));
    }
  }

  /**********************************************************************
   * This method calls Game function based on command passed in.
   *
   * @param command The command the user entered.
   *********************************************************************/
  public void commands(String command) {

    //Checks if the player has rolled pairs three times in a row
    if (numPairs == 3) {
      canRoll = false;
      canBuy = false;
      game.sendPlayerToJail();
      view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
      nextPlayer();
      return;
    }

    //Checks which command the player used
    switch (command.toLowerCase()) {
      case "roll":
        if (canRoll) {
          roll();
        } else {
          view.printActionError();
        }
        break;
      case "buy":
        if (canBuy) {
          buy();
        } else {
          view.printActionError();
        }
        break;
      case "list":
        list();
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
          view.printActionError();
        }
        break;
      case "mortgage":
        if (canMortgage) {
          mortgage();
        } else {
          view.printActionError();
        }
        break;
      case "draw":
        if (canDraw) {
          draw();
        } else {
          view.printActionError();
        }
        break;
      default:
        view.notAValidCommand();

    }

    //Checks if the player has any actions left
    if (!canRoll && !canBuy) {
      nextPlayer();
    }
  }

  /**********************************************************************
   * This method allows the player to mortgage properties.
   * If the player owns a group where some of the properties
   * have houses or hotels, then the player will be asked to
   * sell all building before being able to mortgage the desired
   * property.
   *********************************************************************/
  private void mortgage() {
    //The variable that will hold the property in question
    OwnableSquare property;

    //The variable with the property id
    int propertyId = 0;

    //This variable will make sure the player has a chance to mortgage as many properties as desired
    Boolean run = true;

    //This property will hold the player command
    String command;

    //Starts the mortgage loop
    while (run) {
      //Propts the user and intakes a command
      view.MortgageInit();
      view.displayProperties(game.getCurrentPlayer());
      command = view.getCommand();

      //Checks if the user wants to exite the mortgage loop
      if (command.equalsIgnoreCase("done")) {
        run = false;
        break;
      }

      //If the player doesn't enter a valid number he well re start the loop
      try {
        propertyId = Integer.parseUnsignedInt(command);
      } catch (NumberFormatException e) {
        view.printActionError();
        continue;
      }

      //find the property the player Owns from the board
      property = game.getBoard().getOwnableSquare(game.getCurrentPlayer().getPropertiesOwned()
          .get(propertyId).getPOSITION());

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
            view.printSellBuilding();
            command = view.getCommand();

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
              view.printActionError();
            }
          }

          //Checks whether player completed all requirements for the property to be mortgaged
          if (group == null) {
            //mortgages decired property and pays player the corresponding value
            property.setMortgaged(true);
            game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());

            //prompts the player
            view.printMortgagedProperty(property);
          } else {
            view.printMustSellBuildings();
          }
        }
      } else {

        property.setMortgaged(true);
        game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
        view.printMortgagedProperty(property);
      }

    }
  }


  /**********************************************************************
   * Returns all current possible actions for the current player
   *********************************************************************/
  public void possibleActions() {
    ArrayList<String> actions = new ArrayList<>();
    actions.add("'list' - Show player money and properties owned.");
    actions
        .add("'house' - Shows list and prompts user for a property they want to build a house on");
    actions
        .add("'hotel' - Shows list and prompts user for a property they want to build a hotel on");

    if(canDraw){
        actions.add("'draw' - Draw a card.");
    }
    if (!canRoll) {
      actions.add("'done' - Ends Player's turn.");
    }
    if (canRoll) {
      actions.add("'roll' - Player rolls and moves");
    }
    if (canBuy) {
      actions.add("'buy' - Player tries to buy the property they are on.");
    }
    if (canMortgage) {
      actions.add("'mortgage' - Player mortgages properties owned");
    }
    for (int i = 0; i < actions.size(); i++) {
      view.printPossibleActions(actions.get(i), i);
    }
  }

//helpers

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
    view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
    if (game.rolledPair()) {
      numPairs++;
    } else {
      canRoll = false;
    }

    int diceSum = game.getDieOne().getValue() + game.getDieTwo().getValue();
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

    view.printLocation(diceSum, locationName, locationOwner);
  }

  /**********************************************************************
   * This method performs all the logic for the buy command.
   *********************************************************************/
  private void buy() {
    //TODO:if a player lands in a square that is owned by another player, it should have a
    // different message when trying to buy it

    // Check if current location is ownable and can be bought.
    if (game.checkIfOwnable(game.getCurrentPlayerLocation()) != null && !canRoll) {
      canBuy = false;
      // Call buyOwnable, check if it was successful or not
      if (game.buyOwnableSquare((OwnableSquare) game.getCurrentPlayerLocation(),
          game.getCurrentPlayer(),
          ((OwnableSquare) game.getCurrentPlayerLocation()).getPRICE())) {
        // Player had enough money
        view.printBuySuccessful(game.getCurrentPlayerLocation().getName());
      } else {
        // Player did not have enough money
        view.printBuyFail();
      }
    } else {
      // This action could not be performed.
      view.printCannotBuy();
    }
  }

  /**********************************************************************
   * This method performs all the logic for the draw command
   *********************************************************************/
  private void draw(){
      Card card = null;
    // Determine which deck to draw from
    String deckType = game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName();

    if(deckType.equals("COMMUNITY CHEST")){
      card = game.drawCard(false);
    }
    else if(deckType.equals("CHANCE")){
      card = game.drawCard(true);
    }

    view.printCardUse(card.getCardDescription());
    canDraw = false;
  }

  private void tax() {
    view.printTaxCollected(game.collectFee());
  }

  /**********************************************************************
   * This method performs all the logic for the list command.
   *********************************************************************/
  private void list() {

    view.printPlayerWallet(game.getCurrentPlayer().getWallet());
    //Stores the all the ownable squares in a local variable.
    ArrayList<OwnableSquare> ownableSquareArrayList = game.getCurrentPlayer().getPropertiesOwned();

    //Iterates through all the ownable squares and  prints their name.
    if (ownableSquareArrayList.size() == 0) {
      view.printOwnedSquares("None", 0);
    } else {
      for (int i = 0; i < ownableSquareArrayList.size(); i++) {
        view.printOwnedSquares(ownableSquareArrayList.get(i).getName(), i);
      }
    }
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
  }

  /**********************************************************************
   * This method builds a house on a property
   *********************************************************************/
  private void buildHouse() {
    // Show the user all owned properties and prompt for which prop to build on
    list();
    String propertyName = view.getPropertyToBuildOn();

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

                //remove one house from bank
                game.getBank().setNumHouses(game.getBank().getNumHouses() - 1);

                // Print build success
                view.printBuildSuccessful("House", property.getName());
              } else {
                view.printBuyFail();
              }
            } else {
              view.printHouseCountsError();
            }
          } else {
            view.printPropertyMaxHouse();
          }
        } else {
          view.printPropertyHasHotel();
        }

      } else {
        view.printDoesNotOwnMonopoly();
      }
    } else {
      view.printBankOutOfHouses();
    }

  }

  /**********************************************************************
   * This method builds a hotel on a property
   *********************************************************************/
  private void buildHotel() {
    // Show the user all owned properties and prompt for which prop to build on
    list();
    String propertyName = view.getPropertyToBuildOn();

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
                    if (propertySquare.getNumHouses() < 3 && propertySquare.isHasHotel() == false) {
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
                // remove 4 houses from the desired property
                property.setNumHouses(property.getNumHouses() - 4);
                // remove one hotel from the bank
                game.getBank().setNumHotels(game.getBank().getNumHotels() - 1);
                // add 4 houses to the bank
                game.getBank().setNumHouses(game.getBank().getNumHouses() + 4);

                // Print build success
                view.printBuildSuccessful("Hotel", property.getName());
              } else {
                view.printBuyFail();
              }
            } else {
              view.printHouseCountsError();
            }

          } else {
            view.printNumHouseError();
          }
        } else {
          view.printPropertyHasHotel();
        }
      } else {
        view.printDoesNotOwnMonopoly();
      }
    } else {
      view.printBankOutOfHotels();
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
    private void checkDraw(){
        // If currentLocation type is 2 and ites CHANCE or COMMUNITY CHEST sqaure, canDraw = true
        if(game.getBoard().getLocationType(game.getCurrentPlayer().getPosition()) == 2 &&
                (game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("CHANCE")
                || game.getBoard().getSquaresList().get(game.getCurrentPlayer().getPosition()).getName().equals("COMMUNITY CHEST"))){
            canDraw = true;
        }
        else{
            canDraw = false;
        }
    }

}


