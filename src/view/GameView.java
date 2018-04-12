package view;

import Controller.GameController;
import view.boardspaces.MenuPanel;
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


	/**
	 * A GridBagConstraint to setup the layout
	 * of this view.
	 */
	private GridBagConstraints constraintsBag;

	/**
	 * The Main menu component.
	 */
	private MenuPanel menu;

	/**
	 * The game controller.
	 */
	private GameController controller;


	/**
	 * The constructor creates all of the JPanels needed and displays
	 * them in the correct alignment.
	 **/
	public GameView(GameController controller){

		this.controller = controller;

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

		constraintsBag = new GridBagConstraints();

		constraintsBag.gridx = 0;
		constraintsBag.gridy = 0;
		add(buttonPanel, constraintsBag);

		constraintsBag.gridx = 1;
		constraintsBag.gridy = 0;
		constraintsBag.gridheight = 2;
		constraintsBag.weighty = 2;
		add(gamePanel, constraintsBag);

		constraintsBag.gridx = 2;
		constraintsBag.gridy = 0;
		constraintsBag.gridheight = 2;
		constraintsBag.weighty = 2;
		add(playerDetailPanel, constraintsBag);

		constraintsBag.gridx = 0;
		constraintsBag.gridy = 1;
		add(textPanel, constraintsBag);;

		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setVisible(true);


		menu.getNewGame().addActionListener(click -> {
			controller.newGame();
			NewGameDialog dialog = new NewGameDialog();
			controller.addPlayers(dialog.getPlayers());

		});
	}
	//Todo: Merge functions from TextController into the GUI.
	//Todo: make a menu bar for the top of the JFrame.


	/**
	 * Returns the game panel.
	 * @return gamepanel main game panel.
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * Sets the main game panel.
	 * @param gamePanel the new game panel.
	 */
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	/**
	 * Returns the playerFetailPanel
	 * @return the main GameDetailPanel.
	 */
	public PlayerDetailPanel getPlayerDetailPanel() {
		return playerDetailPanel;
	}

	/**
	 * Sets the Main Player Detail Panel.
	 * @param playerDetailPanel the main game detail panel.
	 */
	public void setPlayerDetailPanel(PlayerDetailPanel playerDetailPanel) {
		this.playerDetailPanel = playerDetailPanel;
	}

	/**
	 * Returns the main button panel.
	 * @return the main button panel.
	 */
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	/**
	 * Sets the main button panel.
	 * @param buttonPanel the new main button panel.
	 */
	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	/**
	 * Returns the main text panel.
	 * @return the main text panel.
	 */
	public TextPanel getTextPanel() {
		return textPanel;
	}

	/**
	 * Sets the main text panel.
	 * @param textPanel the new main text panel.
	 */
	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}
}
