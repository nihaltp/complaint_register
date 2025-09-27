package ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.helpers.RowHelper;

public class UIUtils {
    JFrame frame;
    CardLayout cardLayout;
    JPanel panel;
    JPanel tableBody;

    LoginUI loginUI;
    JPanel loginPanel;

    AdminDashboard adminUI;
    JPanel adminPanel;

    UserDashboard userUI;
    JPanel userPanel;

    public UIUtils() {
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        loginUI = new LoginUI();
        loginPanel = loginUI.panel;
        panel.add(loginPanel, "login");

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.add(panel);

        loginUI.loginButton.addActionListener(_ -> {
            String username = loginUI.usernameField.getText();
            if (username.equals("admin")) {
                adminUI = new AdminDashboard();
                adminPanel = adminUI.panel;
                tableBody = adminUI.tableBody;
                panel.add(adminPanel, "admin");

                cardLayout.show(panel, "admin");
            } else {
                userUI = new UserDashboard(username);
                userPanel = userUI.panel;
                tableBody = userUI.tableBody;
                panel.add(userPanel, "user");
                cardLayout.show(panel, "user");
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
