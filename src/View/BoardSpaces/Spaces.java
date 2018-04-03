package View.BoardSpaces;

import Model.BoardPackage.BoardSquare;
import Model.GamePackage.Player;
import View.PlayerToken;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

public abstract class Spaces extends JPanel {
  /**A linked list to track player tokens on this space of the board.
   * A linked list was chosen because of the order of how players
   * enter and leave this space is always constant.
   */
  protected LinkedList<PlayerToken> onSpace;
  private BoardSquare reference;
  protected boolean sqSpace;

  public Spaces(boolean sqSpace){
    onSpace = new LinkedList<>();
    this.sqSpace = sqSpace;
  }

  public void addPlayer(PlayerToken p){
    onSpace.add(p);
    repaint();
    revalidate();
  }

  public PlayerToken removePlayer(){
    PlayerToken p = onSpace.pollFirst();
    repaint();
    revalidate();
    return p;
  }


  public void paintComponent(Graphics g){
    super.paintComponent(g);
    int i = 1;
    int offset = 0;

    for(PlayerToken p: onSpace){
      if(i >= 2)
        g.drawImage(p.getToken(),15,5 + offset,10,10,null);
      else
        g.drawImage(p.getToken(),5,5+ offset,10,10, null);
    }
  }
}
