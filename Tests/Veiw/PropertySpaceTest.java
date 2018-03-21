package Veiw;

import View.PropertySpace;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PropertySpaceTest {

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("Tile Test");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(200,400);

    PropertySpace space = new PropertySpace(Color.CYAN,"Parrot Town", 500);
   // frame.setLayout(new FlowLayout());
    frame.getContentPane().add(space);
    frame.setVisible(true);
  }
}
