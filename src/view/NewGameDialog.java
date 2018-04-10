package view;

import Model.GamePackage.Player;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**********************************************************************
 * The NewGameDialog is used to create a new game of Monopoly.
 * It ask for how many players are playing and what their names are.
 *
 * @author David Baas
 * @version 4/9/2018
 *********************************************************************/
public class NewGameDialog extends JDialog {

  /**
   * A static Label constant to prompt the user for instructions.
   */
  private static final JLabel instructions = new JLabel("Enter player"
      + " details.");

  /**
   * A final int for the number of players that can play.
   */
  private static final int PLAYERS = 4;

  /**
   * An array of JPanels to hold a player detail form.
   */
  private JPanel[] playerPanels;

  /**
   * An array of check-boxes for enabling what boxes are considered valid
   * players
   */
  private JCheckBox[] checkBoxes;

  /**
   * An array of text fields to get the names of the Players
   */
  private JTextField[] textFields;

  /**
   * A flag to set weather a line is used or not.
   */
  private boolean[] selectedPlayers;
  /**
   * A panel to hold the buttons that are used on this dialog box.
   */
  private JPanel buttonPanel;

  /**
   * A button to cancel starting a new game.
   */
  private JButton cancelItem;

  /**
   * A button to accept the inputs and to start the game.
   */
  private JButton okayItem;

  /********************************************************************
   * The constructor initializes all of the elements of the dialog box
   * and positions them on the box correctly. An reference to a vector
   * of players is passed in to the dialog box to be modified in order
   * to be used when creating the game.
   * @param players the ArrayList being passed to this dialog box.
   *******************************************************************/
  public NewGameDialog(ArrayList<Player> players){
    setTitle("New Game");

    playerPanels = new JPanel[PLAYERS];
    checkBoxes = new JCheckBox[PLAYERS];
    textFields = new JTextField[PLAYERS];
    selectedPlayers = new boolean[PLAYERS];

    buttonPanel = new JPanel();
    cancelItem = new JButton("Cancel");
    okayItem = new JButton("Ok");

    cancelItem.addActionListener(e -> System.exit(0));

    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add(okayItem,BorderLayout.WEST);
    buttonPanel.add(cancelItem,BorderLayout.EAST);

    setLayout(new GridLayout(PLAYERS + 2, 0, 30, 30));
    add(instructions);

    /*
     *  This for loop creates all of the text panels and check boxes as
     * well as adds itemListeners to the checkboxes.
     */
    for (int i = 0; i < PLAYERS; ++i) {
      playerPanels[i] = new JPanel();
      checkBoxes[i] = new JCheckBox();
      selectedPlayers[i] = false;
      textFields[i] = new JTextField();

      playerPanels[i].setLayout(new BorderLayout());

      //Keep the lambda function happy with finalI.
      int finalI = i;
      checkBoxes[i].addItemListener(item -> {
        if (checkBoxes[finalI].isSelected()) {
          selectedPlayers[finalI] = true;
        }
      });

      playerPanels[i].add(checkBoxes[i], BorderLayout.WEST);
      playerPanels[i].add(textFields[i], BorderLayout.CENTER);

      add(playerPanels[i]);
    }

    add(buttonPanel);

    setSize(300, 350);
    setLocationRelativeTo(null);
    setModal(true);
    setVisible(true);
  }

  /********************************************************************
   * The getPlayerImage returns a file path as a string based on what
   * player is being created.
   * @param playerNumber The player being created.
   * @return A string that is the reletive path of an image.
   *******************************************************************/
  private String getPlayerImage(int playerNumber) {
    switch (playerNumber) {
      case 1:
        return "res/boat.png";
      case 2:
        return "res/car.png";
      case 3:
        return "res/hat.png";
      case 4:
      default:
        return "res/iron.png";
    }
  }
}
