package view;

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
  private JButton roll;

  /**
   * A button to build houses.
   */
  private JButton buildHouse;

  /**A button to build hotels
   */
  private JButton buildHotel;

  /**
   * A button to sell properties
   */
  private JButton sell;

  /**
   * A button to mortgage properties.
   */
  private JButton mortgage;

  /**A button to be done with the turn.
   *
   */
  private JButton done;

  /********************************************************************
   * The constructor creates all of the buttons and places them in the
   * correct position.
   *******************************************************************/
  public ButtonPanel() {
    setLayout(new GridLayout(6, 1, 10, 10));
    setSize(700, 100);

    roll = new JButton("Roll");
    buildHouse = new JButton("Build House");
    buildHotel = new JButton("Build Hotel");
    sell = new JButton("Sell");
    mortgage = new JButton("Mortgage");
    done = new JButton("Done");

    //Todo: Add ActionListeners for the buttons.

    add(roll);
    add(buildHouse);
    add(buildHotel);
    add(sell);
    add(mortgage);
    add(done);

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
