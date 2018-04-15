package view.boardspaces;


import Model.GamePackage.Player;
import java.awt.Graphics;
import java.util.ArrayList;
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
  public void removePlayer(Player player) {
    onSpace.remove(player);
    repaint();
    revalidate();
  }

  /********************************************************************
   * Draws the token on the space.
   * @param graphics the Graphics component being used to draw.
   *******************************************************************/
  protected void drawTokens(Graphics graphics) {
    int i = 1;
    int offset = 0;

    for (Player player : onSpace) {
//      if (i >= 2) {
//        graphics.drawImage(player.getToken(), 15+ offset, 35 , 20, 20, null);
//      }
//      else {
//        graphics.drawImage(player.getToken(), 15 + offset, 30, 20, 20, null);
//      }
//      offset += 20;
//      ++i;
//      if(i >= 2)
//        offset = 0;
//    }
      if(sqSpace){
        graphics.drawImage(player.getToken(),20 + offset, 35 , 15, 15, null);
        offset += 15;
      }
      else{
        if (i > 2) {
          if(i== 3)
            offset = 0;
          graphics.drawImage(player.getToken(), 25+ offset, 45 , 20, 20, null);
        }
        else
          graphics.drawImage(player.getToken(),25 + offset, 30 , 15, 15, null);
        ++i;
        offset += 15;
      }
    }
  }
}
