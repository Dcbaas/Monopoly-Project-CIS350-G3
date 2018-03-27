package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CenterImage extends JPanel {

  private static Image monopolyLogo;

  public CenterImage() throws IOException {
    monopolyLogo = ImageIO.read(new File("res/MonopolyLogo.jpg"));
  }

  public void paintComponent(Graphics g) {
    g.drawImage(monopolyLogo, 0, 0, 900, 900, null);
  }


  @Override
  public Dimension getMinimumSize() {
    return new Dimension(900, 900);
  }
}
