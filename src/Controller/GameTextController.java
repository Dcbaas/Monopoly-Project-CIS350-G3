package Controller;

import Model.BoardPackage.OwnableSquare;
import Model.GamePackage.Game;
import View.GameTextView;

/**********************************************************************
 * The text based controller for monopoly
 *
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class GameTextController {

  Game game;
  GameTextView view;
  private int numpairs;
  private boolean canRoll;

  /**********************************************************************
   * The constructor that builds a game controller with a Game and View
   *
   * @param game The Game object
   * @param view The view object
   *********************************************************************/
  public GameTextController(Game game, GameTextView view, Boolean canRoll) {
    this.game = game;
    this.view = view;
    this.canRoll = canRoll;
  }

  /**********************************************************************
   * This method takes info from the view calls the games add players method
   *
   * @param num The number of players passed from the view
   *********************************************************************/
  public void addPlayers(int num) {
    for (int i = 0; i < num; i++) {
      game.addPlayer(view.getPlayerName(i + 1), view.getPlayerToken(i + 1));
    }
  }

  /**********************************************************************
   * This method calls Game function based on command passed in
   *
   * @param command The command the user entered.
   *********************************************************************/
  public void commands(String command) {
    if (numpairs == 3) {
      canRoll = false;
      game.sendPlayerToJail();
      view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
    }

    //TODO: Add validation to prevent player from rolling twice per turn. Not sure where this will go yet
    if (command.equals("roll") && canRoll) {
      game.rollDice();
      view.printDies(game.getDieOne().getValue(), game.getDieTwo().getValue());
      if (game.rolledPair()){
        numpairs ++;
      }else{
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

    //Checks if the player can re roll the dice and increses the amount of pairs rolled.
    if (!canRoll) {
      game.nextTurn();
      numpairs = 0;
      canRoll = true;
    }

    return;
  }
}
