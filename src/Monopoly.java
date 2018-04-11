
import Model.GamePackage.Game;
import Model.GamePackage.Player;
import Controller.GameController;
import Model.GamePackage.Game;
import javax.swing.JMenuItem;
import view.GameView;
import java.io.IOException;
import java.util.ArrayList;
import view.NewGameDialog;

public class Monopoly {

  private static boolean isRunning = false;

  private static JMenuItem newGameItem;

  private static JMenuItem quitItem;

	public static void main(String[] args)
			throws IOException, InterruptedException {

    newGameItem = new JMenuItem("New Game");
    quitItem = new JMenuItem("Quit");

    quitItem.addActionListener(e -> System.exit(0));

    //newGameItem.addActionListener();
    ArrayList<Player> players = new ArrayList<>();
    NewGameDialog newGameDialog = new NewGameDialog(players);

    Game game = new Game("res/board.txt","res/community.txt",
        "res/chance.txt",players);

    GameView view = new GameView();
    //Initialization
    view.getPlayerDetailPanel().setDisplay(game.getCurrentPlayer());

    GameController controller = new GameController(game, view, true, true, false, false);

    for(Player player: players)
      view.getGamePanel().addPlayer(player);

    // TODO ============= REMOVE TESTING ONLY=================================
    view.getTextPanel().printToTextArea("Hello World!");

    // Blocking synchronized code. Makes program wait for textField Input
    String command = controller.promptUser();

    // Program waits until something is entered, then calls this line and prints it.
    view.getTextPanel().printToTextArea(command);
    //TODO ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  }
}
