package Controller;

import Model.BoardPackage.OwnableSquare;
import Model.GamePackage.Game;
import View.GameTextView;
import java.util.ArrayList;

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
      case "done":
        if (!canRoll) {
          nextPlayer();
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

  public void possibleActions(){
    ArrayList<String> actions = new ArrayList<>();
    actions.add("'list' - Show player money and properties owned.");
    if(!canRoll) {
      actions.add("'done' - Ends Player's turn.");
    }
    if(canRoll){
      actions.add("'roll' - Player rolls and moves");
    }
    if(canBuy){
      actions.add("'buy' - Player tries to buy the property they are on.");
    }
    for(int i = 0; i < actions.size(); i++){
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
      // This aciton could not be performed.
      view.printCannotBuy();
    }
  }

  /**********************************************************************
   * This methof performs all the logic for the list command.
   *********************************************************************/
  private void list() {

    view.printPlayerWallet(game.getCurrentPlayer().getWallet());
    //Stores the all the ownable squares in a local variable.
    ArrayList<OwnableSquare> ownableSquareArrayList = game.getCurrentPlayer().getPropertiesOwned();

    //Iterates through all the ownable squares and  prints their name.
    if(ownableSquareArrayList.size() == 0){
      view.printOwnedSquares("None", 0);
    }
    else {
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
}


