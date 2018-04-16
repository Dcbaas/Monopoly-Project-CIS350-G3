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

  /**
   * A button to build hotels.
   */
  private JButton buildHotel;

  /**
   * A button to buy properties.
   */
  private JButton buy;

  /**
   * A button to mortgage properties.
   */
  private JButton mortgage;

  /**
   * A button to draw a card.
   */
  private JButton drawCard;

  /**
   * A button to be done with the turn.
   */
  private JButton done;

  /**
   * A reference to the parent GameView.
   */
  private GameView parent;

  /********************************************************************
   * The constructor creates all of the buttons and places them in the
   * correct position.
   *******************************************************************/
  public ButtonPanel(GameView parent) {
    this.parent = parent;

    setLayout(new GridLayout(7,1, 10, 10));
    setSize(700, 100);

    roll = new JButton("Roll");
    buildHouse = new JButton("Build House");
    buildHotel = new JButton("Build Hotel");
    buy = new JButton("Buy");
    mortgage = new JButton("Mortgage");
    drawCard = new JButton("Draw Card");
    done = new JButton("Done");

    //Todo: Add ActionListeners for the buttons.

    roll.addActionListener(click -> {
      try {
        parent.getController().commands("roll");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    buildHouse.addActionListener(click -> {
      try {
        parent.getController().commands("house");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    buildHotel.addActionListener(click -> {
      try {
        parent.getController().commands("hotel");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    buy.addActionListener(click -> {
      try {
        parent.getController().commands("buy");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    mortgage.addActionListener(click -> {
      try {
        parent.getController().commands("mortgage");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    drawCard.addActionListener(click -> {
      try {
        parent.getController().commands("draw");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    done.addActionListener(click -> {
      try {
        parent.getController().commands("done");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    add(roll);
    add(buildHouse);
    add(buildHotel);
    add(buy);
    add(mortgage);
    add(drawCard);
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

  /********************************************************************
   * Checks the state of the game and enables and disables buttons
   * that can be utilized or are not usable.
   *******************************************************************/
  public void toggleButtons() {
    roll.setEnabled(parent.getController().isCanRoll());
    buy.setEnabled(parent.getController().isCanBuy());
    buildHouse.setEnabled(true); //Todo Is there logic for this?
    buildHotel.setEnabled(true);//Todo same question.
    mortgage.setEnabled(parent.getController().isCanMortgage());
    drawCard.setEnabled(parent.getController().isCanDraw());
    done.setEnabled(!parent.getController().isCanRoll() && !parent.getController().isCanDraw());
  }

  /********************************************************************
   * Sets all the buttons to disabled before a new game is created.
   *******************************************************************/
  public void preGame(){
    roll.setEnabled(false);
    buy.setEnabled(false);
    buildHouse.setEnabled(false);
    buildHotel.setEnabled(false);
    mortgage.setEnabled(false);
    drawCard.setEnabled(false);
    done.setEnabled(false);
  }
}
