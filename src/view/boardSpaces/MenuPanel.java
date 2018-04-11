package view.boardSpaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * This is the Menu bar with its Items It allows for the whole piece
 * to be single class
 *
 * @author Santiago Quiroga
 * @version April/11/2018
 */
public class MenuPanel  {

  /**
   * the menu Bar/ top level component
   */
  JMenuBar menuBar;

  /**
   * a single menu button
   */
  JMenu menu;

  /**
   * Exit and neg game menu items
   */
  JMenuItem exit, newGame;


  /**
   * Basic constructor
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
    exit= new JMenuItem("exit", KeyEvent.VK_Q);
    exit.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    menu.add(exit);


    newGame = new JMenuItem("new game", KeyEvent.VK_N);
    newGame.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menu.add(newGame);

  }

  /**
   * Menu getter
   * @return JMenu for this class
   */
  public JMenu getMenu() {
    return menu;
  }

  /**
   * Returns Menu item for exit
   * @return JMenuItem for menu JMenu
   */
  public JMenuItem getExit() {
    return exit;
  }

  /**
   * Returns Menu item for newGame
   * @return JMenuItem for menu JMenu
   */
  public JMenuItem getNewGame() {
    return newGame;
  }

  /**
   * Returns Menu bar
   * @return JMenuBar fot this class
   */
  public JMenuBar getMenuBar() {
    return menuBar;
  }
}
