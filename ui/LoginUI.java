package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginUI {
    public JPanel panel;
    public JTextField usernameField;
    public JPasswordField passwordField; // Renamed from 'password' for clarity
    public JButton loginButton;

    public LoginUI() {
        // Main panel using GridBagLayout for flexible alignment
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40, 40, 40, 40)); // Add generous padding around the form

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Spacing between components
        gbc.gridwidth = 2; // Components will span two columns by default
        gbc.anchor = GridBagConstraints.CENTER; // Center-align components

        // 1. A welcoming title
        JLabel titleLabel = new JLabel("Complaint Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // --- Form Fields ---
        gbc.gridwidth = 1; // Reset grid width for labels and fields
        gbc.anchor = GridBagConstraints.EAST; // Align labels to the right

        // 2. Username Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(usernameLabel, gbc);

        // 3. Username Text Field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align fields to the left
        usernameField = new JTextField(15); // Set a preferred width in columns
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(usernameField, gbc);

        // 4. Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passwordLabel, gbc);

        // 5. Password Field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passwordField, gbc);

        // 6. Login Button
        gbc.gridwidth = 2; // Span both columns
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make button fill the width
        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(loginButton, gbc);
    }
}