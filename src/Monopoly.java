import Model.GamePackage.Game;
import Model.GamePackage.Player;
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


//		GameView view = new GameView();
//
//        view.getTextPanel().getTextField().setEditable(true);
//		view.getTextPanel().printToTextArea("Hello World!");
//
//        // Blocking synchronized code. Makes program wait for textField Input
//        final List<String> holder = view.getTextPanel().getHolder();
//
//        String command;
//        // Make textField Editable
//        view.getTextPanel().getTextField().setEditable(true);
//
//        // Blocking synchronized code. Makes program wait for textField Input
//        synchronized (holder) {
//
//            // wait for input from field
//            while (holder.isEmpty()) {
//                holder.wait();
//            }
//            command = holder.remove(0);
//        }
//
//        // Make textField not Editable
//        view.getTextPanel().getTextField().setEditable(false);
//
//        // Program waits until something is entered, then calls this line and prints it.
//        view.getTextPanel().printToTextArea(command);
    }
}
