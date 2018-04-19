package model.gamepackage;

import java.util.Random;

/**
 The Die Class will be a random number generator that "rolls"
 a die some methods to return the value of the die.

 @author Dylan Kernohan
 @since 2/11/2018
 @version 3/17/2018
 */
public class Die {

  /**
   * current value of the die.
   */
  int value;

  /**
   Default constructor.
   */
  public Die() {
  }

  /**
   Get the die value.
   @return value The value of the die.
   */
  public int getValue() {
    return value;
  }

  /**
   Set the die value.
   @param value the int value this Die is being set to.
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   Generate a random number between 1 - 6 and assign it to the die.
   */
  public void roll() {
    // Generate a random number
    Random random = new Random();

    //Assign random number to value
    value = random.nextInt(6) + 1;

  }
}
