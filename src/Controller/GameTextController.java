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


//    public void setup(){
//        // Prompt user for number of players
//        System.out.print("How many players are playing?\n>>");
//
//        for(int i = 0; i <= num; i++){
//
//        }
//    }
    /**********************************************************************
     * This method calls Game function based on command passed in
     *
     * @author Dylan Kernohan
     * @param command The command the user entered.
     * @version 3/12/2018
     *********************************************************************/
    public void commands(String command){

        if(command.equals("roll")){

            view.printLocation(5, "Square", "Dylan");
        }

        return;
    }
}
