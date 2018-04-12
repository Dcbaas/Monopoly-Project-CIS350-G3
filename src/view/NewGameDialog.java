package view;

import Model.GamePackage.Player;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  private static final JLabel INSTRUCTIONS = new JLabel("Enter the "
      + "names of players");

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

  /**
   * A reference to the ArrayList of Players that will be used for this game of
   * Monopoly.
   */
  private ArrayList<Player> parentArrayList;

  /********************************************************************
   * The constructor initializes all of the elements of the dialog box
   * and positions them on the box correctly. An reference to a vector
   * of players is passed in to the dialog box to be modified in order
   * to be used when creating the game.
   *******************************************************************/
  public NewGameDialog() {
    parentArrayList = new ArrayList<>();

    setTitle("New Game");

    playerPanels = new JPanel[PLAYERS];
    checkBoxes = new JCheckBox[PLAYERS];
    textFields = new JTextField[PLAYERS];

    buttonPanel = new JPanel();
    cancelItem = new JButton("Cancel");
    okayItem = new JButton("Ok");

    cancelItem.addActionListener(e -> System.exit(0));
    okayItem.addActionListener(e -> {
      try {
        onOk();
      } catch (IOException e1) {
        e1.printStackTrace();
        System.exit(0);
      }
    });

    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add(okayItem, BorderLayout.WEST);
    buttonPanel.add(cancelItem, BorderLayout.EAST);

    setLayout(new GridLayout(PLAYERS + 2, 0, 30, 30));
    add(INSTRUCTIONS);

    /*
     *  This for loop creates all of the text panels and check boxes as
     * well as adds itemListeners to the checkboxes.
     */
    for (int i = 0; i < PLAYERS; ++i) {
      playerPanels[i] = new JPanel();
      checkBoxes[i] = new JCheckBox();
      textFields[i] = new JTextField();

      playerPanels[i].setLayout(new BorderLayout());

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
      case 0:
        return "res/boat.png";
      case 1:
        return "res/car.png";
      case 2:
        return "res/hat.png";
      case 3:
      default:
        return "res/iron.png";
    }
  }

  /********************************************************************
   * The onOk method parses the name inputs and creates the list of
   * players who will be playing this game of Monopoly.
   *
   * @throws IOException If there is a problem loading the image file.
   *******************************************************************/
  private void onOk() throws IOException {
    boolean approved = true;
    for (int i = 0; i < PLAYERS; ++i) {
      if (checkBoxes[i].isSelected()) {
        String playerName = textFields[i].getText();
        if (playerName.length() != 0) {
          parentArrayList
              .add(new Player(playerName, new File(getPlayerImage(i)),
                  1500));
        } else {
          approved = false;
        }
      }
    }
    //Was there an invalid input. If yes force error correction.
    if (approved) {
      dispose();
    } else {
      JOptionPane.showMessageDialog(this, "Error: Invalid input on one "
          + "of the name boxes.");
      parentArrayList.clear();
    }
  }

  /**
   * Returns the ArrayList of generated Players.
   * @return The arrayList of players.
   */
  public ArrayList<Player> getPlayers() {
    return parentArrayList;
  }
}
