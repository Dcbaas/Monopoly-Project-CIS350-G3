package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * The Text Panel creates a JPanel with the ability to dispaly
 * information to the user using text while also allowing for text input.
 *
 * @author David Baas
 * @version 4/4/2018
 */
public class TextPanel extends JPanel implements FocusListener {

  /**
   * Hint text.
   */
  private static final String DEFAULT_TEXT = "Enter a Command";


  /**
   * A JTextArea to display information.
   */
  private JTextArea txtArea;

  /**
   * A JTextField to get input.
   */
  private JTextField textField;

  /**
   * A string that holds the info from textField.
   */
  private String command;

  /**
   * The constructor initializes the TextPanes and positions them on
   * the JPanel.
   */
  TextPanel() {
    txtArea = new JTextArea();
    textField = new JTextField();

    // Action listener for command text field. 'Enter' activates
    textField.addActionListener(e -> {

      command = textField.getText();
      // Clear textField
      textField.setText("");
    });

    txtArea.setEditable(false);
    textField.setEditable(false);

    setLayout(new BorderLayout());

    add(txtArea, BorderLayout.CENTER);
    add(textField, BorderLayout.SOUTH);

    JScrollPane scroll = new JScrollPane(txtArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(scroll);

    Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
    setBorder(blackLine);

    //Adds a focus listener to the command TextField int order to detect actions
    textField.addFocusListener(this);
  }

  /**
   * Get the minimum dimension of this TextPanel.
   *
   * @return The minimum dimension of this TextPanel.
   */
  public Dimension getMinimumSize() {
    return new Dimension(500, 540);
  }


  /**
   * Get the text area.
   *
   * @return The text area.
   */
  public JTextArea getTxtArea() {
    return txtArea;
  }

  /**
   * Set teh text area.
   *
   * @param txtArea The text are.
   */
  public void setTxtArea(JTextArea txtArea) {
    this.txtArea = txtArea;
  }

  /**
   * Get the text field (command box).
   *
   * @return The command text field
   */
  public JTextField getTextField() {
    return textField;
  }

  /**
   * Set the text field (command box).
   *
   * @param textField The command text field.
   */
  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  /**
   * Get the command.
   *
   * @return The command.
   */
  public String getCommand() {
    return command;
  }

  /**
   * Set the command.
   *
   * @param command The command you want to set.
   */
  public void setCommand(String command) {
    this.command = command;
  }


  /**
   * Prints a message to the text area.
   *
   * @param message The message you want to print.
   */
  public void printToTextArea(String message) {
    txtArea.append(message + "\n");
    txtArea.append("--------------------------------------------------------------"
        + "----------------------------------------------------------\n");
  }


  /**
   * Sets The hint text for the JTextfield.
   * *
   */
  public void setHintText() {
    textField.setText(DEFAULT_TEXT);
  }


  /**
   * Performs and action when the textfeld is being focused.
   * Enables the hint text.
   *
   * @param e event triggered from a component.
   */
  @Override
  public void focusGained(FocusEvent e) {
    textField.setText("");
  }

  /**
   * Performs and action when the textfeld is being focused.
   * Disables the hint text.
   *
   * @param e event triggered from a component.
   */
  @Override
  public void focusLost(FocusEvent e) {
    setHintText();
  }
}
