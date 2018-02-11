
import GamePackage.Die;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*****************************************************************
 JUnit tests for the Die Class

 @author Dylan Kernohan
 @version Winter 2018
 *****************************************************************/
public class DieTest {

    Die die = new Die();

    /*****************************************************************
     Test the setValue method
     *****************************************************************/
    @Test
    public void setValueTest(){
        die.setValue(1);
        assertEquals(1, die.getValue());
    }

    /*****************************************************************
     Tests the getValue method
     *****************************************************************/
    @Test
    public void getValueTest(){
        die.setValue(2);
        assertEquals(2, die.getValue());

    }

    /*****************************************************************
     Tests the roll method
     *****************************************************************/
    @Test
    public void rollTest(){
        die.roll();
        assertTrue(die.getValue() >= 1);
        assertTrue(die.getValue() <= 6);
    }

}
