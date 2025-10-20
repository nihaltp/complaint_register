package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginUI {
  JPanel panel;
  JPanel usernamePanel;
  JPanel passwordPanel;
  JPanel loginPanel;

  JLabel usernameLabel;
  JLabel passwordLabel;

  private JTextField usernameField;
  private JPasswordField password;
  JButton loginButton;

  private static final String USERNAME_PLACEHOLDER = "Enter your username";
  private static final String PASSWORD_PLACEHOLDER = "Enter your password";

  public LoginUI() {
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(new EmptyBorder(30, 100, 30, 50));

    usernameLabel = new JLabel("Username: ");

    usernameField = new JTextField();
    usernameField.setPreferredSize(new Dimension(180, 25));
    addListener(usernameField, USERNAME_PLACEHOLDER, false);

    usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    usernamePanel.add(usernameLabel);
    usernamePanel.add(usernameField);

    passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    passwordLabel = new JLabel("Password: ");

    password = new JPasswordField();
    password.setPreferredSize(new Dimension(180, 25));
    addListener(password, PASSWORD_PLACEHOLDER, true);

    passwordPanel.add(passwordLabel);
    passwordPanel.add(password);

    loginButton = new JButton("LOGIN");
    loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    loginPanel.add(loginButton);

    panel.add(usernamePanel);
    panel.add(passwordPanel);
    panel.add(loginPanel);
  }

  private void addListener(JTextField field, String placeholder, boolean isPassword) {
    field.setForeground(Color.GRAY);
    if (isPassword && field instanceof JPasswordField) {
      ((JPasswordField) field).setEchoChar((char) 0);
    }
    field.setText(placeholder);

    field.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            if (field.getText().equals(placeholder)) {
              field.setText("");
              field.setForeground(Color.BLACK);
              if (isPassword && field instanceof JPasswordField)
                ((JPasswordField) field).setEchoChar('•');
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (field.getText().equals("")) {
              field.setForeground(Color.GRAY);
              field.setText(placeholder);
              if (isPassword && field instanceof JPasswordField)
                ((JPasswordField) field).setEchoChar((char) 0);
            }
          }
        });
  }

  /**
   * Retrieves the username from the login form, ignoring the placeholder.
   *
   * @return The username entered by the user, or an empty string if the user did not enter a
   *     username.
   */
  public String getUsername() {
    String username = usernameField.getText();
    if (username.equals(USERNAME_PLACEHOLDER)) {
      return "";
    }
    return username;
  }

  /**
   * Retrieves the password from the login form, ignoring the placeholder.
   *
   * @return The password entered by the user, or an empty string if the user did not enter a
   *     password.
   */
  public String getPassword() {
    String pwd = new String(password.getPassword());
    if (pwd.equals(PASSWORD_PLACEHOLDER)) {
      return "";
    }
    return pwd;
  }

  /**
   * Clears the username and password fields by setting their text to the placeholder and their
   * foreground color to gray. If the password field is a JPasswordField, it also sets the echo
   * character to 0.
   */
  public void clearFields() {
    usernameField.setText(USERNAME_PLACEHOLDER);
    usernameField.setForeground(Color.GRAY);
    password.setText(PASSWORD_PLACEHOLDER);
    password.setForeground(Color.GRAY);
    if (password instanceof JPasswordField) {
      ((JPasswordField) password).setEchoChar((char) 0);
    }
  }
}
