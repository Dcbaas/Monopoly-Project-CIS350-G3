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
 * @version 3/21/2018
 *********************************************************************/
public class HousingPanel extends JPanel {

  /**An integer to track how many houses are on the tile.*/
  private int houseCounter;

  /**A static Image to track an image of a Monopoly house.*/
  private static Image houseImg;

  /**A static Image to track an image of a Monopoly hotel.*/
  private static Image hotelImg;

   /*******************************************************************
    * The constructor creates the images and sets the hotels to zero as
    * well as sets the background of the panel depending on what
    * grouping the parent property belongs to.
    * @param color the Color this HousingPanel will be set to.
    * @throws IOException If there is a failure loading either housing
    * image.
    ******************************************************************/
  public HousingPanel(Color color) throws IOException {
    houseCounter = 0;

    houseImg = ImageIO.read(new File("res/MonopolyHouse.png"));
    //Todo: Create a hotel Img and instantiate.
    setLayout(null);
    setBackground(Color.CYAN);
    //setPreferredSize(new Dimension(150,100));
    //setVisible(true);
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
    this.houseCounter = houseCounter;
  }

  /*******************************************************************
   * The paintComponent draws the houses in the panel based off the
   * current number of houses this panel currently has.
   * @param g Graphics component of this panel.
   ******************************************************************/
  @Override
  public void paintComponent(Graphics g) {
    switch (houseCounter) {
      case 1:
        g.drawImage(houseImg,0,0,50,30,null);
        break;
      case 2:
        g.drawImage(houseImg,0,0,null);
        g.drawImage(houseImg,0,25,null);
        break;
      case 3:
        g.drawImage(houseImg,0,0,null);
        g.drawImage(houseImg,0,25,null);
        g.drawImage(houseImg,0,50,null);
        break;
      case 4:
        g.drawImage(houseImg,0,0,null);
        g.drawImage(houseImg,0,25,null);
        g.drawImage(houseImg,0,50,null);
        g.drawImage(houseImg,0,75,null);
        break;
      case 5:
        break;
      default:
    }
  }
}
