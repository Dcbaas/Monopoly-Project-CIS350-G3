package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ButtonPanel extends JPanel {
	
	private JButton menu;
	private JButton build;
	private JButton sell;
	private JButton mortgage;
	private JButton trade;
	int x;

	private GamePanel parent;

	
	public ButtonPanel(GamePanel parent) {
		x = 0;
		setLayout(new GridLayout(5,1,10,10));
		setSize(700, 100);
		this.parent = parent;

		
		menu = new JButton("Menu");
		build = new JButton("Build");
		sell = new JButton("Sell");
		mortgage = new JButton("Mortgage");
		trade = new JButton("Trade");

		add(menu);
		add(build);
		add(sell);
		add(mortgage);
		add(trade);

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
	}

	public Dimension getMinimumSize(){
	  return new Dimension(500,540);
  }
}
