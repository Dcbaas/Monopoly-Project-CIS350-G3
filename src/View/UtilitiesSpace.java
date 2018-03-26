package View;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class UtilitiesSpace extends JPanel {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 200;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 400;

  private static Image utilitiesImg;

  private Dimension dimension;

  public UtilitiesSpace(boolean waterworks) throws IOException {
    if (waterworks) {
      dimension = new Dimension(WIDTH, HEIGHT);
      utilitiesImg = ImageIO.read(new File("res/waterWorks.png"));
    } else {
      dimension = new Dimension(HEIGHT, WIDTH);
      utilitiesImg = ImageIO.read(new File("res/electricCompany.jpg"));
    }
  }

  
}
