
import Model.GamePackage.Game;
import Model.GamePackage.Player;
import Controller.GameController;
import Model.GamePackage.Game;
import view.GameView;
import java.io.IOException;
import java.util.ArrayList;
import view.NewGameDialog;

public class Monopoly {

  private static boolean isRunning = false;

	public static void main(String[] args)
			throws IOException, InterruptedException {

    ArrayList<Player> players = new ArrayList<>();
    NewGameDialog newGameDialog = new NewGameDialog(players);

    Game game = new Game("res/board.txt","res/community.txt",
        "res/chance.txt",players);

    //Todo: Finish The initialization.

		GameView view = new GameView();
        Game game = new Game("res/board.txt", "res/community.txt", "res/chance.txt");
        GameController controller = new GameController(game, view, true, true, false, false);

        // TODO ============= REMOVE TESTING ONLY=================================
		view.getTextPanel().printToTextArea("Hello World!");

        // Blocking synchronized code. Makes program wait for textField Input
        String command = controller.promptUser();

        // Program waits until something is entered, then calls this line and prints it.
        view.getTextPanel().printToTextArea(command);
        //TODO ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    }
}
