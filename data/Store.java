package data;

import db.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Store {
  /**
   * Saves a complaint to the database.
   *
   * @param username - the username of the user who submitted the complaint
   * @param subject - the subject of the complaint
   * @param description - the description of the complaint
   * @param priority - the priority of the complaint
   * @param ID - the ID of the complaint
   */
  public static void saveComplaint(
      String username, String subject, String description, String priority, int ID) {
    String sql =
        "INSERT INTO complaints (id, username, subject, description, priority) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, ID);
      ps.setString(2, username);
      ps.setString(3, subject);
      ps.setString(4, description);
      ps.setString(5, priority);

      ps.executeUpdate(); // execute the insert

    } catch (SQLException e) {
      System.err.println("Error saving complaint:");
      e.printStackTrace();
    }
  }

  /**
   * Updates the priority of a complaint with the given ID in the database.
   *
   * @param ID the ID of the complaint to be updated
   * @param priority the new priority of the complaint
   */
  public static void updatePriority(int ID, String priority) {
    String sql = "UPDATE complaints SET priority = ? WHERE id = ?";

    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, priority);
      ps.setInt(2, ID);

      ps.executeUpdate(); // Execute the update statement

    } catch (SQLException e) {
      System.err.println("Error updating complaint priority:");
      e.printStackTrace();
    }
  }
  
  /**
   * Updates the subject and description of a complaint with the given ID.
   *
   * @param ID          the ID of the complaint to be updated
   * @param subject     the new subject of the complaint
   * @param description the new description of the complaint
   */
  public static void updateComplaint(int ID, String subject, String description) {
    String sql = "UPDATE complaints SET subject = ?, description = ? WHERE id = ?";

    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, subject);
      ps.setString(2, description);
      ps.setInt(3, ID);

      ps.executeUpdate(); // Execute the update statement

    } catch (SQLException e) {
      System.err.println("Error updating complaint:");
      e.printStackTrace();
    }
  }

  /**
   * Deletes a complaint from the database.
   *
   * @param ID the ID of the complaint to delete
   */
  public static void deleteComplaint(int ID) {
    String sql = "DELETE FROM complaints WHERE id = ?";
    try (Connection conn = DBconnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, ID);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error deleting complaint:");
      e.printStackTrace();
    }
  }
}