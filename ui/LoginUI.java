package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class LoginUI {
    JPanel panel;
    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel loginPanel;

    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameField;
    JPasswordField password;
    JButton loginButton;

    public LoginUI () {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 100, 30, 50));

        usernameLabel = new JLabel("Username: ");

        usernameField = new JTextField();
        usernameField.setText("username");
        usernameField.setPreferredSize(new Dimension(180, 25));

        usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        passwordLabel = new JLabel("Password: ");

        password = new JPasswordField();
        password.setText("password");
        password.setPreferredSize(new Dimension(180, 25));

        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);

        loginButton = new JButton("LOGIN");
        loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        loginPanel.add(loginButton);

        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(loginPanel);
    }
}
