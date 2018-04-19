package model.boardpackage;

/**
 The RailRoadSquare class is a BoardSquare that tracks
 information specific to RailRoadSquares.

 @author Dylan Kernohan David Baas
 @since 2/12/2018
 @version 3/17/2018
 **/
public class RailRoadSquare extends OwnableSquare {

  /**
   * The initial rent of the RailRoad square.
   */
  private final int BASE_RENT;

  /**
   * The rent if a player owns two RailRoads.
   */
  private final int TWO_RENT;

  /**
   * The rent if a player owns three RailRoads.
   */
  private final int THREE_RENT;

  /**
   * The rent if a player owns four RailRoads.
   */
  private final int FOUR_RENT;

  /**
   Constructor instantiates the variables and sets the name of the
   BoardSquare to what is input as a parameter.

   @param name String that will be the name of the BoardSquare.
   @param position The position of this RailroadSquare on the
          board.
   @param price The price of the RailRoad square
   @param baseRent The initial rent of the RailRoad square
   @param twoRent rent if a player owns two RailRoads
   @param threeRent rent if a player owns three RailRoads
   @param fourRent The rent if a player owns four RailRoads
   @param mortgageValue The value a player receives if they
          mortgage this RailRoad
   @param groupNumber the Railroad group this Railroad belongs to.
   */
  public RailRoadSquare(String name, int position, int price, int baseRent,
      int twoRent, int threeRent, int fourRent,
      int mortgageValue, int groupNumber) {

    super(name, position, price, mortgageValue, groupNumber);
    type = 1;

    this.BASE_RENT = baseRent;
    this.TWO_RENT = twoRent;
    this.THREE_RENT = threeRent;
    this.FOUR_RENT = fourRent;
  }

  /**
   Get the initial rent of the RailRoad square.

   @return BASE_RENT The initial rent of the RailRoad square.
   */
  public int getBASE_RENT() {
    return BASE_RENT;
  }

  /**
   Get the rent of the RailRoad when the player owns two RailRoad
   squares.

   @return TWO_RENT The rent if the player owns two RailRoads.
   */
  public int getTWO_RENT() {
    return TWO_RENT;
  }

  /**
   Get the rent of the RailRoad when the player owns three RailRoad
   squares.

   @return THREE_RENT The rent if the player owns three RailRoads.
   */
  public int getTHREE_RENT() {
    return THREE_RENT;
  }

  /**
   Get the rent of the RailRoad when the player owns four RailRoad
   squares.

   @return FOUR_RENT The rent if the player owns four RailRoads.
   */
  public int getFOUR_RENT() {
    return FOUR_RENT;
  }
}
