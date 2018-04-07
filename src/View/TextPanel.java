package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**********************************************************************
 * The Text Panel creates a JPanel with the ability to dispaly
 * information to the user using text while also allowing for text input.
 *
 * @author David Baas
 * @version 4/4/2018
 *********************************************************************/
public class TextPanel extends JPanel {

  /**
   * A JTextArea to display information.
   */
  private JTextArea txtArea;

  /**A JTextField to get input.*/
  private JTextField cmd;

  /********************************************************************
   * The constructor initializes the TextPanes and positions them on
   * the JPanel.
   *******************************************************************/
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

  /********************************************************************
   * Get the minimum dimension of this TextPanel.
   *
   * @return The minimum dimension of this TextPanel.
   *******************************************************************/
  public Dimension getMinimumSize(){
    return new Dimension(500,540);
  }
  //Todo: Implement functionality for getting information onto the JTextArea.
  //Todo: Implement functionality for reciving information from the JTextField.

  /********************************************************************
   * Get the text area
   *
   *@return The text area
   *******************************************************************/
  public JTextArea getTxtArea() {
    return txtArea;
  }

  /********************************************************************
   * Set teh text area\
   *
   * @param txtArea The text are
   *******************************************************************/
  public void setTxtArea(JTextArea txtArea) {
    this.txtArea = txtArea;
  }

  /********************************************************************
   * Get the text field (command box)
   *
   * @return The command text field
   *******************************************************************/
  public JTextField getCmd() {
    return cmd;
  }

  /********************************************************************
   * Set the text field (command box)
   *
   * @param cmd The command text field
   *******************************************************************/
  public void setCmd(JTextField cmd) {
    this.cmd = cmd;
  }
}
