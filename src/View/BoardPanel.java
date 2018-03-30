package View;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel implements ActionListener {

	public BoardPanel() {
		setLayout(new GridBagLayout());
		setSize(200, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
