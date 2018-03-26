package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CardSpace extends JPanel {

  private boolean horizontal;

  private Image cardImg;

  private static final int WIDTH = 200;

  private static final int HEIGHT = 400;

  public CardSpace(boolean horizontal, boolean chance) throws IOException {

    if (chance) {
      cardImg = ImageIO.read(new File("res/chance.png"));
    } else {
      cardImg = ImageIO.read(new File("res/community-chest.png"));
    }

    this.horizontal = horizontal;
  }

  //ToDo Draw the horizontal view better.
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(cardImg, 30, 50, 130, 180, null);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the board space.
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
