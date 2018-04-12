
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
    controller.setHolder(view.getTextPanel().getHolder());

    // TODO ============= REMOVE TESTING ONLY=================================
    view.getTextPanel().printToTextArea("Hello World!");

    // Blocking synchronized code. Makes program wait for textField Input
    String command = controller.promptUser();

    // Program waits until something is entered, then calls this line and prints it.
    view.getTextPanel().printToTextArea(command);
    //TODO ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  }
}
