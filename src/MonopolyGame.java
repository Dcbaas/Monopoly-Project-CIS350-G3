import Controller.GameTextController;
import Model.GamePackage.Game;
import View.GameTextView;

import java.util.Scanner;

/**********************************************************************
 * This is the class that is ran and holds the main
 *
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class MonopolyGame {
    public static void main(String[] args){
        Game game = new Game("res/board.txt", "res/community.txt", "res/chance.txt");
        GameTextView view = new GameTextView();
        GameTextController controller = new GameTextController(game, view);

        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while(!quit){
            // Prompt user for command
            System.out.print(">> ");
            String command = scanner.nextLine();

            controller.commands(command);
        }

    }
}
