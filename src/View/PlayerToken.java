package View;

import Model.GamePackage.Player;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerToken{
  private Image token;

  private Player parent;

  public PlayerToken(String fileName, Player parent) throws IOException {
    token = ImageIO.read(new File(fileName));
    this.parent = parent;
  }

  public Image getToken() {
    return token;
  }
}
