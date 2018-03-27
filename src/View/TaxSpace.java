package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**********************************************************************
 * The TaxSpace creates a board space that is a tax square on the board
 * of Monopoly
 *
 * @author David Baas
 * @version 3/26/2018
 *********************************************************************/
public class TaxSpace extends JPanel {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 200;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 400;

  private JLabel taxName;

  private JLabel taxInfo;

  private static final String[] infoStrings = {"Pay 10% or $200", "Pay $75.00"};

  private boolean incomeTax;

  private Dimension dimension;

  public TaxSpace(boolean incomeTax) {
    this.incomeTax = incomeTax;

    if (incomeTax) {
      taxName = new JLabel("Income Tax");
      taxInfo = new JLabel(infoStrings[0]);
      dimension = new Dimension(WIDTH, HEIGHT);
    } else {
      taxName = new JLabel("Luxury Tax");
      taxInfo = new JLabel(infoStrings[1]);
    }

    setLayout(new GridBagLayout());
    drawLabels();
  }

  private void drawLabels() {
    GridBagConstraints g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 0;
    g.anchor = GridBagConstraints.NORTH;
    g.weighty = 2;
    add(taxName, g);

    g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 2;
    g.anchor = GridBagConstraints.SOUTH;
    add(taxInfo, g);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the TaxSpace.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return dimension;
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the TaxSpace.
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the TaxSpace.
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
