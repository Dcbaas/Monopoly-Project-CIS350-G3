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

    public GameTextController(Game game, GameTextView view) {
        this.game = game;
        this.view = view;
    }

    public void main(){
        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        System.out.println(command);
    }
}
