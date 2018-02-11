package GamePackage;

/*****************************************************************
 The Die Class will be a random number generator that "rolls"
 a die some methods to return the value of the die.

 @author Dylan Kernohan
 @version Winter 2018
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
     @return none
     *****************************************************************/
    public void setValue(int value){
        this.value = value;
    }

    /*****************************************************************
     Get the die value
     @return value the value of the die
     *****************************************************************/
    public int getValue(){
        return value;
    }

    /*****************************************************************
     Generate a random number between 1 - 6 and assign it to the die
     @return none
     *****************************************************************/
    public void roll(){
        // Generate a random number
        Random random = new Random();
        random.nextInt(6) + 1;

        // Assign random number to value
        value = random;
    }
}