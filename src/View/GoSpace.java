package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GoSpace extends JPanel {


  private static final int LENGTH = 400;

  private static Image goImg;

  public GoSpace() throws IOException {
    goImg = ImageIO.read(new File("res/goImg.gif"));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(goImg, 0, 0, 400, 400, null);
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
