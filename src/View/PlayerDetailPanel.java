package View;

import Model.BoardPackage.OwnableSquare;
import Model.GamePackage.Player;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**********************************************************************
 * The PlayerDetailPanel displays information about the current player
 * taking their turn. It displays information on the Name, Wealth and
 * properties they own.
 *
 * @author David Baas
 * @version 4/4/2018
 *********************************************************************/
public class PlayerDetailPanel extends JPanel {

	/**A Label to hold the players name.*/
	private JLabel player;

	/**A Label to track a players wealth.*/
	private JLabel wealth;

	/**A final label for dispalying a header for a properties list.*/
	private final JLabel PROPERTIES_LBL = new JLabel("Properties");

	/**A text area to list properties.*/
	private JTextArea propertiesList;

	/********************************************************************
	 * The constructor initializes all of the JComponents and places them
	 * on the panel.
	 *******************************************************************/
	public PlayerDetailPanel() {
		player = new JLabel();
		wealth = new JLabel();
		propertiesList = new JTextArea();

		add(player);
		add(wealth);
		add(propertiesList);
	}


	/********************************************************************
	 * The setDisplay method takes a player and pulls the relevent
	 * information about the player and displays it as text
	 * @param player
	 *******************************************************************/
	public void setDisplay(Player player){
		this.player.setText(player.getDisplayName());

		wealth.setText("Wealth: $" + player.getWallet());

		//Clear the TextArea
		propertiesList.setText("");
		for(OwnableSquare ownableSquare: player.getOwnableProperties())
			propertiesList.append(ownableSquare.getName() + "\n");
	}
}
