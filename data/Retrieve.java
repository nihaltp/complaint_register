package data;

import auth.Auth;
import java.util.List;
import javax.swing.JPanel;
import ui.helpers.RowHelper;

public class Retrieve {
  /**
   * Retrieves a new ID from the database. This ID can be used to save a new complaint.
   *
   * @return - a new ID from the database
   */
  public static int getNewID() {
    // return new ID from database
    int ID = 99; // TODO: get actual ID from database
    return ID;
  }

  /**
   * Shows all complaints in the database, either all complaints or only the user's complaints,
   * depending on whether the user is an admin or not.
   *
   * @param body the panel to add the complaints to
   * @param username the username of the user who is viewing the complaints
   */
  public static void showComplaints(JPanel body, String username) {
    // add complaints to body using RowHelper.addRow(...)
    if (!Auth.isUser(username)) {
      // user not found
      throw new IllegalArgumentException("User not found: " + username);
    }
    if (Auth.isAdmin(username)) {
      // show all complaints
      // TODO: get all complaints from database
      RowHelper.addRow(body, "Subject 1", "High", 1);
      RowHelper.addRow(body, "Subject 2", "Low", 2);
      RowHelper.addRow(body, "Subject 3", "Medium", 3);
    } else {
      // show only user's complaints
      // TODO: get user's complaints from database
      RowHelper.addRow(body, "Subject 1", "High", 1);
      RowHelper.addRow(body, "Subject 2", "Low", 2);
    }
  }

  /**
   * Retrieves a complaint from the database using the given ID.
   *
   * @param ID the ID of the complaint to be retrieved
   * @return a list of strings containing the complaint details, in the order of priority, subject, description, and complainer
   */
  public static List<String> getComplaint(int ID) {
    // TODO: retrieve complaint from database using ID
    String priority = "new"; // TODO: get actual priority from database
    String subject = "sample subject"; // TODO: get actual subject from database
    String description =
        "Sample description, should be updated with backed to retrive from the database\n";
    description +=
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."; // TODO: get actual description from database
    String complainer = "Anonymous"; // TODO: get actual complainer from database
    return List.of(priority, subject, description, complainer);
  }
}
