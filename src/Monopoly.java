
import Model.GamePackage.Game;
import Controller.GameController;
import view.GameView;
import java.io.IOException;

/**
 * This is the Glue class for the Monopoly Game.
 */
public class Monopoly {

  /**
   * Runs Initial monopoly game Logic.
   * @param args
   * @throws IOException
   * @throws InterruptedException
   */
	public static void main(String[] args)
			throws IOException, InterruptedException {




    GameController controller = new GameController(new Game("res/board.txt","res/community.txt",
        "res/chance.txt"), null, true, true, false, false);

      GameView view = new GameView(controller);

    //Set the vie and the holder for the controller
    controller.setView(view);
    

  }
}
