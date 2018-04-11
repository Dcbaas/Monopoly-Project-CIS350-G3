
import Model.GamePackage.Game;
import Model.GamePackage.Player;
import Controller.GameController;
import view.GameView;
import java.io.IOException;
import java.util.ArrayList;
import view.NewGameDialog;

public class Monopoly {

	public static void main(String[] args)
			throws IOException, InterruptedException {

    GameView view = new GameView();


    GameController controller = new GameController(new Game("res/board.txt","res/community.txt",
        "res/chance.txt"), view, true, true, false, false);


    // TODO ============= REMOVE TESTING ONLY=================================
    view.getTextPanel().printToTextArea("Hello World!");

    // Blocking synchronized code. Makes program wait for textField Input
    String command = controller.promptUser();

    // Program waits until something is entered, then calls this line and prints it.
    view.getTextPanel().printToTextArea(command);
    //TODO ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  }
}
