package View.BoardSpaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Created by unclear on 4/9/18.
 */
public class MenuPanel  {
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem exit, newGame;

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
    exit.getAccessibleContext().setAccessibleDescription(
        "This doesn't really do anything");
    menu.add(exit);


    newGame = new JMenuItem("new game", KeyEvent.VK_N);
    newGame.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menu.add(newGame);

  }

  public JMenu getMenu() {
    return menu;
  }

  public JMenuItem getExit() {
    return exit;
  }

  public JMenuItem getNewGame() {
    return newGame;
  }

  public JMenuBar getMenuBar() {
    return menuBar;
  }
}
