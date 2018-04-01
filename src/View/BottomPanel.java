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
	
	private ButtonListener buttonListener;
	
	public BottomPanel() {
		setLayout(new FlowLayout());
		setSize(700, 100);
		
		buttonListener = new ButtonListener();
		
		menu = new JButton("Menu");
		build = new JButton("Build");
		sell = new JButton("Sell");
		mortgage = new JButton("Mortgage");
		trade = new JButton("Trade");
		
		menu.addActionListener(buttonListener);
		build.addActionListener(buttonListener);
		sell.addActionListener(buttonListener);
		mortgage.addActionListener(buttonListener);
		trade.addActionListener(buttonListener);
		
		add(menu);
		add(build);
		add(sell);
		add(mortgage);
		add(trade);
	}
	
	private class ButtonListener implements ActionListener {
		//add code to attach controller methods to buttons
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == menu) {
				System.out.println("Test");
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
