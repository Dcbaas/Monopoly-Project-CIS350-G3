package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**********************************************************************
 * The CardSpace class creates a JPanel that displays the icons of a
 * Chance or Community Chest spaces on the Board of Monopoly.
 *
 * @author David Baas
 * @version 3/25/2018
 *********************************************************************/
public class CardSpace extends JPanel {

  /**
   * A static final int to track the width of the tile.
   */
  private static final int WIDTH = 50;

  /**
   * A static final int to track the height of the tile.
   */
  private static final int HEIGHT = 100;

  /**
   * A boolean to track of this CardSpace is horizontal.
   */
  private boolean horizontal;

  /**
   * An Image to diplay a chance or CC icon.
   */
  private Image cardImg;

  /********************************************************************
   * The constructor creates a CardSpace tile and initializes what
   * image is displayed.
   * @param horizontal Boolean for if the CardSpace is horizontal.
   * @param chance Boolean for if this CardSpace is a Chance or CC
   * space.
   * @throws IOException if there is a problem loading the image
   * files.
   *******************************************************************/
  public CardSpace(boolean horizontal, boolean chance) throws IOException {

    if (chance) {
      cardImg = ImageIO.read(new File("res/chance.png"));
    } else {
      cardImg = ImageIO.read(new File("res/community-chest.png"));
    }

    this.horizontal = horizontal;

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);

  }

  /********************************************************************
   * The paint Component draws the image on the screen for the Tile.
   *
   * @param g The Graphics component drawing the image.
   *******************************************************************/
  //ToDo Draw the horizontal view better.
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(cardImg, 30, 50, 130, 180, null);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the CardSpace
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    if (horizontal) {
      return new Dimension(HEIGHT, WIDTH);
    }
    return new Dimension(WIDTH, HEIGHT);
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the CardSpace
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the CardSpace
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
