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
                panel.add(adminPanel, "admin");

                cardLayout.show(panel, "admin");
            } else {
                userUI = new UserDashboard(username);
                userPanel = userUI.panel;
                panel.add(userPanel, "user");
                cardLayout.show(panel, "user");
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
