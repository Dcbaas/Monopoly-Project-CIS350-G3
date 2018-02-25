package Model.GamePackage;

import java.util.Random;

/*****************************************************************
 The Die Class will be a random number generator that "rolls"
 a die some methods to return the value of the die.

 @author Dylan Kernohan
 @since 2/11/2018
 @version 2/21/2018
 *****************************************************************/
public class Die {

    /** current value of the die */
    int value;

    /*****************************************************************
     Default constructor
     *****************************************************************/
    public Die( ) {
    }

    /*****************************************************************
     Set the die value
     @param value
     *****************************************************************/
    public void setValue(int value){
        this.value = value;
    }

    /*****************************************************************
     Get the die value
     @return value The value of the die
     *****************************************************************/
    public int getValue(){
        return value;
    }

    /*****************************************************************
     Generate a random number between 1 - 6 and assign it to the die
     *****************************************************************/
    public void roll() {
        // Generate a random number
        Random random = new Random();

        // Assign random number to value
        value = random.nextInt(6) + 1;

       
    }
}
