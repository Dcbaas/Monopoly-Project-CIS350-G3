import controller.GameController;

import java.io.IOException;

import model.gamepackage.Game;

import view.GameView;

/**
 * This is the Glue class for the Monopoly Game.
 */
public class Monopoly {

  /**
   * Runs Initial monopoly game Logic.
   */
  public static void main(String[] args)
      throws IOException, InterruptedException {

    GameController controller = new GameController(new Game("res/board.txt", "res/community.txt",
        "res/chance.txt"), null, true, true, false, false);

    GameView view = new GameView(controller);

    //Set the vie and the holder for the controller
    controller.setView(view);

    view.getButtonPanel().preGame();
  }
}
