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

/**
 * Created by unclear on 2/5/18.
 */
public class Board {
    List<BoardSquare> squaresList;

    public Board(String fileName) {
        squaresList = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> text = Files.lines(Paths.get(fileName))) {
            text.forEach(line -> {
                Scanner scnr = new Scanner(line).useDelimiter("[,\r\n]+");
                switch (scnr.nextInt()){
                    case 0:
                        squaresList.add(new PropertySquare(scnr.next(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt()));
                        break;
                    case 1:
                        squaresList.add(new RailRoadSquare(scnr.next(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt()));
                        break;
                    case 2:
                        squaresList.add(new BoardSquare(scnr.next(),scnr.nextInt()) {
                        });
                        break;
                    case 3:
                        squaresList.add(new UtilitiesSquare(scnr.next(),scnr.nextInt(), scnr.nextInt(), scnr.nextInt()));
                        break;
                    case 4:
                        squaresList.add(new FreeParkingSquare(scnr.next()));
                        break;
                    case 5:
                        squaresList.add(new GoSquare(scnr.next(), scnr.nextInt()));
                        break;
                    case 6:
                        squaresList.add(new JailSquare(scnr.next()));
                        break;
                    case 7:
                        squaresList.add(new GoToJailSquare(scnr.next()));
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

    public int getLocationType(int position) {
        return squaresList.get(position).getType();
    }

    public OwnableSquare getProperty(int position) {
        return (OwnableSquare) squaresList.get(position);
    }


    public void setPlayerPosition(Player player, int position) {
        squaresList.get(player.getPosition()).removePlayer(player);
        squaresList.get(position).addPlayer(player);
    }

    public ArrayList<OwnableSquare> getOwnableSquares() {
        // finds all BoardSquare classes and returns them as an ArrayList
      return squaresList.stream().filter(boardSquare -> (boardSquare.getType() == 0) || boardSquare.getType() == 1 || boardSquare.getType() == 3).map(boardSquare -> (OwnableSquare) boardSquare).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<BoardSquare> getSquaresList() {
        return squaresList;
    }

    public void sendToJail(Player player) {
//        player.setInJail(0);
        setPlayerPosition(player, 10);
    }
}

