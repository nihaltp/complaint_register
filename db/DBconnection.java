package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
  private static final String URL = "jdbc:mysql://localhost:3306/complaint_system";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "";

  /**
   * Establishes and returns a connection to the database.
   *
   * @return a Connection object or null if an error occurs.
   */
  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      System.err.println("Database connection error!");
      e.printStackTrace();
    }
    return connection;
  }
}
