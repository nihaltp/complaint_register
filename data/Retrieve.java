package data;

import auth.Auth;
import db.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    int newID = 0;
    String sql = "SELECT MAX(id) FROM complaints";

    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      if (rs.next()) {
        newID = rs.getInt(1); // max current id
      }

      newID++; // increment for new unique id

    } catch (SQLException e) {
      System.err.println("Error retrieving new complaint ID");
      e.printStackTrace();
    }

    return newID;
  }

  /**
   * Shows all complaints in the database, either all complaints or only the user's complaints,
   * depending on whether the user is an admin or not.
   *
   * @param body the panel to add the complaints to
   * @param username the username of the user who is viewing the complaints
   */
  public static void showComplaints(JPanel body, String username, String password) {
    // add complaints to body using RowHelper.addRow(...)
    if (!Auth.isUser(username, password)) {
      // user not found
      throw new IllegalArgumentException("User not found: " + username);
    }
    String sql;
    if (Auth.isAdmin(username, password)) {
      // Admin: retrieve all complaints
      sql = "SELECT subject, priority, id FROM complaints";
    } else {
      // Normal user: retrieve only user's complaints
      sql = "SELECT subject, priority, id FROM complaints WHERE username = ?";
    }
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      if (!Auth.isAdmin(username, password)) {
        ps.setString(1, username);
      }
      try (ResultSet rs = ps.executeQuery()) {
        int rowIndex = 0;
        while (rs.next()) {
          String subject = rs.getString("subject");
          String priority = rs.getString("priority");
          int id = rs.getInt("id");
          RowHelper.addRow(body, subject, priority, id);
        }
      }
    } catch (SQLException e) {
      System.err.println("Error retrieving complaints:");
      e.printStackTrace();
    }
  }

  /**
   * Retrieves a complaint from the database using the given ID.
   *
   * @param ID the ID of the complaint to be retrieved
   * @return a list of strings containing the complaint details, in the order of priority, subject,
   *     description, and complainer
   */
  public static List<String> getComplaint(int ID) {
    // TODO: retrieve complaint from database using ID
    String priority = "";
    String subject = "";
    String description = "";
    String complainer = "";

    String sql = "SELECT priority, subject, description, username FROM complaints WHERE id = ?";

    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, ID);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          priority = rs.getString("priority");
          subject = rs.getString("subject");
          description = rs.getString("description");
          complainer = rs.getString("username");
        } else {
          System.err.println("No complaint found with ID: " + ID);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      // Handle exception as needed
    }

    return List.of(priority, subject, description, complainer);
  }
}
