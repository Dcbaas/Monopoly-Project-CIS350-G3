package Veiw;

import View.PropertySpace;
import View.PropertySpace.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**********************************************************************
 * This is a demo class for the group. It is not intended to be a concrete
 * test class.
 *********************************************************************/
@Deprecated
public class PropertySpaceDemo {

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("Tile Test");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(250, 450);

    PropertySpace space = new PropertySpace(Color.CYAN, "Parrot Town", 500, Position.RIGHT);

    JPanel buttonPanel = new JPanel();
    JButton inc = new JButton("Inc");
    JButton dec = new JButton("Dec");
    JButton quit = new JButton("Quit");

    inc.addActionListener(e -> {
      space.setHouses(space.getHouses() + 1);
    });
    dec.addActionListener(e -> {
      space.setHouses(space.getHouses() - 1);
    });
    quit.addActionListener(e -> {
      System.exit(0);
    });

    frame.setLayout(new BorderLayout());
    frame.add(space, BorderLayout.NORTH);
    buttonPanel.add(dec);
    buttonPanel.add(quit);
    buttonPanel.add(inc);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.setVisible(true);
  }
}
