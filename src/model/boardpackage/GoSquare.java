package model.boardpackage;

/**
 * The GoSquare is the first square on the board in Monopoly. It is
 * the square all pieces start on and when it is past or landed on,
 * the player who moved by collects money from the Bank.
 *
 * @author David Baas
 * @since 2/15/2018
 * @version 3/17/2018
 */
public class GoSquare extends BoardSquare {

  /**
   * A integer to money paid out per cycle of the board.
   */
  private final int PAYOUT_AMOUNT;

  /**
   * The constructor creates a GoSquare allowing for the payout
   * amount to be set on initialization.
   * @param name The name of the GoSquare (from BoardSquare)
   * @param PAYOUT_AMOUNT the amount of money paid out per
   *                     board cycle.
   */
  public GoSquare(String name, int PAYOUT_AMOUNT) {
    super(name, 0);

    type = 5;
    this.PAYOUT_AMOUNT = PAYOUT_AMOUNT;
  }

  /**
   * The getPAYOUT_AMOUNT method returns how much money is paid out
   * per board cycle.
   * @return PAYOUT_AMOUNT the amount of money paid out per board
   *          cycle.
   */
  public int getPAYOUT_AMOUNT() {
    return PAYOUT_AMOUNT;
  }
}
