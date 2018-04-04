package View;

import Model.BoardPackage.OwnableSquare;
import Model.GamePackage.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
		propertiesList = new JTextArea(100,100);

		setLayout(new GridLayout(3,0,10,10));
		add(player);
		add(wealth);
		add(propertiesList);

		Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(blackLine);
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

	public Dimension getMinimumSize(){
		return new Dimension(500,1080);
	}
}
