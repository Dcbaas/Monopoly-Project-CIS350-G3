package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PropertySpace extends JPanel {

  private JLabel name;

  private JLabel price;

  private HousingPanel housingPanel;

  public PropertySpace(Color c,String name, int price) throws IOException{
    this.name = new JLabel(name);
    this.price = new JLabel(""+price);
    housingPanel = new HousingPanel(c);

    setTextAlignment();
    createPanel();
  }

  private void setTextAlignment(){
    name.setHorizontalTextPosition(SwingConstants.CENTER);
    price.setHorizontalTextPosition(SwingConstants.CENTER);
    price.setVerticalAlignment(SwingConstants.BOTTOM);
  }
  private void createPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints g = new GridBagConstraints();

    g.gridx = 0;
    g.gridy = 0;
    g.weightx = 2;
    g.gridheight = 2;
    add(housingPanel,g);

    g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 2;
    g.gridheight = 3;
    g.weighty = 2;
    g.anchor = g.NORTH;
    add(name,g);

    g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 5;
    g.anchor = g.SOUTH;
    add(price,g);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(200,400);
  }

  @Override
  public Dimension getMinimumSize(){
    return getPreferredSize();
  }

  @Override
  public Dimension getMaximumSize(){
    return getPreferredSize();
  }
}
