package view.boardspaces;


import Model.GamePackage.Player;
import java.awt.Graphics;
import java.util.ArrayList;
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
  protected ArrayList<Player> onSpace;

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
    onSpace = new ArrayList<>();
    this.sqSpace = sqSpace;
  }

  /********************************************************************
   * Adds a playerToken onto this space.
   * @param player the Player being added.
   *******************************************************************/
  public void addPlayer(Player player) {
    onSpace.add(player);
    repaint();
    revalidate();
  }

  /********************************************************************
   * Removes a playerToken from this space and returns its object.
   * @return playerToken being removed.
   *******************************************************************/
  public void removePlayer(Player player) {
    onSpace.remove(player);
    repaint();
    revalidate();
  }

  /********************************************************************
   * The clearSpace method clears all tokens off the board for a new
   * game.
   *******************************************************************/
  public void clearSpace(){
    onSpace.clear();
    repaint();
    revalidate();
  }

  /********************************************************************
   * Draws the token on the space.
   * @param graphics the Graphics component being used to draw.
   *******************************************************************/
  protected void drawTokens(Graphics graphics, boolean horizontal) {
    int offset = 0;

    for (Player player : onSpace) {
      if (sqSpace) {
        graphics.drawImage(player.getToken(), 20 + offset, 35, 15, 15, null);
        offset += 15;
      } else if (horizontal) {
        graphics.drawImage(player.getToken(), 25 + offset, 15, 15, 15, null);
        offset += 15;
      } else {
        graphics.drawImage(player.getToken(), 20, 25 + offset, 15, 15, null);
        offset += 15;
      }
    }
  }
}
