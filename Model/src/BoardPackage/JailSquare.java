package BoardPackage;

import GamePackage.Player;

import java.util.ArrayList;

/*********************************************************************
 * The JailSquare Class tracks what players are in jail.
 *
 * @author David Baas
 * @version 2/14/2018
 ********************************************************************/
public class JailSquare extends BoardSquare {

    /**An ArrayList to store what players are in jail.*/
    private ArrayList<Player> inJail;

    /*****************************************************************
     * The default constructor creates a JailSquare setting its name
     * variable and initializes the inJail ArrayList.
     *
     * @param name sets the name of the board square.
     ****************************************************************/
    public JailSquare(String name){

        super(name);

        inJail = new ArrayList<Player>();
    }


}
