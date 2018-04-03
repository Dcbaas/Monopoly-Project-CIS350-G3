package View.BoardSpaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**********************************************************************
 * The TaxSpace creates a board space that is a tax square on the board
 * of Monopoly
 *
 * @author David Baas
 * @version 3/26/2018
 *********************************************************************/
public class TaxSpace extends Spaces {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 50;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 100;

  /**
   * An array of infomation strings for taxInfo.
   */
  private static final String[] infoStrings = {"Pay 10% or $200", "Pay $75.00"};

  /**
   * A JLabel to track the name of the TaxSpace.
   */
  private JLabel taxName;

  /**
   * A JLabel to display info about the tax in the TaxSpace.
   */
  private JLabel taxInfo;

  /**
   * A boolean to track what type of tax space this TaxSpace is.
   */
  private boolean incomeTax;

  /**
   * A Dimension to track the dimensions of the tax space.
   */
  private Dimension dimension;

  /********************************************************************
   * The constructor for the TaxSpace creates a tax space depanding on
   * weather it is an income tax space or a luxury space.
   *
   * @param incomeTax Determines what type of tax space this TaxSpace
   * is.
   *******************************************************************/
  public TaxSpace(boolean incomeTax) {
    super(false);

    this.incomeTax = incomeTax;

    if (incomeTax) {
      taxName = new JLabel("Income Tax");
      taxInfo = new JLabel(infoStrings[0]);
      dimension = new Dimension(WIDTH, HEIGHT);
    } else {
      taxName = new JLabel("Luxury Tax");
      taxInfo = new JLabel(infoStrings[1]);
      dimension = new Dimension(HEIGHT, WIDTH);
    }

    setLayout(new GridBagLayout());
    drawLabels();

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }

  /********************************************************************
   * The drawLabels method is a private method that draws the
   * labels onto the JPanel.
   *******************************************************************/
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

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    drawTokens(g);
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
