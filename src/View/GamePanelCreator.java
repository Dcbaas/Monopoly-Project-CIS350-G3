package View;

import View.PropertySpace.Position;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GamePanelCreator extends JPanel {

  private JPanel[] spaces;
  private CenterImage centerImage;


  public GamePanelCreator() {
    spaces = new JPanel[40];
    try {
      loadSpaces();
      centerImage = new CenterImage();
    } catch (IOException e) {
      e.printStackTrace();
    }

    setLayout(new GridBagLayout());
    drawSpaces();

  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension(600, 600);
  }

  private void loadSpaces() throws IOException {
    spaces[0] = new GoSpace();
    spaces[1] = new PropertySpace(new Color(128, 0, 128), "Meiteranian Ave.", 60, Position.BOTTOM);
    spaces[2] = new CardSpace(false, false);
    spaces[3] = new PropertySpace(new Color(128, 0, 128), "Baltic Ave.", 60, Position.BOTTOM);
    spaces[4] = new TaxSpace(true);
    spaces[5] = new RailRoadSpace(false, "Reading RailRoad");
    spaces[6] = new PropertySpace(Color.CYAN, "Oriental Ave.", 100, Position.BOTTOM);
    spaces[7] = new CardSpace(false, true);
    spaces[8] = new PropertySpace(Color.CYAN, "Vermont Ave", 100, Position.BOTTOM);
    spaces[9] = new PropertySpace(Color.CYAN, "Connecticut Ave", 120, Position.BOTTOM);
    spaces[10] = new JailSpace();

    spaces[11] = new PropertySpace(Color.MAGENTA, "St. Charles Place", 140, Position.LEFT);
    spaces[12] = new UtilitiesSpace(false);
    spaces[13] = new PropertySpace(Color.MAGENTA, "States Ave.", 140, Position.LEFT);
    spaces[14] = new PropertySpace(Color.MAGENTA, "Virgina Ave.", 160, Position.LEFT);
    spaces[15] = new RailRoadSpace(true, "Pennsylvania Railroad");
    spaces[16] = new PropertySpace(Color.ORANGE, "St. James Place", 180, Position.LEFT);
    spaces[17] = new CardSpace(true, false);
    spaces[18] = new PropertySpace(Color.ORANGE, "Tennessee Ave.", 180, Position.LEFT);
    spaces[19] = new PropertySpace(Color.ORANGE, "New York Ave.", 200, Position.LEFT);
    spaces[20] = new FreeParkingSpace();

    spaces[21] = new PropertySpace(Color.RED, "Kentucky Ave", 220, Position.TOP);
    spaces[22] = new CardSpace(false, true);
    spaces[23] = new PropertySpace(Color.RED, "Indiana Ave", 220, Position.TOP);
    spaces[24] = new PropertySpace(Color.RED, "Illinois Ave", 240, Position.TOP);
    spaces[25] = new RailRoadSpace(false, "B & O Railroad");
    spaces[26] = new PropertySpace(Color.YELLOW, "Atlantic Ave", 260, Position.TOP);
    spaces[27] = new PropertySpace(Color.YELLOW, "Ventnor Ave", 260, Position.TOP);
    spaces[28] = new UtilitiesSpace(true);
    spaces[29] = new PropertySpace(Color.YELLOW, "Marvin Ave", 280, Position.TOP);
    spaces[30] = new GoToJailSpace();

    spaces[31] = new PropertySpace(Color.GREEN, "Pacific Ave.", 300, Position.RIGHT);
    spaces[32] = new PropertySpace(Color.GREEN, "North Carolina Ave.", 300, Position.RIGHT);
    spaces[33] = new CardSpace(true, false);
    spaces[34] = new PropertySpace(Color.GREEN, "Pennsylvania Ave.", 320, Position.RIGHT);
    spaces[35] = new RailRoadSpace(true, "Short Line");
    spaces[36] = new CardSpace(true, true);
    spaces[37] = new PropertySpace(new Color(0, 0, 139), "Park Place", 350, Position.RIGHT);
    spaces[38] = new TaxSpace(false);
    spaces[39] = new PropertySpace(new Color(0, 0, 139), "Parrot Town", 400, Position.RIGHT);


  }

  private void drawSpaces() {
    GridBagConstraints g;

//    for (int x = 0; x < 10; ++x) {
//      g = new GridBagConstraints();
//      coordinateSelector(g, x, 0);
//      //g.anchor = GridBagConstraints.NORTH;
//      System.out.println(20 + x);
//      add(spaces[20 + x], g);
//    }

//    for (int y = 1; y < 11; ++y) {
//      g = new GridBagConstraints();
//      coordinateSelector(g, 1, y);
//     // g.anchor = GridBagConstraints.WEST;
//      System.out.println(20 + y);
//      add(spaces[20 - y], g);
//    }
//
    for (int y = 0; y < 10; ++y) {
      g = new GridBagConstraints();
      coordinateSelector(g, 10, y);
      //g.anchor = GridBagConstraints.EAST;
      System.out.println(30 + y);
      add(spaces[30 + y], g);
    }
//
//    for (int x = -1; x < 11; ++x) {
//      g = new GridBagConstraints();
//      coordinateSelector(g, x, 10);
//      //g.anchor = GridBagConstraints.SOUTH;
//      System.out.println(10 - x);
//      add(spaces[10 - x], g);
//    }

    g = new GridBagConstraints();
    g.gridx = 1;
    g.gridy = 1;
    g.gridheight = 8;
    g.gridwidth = 8;
    g.weighty = 8;
    g.weightx = 8;
    // g.anchor = GridBagConstraints.CENTER;
    add(centerImage, g);

  }

  private void coordinateSelector(GridBagConstraints g, int x, int y) {
    g.gridx = x;
    g.gridy = y;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("PARROT");

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.setSize(600, 600);

    GamePanelCreator game = new GamePanelCreator();
    frame.add(game);

    frame.setVisible(true);


  }
}
