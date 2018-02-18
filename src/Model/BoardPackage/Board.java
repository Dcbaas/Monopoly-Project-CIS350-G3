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
 * This class represents the monopoly game board, it's main purpouse is to handle player movement.
 *
 * @author Santiago Quiroga
 * @version 2/18/2018
 *****************************************************************************************************/
public class Board {
    /** The list of all saquares in the board */
    List<BoardSquare> squaresList;

    /******************************************************************************************************
     * This constructor takes a txt file and creates a board according to the specifications.
     * file format:
     *  [Type],[Name],[Constructor parameters]
     *
     * @param fileName
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
                switch (scanner.nextInt()){
                    case 0:
                        squaresList.add(new PropertySquare(scanner.next(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),
                                scanner.nextInt(),scanner.nextInt(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),
                                scanner.nextInt(),scanner.nextInt(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
                        break;
                    case 1:
                        squaresList.add(new RailRoadSquare(scanner.next(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),
                                scanner.nextInt(),scanner.nextInt(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
                        break;
                    case 2:
                        squaresList.add(new BoardSquare(scanner.next(),scanner.nextInt()) {
                        });
                        break;
                    case 3:
                        squaresList.add(new UtilitiesSquare(scanner.next(),scanner.nextInt(), scanner.nextInt(),
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
     * Returns the location type of the BoardSquare object based on its position.
     *
     * @param position
     * @return
     *****************************************************************************************************/
    public int getLocationType(int position) {
        return squaresList.get(position).getType();
    }

    /******************************************************************************************************
     *  Returns an OwnableSquare based on its position.
     *
     * @param position
     * @return
     *****************************************************************************************************/
    public OwnableSquare getOwnableSquare(int position) {
        return (OwnableSquare) squaresList.get(position);
    }


    /******************************************************************************************************
     *  Move a player to the given position.
     *
     * @param player
     * @param position
     *****************************************************************************************************/
    public void setPlayerPosition(Player player, int position) {
        squaresList.get(player.getPosition()).removePlayer(player);
        squaresList.get(position).addPlayer(player);
    }


    /******************************************************************************************************
     *  Returns a list of all  OwnableSquares in the board
     *
     * @return
     *****************************************************************************************************/
    public ArrayList<OwnableSquare> getOwnableSquares() {
        // finds all BoardSquare classes and returns them as an ArrayList
      return squaresList.stream().filter(boardSquare ->
              (boardSquare.getType() == 0) || boardSquare.getType() == 1 || boardSquare.getType() == 3)
              .map(boardSquare -> (OwnableSquare) boardSquare).collect(Collectors.toCollection(ArrayList::new));
    }

    /******************************************************************************************************
     *  Return the list of BoardSquare.
     *
     * @return
     *****************************************************************************************************/
    public List<BoardSquare> getSquaresList() {
        return squaresList;
    }

    /******************************************************************************************************
     *  Moves a player to the jail square
     *
     * @param player
     *****************************************************************************************************/
    public void sendToJail(Player player) {
        setPlayerPosition(player, 10);
    }


    /******************************************************************************************************
     * Returns a list of all the squares belonging to a single group.
     *
     * @param groupId
     * @return
     *****************************************************************************************************/
    public List<OwnableSquare> getGroup(int groupId){
        return squaresList.stream().filter(square -> square.getGROUP_NUMBER() == groupId)
                .map(boardSquare -> (OwnableSquare) boardSquare ).collect(Collectors.toCollection(ArrayList::new));
    }
}

