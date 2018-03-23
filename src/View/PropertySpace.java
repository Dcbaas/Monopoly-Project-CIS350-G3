package View;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;

/**********************************************************************
 * The PropertySpace class is an extensions of the JPanel and creates
 * a functional tile for one space on the Monopoly Board. It holds
 * functionality for tracking the name and price of a property as well
 * as how many houses are on the space.
 *
 * @author David Baas
 * @version 2/21/2018
 *********************************************************************/
public class PropertySpace extends JPanel {

  /**A Label for the name of the property*/
  private JLabel name;

  /**A label for the price of the property*/
  private JLabel price;

  /**A Housing panel for drawing houses */
  private HousingPanel housingPanel;


  /********************************************************************
   * The constructor initializes all of the variables and sets the
   * color of the property.
   * @param c The color of the property.
   * @param name The name of the property.
   * @param price The price of the property.
   * @throws IOException If the images for the HousingPanel don't load.
   *******************************************************************/
  public PropertySpace(Color c,String name, int price) throws IOException{
    this.name = new JLabel(name);
    this.price = new JLabel(""+price);
    housingPanel = new HousingPanel(c);

    setTextAlignment();
    createPanel();
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

  /********************************************************************
   * The setTextAlignment method is a private method that aligns the
   * text of the Labels to the center of the panel.
   *******************************************************************/
  private void setTextAlignment(){
    name.setHorizontalTextPosition(SwingConstants.CENTER);
    price.setHorizontalTextPosition(SwingConstants.CENTER);
    price.setVerticalAlignment(SwingConstants.BOTTOM);
  }

  /********************************************************************
   * The createPanel method is a private method that places the
   * JComponents into the correct positions to resemble a Monopoly
   * space.
   *******************************************************************/
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

  /********************************************************************
   * The getPreferredSize method is used to Lock the size of the Panel
   * to the correct size.
   * @return The dimensions of the board space.
   *******************************************************************/
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(200,400);
  }

  /********************************************************************
   * The getMinimumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPrefferedSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMinimumSize(){
    return getPreferredSize();
  }

  /********************************************************************
   * The getMaximumSize method refers to the getPreferredSize method
   * to lock the size of the panel.
   * @return getPreferredSize the dimensions of the BoardSpace
   *******************************************************************/
  @Override
  public Dimension getMaximumSize(){
    return getPreferredSize();
  }

  @Override
  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D) g;

    g2d.rotate(Math.PI / 2);
    super.paintComponent(g);
  }
}
