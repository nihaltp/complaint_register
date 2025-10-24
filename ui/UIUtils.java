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

  Dashboard UI;
  JPanel UIpanel;

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
              UI = new AdminDashboard();
              UIpanel = UI.getPanel();
              tableBody = UI.getTableBody();
              panel.add(UIpanel, "admin");
              Retrieve.showComplaints(tableBody, username);
              cardLayout.show(panel, "admin");

              UI.getLogoutButton()
                  .addActionListener(
                      e -> {
                        logout();
                      });

            } else if (status.equals("user")) {
              // --- NORMAL USER LOGIN ---
              UI = new UserDashboard(username);
              UIpanel = UI.getPanel();
              tableBody = UI.getTableBody();
              panel.add(UIpanel, "user");
              Retrieve.showComplaints(tableBody, username);
              cardLayout.show(panel, "user");

              UI.getLogoutButton()
                  .addActionListener(
                      e -> {
                        logout();
                      });
            } else {
              loginUI.showError("Invalid username or password.");
            }
          } catch (Exception ex) {
            loginUI.showError("An error occurred during login.");
            System.err.println("An error occurred during login:");
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

    // Add logic for the delete button
    if (cUI.deleteButton != null) {
      cUI.deleteButton.addActionListener(
          e -> {
            // 1. Delete from database
            Store.deleteComplaint(ID);

            // 2. Refresh the dashboard view
            tableBody.removeAll();
            RowHelper.resetSlNo();
            Retrieve.showComplaints(tableBody, username);
            tableBody.revalidate();
            tableBody.repaint();

            // 3. Go back to the dashboard
            backToDashboard(username);
          });
    }
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
    cUI.subjectField.setEditable(true);
    cUI.descriptionArea.setEditable(true);

    JButton submitButton = new JButton("Submit");
    cUI.anonymousButton.setVisible(true); // Show the anonymous button

    submitButton.addActionListener(
        e -> {
          submit(false, ID, cUI);
        });

    cUI.anonymousButton.addActionListener(
        e -> {
          submit(true, ID, cUI);
        });

    cUI.bottomPanel.remove(cUI.timeLabel);
    cUI.bottomPanel.remove(cUI.complainerLabel);
    cUI.bottomPanel.add(submitButton);
    cUI.bottomPanel.add(cUI.anonymousButton); // Add anonymous button to the panel

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

  public static String getUsername() {
    return username;
  }

  public static void submit(boolean isAnon, int ID, ComplaintUI UI) {
    String subject = UI.subjectField.getText();
    if (subject.startsWith("Subject: ")) {
      subject = subject.substring("Subject: ".length());
    }
    String description = UI.descriptionArea.getText();
    if (subject == "" || description == "") {
      return;
    }
    String priority = "";

    Store.saveComplaint((isAnon) ? "anonymous" : username, subject, description, priority, ID);
    RowHelper.addRow(tableBody, subject, priority, ID);
    tableBody.revalidate();
    tableBody.repaint();
    backToDashboard(username);
  }
}
