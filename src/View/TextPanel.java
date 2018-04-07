package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

/**********************************************************************
 * The Text Panel creates a JPanel with the ability to dispaly
 * information to the user using text while also allowing for text input.
 *
 * @author David Baas
 * @version 4/4/2018
 *********************************************************************/
public class TextPanel extends JPanel {

    // Holder for synchronized call
    final List<String> holder = new LinkedList<String>();

  /**
   * A JTextArea to display information.
   */
  private JTextArea txtArea;

  /**A JTextField to get input.*/
  private JTextField textField;

  /**A string that holds the info from textField.*/
  private String command;

  /********************************************************************
   * The constructor initializes the TextPanes and positions them on
   * the JPanel.
   *******************************************************************/
  public TextPanel() {
    txtArea = new JTextArea();
      textField = new JTextField();

    // Action listener for command text field. 'Enter' activates
      textField.addActionListener(e -> {

          // This says something has been sent and stop waiting.
          synchronized (holder) {
              holder.add(textField.getText());
              holder.notify();
          }
      command = textField.getText();
      textField.setText("");
    });

    txtArea.setEditable(false);
    textField.setEditable(false);

    setLayout(new BorderLayout());

    add(txtArea,BorderLayout.CENTER);
    add(textField,BorderLayout.SOUTH);

    JScrollPane scroll = new JScrollPane (txtArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(scroll);

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
  public JTextField getTextField() {
    return textField;
  }

  /********************************************************************
   * Set the text field (command box)
   *
   * @param textField The command text field
   *******************************************************************/
  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

    /********************************************************************
     * Get the command
     *
     * @return The command
     *******************************************************************/
  public String getCommand() {
      return command;
  }

    /********************************************************************
     * Set the command
     *
     * @param command The command you want to set
     *******************************************************************/
  public void setCommand(String command) {
      this.command = command;
  }


    /********************************************************************
     * Prints a message to the text area1
     *
     * @param message The message you want to print.
     *******************************************************************/
  public void printToTextArea(String message){
      txtArea.append(message + "\n");
      txtArea.append("--------------------------------------------------------------" +
                        "----------------------------------------------------------\n");
  }

    /********************************************************************
     * Get the holder so others can use it.
     *
     * @return The holder
     *******************************************************************/
  public List<String> getHolder() {
      return holder;
  }
}
