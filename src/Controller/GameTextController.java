package Controller;

import Model.BoardPackage.BoardSquare;
import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
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

  /**********************************************************************git
   * The constructor that builds a game controller with a Game and View
   *
   * @param game The Game object
   * @param view The view object
   *********************************************************************/
  public GameTextController(Game game, GameTextView view, boolean canRoll, boolean canBuy) {
    this.game = game;
    this.view = view;
    this.canRoll = canRoll;
    this.canBuy = canBuy;
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
          if(!game.getCurrentPlayer().getPropertiesOwned().isEmpty()){
            mortgage();
          }else{
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

  private void mortgage() {
    OwnableSquare property;
    int propertyId = 0;
    Boolean run = true;
    String command;

    while (run){
      view.MortgageInit();
      view.displayProperties(game.getCurrentPlayer());
      command = view.getCommand();

      if(command.equalsIgnoreCase("done")){
        run = false;
        continue;
      }

      try{
       propertyId = Integer.parseUnsignedInt(command);
      }
        catch(NumberFormatException e){
          view.printActionError();
          continue;
        }
     property = game.getBoard().getOwnableSquare(game.getCurrentPlayer().getPropertiesOwned()
         .get(propertyId).getPOSITION());
    if (game.getCurrentPlayer().getPropertiesOwned().contains(property.getGROUP_NUMBER())){
        ArrayList<PropertySquare> group = (ArrayList<PropertySquare>) game.getBoard()
            .getGroup(property.getGROUP_NUMBER())
            .stream().map(ownableSquare -> (PropertySquare)ownableSquare);

        group = group.stream()
            .filter(groupProperty -> groupProperty.getNumHouses() > 0 || groupProperty.isHasHotel()).collect(
            Collectors.toCollection(ArrayList<PropertySquare>::new));

        if (!group.isEmpty()){
          view.printSellBuilding();
          boolean answered = false;
          while(!answered){
            command = view.getCommand();
              if(command.equalsIgnoreCase("done")){
                run = false;
                break;
              }
            if(command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("no")) {
              answered = true;
              if (command.equalsIgnoreCase("yes")) {
                sellBuildings(group);
                game.getCurrentPlayer().removeGroupOwned(group.get(0).getGROUP_NUMBER());
                group = null;
              }
            }
          }
          if (group == null){
            property.setMortgaged(true);
            game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
            view.printMortgagedProperty(property);
          }else{
            view.printMustSellBuildings();
          }
        }
    }else{
      property.setMortgaged(true);
      game.getCurrentPlayer().receiveMoney(property.getMORTGAGE_VAL());
      view.printMortgagedProperty(property);
    }

    }
  }

  private void sellBuildings(ArrayList<PropertySquare> group) {
    int payout = 0;
    for (PropertySquare property: group) {
      payout +=(property.isHasHotel())?   (property.getHotelCost() / 2) :property.getNumHouses() * (property.getHouseCost() / 2);
      property.setNumHouses(0);
      property.setHasHotel(false);
    }
    game.getCurrentPlayer().receiveMoney(payout);
  }


  public void possibleActions() {
    ArrayList<String> actions = new ArrayList<>();
    actions.add("'list' - Show player money and properties owned.");
    actions
        .add("'house' - Shows list and prompts user for a property they want to build a house on");
    actions
        .add("'hotel' - Shows list and prompts user for a property they want to build a hotel on");

    if (!canRoll) {
      actions.add("'done' - Ends Player's turn.");
    }
    if (canRoll) {
      actions.add("'roll' - Player rolls and moves");
    }
    if (canBuy) {
      actions.add("'buy' - Player tries to buy the property they are on.");
    }
    for (int i = 0; i < actions.size(); i++) {
      view.printPossibleActions(actions.get(i), i);
    }
  }

//helpers

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
    game.movePlayer(game.getCurrentPlayer(), game.getDieOne().getValue(),
        game.getDieTwo().getValue());
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

}


