package View;

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


	private SidePanel sidePanel;

	/**A button panel to hold the buttons.*/
	private ButtonPanel buttonPanel;

	/**A GridBagConstraint to setup the layout
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
		sidePanel = new SidePanel();
		buttonPanel = new ButtonPanel(gamePanel);

		c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		c.anchor = c.NORTHWEST;
		add(gamePanel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.weighty = 2;
		c.weightx = 1;
		add(sidePanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(buttonPanel, c);

		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
