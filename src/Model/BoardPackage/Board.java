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
    List<BoardSquare> squaresList = new ArrayList<>();
    int goID, jailId, goToJailID, freeParkID;

    public Board(String fileName) {
        //read file into stream, try-with-resources
        try (Stream<String> text = Files.lines(Paths.get(fileName))) {
            text.forEach(line -> {
                Scanner scnr = new Scanner(line).useDelimiter("[,\r\n]+");
                switch (scnr.nextInt()){
                    case 0:
                        squaresList.add(new PropertySquare(scnr.next(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt()));
                        break;
                    case 1:
                        squaresList.add(new RailRoadSquare(scnr.next(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),scnr.nextInt(),
                                scnr.nextInt(),scnr.nextInt()));
//                        break;
//                    case 3:
//                        new UtilitiesSquare(); //TODO: finish constructor logic
//                        break;
                    case 4:
                        squaresList.add(new FreeParkingSquare(scnr.next()));
                        break;
                    case 5:
                        squaresList.add(new GoSquare(scnr.next(), scnr.nextInt()));
                        break;
                    case 6:
                        squaresList.add(new JailSquare(scnr.next()));
                    case 7:
                        new GoToJailSquare(scnr.next());
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
//        return squaresList.get(position).getType(); //TODO:Create type for BoarSquareClass
    }

    public PropertySquare getProperty(int position) {
        return (PropertySquare) squaresList.get(position);
    }


    public void setPlayerPosition(Player player, int position) {
//        squaresList.get(player.getPosition()).removePlayer(player); //TODO: create position variable for Player class
        squaresList.get(position).addPlayer(player);
    }

    public List<BoardSquare> getProperties() {
        //finds all BoardSquare classes and returns them as an ArrayList
//        return squaresList.stream().filter(boardSquare -> boardSquare.getType() == 1).collect(Collectors.toCollection(ArrayList::new));
        return null;
    }

    public List<BoardSquare> getSquaresList() {
        return squaresList;
    }

    public void sendToJail(Player player) {
        setPlayerPosition(player, jailId);
    }
}

