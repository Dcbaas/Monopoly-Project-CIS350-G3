package View;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame implements ActionListener {
	private JFrame frame;
	private JPanel gamePanel;
	private JPanel sideBar;
	private JPanel bottomBar;
	
	private GridBagConstraints c;
	
	
	public GameView() {
		frame = new JFrame("Monopoly");
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		
		gamePanel = new GamePanel();
		sideBar = new SideBar();
		bottomBar = new BottomBar();
		
		c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		frame.add(gamePanel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		frame.add(sideBar, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		frame.add(bottomBar, c);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
