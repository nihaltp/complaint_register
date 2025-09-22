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
        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }
}
