package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TextPanel extends JPanel {

  private JTextArea txtArea;

  private JTextField cmd;

  public TextPanel() {
    txtArea = new JTextArea();
    cmd = new JTextField();

    txtArea.setEditable(false);

    setLayout(new BorderLayout());

    add(txtArea,BorderLayout.CENTER);

    add(cmd,BorderLayout.SOUTH);

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);
  }

  public Dimension getMinimumSize(){
    return new Dimension(500,540);
  }
}
