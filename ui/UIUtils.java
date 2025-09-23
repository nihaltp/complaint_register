package ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIUtils {
    JFrame frame;
    CardLayout cardLayout;
    JPanel panel;

    LoginUI loginUI;
    JPanel loginPanel;

    AdminDashboard adminUI;
    JPanel adminPanel;

    public UIUtils() {
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        loginUI = new LoginUI();
        loginPanel = loginUI.panel;
        panel.add(loginPanel, "login");

        adminUI = new AdminDashboard();
        adminPanel = adminUI.panel;
        panel.add(adminPanel, "admin");

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.add(panel);

        loginUI.loginButton.addActionListener(_ -> {
            String username = loginUI.usernameField.getText();
            if (username.equals("admin")) {
                cardLayout.show(panel, "admin");
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
