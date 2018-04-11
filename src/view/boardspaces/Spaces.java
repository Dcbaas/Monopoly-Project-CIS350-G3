package view.boardspaces;


import Model.GamePackage.Player;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**********************************************************************
 * The Spaces class is an abstract class which all of the boardspaces
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
  protected LinkedList<Player> onSpace;

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
  public void addPlayer(Player playerToken) {
    onSpace.add(playerToken);
    repaint();
    revalidate();
  }

  /********************************************************************
   * Removes a playerToken from this space and returns its object.
   * @return playerToken being removed.
   *******************************************************************/
  public Player removePlayer() {
    Player p = onSpace.pollFirst();
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

    for (Player player : onSpace) {
      if (i >= 2) {
        graphics.drawImage(player.getToken(), 15+ offset, 40 , 20, 20, null);
      }
      else {
        graphics.drawImage(player.getToken(), 15 + offset, 40, 20, 20, null);
      }
      offset += 20;
    }
  }
}
