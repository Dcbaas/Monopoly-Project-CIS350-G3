package View;

import Model.GamePackage.Game;

/**********************************************************************
 * The text based view for monopoly
 *
 * @author Dylan Kernohan
 * @version 3/12/2018
 *********************************************************************/
public class GameTextView {

    public void printLocation(int num, String name, String owner){
        System.out.println("------------------------------------------------------");
        System.out.printf("You rolled: %d\nYou are at location: %s\nOwner of this location: %s\n"  ,num, name, owner );
        System.out.println("------------------------------------------------------");

    }
}
