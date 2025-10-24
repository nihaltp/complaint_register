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
  private JFrame frame;
  private static CardLayout cardLayout;
  private static JPanel panel;
  private static JPanel tableBody;

  private LoginUI loginUI;
  private JPanel loginPanel;

  private Dashboard UI;
  private JPanel UIpanel;

  private static String username;

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
    loginPanel = loginUI.getPanel();
    panel.add(loginPanel, "login"); // Add login panel to the card layout

    // 3. Add the logic for the login button
    loginUI
        .getLoginButton()
        .addActionListener(
            a -> {
              try {
                username = loginUI.getUsername();
                String password = loginUI.getPassword();

                if (username.isEmpty() || password.isEmpty()) {
                  loginUI.showError("Empty username or password.");
                  return;
                }

                try {
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
                } catch (Exception e) {
                  loginUI.showError("Error logging in.");
                  System.err.println("Error logging in:");
                  e.printStackTrace();
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
    JPanel cPanel = cUI.getComplaintUI();
    panel.add(cPanel, "complaint");
    cardLayout.show(panel, "complaint");

    cUI.getBackButton()
        .addActionListener(
            b -> {
              backToDashboard(username);
            });

    // Add logic for the delete button
    if (cUI.getDeleteButton() != null) {
      cUI.getDeleteButton()
          .addActionListener(
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
    cUI.subjectEditable(true);
    cUI.descriptionEditable(true);

    JButton submitButton = new JButton("Submit");
    cUI.anonymousButtonVisible(true); // Show the anonymous button

    submitButton.addActionListener(
        e -> {
          submit(false, ID, cUI);
        });

    cUI.getAnonymousButton()
        .addActionListener(
            e -> {
              submit(true, ID, cUI);
            });

    cUI.getBottomPanel().remove(cUI.getTimeLabel());
    cUI.getBottomPanel().remove(cUI.getComplainerLabel());
    cUI.getBottomPanel().add(submitButton);
    cUI.getBottomPanel().add(cUI.getAnonymousButton()); // Add anonymous button to the panel

    JPanel cPanel = cUI.getComplaintUI();
    panel.add(cPanel, "complaint");
    cardLayout.show(panel, "complaint");

    cUI.getBackButton()
        .addActionListener(
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
    String subject = UI.getSubjectField().getText();
    if (subject.startsWith("Subject: ")) {
      subject = subject.substring("Subject: ".length());
    }
    String description = UI.getDescriptionArea().getText();
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
