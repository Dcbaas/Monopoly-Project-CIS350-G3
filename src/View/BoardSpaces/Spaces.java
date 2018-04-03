package View.BoardSpaces;


import View.PlayerToken;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**********************************************************************
 * The Spaces class is an abstract class which all of the BoardSpaces
 * draw from primarily to draw player tokens on the screen.
 *
 * @author David Baas
 * @version 4/3/2018
 *********************************************************************/
public abstract class Spaces extends JPanel {

  /**
   * A linked list to track player tokens on this space of the board.
   */
  /*
   * A linked list was chosen because of the order of how players
   * enter and leave this space is always constant.
   */
  protected LinkedList<PlayerToken> onSpace;

  /**
   * A boolean to track if this space is on a corner.
   */
  protected boolean sqSpace;

  /********************************************************************
   * The Constructor initializes the linked list and sets the sqSpace
   * flag.
   * @param sqSpace boolean for weather this space is a corner.
   *******************************************************************/
  public Spaces(boolean sqSpace) {
    onSpace = new LinkedList<>();
    this.sqSpace = sqSpace;
  }

  /********************************************************************
   * Adds a playerToken onto this space.
   * @param playerToken the PlayerToken being added.
   *******************************************************************/
  public void addPlayer(PlayerToken playerToken) {
    onSpace.add(playerToken);
    repaint();
    revalidate();
  }

  /********************************************************************
   * Removes a playerToken from this space and returns its object.
   * @return playerToken being removed.
   *******************************************************************/
  public PlayerToken removePlayer() {
    PlayerToken p = onSpace.pollFirst();
    repaint();
    revalidate();
    return p;
  }

  /********************************************************************
   * Draws the token on the space.
   * @param graphics the Graphics component being used to draw.
   *******************************************************************/
  protected void drawTokens(Graphics graphics) {
    int i = 1;
    int offset = 0;

    for (PlayerToken p : onSpace) {
      if (i >= 2) {
        graphics.drawImage(p.getToken(), 15, 20 + offset, 10, 10, null);
      } else {
        graphics.drawImage(p.getToken(), 5, 5 + offset, 10, 10, null);
      }
    }
  }
}
