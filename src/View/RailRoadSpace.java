package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**********************************************************************
 * The RailRoadSpace creates a space that resembles a RR space on the
 * board of Monopoly.
 *
 * @author David Baas
 * @version 3/24/2018
 *********************************************************************/
public class RailRoadSpace extends JPanel {

  public static final int WIDTH = 200;
  public static final int HEIGHT = 400;
  private Image railRoadImg;

  private JLabel name;

  private JLabel price;

  private boolean horizontal;

  private Dimension dimension;

  public RailRoadSpace(boolean horizontal, String name) throws IOException {
    this.name = new JLabel(name);
    price = new JLabel("$200");
    railRoadImg = ImageIO.read(new File("res/Monopoly RR.jpg"));

    dimension = new Dimension(WIDTH, HEIGHT);
    this.horizontal = false;

    if (horizontal) {
      dimension = new Dimension(HEIGHT, WIDTH);
      this.horizontal = horizontal;
    }

    setLayout(new GridBagLayout());

    drawSpace();

  }

  private void drawSpace() {
//    add(name,BorderLayout.NORTH);
//    //add(railRoadImg);
//
//    add(price, BorderLayout.SOUTH);
    GridBagConstraints g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 0;
    g.weighty = 1;
    g.anchor = GridBagConstraints.NORTH;

    add(name, g);

    g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 2;
    g.anchor = GridBagConstraints.SOUTH;
    add(price, g);
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!horizontal) {
      g.drawImage(railRoadImg, 10, 100, 150, 150, null);
    } else {
      g.drawImage(railRoadImg, 100, 10, 150, 150, null);
    }

  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the board space.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPrefferedSize the dimensions of the BoardSpace
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
