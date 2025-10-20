package ui;

import auth.Auth;
import auth.login;
import data.Retrieve;
import data.Store;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.helpers.RowHelper;

public class UIUtils {
  JFrame frame;
  static CardLayout cardLayout;
  static JPanel panel;
  static JPanel tableBody;

  LoginUI loginUI;
  JPanel loginPanel;

  AdminDashboard adminUI;
  JPanel adminPanel;

  UserDashboard userUI;
  JPanel userPanel;

  static String username;

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
    loginUI.loginButton.addActionListener(
        a -> {
          try {
            username = loginUI.getUsername();
            String password = loginUI.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
              loginUI.showError("Empty username or password.");
              return;
            }

            String status = login.check(username, password);
            if (status.equals("admin")) {
              // --- ADMIN LOGIN ---
              adminUI = new AdminDashboard();
              adminPanel = adminUI.panel;
              tableBody = adminUI.tableBody;
              panel.add(adminPanel, "admin");
              Retrieve.showComplaints(tableBody, username);
              cardLayout.show(panel, "admin");
              adminUI.logoutButton.addActionListener(
                  e -> {
                    logout();
                  });

            } else if (status.equals("user")) {
              // --- NORMAL USER LOGIN ---
              userUI = new UserDashboard(username);
              userPanel = userUI.panel;
              tableBody = userUI.tableBody;
              panel.add(userPanel, "user");
              Retrieve.showComplaints(tableBody, username);
              cardLayout.show(panel, "user");

              userUI.logoutButton.addActionListener(
                  e -> {
                    logout();
                  });
            } else {
              loginUI.showError("Invalid username or password.");
            }
          } catch (Exception ex) {
            loginUI.showError("An error occurred during login.");
            ex.printStackTrace();
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

    cUI.backButton.addActionListener(
        b -> {
          backToDashboard(username);
        });
  }

  /**
   * Adds a complaint to the database
   *
   * <p>This method creates a new complaint with the given ID and adds it to the database. It then
   * adds the complaint to the table body and repaints the table body. Finally, it goes back to the
   * dashboard.
   */
  public static void addComplaint() {
    int ID = Retrieve.getNewID();

    ComplaintUI cUI = new ComplaintUI(ID);

    JButton submitButton = new JButton("Submit");

    submitButton.addActionListener(
        e -> {
          String subject = cUI.subjectField.getText();
          if (subject.startsWith("Subject: ")) {
            subject = subject.substring("Subject: ".length());
          }
          String description = cUI.descriptionArea.getText();
          String priority = "";

          Store.saveComplaint(username, subject, description, priority, ID);
          RowHelper.addRow(tableBody, subject, priority, ID);
          tableBody.revalidate();
          tableBody.repaint();
          backToDashboard(username);
        });

    cUI.bottomPanel.remove(cUI.timeLabel);
    cUI.bottomPanel.remove(cUI.complainerLabel);
    cUI.bottomPanel.add(submitButton);

    JPanel cPanel = cUI.complaintUI;
    panel.add(cPanel, "complaint");
    cardLayout.show(panel, "complaint");

    cUI.backButton.addActionListener(
        _ -> {
          backToDashboard(username);
        });
  }

  public static void backToDashboard(String currentUser) {
    if (Auth.isAdmin(currentUser)) {
      cardLayout.show(panel, "admin");
    } else {
      cardLayout.show(panel, "user");
    }
  }

  public void logout() {
    cardLayout.show(panel, "login");
    ui.helpers.RowHelper.resetSlNo(); // Reset serial number for new login
    loginUI.clearFields();
  }
}