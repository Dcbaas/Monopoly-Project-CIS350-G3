package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


/**
 * The MenuPanel packages all of the components of the JMenu into one
 * class for encapsulation. The menu allows for a new game to be created
 * and for the game to be quit at any time.
 * >>>>>>> Develop:src/view/MenuPanel.java
 *
 * @author Santiago Quiroga.
 * @version 4/11/2018.
 */
public class MenuPanel {

  /**
   * The menu Bar/ top level component.
   */
  private JMenuBar menuBar;

  /**
   * A single menu button.
   */
  private JMenu menu;

  /**
   * Exit  menu item.
   */
  private JMenuItem exit;

  /**
   * New game  menu Item.
   */
  private JMenuItem newGame;

  /**
   * Testing settings on.
   */
  private JMenuItem testing;

  /**
   * Basic constructor. Creates the menu bar and items along with
   * adding action listeners to each item.
   */
  public MenuPanel() {
    //instantiates menu bar
    menuBar = new JMenuBar();

    //Builds the menu.
    menu = new JMenu("menu");
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription(
        "Game options");
    menuBar.add(menu);

    //Menu items
    exit = new JMenuItem("exit", KeyEvent.VK_Q);
    exit.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    menu.add(exit);

    newGame = new JMenuItem("new game", KeyEvent.VK_N);
    newGame.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menu.add(newGame);

    testing = new JMenuItem("testing", KeyEvent.VK_ENTER);
    testing.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_ENTER, ActionEvent.ALT_MASK));
    menu.add(testing);

  }

  /**
   * Menu getter.
   *
   * @return JMenu for this class.
   */
  public JMenu getMenu() {
    return menu;
  }

  /**
   * Returns Menu item for exit.
   *
   * @return JMenuItem for menu JMenu.
   */
  public JMenuItem getExit() {
    return exit;
  }

  /**
   * Returns Menu item for newGame.
   *
   * @return JMenuItem for menu JMenu.
   */
  public JMenuItem getNewGame() {
    return newGame;
  }

  /**
   * Returns Menu bar.
   *
   * @return JMenuBar fot this class.
   */
  public JMenuBar getMenuBar() {
    return menuBar;
  }


  /**
   * Returns Menu item for testing.
   *
   * @return JMenuItem for menu JMenu.
   */
  public JMenuItem getTesting() {
    return testing;
  }
}
