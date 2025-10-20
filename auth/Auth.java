package auth;

import db.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
  /**
   * Checks if the given username is an administrator.
   *
   * @param username the username to check
   * @return true if the username is an administrator, false otherwise
   */
  public static boolean isAdmin(String username) {
    // check if the user is admin
    boolean found = false;
    String sql = "SELECT 1 FROM admin WHERE username = ?";
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, username);
      try (ResultSet rs = ps.executeQuery()) {
        found = rs.next(); // returns true if a record exists with matching username and password
      }
    } catch (SQLException e) {
      System.err.println("Error checking user credentials:");
      e.printStackTrace();
    }
    return found; // TODO: replace with actual check
  }

  /**
   * Checks if the given username is in the database.
   *
   * @param username - the username to check
   * @return true if the username is in the database, false otherwise
   */
  public static boolean isUser(String username) {
    // check if the username is in the database
    boolean found = false;
    String sql = "SELECT 1 FROM users WHERE username = ?";
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, username);

      try (ResultSet rs = ps.executeQuery()) {
        found = rs.next(); // returns true if a record exists with matching username and password
      }
    } catch (SQLException e) {
      System.err.println("Error checking user credentials:");
      e.printStackTrace();
    }
    return found;
  }
}
