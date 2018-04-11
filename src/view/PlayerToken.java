package view;

import Model.GamePackage.Player;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**********************************************************************
 * The PlayerToken class creates a PlayerToken Image that can be used
 * to dispaly the player on the board.
 *********************************************************************/
@Deprecated
public class PlayerToken {

  /**
   * An image for the token piece.
   */
  private Image token;

  /**
   * An reference to the Player this token is for.
   */
  private Player parent;
  //Todo: Make the PlayerToken reference the Parent or merge this functionality of an image into Player itself.

  /******************************************************************
   * The constructor initializes the instance variables.
   *
   * @param fileName The image that is being displayed for this
   * PlayerToken.
   * @param parent The reference to the parent Player.
   * @throws IOException if there is a problem Loading a file.
   */
  public PlayerToken(String fileName, Player parent) throws IOException {
    token = ImageIO.read(new File(fileName));
    this.parent = parent;
  }

  /******************************************************************
   * Get the image for this PlayerToken.
   * @return The image for this PlayerToken.
   *******************************************************************/
  public Image getToken() {
    return token;
  }
}
