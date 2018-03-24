package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**********************************************************************
 * The HousingPanel provide abstraction for drawing houses on a
 * property tile on the board. It handles all of the functionality of
 * drawing or removing a house or hotel in the space where they are
 * placed on a board square.
 *
 * @author David Baas
 * @since 3/21/2018
 * @version 3/24/2018
 *********************************************************************/
public class HousingPanel extends JPanel {

  /**
   * A final int to track the width of a housing panel.
   */
  private static final int WIDTH = 200;

  /**
   * A final int to track the height of a housing panel.
   */
  private static final int HEIGHT = 100;

  /**
   * A static Image to track an image of a Monopoly house.
   */
  private static Image houseImg;

  /**
   * A static Image to track an image of a Monopoly hotel.
   */
  private static Image hotelImg;

  /**
   * A final boolean to track if the housing panel is vertical
   */
  private final boolean VERTICAL;

  /**
   * An integer to track how many houses are on the tile.
   */
  private int houseCounter;

  /*******************************************************************
   * The constructor creates the images and sets the hotels to zero as
   * well as sets the background of the panel depending on what
   * grouping the parent property belongs to.
   * @param color the Color this HousingPanel will be set to.
   * @throws IOException If there is a failure loading either housing
   * image.
   ******************************************************************/
  public HousingPanel(Color color, boolean vertical) throws IOException {
    houseCounter = 0;

    houseImg = ImageIO.read(new File("res/MonopolyHouse.png"));
    hotelImg = ImageIO.read(new File("res/MonopolyHotel.png"));

    VERTICAL = vertical;
    setOpaque(true);
    setBackground(color);
  }

  /*******************************************************************
   * The getHouses method returns the number of houses on this panel.
   *
   * @return houseCounter The number houses on this panel.
   ******************************************************************/
  public int getHouses() {
    return houseCounter;
  }

  /*******************************************************************
   * The setHouses method sets the number of houses on this panel.
   * @param houseCounter the number of houses on this panel.
   ******************************************************************/
  public void setHouses(int houseCounter) {
    if (houseCounter > 5) {
      this.houseCounter = 5;
    } else if (houseCounter < 0) {
      this.houseCounter = 0;
    } else {
      this.houseCounter = houseCounter;
    }
    repaint();
    revalidate();
  }

  /*******************************************************************
   * The paintComponent draws the houses in the panel based off the
   * current number of houses this panel currently has.
   * @param g Graphics component of this panel.
   ******************************************************************/
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (VERTICAL) {
      drawVertical(g);
    } else {
      drawHorizontal(g);
    }
  }

  /*******************************************************************
   * The drawHorizontal method draws the houses horizontally for the
   * paint component.
   ******************************************************************/
  private void drawHorizontal(Graphics g) {
    switch (houseCounter) {
      case 1:
        g.drawImage(houseImg, 10, 20, 40, 50, null);
        break;
      case 2:
        g.drawImage(houseImg, 10, 20, 40, 50, null);
        g.drawImage(houseImg, 50, 20, 40, 50, null);
        break;
      case 3:
        g.drawImage(houseImg, 10, 20, 40, 50, null);
        g.drawImage(houseImg, 50, 20, 40, 50, null);
        g.drawImage(houseImg, 90, 20, 40, 50, null);
        break;
      case 4:
        g.drawImage(houseImg, 10, 20, 40, 50, null);
        g.drawImage(houseImg, 50, 20, 40, 50, null);
        g.drawImage(houseImg, 90, 20, 40, 50, null);
        g.drawImage(houseImg, 130, 20, 40, 50, null);
        break;
      case 5:
        g.drawImage(hotelImg, 70, 30, 50, 40, null);
        break;
      default:
    }
  }

  /*******************************************************************
   * The drawHorizontal method draws the houses vertically for the
   * paint component.
   ******************************************************************/
  private void drawVertical(Graphics g) {
    switch (houseCounter) {
      case 1:
        g.drawImage(houseImg, 20, 10, 40, 50, null);
        break;
      case 2:
        g.drawImage(houseImg, 20, 10, 40, 50, null);
        g.drawImage(houseImg, 20, 50, 40, 50, null);
        break;
      case 3:
        g.drawImage(houseImg, 20, 10, 40, 50, null);
        g.drawImage(houseImg, 20, 50, 40, 50, null);
        g.drawImage(houseImg, 20, 90, 40, 50, null);
        break;
      case 4:
        g.drawImage(houseImg, 20, 10, 40, 50, null);
        g.drawImage(houseImg, 20, 50, 40, 50, null);
        g.drawImage(houseImg, 20, 90, 40, 50, null);
        g.drawImage(houseImg, 20, 130, 40, 50, null);
        break;
      case 5:
        g.drawImage(hotelImg, 30, 70, 50, 40, null);
        break;
      default:
    }
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the housing panel.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    if (VERTICAL) {
      return new Dimension(HEIGHT, WIDTH);
    }
    return new Dimension(WIDTH, HEIGHT);
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the housingPanel.
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the housingPanel.
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
