package view.boardspaces;

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
 * The Utilities Space draws a JPanel that resembles one of the
 * utilities squares from the Board of Monopoly.
 *
 * @author David Baas
 * @version 2/26/2018
 *********************************************************************/
public class UtilitiesSpace extends Spaces {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 50;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 100;

  /**
   * A static final JLabel for the price of the utility.
   */
  private static final JLabel price = new JLabel("$150");

  /**
   * An Image for the icon of the utility.
   */
  private Image utilitiesImg;

  /**
   * A JLabel for the name of the utility.
   */
  private JLabel name;

  /**
   * A Dimension to track the dimensions of the utility.
   */
  private Dimension dimension;

  /**
   * A boolean to track weather the utility is a Water Works or The Electric
   * company.
   */
  private boolean waterworks;


  /********************************************************************
   * The constructor creates a UtilitySpace dpendent on what value is
   * set for the waterworks variable.
   * @param waterworks The type of utility this UtilitiesSpace will be.
   * @throws IOException If there is a problem loading the file.
   *******************************************************************/
  public UtilitiesSpace(boolean waterworks) throws IOException {
    super(false);
    this.waterworks = waterworks;

    if (waterworks) {
      dimension = new Dimension(WIDTH, HEIGHT);
      utilitiesImg = ImageIO.read(new File("res/waterWorks.png"));
      name = new JLabel("Water Works");
    } else {
      dimension = new Dimension(HEIGHT, WIDTH);
      //Todo: Find a Better image.
      utilitiesImg = ImageIO.read(new File("res/electricCompany.jpg"));
      name = new JLabel("Electric Company");
    }
    setLayout(new GridBagLayout());
    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }

  /********************************************************************
   * The paintComponent draws the utility icon onto the JPanel.
   * @param g The Graphics component being drawn.
   *******************************************************************/
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (waterworks) {
      g.drawImage(utilitiesImg, 0, 20, 50, 50, null);
    } else {
      g.drawImage(utilitiesImg, 0, 0, 50, 50, null);
    }
    drawTokens(g, waterworks);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the UtilitiesSpace.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return dimension;
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the UtilitiesSpace.
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the UtilitiesSpace.
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
