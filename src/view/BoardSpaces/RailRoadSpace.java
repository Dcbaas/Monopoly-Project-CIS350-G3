package view.BoardSpaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**********************************************************************
 * The RailRoadSpace creates a space that resembles a RR space on the
 * board of Monopoly.
 *
 * @author David Baas
 * @version 3/24/2018
 *********************************************************************/
public class RailRoadSpace extends Spaces {

  /**
   * A final int to size the locomotive image.
   */
  public static final int IMG_SIZE = 50;

  /**
   * private int houseCounter; A final int to track the width of a housing panel.
   */
  private static final int WIDTH = 50;
  /**
   * A final int to track the height of a housing panel.
   */
  private static final int HEIGHT = 100;
  /**
   * An image of the Monopoly Locomotive.
   */

  private static Image railRoadImg;

  /**
   * A JLabel to display the name.
   */
  private JLabel name;

  /**
   * A JLabel for the price.
   */
  private JLabel price;

  /**
   * A boolean to track if this is a horizontal RR.
   */
  private boolean horizontal;

  /**
   * A Dimension to scale the size of the RRSpace.
   */
  private Dimension dimension;


  /********************************************************************
   * The constrctor creates and initializes all of the variables. It
   * formats the panel based off of if the horizontal flag is set to
   * true.
   * @param horizontal flag to determine if this space will be oriented
   * horizontal.
   * @param name The name of the RR this RRSpace represents.
   * @throws IOException If there is a problem loading the RR locomotive
   * image.
   *******************************************************************/
  public RailRoadSpace(boolean horizontal, String name) throws IOException {
    super(false);

    railRoadImg = ImageIO.read(new File("res/Monopoly RR.jpg"));

    dimension = new Dimension(WIDTH, HEIGHT);
    this.horizontal = false;

    if (horizontal) {
      dimension = new Dimension(HEIGHT, WIDTH);
      this.horizontal = horizontal;
    }

    setLayout(new GridBagLayout());
    //drawSpace();
    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);

  }

  /********************************************************************
   * The paint Component paints the image onto the screen.
   * @param g The Graphics component.
   *******************************************************************/
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!horizontal) {
      g.drawImage(railRoadImg, 0, 20, IMG_SIZE, IMG_SIZE, null);
    } else {
      g.drawImage(railRoadImg, 20, 0, IMG_SIZE, IMG_SIZE, null);
    }
    drawTokens(g);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the board space.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return dimension;
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
