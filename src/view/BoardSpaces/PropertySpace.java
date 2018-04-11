package view.BoardSpaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**********************************************************************
 * The PropertySpace class is an extensions of the JPanel and creates
 * a functional tile for one space on the Monopoly Board. It holds
 * functionality for tracking the name and price of a property as well
 * as how many houses are on the space.
 *
 * @author David Baas
 * @since 2/21/2018
 * @version 2/23/2018
 *********************************************************************/
public class PropertySpace extends Spaces {

  /**
   * A final int to track the width of a space.
   */
  private static final int WIDTH = 50;

  /**
   * A final int to track the height of a space.
   */
  private static final int HEIGHT = 100;

  //I wanted this to be final but Java didn't like it.
  /**
   * A Dimension to store the dimensions of the space.
   */
  private Dimension dimensions;

//  /**
//   * A Label for the name of the property.
//   */
//  private JLabel name;
//
//  /**
//   * A label for the price of the property.
//   */
//  private JLabel price;

  /**
   * A Housing panel for drawing houses.
   */
  private HousingPanel housingPanel;

  /********************************************************************
   * The constructor initializes all of the variables and sets the
   * color of the property.
   * @param c The color of the property.
   * @param name The name of the property.
   * @param price The price of the property.
   * @throws IOException If the images for the HousingPanel don't load.
   *******************************************************************/
  public PropertySpace(Color c, String name, int price, Position position) throws IOException {
    super(false);

    housingPanel = new HousingPanel(c, false);

    setLayout(new BorderLayout());

    switch (position) {
      case BOTTOM:
        housingPanel = new HousingPanel(c, false);
        createBotPanel();
        dimensions = new Dimension(WIDTH, HEIGHT);
        break;
      case TOP:
        housingPanel = new HousingPanel(c, false);
        createTopPanel();
        dimensions = new Dimension(WIDTH, HEIGHT);
        break;
      case LEFT:
        housingPanel = new HousingPanel(c, true);
        createLeftPanel();
        dimensions = new Dimension(HEIGHT, WIDTH);
        break;
      case RIGHT:
      default:
        housingPanel = new HousingPanel(c, true);
        createRightPanel();
        dimensions = new Dimension(HEIGHT, WIDTH);
    }

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);

  }

  /*******************************************************************
   * The getHouses method returns the number of houses on this property.
   *
   * @return houseCounter The number houses on this property.
   ******************************************************************/
  public int getHouses() {
    return housingPanel.getHouses();
  }

  /*******************************************************************
   * The setHouses method sets the number of houses on this property.
   * @param houseCounter the number of houses on this property.
   ******************************************************************/
  public void setHouses(int houseCounter) {
    housingPanel.setHouses(houseCounter);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    drawTokens(g);
  }

  /********************************************************************
   * The createBotPanel method is a private method that places the
   * JComponents into the correct positions to resemble a Monopoly
   * space on the bottom part of the board.
   *******************************************************************/
  private void createBotPanel() {
    add(housingPanel, BorderLayout.NORTH);
  }

  /********************************************************************
   * The createTopPanel method is a private method that places the
   * JComponents into the correct positions to resemble a Monopoly
   * space on the top part of the board.
   *******************************************************************/
  private void createTopPanel() {
    add(housingPanel, BorderLayout.SOUTH);
  }

  /********************************************************************
   * The createLeftPanel method is a private method that places the
   * JComponents into the correct positions to resemble a Monopoly
   * space on the left part of the board.
   *******************************************************************/
  private void createLeftPanel() {
    add(housingPanel, BorderLayout.EAST);
  }

  /********************************************************************
   * The createRightPanel method is a private method that places the
   * JComponents into the correct positions to resemble a Monopoly
   * space on the right part of the board.
   *******************************************************************/
  private void createRightPanel() {
    add(housingPanel, BorderLayout.WEST);
  }

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the board space.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return dimensions;
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPrefferedSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }

  /**
   * Enum position types for depending where on the board this property space is.
   */
  public enum Position {
    TOP, BOTTOM, LEFT, RIGHT
  }
}
