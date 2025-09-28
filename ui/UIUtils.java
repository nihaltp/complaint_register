package ui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.helpers.RowHelper;

public class UIUtils {
    JFrame frame;
    static CardLayout cardLayout;
    static JPanel panel;
    JPanel tableBody;

    LoginUI loginUI;
    JPanel loginPanel;

    AdminDashboard adminUI;
    JPanel adminPanel;

    UserDashboard userUI;
    JPanel userPanel;

    // The constructor is where all the setup logic should go.
    public UIUtils() {
        // 1. Initialize the main frame and panels
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        frame = new JFrame("Complaint Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.add(panel);

        // 2. Setup the Login Screen
        loginUI = new LoginUI();
        loginPanel = loginUI.panel;
        panel.add(loginPanel, "login"); // Add login panel to the card layout

        // 3. Add the logic for the login button
        loginUI.loginButton.addActionListener(_ -> {
            String username = loginUI.usernameField.getText();

            if (username.equals("admin")) {
                // --- ADMIN LOGIN ---
                adminUI = new AdminDashboard();
                adminPanel = adminUI.panel;
                tableBody = adminUI.tableBody;
                panel.add(adminPanel, "admin");
                cardLayout.show(panel, "admin");

                // --- NEW: Add the logout listener for the admin dashboard ---
                adminUI.logoutButton.addActionListener(e -> {
                    cardLayout.show(panel, "login");
                });

            } else {
                // --- NORMAL USER LOGIN ---
                userUI = new UserDashboard(username);
                userPanel = userUI.panel;
                tableBody = userUI.tableBody;
                panel.add(userPanel, "user");
                cardLayout.show(panel, "user");
                
                // --- NEW: Add the logout listener for the user dashboard ---
                userUI.logoutButton.addActionListener(e -> {
                    cardLayout.show(panel, "login");
                });
            }
        });
    }

    // This method starts the UI
    public void show() {
        frame.setVisible(true);
    }

    // This method can be called from other parts of the app to show a specific complaint
    public static void showComplaint(int ID) {
        ComplaintUI cUI = new ComplaintUI(ID);
        JPanel cPanel = cUI.complaintUI;
        panel.add(cPanel, "complaint");
        cardLayout.show(panel, "complaint");
    }
}