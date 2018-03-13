package Controller;

import java.lang.*;
import java.io.*;
import Model.GamePackage.Game;
import View.GameTextView;

import java.util.*;

import static java.lang.System.out;

/**********************************************************************
 * The text based controller for monopoly
 *
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class GameTextController {
    Game game;
    GameTextView view;
    Scanner scanner = new Scanner(System.in);

    /**********************************************************************
     * The constructor that builds a game controller with a Game and View
     *
     * @author Dylan Kernohan
     * @param game The Game object
     * @param view The view object
     * @version 3/12/2018
     *********************************************************************/
    public GameTextController(Game game, GameTextView view) {
        this.game = game;
        this.view = view;
    }

    /**********************************************************************
     * This method prompts the user for commands and calls game function
     *
     * @author Dylan Kernohan
     * @version 3/12/2018
     *********************************************************************/
    public void commands(){
        boolean quit = false;
        while(!quit) {

            // Prompt user for command
            System.out.print(">> ");
            String command = scanner.nextLine();

            // Check if command is quit. If so, exit command loop
            if(command.equals("quit") || command.equals("Quit")){
                quit = true;
            }
        }
        return;
    }
}
