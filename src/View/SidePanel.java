package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SidePanel extends JPanel {
	
	private JLabel stats;
	
	private JTabbedPane players;
	
	private JComponent p1Panel;
	private ImageIcon p1Icon;
	private JLabel p1NameToken;
	private JLabel p1Wealth;
	
	private JComponent p2Panel;
	private ImageIcon p2Icon;
	private JLabel p2NameToken;
	private JLabel p2Wealth;
	
	private JComponent p3Panel;
	private ImageIcon p3Icon;
	private JLabel p3NameToken;
	private JLabel p3Wealth;
	
	private JComponent p4Panel;
	private ImageIcon p4Icon;
	private JLabel p4NameToken;
	private JLabel p4Wealth;
	
	public SidePanel() {
		setLayout(new FlowLayout());
		setSize(400, 400);
		players = new JTabbedPane();
		
		//p1Icon = createImageIcon("res/boat.png");
		//p1Panel = makeTextPanel("Player 1");
		players.addTab("Tab 1", p1Icon, p1Panel, "Player 1's Wealth");
		players.addTab("Tab 2", p2Icon, p2Panel, "Player 2's Wealth");
		players.addTab("Tab 3", p3Icon, p3Panel, "Player 3's Wealth");
		players.addTab("Tab 4", p4Icon, p4Panel, "Player 4's Wealth");
		
		add(players);
	}
}
