package view.boardspaces;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/**********************************************************************
 * The GoSpace class creates a JPanel that resembles a Go Square from
 * the game of Monopoly.
 *
 * @author David Baas
 * @version 3/26/2018
 *********************************************************************/
public class GoSpace extends Spaces {

  /**
   * A static int constant to track the length of an edge in the square.
   */
  private static final int LENGTH = 100;

  /**
   * An image to hold the image of the Go icon.
   */
  private static Image goImg;


  /********************************************************************
   * The constructor initializes the image.
   *
   * @throws IOException If there is an error in loading the image.
   *******************************************************************/
  public GoSpace() throws IOException {
    super(true);

    goImg = ImageIO.read(new File("res/goImg.gif"));

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }

  /*******************************************************************
   * The paintComponent draws the Go Image onto the JPanel.
   *
   * @param g Graphics component of this panel.
   ******************************************************************/
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(goImg, 0, 0, LENGTH, LENGTH, null);
    drawTokens(g);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the GoSquare.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(LENGTH, LENGTH);
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the GoSquare.
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the GoSquare.
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
