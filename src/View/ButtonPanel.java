package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ButtonPanel extends JPanel {

  /**
   * ToDo: Make the menu an option at the top with features.
   */
  private JButton menu;

  /**
   * A button to build properties.
   */
  private JButton build;

  /**
   * A button to sell properties
   */
  private JButton sell;

  /**
   * A button to mortgage properties.
   */
  private JButton mortgage;

  /********************************************************************
   * The constructor creates all of the buttons and places them in the
   * correct position.
   *******************************************************************/
  public ButtonPanel() {
    setLayout(new GridLayout(5, 1, 10, 10));
    setSize(700, 100);

    menu = new JButton("Menu");
    build = new JButton("Build");
    sell = new JButton("Sell");
    mortgage = new JButton("Mortgage");

    //Todo: Add ActionListeners for the buttons.

    add(menu);
    add(build);
    add(sell);
    add(mortgage);

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }

  /********************************************************************
   * Get the minimum size of this Button Panel.
   * @return The minimum size of this ButtonPanel.
   *******************************************************************/
  public Dimension getMinimumSize() {
    return new Dimension(500, 540);
  }
}
