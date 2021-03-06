package view;

import Model.GamePackage.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JPanel;
import view.boardspaces.CardSpace;
import view.boardspaces.FreeParkingSpace;
import view.boardspaces.GoSpace;
import view.boardspaces.GoToJailSpace;
import view.boardspaces.JailSpace;
import view.boardspaces.PropertySpace;
import view.boardspaces.PropertySpace.Position;
import view.boardspaces.RailRoadSpace;
import view.boardspaces.Spaces;
import view.boardspaces.TaxSpace;
import view.boardspaces.UtilitiesSpace;

/********************************************************************
 * The GamePanel Class creates a JPanel that contains all of the
 * individual spaces that are a Monopoly Board.
 *
 * @author David Baas
 * @version 3/27/2018
 *******************************************************************/
public class GamePanel extends JPanel {

  public static final int LENGTH = 650;
  /**
   * An array of JPanels for all of the spaces of the board. A regular array was
   * chosen because of the static item size.
   */
  private Spaces[] spaces;

  /********************************************************************
   * Constructor initializes all of the instance variables and draws
   * the panel in the correct orientation.
   *******************************************************************/
  public GamePanel() {
    spaces = new Spaces[40];
    try {
      loadSpaces();
    } catch (IOException e) {
      e.printStackTrace();
    }

    setLayout(new GridBagLayout());
    drawSpaces();
  }

  /********************************************************************
   * Sets the number of houses on the space being modified.
   *
   * @param spaceNum The boardSpace
   * @param houses the number of houses being added.
   *******************************************************************/
  public void setHouses(int spaceNum, int houses) {
    Spaces temp;
    if (spaces[spaceNum] instanceof PropertySpace) {
      temp = (PropertySpace) spaces[spaceNum];
      ((PropertySpace) temp).setHouses(houses);
    }
  }

  /********************************************************************
   * The movePlayer method takes the player that first entered the space
   * and moves it a distance.
   * @param startSpace The starting space of the player moving.
   * @param distance The distance the player is moving.
   *******************************************************************/
  public void movePlayer(int startSpace, int distance, Player currentPlayer) {
    spaces[startSpace].removePlayer(currentPlayer);
    if (startSpace + distance >= spaces.length) {
      spaces[startSpace + distance - spaces.length].addPlayer(currentPlayer);
    } else {
      spaces[startSpace + distance].addPlayer(currentPlayer);
    }
  }

  /********************************************************************
   * Adds a player to the GoSquare on the view board.
   * @param player The player being added to the board.
   *******************************************************************/
  public void addPlayer(Player player) {
    spaces[0].addPlayer(player);
  }

  /********************************************************************
   * The clearBoard method clears the board of all tokens and houses.
   *******************************************************************/
  public void clearBoard() {
    for (Spaces space : spaces) {
      space.clearSpace();

      if (space instanceof PropertySpace) {
        ((PropertySpace) space).setHouses(0);
      }
    }
  }


