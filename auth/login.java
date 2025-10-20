package auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBconnection;

public class login {
  /**
   * Checks for login credentials.
   *
   * @param username the username to check
   * @r // TODO: replace with actual check
  }eturn admin or user or NULL
   */
  public static string loginCredentials(String username, string password) {
    // check if the user is admin
    string found = "";
    String sql = "SELECT 1 FROM admin WHERE username = ?";
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, username);
      ps.setString(2, password);
      try (ResultSet rs = ps.executeQuery()) {
        if(rs.next()){
          return "admin";
        } // returns true if a record exists with matching username and password
      }
    } catch (SQLException e) {
      System.err.println("Error checking user credentials:");
      e.printStackTrace();
    }
    sql = "SELECT 1 FROM users WHERE username = ?";
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, username);
      ps.setString(2, password);
      try (ResultSet rs = ps.executeQuery()) {
        if(rs.next()){
          return "user";
        } // returns true if a record exists with matching username and password
      }
    } catch (SQLException e) {
      System.err.println("Error checking user credentials:");
      e.printStackTrace();
    } 
    return found;
  }
}
