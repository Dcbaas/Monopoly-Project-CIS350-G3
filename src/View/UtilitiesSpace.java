package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**********************************************************************
 * The Utilities Space draws a JPanel that resembles one of the
 * utilities squares from the Board of Monopoly.
 *
 * @author David Baas
 * @version 2/26/2018
 *********************************************************************/
public class UtilitiesSpace extends JPanel {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 200;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 400;
  
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
   * A boolean to track weather the utility is a Water Works or The Electric company.
   */
  private boolean waterworks;


  /********************************************************************
   * The constructor creates a UtilitySpace dpendent on what value is
   * set for the waterworks variable.
   * @param waterworks The type of utility this UtilitiesSpace will be.
   * @throws IOException If there is a problem loading the file.
   *******************************************************************/
  public UtilitiesSpace(boolean waterworks) throws IOException {
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
    drawLabels();
  }

  /********************************************************************
   * The drawLabels method is a private method that draws the
   * labels onto the JPanel.
   *******************************************************************/
  private void drawLabels() {
    GridBagConstraints g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 0;
    g.anchor = GridBagConstraints.NORTH;
    g.weighty = 2;
    add(name, g);

    g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 2;
    g.anchor = GridBagConstraints.SOUTH;
    add(price, g);
  }

  /********************************************************************
   * The paintComponent draws the utility icon onto the JPanel.
   * @param g The Graphics component being drawn.
   *******************************************************************/
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (waterworks) {
      g.drawImage(utilitiesImg, 130, 30, 110, 110, null);
    } else {
      g.drawImage(utilitiesImg, 25, 40, 130, 250, null);
    }
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
