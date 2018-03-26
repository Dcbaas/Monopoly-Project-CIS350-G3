package View;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private BufferedImage board;

	public BoardPanel() {
		setLayout(new GridBagLayout());
		setSize(200, 600);
		
		try {
			board = ImageIO.read(new File("res\board.jpg"));
		} catch(IOException ex) {
			System.out.println("Could not find filename");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, this);            
	}
	
}
