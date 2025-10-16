package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnection;
import models.User;

public class AuthService {
    /**
     * Authenticates a user against the database.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return A User object if the login is successful, otherwise null.
     */
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Create a User object with data from the database
                return new User(
                    rs.getInt("id"), 
                    rs.getString("username"), 
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication.");
            e.printStackTrace();
        }
        return null; 
    }
}
