package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PropertySpace extends JPanel {

  private JLabel name;

  private JLabel price;

  private HousingPanel housingPanel;

  public PropertySpace(Color c,String name, int price) throws IOException{
    this.name = new JLabel(name);
    this.price = new JLabel(""+price);
    housingPanel = new HousingPanel(c);

    setLayout(new BorderLayout());
    add(housingPanel,BorderLayout.NORTH);
    add(this.name, BorderLayout.CENTER);
    add(this.price, BorderLayout.SOUTH);
  }

}
