package view;

import Model.BoardPackage.OwnableSquare;
import Model.GamePackage.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**********************************************************************
 * The PlayerDetailPanel displays information about the current player
 * taking their turn. It displays information on the Name, Wealth and
 * properties they own.
 *
 * @author David Baas
 * @version 4/4/2018
 *********************************************************************/
public class PlayerDetailPanel extends JPanel {

  /**
   * A JPanel to hold the JLabels.
   */
  private JPanel labelPanel;

  /**
   * A Label to hold the players name.
   */
  private JLabel player;
  /**
   * A Label to track a players wealth.
   */
  private JLabel wealth;
  /**
   * A text area to list properties.
   */
  private JTextArea propertiesList;

  /********************************************************************
   * The constructor initializes all of the JComponents and places them
   * on the panel.
   *******************************************************************/
  public PlayerDetailPanel() {
    labelPanel = new JPanel();
    player = new JLabel();
    wealth = new JLabel();
    propertiesList = new JTextArea(100, 100);
    propertiesList.setEditable(false);

    setLayout(new BorderLayout());
    labelPanel.add(player);
    labelPanel.add(wealth);
    add(labelPanel, BorderLayout.NORTH);
    add(propertiesList, BorderLayout.CENTER);

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }


  /********************************************************************
   * The setDisplay method takes a player and pulls the relevant
   * information about the player and displays it as text
   * @param player The Player being passed in.
   *******************************************************************/
  //Todo: Implement this feature in the GameView.
  public void setDisplay(Player player) {
    this.player.setText("Name: " + player.getDisplayName() + " -");

    wealth.setText("Wealth: $" + player.getWallet());

    //Clear the TextArea
    propertiesList.setText("");
    for (OwnableSquare ownableSquare : player.getOwnableProperties()) {
      propertiesList.append(ownableSquare.getName() + "\n");
    }
  }

  /********************************************************************
   * Gets the minimum size of this PlayerDetailPanel
   * @return The minimum size of this PlayerDetailPanel.
   *******************************************************************/
  public Dimension getMinimumSize() {
    return new Dimension(500, 1080);
  }
}
