import Controller.GameTextController;
import Model.GamePackage.Game;
import View.GameTextView;

/**********************************************************************
 * This is the class that is ran and holds the main
 *
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class MonopolyGame {

  public static void main(String[] args) {
    Game game = new Game("res/board.txt", "res/community.txt", "res/chance.txt");
    GameTextView view = new GameTextView();
    GameTextController controller = new GameTextController(game, view, true, true, false, false);

    boolean quit = false;

    // Get number of players for the game
    int numPlayers = view.getNumPlayers();

    // Add all the players to the Game
    controller.addPlayers(numPlayers);

    // Shuffle players and set current player
    game.setPlayerOrder();
    game.setCurrentPlayer(game.getPlayers().get(0));

    // Set all players position to 0 (GO)
    for (int i = 0; i < game.getPlayers().size(); i++) {
      game.getPlayers().get(i).setPosition(0);
    }

    // Start Main game loop
    while (!quit) {
      // Print whos turn it is.
      view.printCurrentPlayer(game.getCurrentPlayer().getDisplayName());

      // Prompt user for command
      controller.possibleActions();
      String command = view.getCommand();
      if (command.equalsIgnoreCase("quit")) {
        quit = true;
      }
      controller.commands(command);

    }

  }
}
