package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	
	private JButton menu;
	private JButton build;
	private JButton sell;
	private JButton mortgage;
	private JButton trade;
	
	public BottomPanel() {
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
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == menu) {
				System.out.println("hi");
			}
			else if(e.getSource() == build) {
				
			}
			else if(e.getSource() == sell) {
				
			}
			else if(e.getSource() == mortgage) {
				
			}
			else if(e.getSource() == trade) {
				
			}
		}
	}
}
