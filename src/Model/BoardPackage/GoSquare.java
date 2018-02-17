package Model.BoardPackage;

/*********************************************************************
 * The GoSquare is the first square on the board in Monopoly. It is
 * the square all pieces start on and when it is past or landed on,
 * the player who moved by collects money from the Bank.
 *
 * @author David Baas
 * @version 2/15/2018
 ********************************************************************/
public class GoSquare extends BoardSquare{

    /**A integer to money paid out per cycle of the board*/
    private final int payoutAmount;

    /*****************************************************************
     * The constructor creates a GoSquare allowing for the payout
     * amount to be set on initialization.
     * @param name The name of the GoSquare (from BoardSquare)
     * @param payoutAmount the amount of money paid out per
     *                     board cycle.
     ****************************************************************/
    public GoSquare(String name, int payoutAmount) {
        super(name);

        type = 5;
        this.payoutAmount = payoutAmount;
    }

    /*****************************************************************
     * The getPayoutAmount method returns how much money is paid out
     * per board cycle.
     * @return payoutAmount the amount of money paid out per board
     * cycle.
     ****************************************************************/
    public int getPayoutAmount() {
        return payoutAmount;
    }
}
