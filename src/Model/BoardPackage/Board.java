package Model.BoardPackage;

import Model.GamePackage.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/******************************************************************************************************
 * This class represents the monopoly game board, it's main purpose is to handle player movement.
 *
 * @author Santiago Quiroga David Baas
 * @since 2/18/2018
 * @version 2/21/2018
 *****************************************************************************************************/
public class Board {

  /**
   * The list of all saquares in the board
   */
  List<BoardSquare> squaresList;

  /******************************************************************************************************
   * This constructor takes a txt file and creates a board according to the specifications.
   * file format:
   *  [Type],[Name],[Constructor parameters]
   *
   * @param fileName The name of the file which will be used to generate the board.
   * @throws IOException if there is a problem reading the file.
   *****************************************************************************************************/
  public Board(String fileName) {
    //Instantiates the squares list
    squaresList = new ArrayList<>();
    //read file into stream,
    try (Stream<String> text = Files.lines(Paths.get(fileName))) {

      //Iterates thought every line in the stream
      text.forEach(line -> {
        //Defines and instantiates a Scanner to read through the each line
        Scanner scanner = new Scanner(line).useDelimiter("[,\r\n]+");

        //Checks for the BoardSquare type in order to call the correct constructor.
        switch (scanner.nextInt()) {
          case 0:
            squaresList.add(new PropertySquare(scanner.next(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt()));
            break;
          case 1:
            squaresList.add(new RailRoadSquare(scanner.next(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt()));
            break;
          case 2:
            squaresList.add(new BoardSquare(scanner.next(), scanner.nextInt()) {
            });
            break;
          case 3:
            squaresList
                .add(new UtilitiesSquare(scanner.next(), scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt()));
            break;
          case 4:
            squaresList.add(new FreeParkingSquare(scanner.next()));
            break;
          case 5:
            squaresList.add(new GoSquare(scanner.next(), scanner.nextInt()));
            break;
          case 6:
            squaresList.add(new JailSquare(scanner.next()));
            break;
          case 7:
            squaresList.add(new GoToJailSquare(scanner.next()));
            break;
          default:
            new IOException();
            break;
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /******************************************************************************************************
   * Returns the location type of a BoardSquare object based on its position.
   *
   * @param position The location of the BoardSquare with relation to the whole Board.
   * @return The integer type of BoardSquare that is at the input position.
   *****************************************************************************************************/
  public int getLocationType(int position) {
    return squaresList.get(position).getType();
  }

  /******************************************************************************************************
   *  Returns an OwnableSquare based on its position.
   *
   * @param position The loction of an OwnableSquare with relation to the whole Board.
   * @return An OwnableSquare instance that is the position input.
   *****************************************************************************************************/
  //TODO: Do we want to throw a custom exception when a boardsquare that isn't ownable is accessed using this method?
  public OwnableSquare getOwnableSquare(int position) {
    return (OwnableSquare) squaresList.get(position);
  }


  /******************************************************************************************************
   *  Moves a player to the given position.
   *
   * @param player The Player being moved on the board.
   * @param position The location where they are being moved to.
   *****************************************************************************************************/
  public void setPlayerPosition(Player player, int position) {
    squaresList.get(player.getPosition()).removePlayer(player);
    squaresList.get(position).addPlayer(player);
  }


  /******************************************************************************************************
   *  Returns a list of all  OwnableSquares in the board
   *
   * @return A list of all OwnableSquares on this Board.
   *****************************************************************************************************/
  public ArrayList<OwnableSquare> getOwnableSquares() {
    // finds all BoardSquare classes and returns them as an ArrayList
    return squaresList.stream().filter(boardSquare ->
        (boardSquare.getType() == 0) || boardSquare.getType() == 1 || boardSquare.getType() == 3)
        .map(boardSquare -> (OwnableSquare) boardSquare)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /******************************************************************************************************
   *  Return the list of BoardSquares on this Board.
   *
   * @return squareList The list of all BoardSquares on this Board.
   *****************************************************************************************************/
  public List<BoardSquare> getSquaresList() {
    return squaresList;
  }

  /******************************************************************************************************
   *  Moves a player to the jail square
   *
   * @param player The Player being sent to jail.
   *****************************************************************************************************/
  public void sendToJail(Player player) {
    setPlayerPosition(player, 10);
  }


  /******************************************************************************************************
   * Returns a list of all the squares belonging to a single group.
   *
   * @param groupId The integer that represents a specific group of OwnableSquares
   * @return A list of OwnableSquares that are in the grouping input.
   *****************************************************************************************************/
  public List<OwnableSquare> getGroup(int groupId) {
    return squaresList.stream().map(squaresList -> (OwnableSquare) squaresList)
        .filter(ownableSquare -> ownableSquare.getGROUP_NUMBER() == groupId)
        .collect(Collectors.toList());
  }

  public ArrayList<BoardSquare> getLocationByType(int typeID) {

    return squaresList.stream().filter(boardSquare -> boardSquare.getType() == typeID)
        .collect(Collectors.toCollection(ArrayList<BoardSquare>::new));
  }

}