  /********************************************************************
   * Returns the absolute minimum size the GamePanel can be.
   *
   * @return The minimum Dimensions this GamePanel can be.
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return new Dimension(LENGTH, LENGTH);
  }

  /********************************************************************
   * Loads all of the spaces from the standard American Monopoly Board.
   * @throws IOException if there is an problem loading any of the
   * assets for the board spaces.
   *******************************************************************/
  private void loadSpaces() throws IOException {
    spaces[0] = new GoSpace();
    spaces[1] = new PropertySpace(new Color(128, 0, 128), "Mediterranean Ave.",
        60, Position.BOTTOM);
    spaces[2] = new CardSpace(false, false);
    spaces[3] = new PropertySpace(new Color(128, 0, 128), "Baltic Ave.", 60,
        Position.BOTTOM);
    spaces[4] = new TaxSpace(true);
    spaces[5] = new RailRoadSpace(false, "Reading RailRoad");
    spaces[6] = new PropertySpace(Color.CYAN, "Oriental Ave.", 100,
        Position.BOTTOM);
    spaces[7] = new CardSpace(false, true);
    spaces[8] = new PropertySpace(Color.CYAN, "Vermont Ave", 100,
        Position.BOTTOM);
    spaces[9] = new PropertySpace(Color.CYAN, "Connecticut Ave", 120,
        Position.BOTTOM);
    spaces[10] = new JailSpace();

    spaces[11] = new PropertySpace(Color.MAGENTA, "St. Charles Place", 140,
        Position.LEFT);
    spaces[12] = new UtilitiesSpace(false);
    spaces[13] = new PropertySpace(Color.MAGENTA, "States Ave.", 140,
        Position.LEFT);
    spaces[14] = new PropertySpace(Color.MAGENTA, "Virgina Ave.", 160,
        Position.LEFT);
    spaces[15] = new RailRoadSpace(true, "Pennsylvania Railroad");
    spaces[16] = new PropertySpace(Color.ORANGE, "St. James Place", 180,
        Position.LEFT);
    spaces[17] = new CardSpace(true, false);
    spaces[18] = new PropertySpace(Color.ORANGE, "Tennessee Ave.", 180,
        Position.LEFT);
    spaces[19] = new PropertySpace(Color.ORANGE, "New York Ave.", 200,
        Position.LEFT);
    spaces[20] = new FreeParkingSpace();

    spaces[21] = new PropertySpace(Color.RED, "Kentucky Ave", 220,
        Position.TOP);
    spaces[22] = new CardSpace(false, true);
    spaces[23] = new PropertySpace(Color.RED, "Indiana Ave", 220, Position.TOP);
    spaces[24] = new PropertySpace(Color.RED, "Illinois Ave", 240,
        Position.TOP);
    spaces[25] = new RailRoadSpace(false, "B & O Railroad");
    spaces[26] = new PropertySpace(Color.YELLOW, "Atlantic Ave", 260,
        Position.TOP);
    spaces[27] = new PropertySpace(Color.YELLOW, "Ventnor Ave", 260,
        Position.TOP);
    spaces[28] = new UtilitiesSpace(true);
    spaces[29] = new PropertySpace(Color.YELLOW, "Marvin Ave", 280,
        Position.TOP);
    spaces[30] = new GoToJailSpace();

    spaces[31] = new PropertySpace(Color.GREEN, "Pacific Ave.", 300,
        Position.RIGHT);
    spaces[32] = new PropertySpace(Color.GREEN, "North Carolina Ave.", 300,
        Position.RIGHT);
    spaces[33] = new CardSpace(true, false);
    spaces[34] = new PropertySpace(Color.GREEN, "Pennsylvania Ave.", 320,
        Position.RIGHT);
    spaces[35] = new RailRoadSpace(true, "Short Line");
    spaces[36] = new CardSpace(true, true);
    spaces[37] = new PropertySpace(new Color(0, 0, 139), "Park Place", 350,
        Position.RIGHT);
    spaces[38] = new TaxSpace(false);
    spaces[39] = new PropertySpace(new Color(0, 0, 139), "Parrot Town", 400,
        Position.RIGHT);


  }

  /********************************************************************
   * Draws the spaces in order onto the panel. For loops were uses as it
   * reduced on the amount of lines that were needed to generate the
   * board.
   *******************************************************************/
  private void drawSpaces() {
    GridBagConstraints g;

    for (int x = 0; x < 10; ++x) {
      g = new GridBagConstraints();
      coordinateSelector(g, x, 0);
      add(spaces[20 + x], g);
    }

    for (int y = 1; y < 10; ++y) {
      g = new GridBagConstraints();
      coordinateSelector(g, 0, y);
      g.anchor = GridBagConstraints.WEST;
      add(spaces[20 - y], g);
    }

    for (int y = 0; y < 10; ++y) {
      g = new GridBagConstraints();
      coordinateSelector(g, 10, y);

      add(spaces[30 + y], g);
    }

    for (int x = 0; x < 11; ++x) {
      g = new GridBagConstraints();
      coordinateSelector(g, x, 10);
      add(spaces[10 - x], g);
    }
  }

  /********************************************************************
   * With GridBag Coordinates being used often, the
   * coordinateSelector was used to cut down on repeated coordinate
   * changes in the drawSpaces class.
   *******************************************************************/
  private void coordinateSelector(GridBagConstraints g, int x, int y) {
    g.gridx = x;
    g.gridy = y;
  }
}
