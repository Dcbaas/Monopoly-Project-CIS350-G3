package View;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomBar extends JPanel {
	
	private JButton menu;
	private JButton build;
	private JButton sell;
	private JButton mortgage;
	private JButton trade;
	
	public BottomBar() {
		setLayout(new FlowLayout());
		setSize(700, 100);
		
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
	}
}
