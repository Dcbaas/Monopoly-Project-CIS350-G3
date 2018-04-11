package view;

import Model.GamePackage.Player;
import View.BoardSpaces.MenuPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**********************************************************************
 * The GameView Class serves as the primary application window where
 * all the components of the Top-Down GUI are collected and displayed.
 *
 * @author David Baas Dustin Ecker
 * @since 4/2/2018
 * @version 4/4/2018
 *********************************************************************/
public class GameView extends JFrame {

  /**A GamePanel for the board display.*/
	private GamePanel gamePanel;


	private PlayerDetailPanel playerDetailPanel;

	/**A button panel to hold the buttons.*/
	private ButtonPanel buttonPanel;

	/**A TextPanel to display information.*/
	private TextPanel textPanel;

	private MenuPanel menu;

	/**
	 * A GridBagConstraint to setup the layout
	 * of this view.
	 */
	private GridBagConstraints c;

	/********************************************************************
	 * The constructor creates all of the JPanels needed and displays
	 * them in the correct alignment.
	 *******************************************************************/
	public GameView(){
		setTitle("Monopoly");
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);

		gamePanel = new GamePanel();
		playerDetailPanel = new PlayerDetailPanel();
		buttonPanel = new ButtonPanel();
		textPanel = new TextPanel();
		menu = new MenuPanel();
		setJMenuBar(menu.getMenuBar());

		c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		add(buttonPanel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.weighty = 2;
		add(gamePanel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 2;
		c.weighty = 2;
		add(playerDetailPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		add(textPanel, c);

//		playerDetailPanel.setDisplay(new Player("Mr. Parrot", "The fucktard", 8000));


		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//Todo: Merge functions from TextController into the GUI.
	//Todo: make a menu bar for the top of the JFrame.


	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public PlayerDetailPanel getPlayerDetailPanel() {
		return playerDetailPanel;
	}

	public void setPlayerDetailPanel(PlayerDetailPanel playerDetailPanel) {
		this.playerDetailPanel = playerDetailPanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public TextPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}
}
