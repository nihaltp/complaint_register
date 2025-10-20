package data;

import auth.Auth;
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
   * Shows all complaints in the database, either all complaints or only the
   * user's complaints, depending on whether the user is an admin or not.
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
}
