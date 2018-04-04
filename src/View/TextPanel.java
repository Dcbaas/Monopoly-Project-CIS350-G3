package View;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {

  private JTextArea txtArea;

  public TextPanel() {
    txtArea = new JTextArea();
    add(txtArea);
  }
}
